def writeCsv[A](values: List[A])(implicit enc: CsvEncoder[A]): String =
  values.map(value => enc.encode(value).mkString(", ")).mkString("\n")


trait CsvEncoder[A]{
  def encode(value: A): List[String]
}

def createEncoder[A](func: A => List[String]): CsvEncoder[A] =
  new CsvEncoder[A] {
    override def encode(value: A): List[String] = func(value)
  }

import shapeless.{Coproduct, :+:, CNil, Inl, Inr}

implicit val cnilEncoder: CsvEncoder[CNil] =
  createEncoder(cnil => throw new Exception("Inconceivable!"))

implicit def coproductEncoder[H, T <: Coproduct](
  implicit hEncoder: CsvEncoder[H],
  tEncoder: CsvEncoder[T]
): CsvEncoder[H :+: T] = createEncoder{
  case Inl(h) => hEncoder.encode(h)
  case Inr(t) => tEncoder.encode(t)
}

implicit val doubleEncoder: CsvEncoder[Double] =
  createEncoder(d => List(d.toString))

sealed trait Shape
final case class Rectangle(width: Double, height: Double) extends Shape
final case class Circle(radius: Double) extends Shape

val shape: List[Shape] = List(
  Rectangle(3.0, 4.0),
  Circle(1.0)
)

writeCsv(shape)