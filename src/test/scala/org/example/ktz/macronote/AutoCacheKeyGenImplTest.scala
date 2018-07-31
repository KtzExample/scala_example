package org.example.ktz.macronote

import org.specs2.mutable.Specification

class AutoCacheKeyGenImplTest extends Specification {
  import AutoCacheKeyGen._
  def foo(x: Int): Int = memoize[Int] {
    println(s"cache missed! $x")
    x
  }

  "Hello" should {
    "hi" in {
      foo(100)
      foo(100)
      foo(100)
      foo(100)
      foo(100)
      foo(100)


      success
    }
  }

}
