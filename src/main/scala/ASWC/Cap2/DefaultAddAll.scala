package ASWC.Cap2

/**
  * Created by ktz on 16. 8. 6.
  */
object DefaultAddAll {
  val intAddAll : AddAll[Int] = new AddAll[Int] {
    import cats.syntax.semigroup._
    import cats.Semigroup
    import cats.Monoid
    implicit val defaultSemiInt = new Semigroup[Int] {
      override def combine(x: Int, y: Int): Int = x + y
    }
    import cats.std.int._
    implicit val intMonoid : Monoid[Int] = new Monoid[Int] {
      override def combine(x: Int, y: Int): Int = x+y

      override def empty: Int = 0
    }
    override def add(items: List[Int]): Int = items.foldLeft(Monoid[Int].empty)(_ + _)
  }
}
