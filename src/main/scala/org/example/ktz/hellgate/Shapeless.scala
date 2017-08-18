package org.example.ktz.hellgate
import shapeless._

object Shapeless extends App{

  // remove
  // Int :: String :: Boolean :: HNil

  // remove[String] => Int :: Boolean :: HNil

  import RichRemover._

  println((1 :: "Hello" :: false :: HNil).remove[String])

}

trait Remover[A, L <: HList] {
  type Out <: HList

  def apply(xs: L): Out
}

object Remover extends RemoverLowPriorityImplicits{
  type AUX[A, L <: HList, AUXOut <: HList] = Remover[A, L]{ type Out = AUXOut }

  implicit def removeBase[A, B, L <: HList](implicit remover: Remover[A, L]): AUX[A, B :: L, B :: remover.Out] =
    new Remover[A, B :: L] {
      type Out = B :: remover.Out

      override def apply(xs: B :: L) = xs.head :: remover(xs.tail)
    }

  implicit def remove[A, L <: HList](implicit remover: Remover[A, L]): AUX[A, A :: L, L] =
    new Remover[A, A :: L] {
      type Out = L

      override def apply(xs: A :: L): L = xs.tail
    }
}
trait RemoverLowPriorityImplicits {

  implicit def hnilRemove[A]: Remover.AUX[A, A :: HNil, HNil] = new Remover[A, A:: HNil] {
    type Out = HNil

    override def apply(xs: A ::HNil): HNil = HNil
  }

  implicit def base[A, L <: HList]: Remover.AUX[A, A :: L, L] = new Remover[A, A :: L] {
    type Out = L

    override def apply(xs: A :: L): L = xs.tail
  }

}

object RichRemover {
  implicit class RemoverSyntax[L <: HList](self: L) {
    def remove[A](implicit R: Remover[A, L]): R.Out = R(self)
  }
}

