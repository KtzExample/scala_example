package ASWC.Cap2

import cats.{Monoid, Semigroup}

/**
  * Created by ktz on 16. 8. 1.
  */
object SetMonoid extends App{
  implicit def setUnionMonoid[A] : Monoid[Set[A]] = new Monoid[Set[A]] {
    override def empty: Set[A] = Set.empty[A]
    override def combine(x: Set[A], y: Set[A]): Set[A] = x union y
  }

  implicit def setIntersectionSemigroup[A] : Semigroup[Set[A]] = new Semigroup[Set[A]] {
    override def combine(x: Set[A], y: Set[A]): Set[A] = x intersect y
  }

  implicit val intMonoid : Monoid[Int] = new Monoid[Int] {
    override def empty: Int = 0
    override def combine(x: Int, y: Int): Int = x + y
  }

  val intSetMonoid : Monoid[Set[Int]] = Monoid[Set[Int]]
}
