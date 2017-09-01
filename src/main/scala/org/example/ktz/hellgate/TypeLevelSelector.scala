package org.example.ktz.hellgate

import shapeless._

class TypeLevelSelector {

}

object test extends App {
  val a = 1 :: "hello" :: true :: HNil

  import ToInt._
  println(ToInt[Succ[Succ[Zero]]])
}

trait Nat {
  type N <: Nat
}

object Nat {
  val zero: Zero = new Zero
}

class Zero extends Nat {

  override type N = Zero

  override def toString: String = "Zero"
}

case class Succ[P <: Nat]() extends Nat {
  override type N = Succ[P]
}

sealed trait  ToInt[N <: Nat] {
  val value: Int
}

object ToInt {

  // Base Class
  implicit def zero: ToInt[Zero] = new ToInt[Zero] {
    override val value: Int = 0
  }

  // Implicit Conversion은 Output 기준이다.
  // Type Projection

  implicit def foo[N <: Nat](implicit Z: ToInt[N]): ToInt[Succ[N]] = new ToInt[Succ[N]] {
    override val value: Int = Z.value + 1
  }

  def apply[N <: Nat](implicit z: ToInt[N]): Int = z.value

  type Four = Succ[Succ[Succ[Succ[Zero]]]]
}

trait Nther[L <: HList, N <: Nat] {
  type Out
  def apply(self: L): Out
}

object Nther {
  type AUX[L <: HList, N <: Nat, R] = Nther[L, N]{type Out = R}

  def findNil[R, List <: R :: HList, IndexZero <: Zero](): AUX[List, IndexZero, R] =
    new Nther[List, IndexZero] {
      type Out = R
      def apply(self: List): R = self.head
    }

  def unwrapHList[T, List <: HList, Index <: Nat](implicit hlist: Nther[List, Index]): AUX[T :: List, Succ[Index], hlist.Out] =
    new Nther[T :: List, Succ[Index]] {
      type Out = hlist.Out
      def apply(self: T :: List): Out = hlist.apply(self.tail)
    }
}