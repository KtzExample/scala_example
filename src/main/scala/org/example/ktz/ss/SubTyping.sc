trait IntSet

class NoneEmpty extends IntSet

class Empty extends IntSet

//def A(intSet: IntSet): NoneEmpty = new NoneEmpty
//
//def B(noneEmpty: NoneEmpty): IntSet = new NoneEmpty
//
//
//val noneEmpty = new NoneEmpty
//
//B(noneEmpty)
//
//val newIntSet = new IntSet {}
//
//B(newIntSet)


type A = IntSet => NoneEmpty

type B = NoneEmpty => IntSet

/**
  *
  * A <: B 이므로, 사용자가 B를 A에 전달 할 경우 전달이 가능하다.
  * A를 받는 함수에 B타입인 f를 적용할 경우
  * NoneEmpty를 IntSet으로 받는다. 그 후,  f함수를 실행하면 NoneEmpty가 나온다.
  * 그리고 이것을 IntSet으로 반환한다.
  * A[B]인것인데,
  * NoneEmpty -> --A-- -> IntSet -f-> NoneEmpty --A-- -> IntSet
  *
  */

def Hello(b: B): Unit = println("")

val a: A = _ => new NoneEmpty

Hello(a)

def Hi(a: A): Unit = println("")

val b: B = _ => new IntSet {}

//Hi(b)


class Hello[+T] {
  def insert[A >: T](ele: A): A = ele
}


val hello = new Hello[String]

hello.insert(new Any)
