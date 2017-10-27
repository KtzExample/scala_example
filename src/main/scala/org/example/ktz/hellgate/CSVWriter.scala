package org.example.ktz.hellgate

import shapeless._

case class Foo(a: Int, b: String)

trait CSVWriter[A] {
  def toCsv(a: A): String
}

// ISB
object CSVWriter {
  implicit val intWriter: CSVWriter[Int] = new CSVWriter[Int] {
    override def toCsv(a: Int): String = a.toString
  }

  implicit val stringWriter: CSVWriter[String] = new CSVWriter[String] {
    override def toCsv(a: String): String = a
  }

  implicit val booleanWriter: CSVWriter[Boolean] = new CSVWriter[Boolean] {
    override def toCsv(a: Boolean): String = if(true) "true" else "false"
  }

  implicit val hNilWriter = new CSVWriter[HNil] {
    override def toCsv(a: HNil): String = ""
  }

  implicit def hListWriter[A, H <: HList](implicit acsvWriter: CSVWriter[A], hcsvWriter: CSVWriter[H]) = new CSVWriter[A :: H] {
    override def toCsv(a: A :: H): String = acsvWriter.toCsv(a.head) + ", " + hcsvWriter.toCsv(a.tail)
  }
}


object Hello extends App {

}