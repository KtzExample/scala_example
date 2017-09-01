package org.example.ktz.hackerrank.day30

object BitwiseAND extends App {
  val nInput: Int = io.StdIn.readInt()

  def getInput(nInput: Int, acc: List[(Int, Int)] = Nil): List[(Int, Int)] =
    if(nInput == 0) acc
    else {
      val input: List[Int] = io.StdIn.readLine().split(" ").toList.map(_.toInt)
      getInput(nInput - 1, acc :+ (input.head -> input.tail.head))
    }

  getInput(nInput).foreach(elem => {
    val (n, k) = elem
    val max = (0 to n).toSet.subsets(2).toList
      .map(subset => subset.head & subset.tail.head)
      .filter(_ < k)
      .max

    println(max)
  })
}
