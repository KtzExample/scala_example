package org.example.ktz.neo.cats

import cats.Monoid
import cats.syntax.all._
import cats.implicits._
import monocle.Fold

object MonoidInstance extends App {

  case class Foo(a: Int)

  implicit val fooMonoidInstance = new Monoid[Foo] {

    override def empty: Foo = Foo(Monoid[Int].empty)

    override def combine(x: Foo, y: Foo): Foo = Foo(Monoid[Int].combine(x.a, y.a))
  }

  implicit val IntPlusMonoid = new Monoid[Int] {

    override def empty: Int = 0

    override def combine(x: Int, y: Int): Int = x + y
  }
//
//  implicit val IntMultiplyMonoid = new Monoid[Int] {
//
//    override def empty: Int = 1
//
//    override def combine(x: Int, y: Int): Int = x * y
//  }

//  import cats.instances.option._
//  val optionA = 1.some
//  val optionB = 2.some

//  optionA <+> optionB

//  println(10 |+| 20)
}
