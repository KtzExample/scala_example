import cats.Functor
import cats.instances.option._

def increment(i: Int): Int = i + 1
def maybeNum: Option[Int] = Some(42)
def maybeRes = Functor[Option].map(maybeNum)(increment)

maybeRes

def add(i: Int, j: Int): Int = i + j

val a: Option[Int] = Some(42)
val b: Option[Int] = Some(33)

import cats.Monad

val maybeRes2 = Monad[Option].flatMap(a){ aa =>
  Monad[Option].map(b) { bb =>
    add(aa, bb)
  }
}

import cats.Applicative

val result = Applicative[Option].map2(a, b)(add)

case class Cell[A](value: A) {
  def map[B](function: A => B): Cell[B] = Cell(function(value))
}

implicit val cellApplicative = new Applicative[Cell] {

  override def pure[A](x: A): Cell[A] = Cell(x)

  override def ap[A, B](ff: Cell[(A) => B])(fa: Cell[A]): Cell[B] =
    fa.map(ff.value)
}

val c1 = Cell(42)
val c2 = Cell(33)

val resultC = Applicative[Cell].map2(c1, c2)(add)

import cats.syntax.cartesian._

val c3 = (c1 |@| c2).map(add)

val tuple = (c1 |@| c2).tupled