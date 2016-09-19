import cats.data._
import cats.std.vector._

Writer(Vector("It all starts here."), 123)

import cats.syntax.applicative._

type Logged[A] = Writer[Vector[String], A]

123.pure[Logged]

import cats.syntax.writer._

Vector("msg1", "msg2", "meg3").tell

val a = Writer(123, Vector("msg1", "msg2", "msg3"))

val b = 123.writer(Vector("msg1", "msg2", "msg3"))

a.value

a.written

val (log, result) = b.run
log
result

a.run