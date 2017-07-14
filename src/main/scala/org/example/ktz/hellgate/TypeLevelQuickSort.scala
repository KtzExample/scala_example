package org.example.ktz.hellgate

/**
  * Created by ktz on 2017. 7. 14..
  */
object TypeLevelQuickSort {
  sealed trait Nat

  final class _0 extends Nat

  final class Succ[P <: Nat] extends Nat

  type _1 = Succ[_0]
  type _2 = Succ[_1]
  type _3 = Succ[_2]
  type _4 = Succ[_3]
  type _5 = Succ[_4]

  trait Sum[A <: Nat, B <: Nat] {
    type Out <: Nat
  }

  object Sum {

    def apply[A <: Nat, B <: Nat](implicit sum: Sum[A, B]): Aux[A, B, sum.Out] = sum

    type Aux[A <: Nat, B <: Nat, AuxOut <: Nat] = Sum[A, B] { type Out = AuxOut}

    implicit def zSum[A <: Nat]: Aux[_0, A, A] = new Sum[_0, A] {
      override type Out = A
    }

    implicit def sum[A <: Nat, B <: Nat](implicit sum: Sum[A, Succ[B]]): Aux[Succ[A], B, sum.Out] = new Sum[Succ[A], B] {
      override type Out = sum.Out
    }
  }
}
