package org.example.ktz.cats

import org.specs2.mutable.Specification

class MonadTransformerTest extends Specification {
  "그냥 시작" should {
    "안녕" in {
      println("안녕")
      success
      5 must_!= 4
    }
  }
}
