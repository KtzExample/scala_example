package org.example.ktz.hellgate

import org.example.ktz.hellgate.TypeLevelQuickSort.hlist.{HList, HNil}

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

  trait LT[A <: Nat, B <: Nat]

  object LT {
    def apply[A <: Nat, B <: Nat](implicit lt: LT[A, B]): LT[A, B] = lt

//    implicit inductive

    implicit def zlt[A <: Nat]: LT[_0, A] = new  LT[_0, A]{}

    implicit def lt[A <: Nat, B <: Nat](implicit lt: LT[A, B]): LT[Succ[A], Succ[B]] = new LT[Succ[A], Succ[B]] {}
  }

  trait LTEq[A <: Nat, B <: Nat]

  object LTEq {

    def apply[A <: Nat, B <: Nat](implicit lteq: LTEq[A, B]): LTEq[A, B] = lteq

    implicit def zlteq1[A <: Nat]: LT[_0, A] = new  LT[_0, A]{}
    implicit def zlteq2[A <: Nat]: LT[_0, _0] = new  LT[_0, _0]{}

    implicit def lteq[A <: Nat, B <: Nat](implicit lt: LT[A, B]): LT[Succ[A], Succ[B]] = new LT[Succ[A], Succ[B]] {}
  }

  // 스칼라의 List는
  object list {
    sealed abstract class List[+A]
    case object Nil extends List[Nothing]
    // head와 tail의 내부 타입이 같아야 한다.
    final case class ::[B](head: B, tail: List[B]) extends List[B]
  }

  // type level의 list는 HList
  object hlist {
    sealed trait HList
    final class ::[+H, +T <: HList] extends HList
    final class HNil extends HList
    // type level의 자연수의 list는
    // 여기서는 hlist를 쓸수 밖에 없다.
    // 왜냐면 그냥 list를 사용하면 타입 정보가 다 날라갈것이다.
    type NS = _1 :: _0 :: _3 :: _2 :: HNil
  }

  trait LTEqs[H <: HList, A <: Nat] {
    type Out <: HList
  }

//  object LTEqs {
//
//    type AUX[H <: HList, A <: Nat, AuxOut <: HList] = LTEq[H, A] {type Out = AuxOut}
//
//    def apply[H <: HNil, A <: Nat](implicit lteqs: LTEqs[H, A]): AUX[H, A, lteqs.Out] = lteqs
//
//    implicit def zlteqs[A <: Nat]: AUX[HNil, A, HNil] = new  LTEqs[HNil, A]{
//      override type Out = HNil
//    }
//  }

}
