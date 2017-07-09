package org.example.ktz.hackerrank.recursion

/**
  * Created by ktz on 2017. 7. 9..
  */
object PascalsTriangle {

  implicit class FactInt(n: Int) {
    def ! : Int = {
      def loop(n: Int, acc: Int): Int =
        if(n == 0) acc
        else loop(n - 1, acc * n)

      loop(n, 1)
    }
  }


  def getListOfRow(rowN: Int): List[Int] =
    (1 :: (2 until rowN).map(index => rowN.! / (index.! * (rowN - index).!)).toList) :+ 1

  def generatePascalList(n: Int, acc: List[List[Int]]): List[List[Int]] =
    if(n == 1) List(List(1)) ++ acc
    else if(n == 2) generatePascalList(n - 1, List(List(1, 2)) ++ acc)
    else generatePascalList(n - 1, getListOfRow(n) :: acc)

  def printPascal(n: Int): Unit =
    generatePascalList(n, List.empty).foreach(list => list.foreach())


  def main(args: Array[String]): Unit = {
    println(5!)
  }
}
