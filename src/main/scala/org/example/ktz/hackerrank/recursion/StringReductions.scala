package org.example.ktz.hackerrank.recursion

object StringReductions {

  def reduceString(input: String, acc: StringBuilder = StringBuilder.newBuilder): String =
    if(input.isEmpty) acc.mkString
    else reduceString(input.tail, if(acc.contains(input.head)) acc else acc.append(input.head))


  def main(args: Array[String]): Unit = {
    val input = scala.io.StdIn.readLine()
    println(reduceString(input))
  }
}
