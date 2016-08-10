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

  import cats.std.int._
  import cats.syntax.semigroup._
  import cats.std.option._

  def add(items : List[Int]) : Int = items.foldLeft(Monoid[Int].empty)(_ |+| _)
  def addOption(items : List[Option[Int]]) : Option[Int] = items.foldLeft(Monoid[Option[Int]].empty)(_ |+| _)

  import andorSyntax._

  true &&& true
  false ||| true

  case class Order(totalCost : Double, quantity : Double)

  implicit val orderAddMonoid : Monoid[Order] = new Monoid[Order] {
    override def empty: Order = Order(0, 0)

    override def combine(x: Order, y: Order): Order = Order(x.totalCost + y.totalCost, x.quantity + y.quantity)
  }

  implicit class orderOps(x : Order) {
    def |+|(order : Order)(implicit monoid : Monoid[Order]) = monoid.combine(x, order)
  }

  Order(1, 1) |+| Order(2, 2)
}