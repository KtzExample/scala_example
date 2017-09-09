package org.example.ktz.shapeless.chap5

object Chap5_2 extends App {

  case class IceCream(name: String, numCherries: Int, inCone: Boolean)

  val iceCream = IceCream("Sundae", 1, false)

  val iceCreamJson: JsonValue = JsonObject(List(
    "name" -> JsonString("Sundae"),
    "numCherries" -> JsonNumber(1),
    "inCone" -> JsonBoolean(false)
  ))

  import shapeless.LabelledGeneric

  val gen = LabelledGeneric[IceCream].to(iceCream)

  println(gen)

  val iceCreamEncoded = JsonEncoder[IceCream].encode(IceCream("Sundae", 1, false))

  println(iceCreamEncoded)
}
