package org.example.ktz.shapeless.chap5

sealed trait JsonValue
case class JsonObject(field: List[(String, JsonValue)]) extends JsonValue
case class JsonArray(items: List[JsonValue]) extends JsonValue
case class JsonString(value: String) extends JsonValue
case class JsonNumber(value: Double) extends JsonValue
case class JsonBoolean(value: Boolean) extends JsonValue
case object JsonNull extends JsonValue

trait JsonEncoder[A] {
  def encode(value: A): JsonValue
}

object JsonEncoder {
  def apply[A](implicit enc: JsonEncoder[A]): JsonEncoder[A] = enc

  def createEncoder[A](func: A => JsonValue): JsonEncoder[A] = new JsonEncoder[A] {
    override def encode(value: A): JsonValue = func(value)
  }

  implicit val stringEncoder: JsonEncoder[String] =
    createEncoder(str => JsonString(str))

  implicit val doubleEncoder: JsonEncoder[Double] =
    createEncoder(double => JsonNumber(double))

  implicit val intEncoder: JsonEncoder[Int] =
    createEncoder(int => JsonNumber(int))

  implicit val booleanEncoder: JsonEncoder[Boolean] =
    createEncoder(bool => JsonBoolean(bool))

  implicit def listEncoder[A](implicit enc: JsonEncoder[A]): JsonEncoder[List[A]] =
    createEncoder(list => JsonArray(list.map(enc.encode)))

  implicit def optionEncoder[A](implicit enc: JsonEncoder[A]): JsonEncoder[Option[A]] =
    createEncoder(opt => opt.map(enc.encode).getOrElse(JsonNull))


}