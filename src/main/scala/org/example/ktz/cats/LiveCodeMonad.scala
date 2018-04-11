package org.example.ktz.cats

import java.time.Instant

import cats.Semigroupal

import scala.concurrent.{Await, Future}
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.Duration

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

  import cats.implicits._
  def getFInt(a: Int): Future[Int] = Future {
    Thread.sleep(a * 1000)
    println(Instant.now.toString)
    a
  }

  Await.result((getFInt(3), getFInt(5)).mapN((a, b) => {
    println(a + b)
    a + b
  }), Duration.Inf)

}
