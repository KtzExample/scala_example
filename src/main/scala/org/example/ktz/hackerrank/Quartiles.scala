package org.example.ktz.hackerrank

object Quartiles extends App{
  val nInput: Int = scala.io.StdIn.readInt()
  val nList: List[Int] = scala.io.StdIn.readLine().split(" ").map(_.toInt).toList.sorted

  private def isOdd(list: List[Int]): Boolean = list.length % 2 == 1

  def getMedium(list: List[Int]): Int = {
    val mediumIndex: Int = list.length / 2

    if (isOdd(list)) list(mediumIndex)
    else (list(mediumIndex - 1) + list(mediumIndex)) / 2
  }

  val mediumIndex: Int = nList.length / 2

  val quartiles: List[Int] =
    if(isOdd(nList))
      List(getMedium(nList.slice(0, mediumIndex)), nList(mediumIndex), getMedium(nList.slice(mediumIndex + 1, nList.length)))
    else
      List(getMedium(nList.slice(0, mediumIndex)), getMedium(nList), getMedium(nList.slice(mediumIndex, nList.length)))

  quartiles.foreach(println)
}
