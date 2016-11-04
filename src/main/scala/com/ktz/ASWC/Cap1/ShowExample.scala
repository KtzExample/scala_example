package com.ktz.ASWC.Cap1

/**
  * Created by ktz on 16. 11. 4.
  */
object ShowExample extends App{
  import cats.std.int._
  import cats.std.string._
  import cats.syntax.show._
  import cats.Show
  val showInt: Show[Int] = Show[Int]
  val showString: Show[String] = Show[String]

  123.show
  "hello".show


  import java.util.Date

  implicit val dateShow = Show.show[Date]{date =>
    s"It`s been ${date.getTime} milliseconds since the epoch"
  }
  new Date(19899) show
}
