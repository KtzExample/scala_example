import cats.Monad
import cats.data.OptionT
import cats.implicits._

import scala.io.StdIn

case class Cell[A](value: A) {
  def bind[B](function: A => Cell[B]): Cell[B] = function(value)
}

implicit val cellMonad = new Monad[Cell] {
  override def flatMap[A, B](fa: Cell[A])(f: (A) => Cell[B]): Cell[B] = fa.bind(f)

  override def tailRecM[A, B](a: A)(f: (A) => Cell[Either[A, B]]): Cell[B] = f(a).value match {
    case Left(a) => tailRecM(a)(f)
    case Right(b) => Cell(b)
  }

  override def pure[A](x: A): Cell[A] = Cell(x)
}

val newCell = Cell(42).flatMap(a => Cell(a + 1))

val c3 = for {
  c1 <- Cell(42)
  c2 <- Cell(23)
} yield c1 + c2

trait ConsoleAction[A] {
  def bind[B](f: A => ConsoleAction[B]): ConsoleAction[B]
}

case class ReadFromConsole() extends ConsoleAction[String] {
  override def bind[B](f: (String) => ConsoleAction[B]): ConsoleAction[B] = {
    val input = StdIn.readLine()
    f(input)
  }
}

case class WriteToConsole(output: String) extends ConsoleAction[Unit] {
  override def bind[B](f: (Unit) => ConsoleAction[B]): ConsoleAction[B] = {
    println(output)
    f()
  }
}

implicit val consoleMonad = new Monad[ConsoleAction] {
  override def flatMap[A, B](fa: ConsoleAction[A])(f: (A) => ConsoleAction[B]): ConsoleAction[B] =
    fa.bind(f)

  override def tailRecM[A, B](a: A)(f: (A) => ConsoleAction[Either[A, B]]): ConsoleAction[B] = ???

  override def pure[A](x: A): ConsoleAction[A] =
    ReadFromConsole.asInstanceOf[ConsoleAction[A]]
}

import cats._
import cats.implicits._

Monad[Option].ifM(Option(true))(Option("truthy"), Option("falsy"))
Monad[List].ifM(List(true, false, true))(List(1, 2), List(3, 4))


case class OptionT[F[_], A](value: F[Option[A]])

Foldable[List].foldMap(List("a", "b", "c"))(_.length)
Foldable[List].foldMap(List(1, 2, 3))(_.toString)