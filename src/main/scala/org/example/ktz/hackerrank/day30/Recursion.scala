package org.example.ktz.hackerrank.day30

object Recursion extends App{

  def factorial(n: Int, acc: Int = 1): Int =
    if(n == 1) acc
    else n * factorial(n - 1, acc)

  val result: Int = factorial(scala.io.StdIn.readInt())

  println(result)

}
