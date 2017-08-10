package org.example.ktz.hackerrank.day30

object LetReview extends App{
  def splitString(str: String): (String, String) = {
    val strWithIndex: Seq[(Char, Int)] = str.zipWithIndex
    (strWithIndex.filter(_._2 % 2 == 1).map(_._1).mkString, strWithIndex.filter(_._2 % 2 == 0).map(_._1).mkString)
  }

  def readString(nOfInput: Int): Unit =
    if(nOfInput == 0) return
    else {
      val (even, odd) = splitString(io.StdIn.readLine())
      println(s"$odd $even")
      readString(nOfInput - 1)
    }

  readString(io.StdIn.readInt())
}
