package org.example.ktz.etc.sealedexample

object SealedExample extends App{

  def WhatIsIt(parent: Parent): Int = parent match {
    case Son1() => 1
    case Son2() => 2
    case Son3() => 3
  }

  println(WhatIsIt(Son1()))
}
