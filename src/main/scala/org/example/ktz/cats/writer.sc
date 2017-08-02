import cats.data.Writer

def greetW(name: String, logged: Boolean) = Writer(List("Composing a greeting"), {
  val userName = if (logged) name else "User"
  s"Hello $userName"
})

def isLoggedW(name: String) =
  Writer(List("Checking if user is logged in"), name.length == 3)

import cats.instances.list._

val name = "Joe"

val resultW = for {
  logged <- isLoggedW(name)
  greeting <- greetW(name, logged)
} yield greeting

val (log, result) = resultW.run

case class MyWriter[V](run: (List[String], V)) {
  def bind[B](f: V => MyWriter[B]): MyWriter[B] = {
    val (log, value) = run
    val (nLog, nValue) = f(value).run
    MyWriter(log ++ nLog, nValue)
  }
}

import cats.Monad

implicit val writerMonad = new Monad[MyWriter] {

  override def flatMap[A, B](fa: MyWriter[A])(f: (A) => MyWriter[B]): MyWriter[B] =
    fa.bind(f)

  override def tailRecM[A, B](a: A)(f: (A) => MyWriter[Either[A, B]]): MyWriter[B] = {
    val (log, value) = f(a).run
    value match {
      case Left(a) => tailRecM(a)(f)
      case Right(b) => MyWriter((log, b))
    }
  }

  override def pure[A](x: A): MyWriter[A] = MyWriter(List(), x)
}

import cats.syntax.flatMap._
import cats.syntax.functor._