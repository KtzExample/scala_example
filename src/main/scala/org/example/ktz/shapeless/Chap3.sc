trait CsvEncoder[A]{
  def encode(value: A): List[String]
}

case class Chap3Employee(name: String, number: Int, manager: Boolean)

implicit val employeeEncoder: CsvEncoder[Chap3Employee] = new CsvEncoder[Chap3Employee] {
  override def encode(value: Chap3Employee): List[String] = List(
    value.name,
    value.number.toString,
    if(value.manager) "yes" else "no"
  )
}

def writeCsv[A](values: List[A])(implicit enc: CsvEncoder[A]): String =
  values.map(value => enc.encode(value).mkString(", ")).mkString("\n")

val employee: List[Chap3Employee] = List(
  Chap3Employee("A", 1, true),
  Chap3Employee("B", 2, false),
  Chap3Employee("C", 3, false)
)

writeCsv(employee)

case class Chap3IceCream(name: String, numCherries: Int, inCon: Boolean)

implicit val iceCreamEncoder: CsvEncoder[Chap3IceCream] = new CsvEncoder[Chap3IceCream] {
  override def encode(value: Chap3IceCream): List[String] = List(
    value.name,
    value.numCherries.toString,
    if(value.inCon) "yes" else "no"
  )
}

val iceCreams: List[Chap3IceCream] = List(
  Chap3IceCream("A", 1, false),
  Chap3IceCream("B", 2, true),
  Chap3IceCream("C", 0, false)
)

writeCsv(iceCreams)

implicit def pairEncoder[A, B](
    implicit aEncoder: CsvEncoder[A],
    bEncoder: CsvEncoder[B]
  ): CsvEncoder[(A, B)] = new CsvEncoder[(A, B)] {
  override def encode(value: (A, B)): List[String] = {
    val (a, b) = value
    aEncoder.encode(a) ++ bEncoder.encode(b)
  }
}


writeCsv(employee zip iceCreams)


object CsvEncoder {
  def apply[A](implicit enc: CsvEncoder[A]): CsvEncoder[A] = enc

  def instance[A](func: A => List[String]): CsvEncoder[A] = new CsvEncoder[A] {
    override def encode(value: A): List[String] = func(value)
  }
}

CsvEncoder[Chap3IceCream]

implicitly[CsvEncoder[Chap3IceCream]]

import shapeless._

the[CsvEncoder[Chap3IceCream]]