package org.example.ktz.hackerrank.day30

object DataTypes {
  def main(args: Array[String]) {
    val i = 4
    val d = 4.0
    val s = "HackerRank "

    val (i2, d2, s2) = (scala.io.StdIn.readInt(), scala.io.StdIn.readDouble(), scala.io.StdIn.readLine())

    println(i + i2)
    println(d + d2)
    println(s + s2)
  }
}
