import shapeless._
import shapeless.tag.@@


type IntOrBoolean = Int :+: Boolean :+: CNil

val hello = Coproduct[IntOrBoolean](1)


def printCoproduct(intOrBoolean: IntOrBoolean): Unit = intOrBoolean match {
  case Inl(i) => println(s"It it Int! - $i")
  case Inr(b) => println(s"It is Boolean! - ${b.head.get}")
}


printCoproduct(hello)

trait A
trait B

type AType = Int @@ A
type BType = Int @@ B

type AOrB = AType :+: BType :+: CNil

def hello(a : AOrB): Int = a match {
  case Inl(a) =>
    println("A!!")
    a
  case Inr(bco) =>
    println("B!!")
    bco.head.get
}


val aco = Coproduct[AOrB](tag[A][Int](1))

hello(aco)

//object Hi extends Poly1 {
//  implicit def caseAType = at[AType](a => {
//    println("A!!")
//    a
//  })
//  implicit def caseBType = at[BType](b => {
//    println("B!!")
//    b
//  })
//}
//val x: AType = tag[A][Int](1)
//Hi(x)