
import org.example.ktz.shapeless.chap4.{Rect, Vec}
import org.example.ktz.shapeless.chap4.RectUtil._
import shapeless._
import shapeless.ops.hlist.IsHCons

lastField(Rect(Vec(1, 2), Vec(3, 4)))

def getWrappedValue[A, H](input: A)(
  implicit
  gen: Generic.Aux[A, H :: HNil]
): H = gen.to(input).head

case class Wrapper(value: Int)

getWrappedValue[Wrapper, Int](Wrapper(42))

def getWrappedValue2[A, Repr <: HList, Head, Tail <: HList](input: A)(
  implicit
  gen: Generic.Aux[A, Repr],
  isHCons: IsHCons.Aux[Repr, Head, HNil]
): Head = gen.to(input).head

getWrappedValue2(Wrapper(42))