package org.example.ktz.shapeless

package object chap3 {
  case class Employee(name: String, number: Int, manager: Boolean)

  case class IceCream(name: String, numCherries: Int, inCon: Boolean)

  trait CsvEncoder[A]{
    def encode(value: A): List[String]
  }

  object CsvEncoder {
    def apply[A](implicit enc: CsvEncoder[A]): CsvEncoder[A] = enc

    def instance[A](func: A => List[String]): CsvEncoder[A] = new CsvEncoder[A] {
      override def encode(value: A): List[String] = func(value)
    }
  }

  sealed trait Shape

  final case class Rectangle(width: Double, height: Double) extends Shape
  final case class Circle(radius: Double) extends Shape


  def createEncoder[A](func: A => List[String]): CsvEncoder[A] = new CsvEncoder[A] {
    override def encode(value: A): List[String] = func(value)
  }

  def writeCsv[A](values: List[A])(implicit enc: CsvEncoder[A]): String =
    values.map(value => enc.encode(value).mkString(", ")).mkString("\n")
}
