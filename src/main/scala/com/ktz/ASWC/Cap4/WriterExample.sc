import cats.data.Writer
import cats.syntax.applicative._
import cats.std.vector._

type Logged[A] = Writer[Vector[String], A]

123.pure[Logged]

import cats.syntax.writer._

Vector("msg1", "msg2", "msg3").tell

import cats.syntax.writer._

val a = Writer(123, Vector("msg1", "msg2", "msg3"))

val b = 123.writer(Vector("msg1", "msg2", "msg3"))

a.value

b.written

val (log, result) = b.run

val writer1 = for {
  a <- 10.pure[Logged]
  _ <- Vector("a", "b", "c").tell
  b <- 32.writer(Vector("x", "y", "z"))
} yield a + b

writer1.run

val writer2 = writer1.mapWritten(_.map(_.toUpperCase()))

writer2.run

val writer3 = writer1.bimap(
  log => log.map(_.toUpperCase()),
  result => result * 100
)

writer3.run

val writer4 = writer1.mapBoth{(log, result) =>
  val log2 = log.map(_ + "!")
  val result2 = result * 1000
  (log2, result2)
}

writer4.run

