package com.ktz.ASWC.Cap1

/**
  * Created by ktz on 2016. 10. 30..
  */

final case class Cat(name : String, age : Int, color : String)

trait Printable[A] {
  def format(value : A) : String
}

object PrintDefault {
  implicit val printableString = new Printable[String]{
    override def format(value: String): String = s"String : $value"
  }
  implicit val printableInt = new Printable[Int] {
    override def format(value: Int): String = s"Int : $value"
  }
  implicit val printableCat = new Printable[Cat] {
    override def format(value: Cat): String =
      s"${value.name} is a ${value.age} years-old ${value.color} cat."
  }
}

object Print {
  def format[A](value : A)(implicit printable: Printable[A]) : String =
    printable.format(value)

  def print[A](value : A)(implicit printable: Printable[A]) : Unit =
    println(format(value))
}

object PrintSyntax {
  implicit class printOps[A](value : A) {
    def format(implicit printable: Printable[A]) : String =
      printable.format(value)

    def print(implicit printable: Printable[A]) : Unit =
      println(format)
  }
}

object PrintMain extends App {
  import PrintDefault._
  import PrintSyntax._
  1.print
  Cat("martin", 28, "orange").print
}