import cats.Monad
import monix.eval.Task

import scala.concurrent.Await

class AuthService {
  def isLogged(name: String): Task[Boolean] = Task{name.length == 3}
}

class UserService {
  def greet(name: String, isLogged: Boolean): Task[String] = Task {
    val actualName = if(isLogged) name else "User"
    s"Hello $actualName"
  }
}

case class Environment(userName: String, userService: UserService, authService: AuthService)

import cats.data

def isLogged = data.ReaderT[Task, Environment, Boolean] { env =>
  env.authService.isLogged(env.userName)
}

def greetUser(logged: Boolean) = data.ReaderT[Task, Environment, String]{env =>
  env.userService.greet(env.userName, logged)
}

implicit val taskMonad = new Monad[Task] {

  override def flatMap[A, B](fa: Task[A])(f: (A) => Task[B]): Task[B] =
    fa.flatMap(f)

  override def tailRecM[A, B](a: A)(f: (A) => Task[Either[A, B]]): Task[B] = f(a).flatMap{
    case Left(a) => tailRecM(a)(f)
    case Right(b) => Task.now(b)
  }

  override def pure[A](x: A): Task[A] = Task.now(x)
}

import monix.execution.Scheduler.Implicits.global
import scala.concurrent.duration._

val resultR = for {
  logged <- isLogged
  greeting <- greetUser(logged)
} yield greeting

val environment = Environment("Joe", new UserService, new AuthService)
Await.result(resultR.run(environment).runAsync, 1 second)

case class ExternalContext(env: Environment)

val externalContext = ExternalContext(environment)

def context2env(ec: ExternalContext): Environment = ec.env

val resultContextR = resultR.local(context2env)

Await.result(resultContextR.run(externalContext).runAsync, 1 second)
