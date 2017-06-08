import cats.Functor

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

import cats.implicits.catsStdInstancesForList

val users = List("Joe", "Kate")

println(Functor[List].fproduct(users)(greet).toMap)