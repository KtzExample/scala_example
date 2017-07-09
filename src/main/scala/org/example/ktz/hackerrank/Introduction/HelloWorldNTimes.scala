package org.example.ktz.hackerrank.Introduction

/**
  * Created by knigh on 2017-07-09.
  */
object HelloWorldNTimes extends App{
  def f(n: Int): Unit = {
    (0 until n).foreach(_ => println("Hello World"))
  }

  var n = scala.io.StdIn.readInt
  f(n)
}