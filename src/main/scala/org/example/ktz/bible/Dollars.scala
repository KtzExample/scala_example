package org.example.ktz.bible

object Chap11 extends App{
  class Dollars(val amount: Int) extends AnyVal {
    override def toString: String = "$" + amount
  }

  val dollars: Dollars = new Dollars(1000)

  val a: String = new String("Hello")
  val b: String = new String("Hello")

  println(a.eq(b))
}