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
    implicit
    aEncoder: CsvEncoder[A],
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

//implicit val boolEncoder: CsvEncoder[Boolean] =
//  new CsvEncoder[Boolean] {
//    override def encode(value: Boolean): List[String] =
//      if(value) List("yes")
//      else List("no")
//  }

def createEncoder[A](func: A => List[String]): CsvEncoder[A] =
  new CsvEncoder[A] {
    override def encode(value: A): List[String] = func(value)
  }

implicit val stringEncoder: CsvEncoder[String] =
  createEncoder(str => List(str))

implicit val intEncoder: CsvEncoder[Int] =
  createEncoder(num => List(num.toString))

implicit val booleanEncoder: CsvEncoder[Boolean] =
  createEncoder(b => if(b) List("yes") else List("no"))

implicit val hnilEncoder: CsvEncoder[HNil] =
  createEncoder(hnil => Nil)

implicit def hlistEncoder[H, T <: HList](
  implicit
  hEncoder: CsvEncoder[H],
  tEncoder: CsvEncoder[T]
): CsvEncoder[H :: T] =
  createEncoder {
    case h :: t =>
      hEncoder.encode(h) ++ tEncoder.encode(t)
  }

val reprEncoder: CsvEncoder[String :: Int :: Boolean :: HNil] =
  implicitly[CsvEncoder[String :: Int :: Boolean :: HNil]]

reprEncoder.encode("abc" :: 123 :: true :: HNil)

//implicit val iceCreamEncoder2: CsvEncoder[Chap3IceCream] = {
//  val gen = Generic[Chap3IceCream]
//  val enc = CsvEncoder[gen.Repr]
//
//  createEncoder(iceCream => enc.encode(gen.to(iceCream)))
//}

writeCsv(iceCreams)

implicit def genericEncoder[A, R](
  implicit
  gen: Generic[A]{type Repr = R},
  enc: CsvEncoder[R]
): CsvEncoder[A] =
  createEncoder(a => enc.encode(gen.to(a)))

