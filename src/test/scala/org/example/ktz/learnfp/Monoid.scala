package org.example.ktz.learnfp


/**
  * 결합, 교환
  * 어디에 사용되는가?
  * @tparam A
  */
trait Monoid[A] {
  def empty: A
  def combine(x: A, y: A): A
}


object MonoidExample {
  val xs = List(1, 2, 3)

  val plusIntMonoid: Monoid[Int] = new Monoid[Int] {
    override def empty: Int = 0

    override def combine(x: Int, y: Int): Int = x + y
  }

  xs.foldLeft(plusIntMonoid.empty)(plusIntMonoid.combine)
}