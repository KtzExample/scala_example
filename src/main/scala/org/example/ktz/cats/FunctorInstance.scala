package org.example.ktz.cats

import cats.Functor

import scala.language.higherKinds

object FunctorInstance extends App{
//  Partial Unification

  import cats.implicits._


  val xs = List(1,2,3,4)

  Functor[List].map(xs)(_ + 1)

  xs.fmap(_ + 1)

  implicit val myIdFunctor: Functor[MyId] = new Functor[MyId] {
    override def map[A, B](fa: MyId[A])(f: A => B): MyId[B] = MyId(f(fa.a))
  }

  val id = MyId(10)

  println(Functor[MyId].map(id)(_ + 1))

  println(id.fmap(_ + 1))
}

case class MyId[A](a: A)

//trait Functor[F[_]] {
//  def map[A, B](fa: F[A])(f: A => B): F[B]
//}

object IdFunctorInstance extends Functor[MyId] {
  override def map[A, B](fa: MyId[A])(f: A => B): MyId[B] = MyId(f(fa.a))
}