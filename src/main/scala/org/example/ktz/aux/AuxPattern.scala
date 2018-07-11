package org.example.ktz.aux

object AuxPattern {
  trait Foo[A] {
    type Out
    def foo(a: A): Out
  }

  object Foo {
    type Aux[A, Out0] = Foo[A] {type Out = Out0}
  }

  trait Bar[A] {
    type Out
    def bar(b: A): Out
  }

  def quz[A, FOut](a: A)(implicit foo: Foo.Aux[A, FOut], bar: Bar[FOut]): bar.Out = bar.bar(foo.foo(a))
}
