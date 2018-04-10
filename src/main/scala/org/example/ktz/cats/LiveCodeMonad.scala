package org.example.ktz.cats

import cats.Semigroupal

import scala.concurrent.Future

object LiveCodeMonad extends App {

  trait Monad[F[_], A] {
    def pure[A](a: A): F[A]
    def flatMap[A, B](fa: F[A])(f: A => F[B]): F[B]
    def map[A, B](fa: F[A])(f: A => B): F[B] =
      flatMap(fa)(f andThen pure)

    def ap[A, B](fa: F[A])(ff: F[A => B]): F[B] =
      flatMap(ff)(f => map(fa)(f))

    def product[A, B](fa: F[A], fb: F[B]) =
      flatMap(fa)(a => map(fb)(b => (a, b)))
  }


  import cats.instances.option._

  println(Semigroupal.tuple3(Option(1), Option(true), Option(3)))

}
