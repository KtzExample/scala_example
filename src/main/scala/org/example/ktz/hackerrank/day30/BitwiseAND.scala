package org.example.ktz.hackerrank.day30

object BitwiseAND extends App {
  val nInput: Int = scala.io.StdIn.readInt()

  def getInput(nInput: Int, acc: List[(Int, Int)] = Nil): List[(Int, Int)] =
    if(nInput == 0) acc
    else {
      val input: List[Int] = scala.io.StdIn.readLine().split(" ").toList.map(_.toInt)
      getInput(nInput - 1, acc :+ (input.head -> input.tail.head))
    }

  def getMaxAnd(from: Int, to: Int, k: Int): Int = {
    def loop(n: Int, max: Int = 0): Int = {
      val newAnd = if((n & from) >= k) 0 else n & from
      if (n >= to)
        math.max(newAnd, max)
      else
        loop(n + 1, math.max(newAnd, max))
    }

    loop(from  + 1)
  }

  def getBitwiseAnd(n: Int, k: Int): Int = {
    def loop(currentBase: Int = 0, max: Int = 0): Int = {
      val newMax = getMaxAnd(currentBase, n, k)
      if (currentBase + 1 == n) math.max(max, newMax)
      else {
        if (newMax + 1 == k) newMax
        else loop(currentBase + 1, math.max(newMax, max))
      }
    }

    loop()
  }

  getInput(nInput).foreach(elem => {
    val (n, k) = elem
    val max = getBitwiseAnd(n, k)

    println(max)
  })
}
