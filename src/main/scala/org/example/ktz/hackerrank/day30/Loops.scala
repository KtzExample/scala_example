package org.example.ktz.hackerrank.day30

object Loops extends App{
  val n = io.StdIn.readInt()

  (1 to 10).foreach(index => println(s"$n x $index = ${n * index}"))
}
