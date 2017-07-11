package org.example.ktz.hackerrank.recursion

import scala.annotation.tailrec

/**
  * Created by ktz on 17. 7. 10.
  */
object FunctionsAndFractals {
  val NumberOfColumns: Int = 63
  val NumberOfRow: Int = 32
  val canvas = List.fill(32)(List.fill(63)('_').mkString)

  case class Triangle(startColumn: Int, startRow: Int, nOfRow: Int, canvas: List[String]) {
    override def toString: String = {
      def replace(n: Int, strToReplace: String): String =
        StringBuilder.newBuilder.append(strToReplace).replace(startColumn - n, startColumn + n - 1, List.fill((n - 1) * 2 + 1)('1').mkString).toString()

      @tailrec
      def getStringOfRow(n: Int, acc: List[String]): List[String] =
        if(n > nOfRow) acc
        else getStringOfRow(n + 1, acc :+ replace(n, canvas(n - 1)))

      getStringOfRow(startRow, List.empty).foldLeft(StringBuilder.newBuilder)((strBuilder, str) => strBuilder.append(str + "\n")).toString()
    }
  }

  def drawTriangles(n: Int) {
    //Draw the N'th iteration of the fractal as described
    // in the problem statement
  }

  def main(args: Array[String]) {
//    drawTriangles(readInt())

    println(Triangle(((63 + 1) / 2)/2, 1, 32/2, canvas))
  }
}
