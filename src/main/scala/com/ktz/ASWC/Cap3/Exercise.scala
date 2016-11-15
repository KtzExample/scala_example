package com.ktz.ASWC.Cap3

import cats.Functor

/**
  * Created by ktz on 2016. 11. 15..
  */

sealed trait Result[+A]
final case class Success[A](value : A) extends Result[A]
final case class Warning[A](value : A, message : String) extends Result[A]
final case class Failure(message : String) extends Result[Nothing]

object Exercise extends App{
  implicit val resultFunctor = new Functor[Result] {
    override def map[A, B](fa: Result[A])(f: (A) => B): Result[B] = fa match {
      case Success(value) => Success(f(value))
      case Warning(value, message) => Warning(f(value), message)
      case f : Failure => f
    }
  }

  def success[A](value : A) : Result[A] = Success(value)
  def warning[A](value : A, message : String) : Result[A] = Warning(value, message)
  def failure(message : String) : Result[Nothing] = Failure(message)

  import cats.syntax.functor._
  success(100) map (_ * 2)
}
