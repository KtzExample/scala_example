import org.example.ktz.shapeless.chap3._
import shapeless.{:+:, CNil, Coproduct, Generic, Inl, Inr}

implicit val cnilEncoder: CsvEncoder[CNil] =
  createEncoder(cnil => throw new Exception("Inconceivable"))

implicit def coproductEncoder[H, T <: Coproduct](
  implicit
  hEncoder: CsvEncoder[H],
  tEncoder: CsvEncoder[T]
): CsvEncoder[H :+: T] = createEncoder{
  case Inl(h) => hEncoder.encode(h)
  case Inr(t) => tEncoder.encode(t)
}

val shapes: List[Shape] = List(
  Rectangle(3.0, 4.0),
  Circle(1.0)
)

implicit val doubleEncoder: CsvEncoder[Double] =
  createEncoder[Double](d => List(d.toString))

implicit val shapeEncoder: CsvEncoder[Shape] = {
  val gen = Generic[Shape]
  val enc = CsvEncoder[gen.Repr]

  createEncoder(a => enc.encode(gen.to(a)))
}

writeCsv(shapes)