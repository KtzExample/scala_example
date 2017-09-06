import shapeless._
import shapeless.ops._
import shapeless.ops.coproduct._


type IntOrBoolean = Int :+: Boolean

val hello = Coproduct[IntOrBoolean](1)


def printCoproduct(intOrBoolean: IntOrBoolean): Unit = intOrBoolean match {
  case Inl(i) => println(s"It it Int! - $i")
  case Inr(b) => println(s"It is Boolean! - $b")
}


printCoproduct(hello)