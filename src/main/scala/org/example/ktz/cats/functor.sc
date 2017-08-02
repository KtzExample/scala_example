import cats.Functor

import scala.concurrent.{Await, Future}

val source = List("Cats", "is", "awesome")
val product = Functor[List].fproduct(source)(_.length).toMap

case class Cell[A](value: A) {
  def map[B](function: A => B): Cell[B] = {
    Cell(function(value))
  }
}

implicit val celFunctor: Functor[Cell] = new Functor[Cell] {
  override def map[A, B](fa: Cell[A])(f: (A) => B): Cell[B] = fa.map(f)
}

import cats.implicits.catsStdInstancesForOption

val maybeName = Option("Joe")
println(Functor[Option].map(maybeName)(_.length))

def greet(name: String): String = s"Hello $name!"

println(Functor[Option].lift(greet)(maybeName))

import cats.implicits._

val users = List("Joe", "Kate")

println(Functor[List].fproduct(users)(greet).toMap)

val listOpt = Functor[List] compose Functor[Option]
listOpt.map(List(Some(1), None, Some(3)))(_ + 1)

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration._

implicit val futureOpt = Functor[Future] compose Functor[List]

val f1 = Future(List(1))
val f2 = Future(2)

Await.result(futureOpt.map(f1)(_ + 1), 2 second)