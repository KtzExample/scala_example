trait A {
  def hi: Unit = {
    println("A")
  }
}

trait B { self: A =>
  override def hi: Unit = {
    println("B")
  }
}

class C extends A with B

val c = new C

c.hi

implicit class PlusOneStrOps(str: String) {
  def plusOne: String = str + "1"
}