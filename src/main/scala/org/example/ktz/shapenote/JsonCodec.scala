package org.example.ktz.shapenote
import shapeless._


object JsonCodec extends App {
  trait Encoder[A] {
    def encode(a: A): String
  }

  implicit val intEncoder = new Encoder[Int] {
    override def encode(a: Int): String = a.toString
  }

  implicit val stringEncoder = new Encoder[String] {
    override def encode(a: String): String = a
  }

  implicit val booleanEncoder = new Encoder[Boolean] {
    override def encode(a: Boolean): String = a.toString
  }

  implicit val hNilEncoder = new Encoder[HNil] {
    override def encode(a: HNil): String = ""
  }

  // 1 번째: case class => HList
  // 2 번째: HNil encoder
  // 3 번째: HList instance

  implicit def hListEncoder[H, T <: HList](
    implicit encoder: Encoder[H],
    tEncoder: Encoder[T]
  ): Encoder[H :: T] = new Encoder[H :: T] {
    override def encode(a: H :: T): String =
      encoder.encode(a.head) + ", " + tEncoder.encode(a.tail)
  }

  implicit def genEncoder[A, H](
    implicit gen: Generic.Aux[A, H],
    encoder: Encoder[H]
  ) = new Encoder[A] {
    override def encode(a: A): String = encoder.encode(gen.to(a))
  }

  case class Foo(a: Int, b: String, c: Boolean)

  val fooGen = Generic[Foo]
  val fooHList: Int :: String :: Boolean :: HNil = fooGen.to(Foo(1, "hello", false))
  println(fooHList)

  val foo: Foo = fooGen.from(fooHList)

  println(foo)

  println(implicitly[Encoder[Foo]].encode(foo))

  implicit class EncoderSyntax[A](a: A) {
    def csv(implicit encoder: Encoder[A]): String = encoder.encode(a)
  }

  println(foo.csv)
}