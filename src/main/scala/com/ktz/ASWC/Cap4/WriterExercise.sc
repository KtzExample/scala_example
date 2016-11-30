import cats.data.Writer
import cats.std.vector._
import cats.syntax.applicative._
import cats.syntax.writer._

type Logged[A] = Writer[Vector[String], A]

42.pure[Logged]

Vector("Message").tell

41.pure[Logged].map(_ + 1)

def slowly[A](body: => A) = try body finally Thread.sleep(100)

def factorial(n: Int): Logged[Int] = {
  if(n == 0)
    1.pure[Logged]
  else {
    for {
      a <- slowly(factorial(n - 1))
      _ <- Vector(s"fact $n ${a * n}").tell
    } yield a * n
  }
}

factorial(5)

import scala.concurrent._
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration._
import scala.concurrent.duration.Duration


Await.result(Future.sequence(Vector(
  Future(factorial(5)),
  Future(factorial(5))
)), Duration.Inf)

