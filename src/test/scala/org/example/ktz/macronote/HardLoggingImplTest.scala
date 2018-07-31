package org.example.ktz.macronote

import org.example.ktz.macronote.HardLogging.log
import org.specs2.mutable.Specification

class HardLoggingImplTest extends Specification {
  import HardLogging._
  "Hello" should {
    "hi" in {
      foo()
      success
    }
  }

  def foo(): Unit = {
    log("Hello World!")
  }
}
