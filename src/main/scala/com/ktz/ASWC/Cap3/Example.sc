import cats.std.function._
import cats.syntax.functor._
import com.sun.net.httpserver.Authenticator.Success

val func1 = (x : Int) => x.toDouble
val func2 = (y : Double) => y * 2
val func3 = func1 map func2

func3(1)

import scala.language.higherKinds

trait Functor[F[_]] {
  def map[A, B](fa : F[A])(f : A => B) : B
}

val f = (x : Int) => x * 2
val f2 = f andThen f

import cats.Functor
import cats.std.list._

import cats.std.option._
val list1 = List(1,2,3)

val list2 = Functor[List].map(list1)(_ * 2)

val option1 = Some(123)

val option2 = Functor[Option].map(option1)(_.toString)

val func = (x : Int) => x + 1

val lifted = Functor[Option].lift(func)

lifted(Some(1))

import cats.std.function._
import cats.syntax.functor._

val fun1 = (a : Int) => a + 1
val fun2 = (a : Int) => a * 2
val fun3 = fun1 map fun2

fun3(123)