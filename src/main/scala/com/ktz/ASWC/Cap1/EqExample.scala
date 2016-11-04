package com.ktz.ASWC.Cap1

/**
  * Created by ktz on 16. 11. 4.
  */
object EqExample extends App{
  import cats.Eq
  import cats.std.int._

  val eqInt = Eq[Int]

  eqInt.eqv(123, 123)
  import cats.syntax.eq._

  123 === 123
}
