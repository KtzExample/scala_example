package org.example.ktz.hackerrank.recursion


object TheSumsOfPowers {

  implicit class RichList(list: List[Int]) {
    def toPowerSum(pow: Int): Int = list.map(i => math.pow(i, pow)).sum.toInt
  }

  implicit class PowInt(int: Int) {
    def toPow(pow: Int): Int = math.pow(int, pow).toInt
  }

  import scala.annotation.tailrec
  @tailrec
  def maxSquare(X: Int, N: Int, result: Int = 0): Int =
    if(math.pow(result + 1, N) > X) result
    else maxSquare(X, N, result + 1)

  def numberOfWays(X: Int, N: Int): Int = {
    def loop(current: Int, acc: List[Int]): List[List[Int]] =
      if(acc.toPowerSum(N) > X)
        Nil
      else if (acc.toPowerSum(N) == X)
        List(acc)
      else
        (current  to 1 by -1).toList.flatMap(i => loop(i - 1, i :: acc))

    loop(maxSquare(X, N), List.empty).length
  }

  def main(args: Array[String]) {
    println(numberOfWays(scala.io.StdIn.readInt(),scala.io.StdIn.readInt()))
  }
}
