package ASWC.Cap4

/**
  * Created by ktz on 16. 9. 20.
  */
object WriterExample extends App{

  import cats.data.Writer
  import cats.std.vector._

  Writer(Vector("It all starts here."), 123)

  import cats.syntax.applicative._

  type Logged[A] = Writer[Vector[String], A]

  123.pure[Logged]

  import cats.syntax.writer._

  println(Vector("msg1", "msg2", "meg3").tell)
  println

  val a = Writer(123, Vector("msg1", "msg2", "msg3"))
  println(a)
  val b = 123.writer(Vector("msg1", "msg2", "msg3"))
  println(b)
  println
}
