package org.ktz.example.implicitexample

import simulacrum.{op, typeclass}

/**
  * Created by ktz on 2016. 12. 27..
  */
object TypeClass2 extends App{
  case class Foo[A](a: A, b: A)

  implicit var fooFunctor: Functor[Foo] = new Functor[Foo] {
    override def map[A, B](fa: Foo[A])(f: (A) => B): Foo[B] =
      Foo(f(fa.a), f(fa.b))
  }

  import Functor.ops._

  val foo2 = Foo(10, 20)
  foo2 map (_ * 10)
}

@typeclass trait Functor[F[_]] {
  def map[A, B](fa: F[A])(f: A => B): F[B]
}