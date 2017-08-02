import cats.{Functor, Monad}

type Id[A] = A

val greet = (name: String) => s"Hello $name"

val greeting = Functor[Id].map("Joe")(greet)

implicit val idMonad = new Monad[Id] {

  override def flatMap[A, B](fa: Id[A])(f: (A) => Id[B]): Id[B] = f(fa)

  override def tailRecM[A, B](a: A)(f: (A) => Id[Either[A, B]]): Id[B] = f(a) match {
    case Left(a) => tailRecM(a)(f)
    case Right(b) => b
  }

  override def pure[A](x: A): Id[A] = x
}

import cats.syntax.flatMap._
import cats.syntax.functor._

val id1: Id[Int] = 42
val id2: Id[Int] = 23

val resultId = for {
  num1 <- id1
  num2 <- id2
} yield num1 + num2