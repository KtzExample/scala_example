package org.example.ktz.bible

object Chap12 extends App{
  val hello = new A with C with B
  println((hello.hello _).toString())
  println(hello.hello(1))
}

trait Parent {
  def hello(n: Int): Int
}

trait A extends Parent{
  def hello(n: Int): Int = {
    println("Call A")
    n
  }
}

trait B extends Parent{
  abstract override def hello(n: Int): Int = {
    println("Call B")
    super.hello(n) + 1
  }
}


trait C extends Parent{
  abstract override def hello(n: Int): Int = {
    println("Call C")
    super.hello(n) * 2
  }
}