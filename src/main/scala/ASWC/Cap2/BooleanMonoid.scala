package ASWC.Cap2

import cats.Monoid

/**
  * Created by ktz on 16. 7. 31.
  */
object BooleanMonoid {
  implicit val booleanAndMonoid = new andable[Boolean] {
    override def empty: Boolean = true
    override def combine(x: Boolean, y: Boolean): Boolean = x && y
  }

  implicit val booleanOrMonoid = new orable[Boolean] {
    override def empty: Boolean = false
    override def combine(x: Boolean, y: Boolean): Boolean = x || y
  }
  implicit val booleanXorMonoid = new Monoid[Boolean] {
    override def empty: Boolean = false
    override def combine(x: Boolean, y: Boolean): Boolean = (x && !y) || (!x && y)
  }
  implicit val booleanXnorMonoid = new Monoid[Boolean] {
    override def empty: Boolean = true
    override def combine(x: Boolean, y: Boolean): Boolean = (!x || y) && (x || !y)
  }

  import andorSyntax._

  true &&& true
  false ||| true
}
