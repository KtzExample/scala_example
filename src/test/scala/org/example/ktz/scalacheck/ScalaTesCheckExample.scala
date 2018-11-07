package org.example.ktz.scalacheck

import org.scalacheck.{Arbitrary, Gen}
import org.scalatest.prop.Checkers
//import org.scalacheck.Arbitrary._
import org.scalacheck.Prop._
import org.scalatest.FunSuite


case class HelloWorld(a: Int, str: String)


class ScalaTesCheckExample extends FunSuite with Checkers {
  test("Hello World") {
    check {
      (a: String, b: String) => (a + b).startsWith(a)
    }
  }

  test("Hello World2") {
    check {
      (a: String, b: String) => (a + b).endsWith(b)
    }
  }


  implicit val from0to100Arbitrary: Arbitrary[Int] = Arbitrary(Gen.choose(0, 100))

  test("From 0 to 100 test") {
    check((a: Int) => {
      println(s"test a: $a")
      a + a <= 200
    })
  }

  test("From 0 to 100 test again") {
    check((a: Int) => {
      println(s"test a: $a")
      a >= 0
    })
  }
}
