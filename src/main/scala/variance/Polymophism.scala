package variance

/**
  * Created by ktz on 16. 8. 28.
  */
trait Parent {
  def hello : String
  def hi : String
}

class Sun1 extends Parent {
  override def hello: String = """Hello! I`m Sun1"""

  override def hi: String = """Hi I`m Sun1"""
}

class Sun2 extends Parent {
  override def hello: String = """Hello! I`m Sun2"""

  override def hi: String = """Hi I`m Sun2"""
}

trait Wrapper[+A <: Parent] {
  def sayHello[B <: Parent](value : B) : Unit = println(value.hello)
  def sayHi[B <: Parent](value : B) : Unit = println(value.hi)
}

object Polymophism extends App {
  def sayHiNHello(parent: Parent) : Unit = {
    println(s"${parent.hello} & ${parent.hi}")
  }

  val sun1 : Parent = new Sun1
  val sun2 : Parent = new Sun2

  sayHiNHello(sun1)
  sayHiNHello(sun2)



}