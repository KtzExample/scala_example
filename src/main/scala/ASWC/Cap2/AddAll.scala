package ASWC.Cap2

import cats.Monoid
import cats.Semigroup

/**
  * Created by ktz on 16. 8. 6.
  */
trait AddAll[A] {
  def add(items : List[A]) : A
  def addOption[A : Monoid](items : List[A]) : A = {
    import cats.syntax.semigroup._
    items.foldLeft(Monoid.empty)((x, y) => x |+| y)
  }
}

object AddAllMain extends App {
  import cats.std.int._
  implicit val intAddAll : AddAll[Int] = new AddAll[Int] {
    override def add(items: List[Int]): Int = items.sum
  }
  println(intAddAll.add(List(1,2,3,4,5)))
  import cats.std.option._
  println(intAddAll.addOption(List(Some(1), None, Some(2))))
  case class Order(totalCost : Double, quantity : Double)

  implicit val orderAddAll : AddAll[Order] = new AddAll[Order] {
    override def add(items: List[Order]): Order = items.foldLeft(Order(0,0))((x, y) => Order(x.totalCost + y.totalCost, x.quantity + y.quantity))
  }

  println(orderAddAll.add(List(Order(1,1), Order(2,2))))




















}