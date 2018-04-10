package org.example.ktz.neo.cats

import cats.effect.IO

import scala.concurrent.{Future, Promise}
import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.Success

object IOMonadExample extends App {
  def fromFuture[A](f: Future[A]): IO[A] = IO.async {
    cb => f.map(Right(_)).recover {
      case e: Throwable => Left(e)
    }.map(cb)
  }

  def asynchronous(x: Int)(f: Int => Unit): Unit =
    f(x * 2)

  val p = Promise[Int]()
  asynchronous(10)(x => p.complete(Success(x)))

//  asynchronous(10)(x => IO.async(Right(_)))

  private val value: IO[Int] = IO.async[Int](cb => {
    asynchronous(10)(x => cb(Right(x)))
  })

  value

  def synchronous(x: Int): Int = x * 2

  IO[Int] { synchronous(10) }
}