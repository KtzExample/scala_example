package org.example.ktz.shapeless.chap4

case class Rect(vec1: Vec, vec2: Vec)
case class Vec(x: Int, y: Int)

import shapeless.{HList, ::, Generic}
import shapeless.ops.hlist.Last

trait Second[L <: HList] {
  type Out
  def apply(value: L): Out
}

object Second {
  type AUX[L <: HList, R] = Second[L] { type Out = R}

  def apply[L <: HList](implicit second: Second[L]): AUX[L, second.Out] = second

  implicit def hlistSecond[A, B, Rest <: HList]: AUX[A :: B :: Rest, B] = new Second[A :: B :: Rest] {
    override type Out = B

    override def apply(value: A :: B :: Rest): B = value.tail.head
  }
}

object RectUtil extends App{
  def lastField[A, Out <: HList](input: A)(
    implicit
    gen: Generic.Aux[A, Out],
    last: Last[Out]
  ): last.Out = {
    last.apply(gen.to(input))
  }

  lastField(Rect(Vec(1, 2), Vec(3, 4)))[]
}