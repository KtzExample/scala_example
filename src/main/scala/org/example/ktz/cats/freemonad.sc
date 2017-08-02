trait ActionA[A]

case class ReadAction() extends ActionA[String]

case class WriterAction(output: String) extends ActionA[Unit]

import cats.Monad
import cats.free.Free

import scala.concurrent.Await
import scala.io.StdIn

type ActionF[A] = Free[ActionA, A]

import cats.free.Free.liftF

def read(): ActionF[String] = liftF[ActionA, String](ReadAction())

def write(output: String): ActionF[Unit] =
  liftF[ActionA, Unit](WriterAction(output))

val result = for {
  _ <- write("Write your name: ")
  name <- read()
  res <- write(s"Hello $name")
} yield res

import cats.arrow.FunctionK
import cats.Id

val idInterpreter: FunctionK[ActionA, Id] = new FunctionK[ActionA, Id] {
  override def apply[A](fa: ActionA[A]): Id[A] = fa match {
    case ReadAction() =>
      val input = StdIn.readLine()
      input
    case WriterAction(output) =>
      println(output)
  }
}

import monix.eval.Task

def taskInterpreter: FunctionK[ActionA, Task] = new FunctionK[ActionA, Task] {
  override def apply[A](fa: ActionA[A]): Task[A] = fa match {
    case ReadAction() => Task.delay {
      val input = StdIn.readLine()
      input
    }
    case WriterAction(output) => Task.delay(println(output))
  }
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

import scala.concurrent.duration._

Await.result(result.foldMap(taskInterpreter).runAsync, 1 second)