package org.example.ktz.neo.cats

import cats.Monad
import com.twitter.util.{Future => TwitterFuture}

import scala.concurrent.{Future => ScalaFuture}

object MonadExample extends App {
  val ten = 10

  import cats.syntax.all._
  import cats.implicits._

//  ten.pure[ScalaFuture]

  val e: Either[String, Int] = Right(1)

  println(e.left.map(_.toInt).toString)

//  implicit def eitherMonadInstance[Left, Right] = {
//    type EitherRight[A] = Either[Left, A]
//    new Monad[EitherRight[Right]] {
//
//      override def flatMap[A, B](fa: EitherRight[A])(f: A => EitherRight[B]): EitherRight[B] = fa match {
//        case Left(left) => Left(left)
//        case Right(value) => f(value)
//      }
//
//      override def tailRecM[A, B](a: A)(f: A => EitherRight[Either[A, B]]): EitherRight[B] = ???
//
//      override def pure[A](x: A): EitherRight[A] = Right(x)
//    }
//  }

}
