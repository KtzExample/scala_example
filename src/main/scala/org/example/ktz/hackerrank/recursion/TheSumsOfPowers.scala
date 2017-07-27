package org.example.ktz.hackerrank.recursion


object TheSumsOfPowers {

  import scala.annotation.tailrec

  @tailrec
  def maxSquare(X: Int, N: Int, result: Int = 0): Int =
    if(math.pow(result + 1, N) > X) result
    else maxSquare(X, N, result + 1)

  def makePowerSet(elementList: List[Int]): List[List[Int]] = {
    @tailrec
    def pwr(t: List[Int], ps: List[List[Int]]): List[List[Int]] =
      if (t.isEmpty) ps
      else pwr(t.tail, ps ++ (ps map (_ :+ t.head)))

    pwr(elementList, List(List.empty))
  }

  def numberOfWays(X: Int, N: Int): Int = {
    val powerSet = makePowerSet((maxSquare(X, N) to 1 by -1).toList)

    powerSet.count { partialSet =>
      partialSet.map(ele => math.pow(ele, N)).sum == X
    }
  }

  def main(args: Array[String]) {
    println(numberOfWays(io.StdIn.readInt(),io.StdIn.readInt()))
  }
}
