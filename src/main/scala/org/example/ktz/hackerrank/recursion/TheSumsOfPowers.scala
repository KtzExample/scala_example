package org.example.ktz.hackerrank.recursion


object TheSumsOfPowers {

  import scala.annotation.tailrec

  @tailrec
  def maxSquare(X: Int, N: Int, result: Int = 0): Int =
    if(math.pow(result + 1, N) > X) result
    else maxSquare(X, N, result + 1)

  def takeN(elementList: List[Int], n: Int): List[List[Int]] = List.fill(n)(elementList)  // TODO 이거 아직 안 끝남!!


  def makePowerSet(elementList: List[Int]): List[List[Int]] = {
    @tailrec
    def pwr(elementList: List[Int], powerSet: List[List[Int]]): List[List[Int]] =
      if (elementList.isEmpty) powerSet
      else pwr(elementList.tail, powerSet ++ (powerSet map (_ :+ elementList.head)))

    val list = takeN(elementList, 3)


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
