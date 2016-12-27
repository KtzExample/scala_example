package org.ktz.example.implicitexample

/**
  * Created by ktz on 2016. 12. 27..
  */
object TypeClass1 extends App{
  trait Monoid[A] {
    def zero: A
    def append(a: A, b: A): A
  }

//  implicit val intSumMonoid: Monoid[Int] = new Monoid[Int] {
//    override def zero: Int = 0
//    override def append(a: Int, b: Int): Int = a + b
//  }

  case class Foo(a: Int, b: Int)

  implicit val fooMonoidInstance: Monoid[Foo] = new Monoid[Foo] {
    override def zero: Foo = Foo(0, 0)

    override def append(a: Foo, b: Foo): Foo = Foo(a.a + b.a, a.b + b.b)
  }

  object Monoid {
    def zero[A](implicit monoid: Monoid[A]): A = monoid.zero
    def append[A](a: A, b: A)(implicit monoid: Monoid[A]): A =
      monoid.append(a, b)

    def append2[A: Monoid](a: A, b: A): A =
      implicitly[Monoid[A]].append(a, b)
  }

  println(Monoid.zero)
}
