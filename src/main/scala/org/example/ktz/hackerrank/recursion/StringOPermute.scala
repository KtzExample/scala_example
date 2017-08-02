package org.example.ktz.hackerrank.recursion

/**
  * Created by ktz on 2017. 7. 16..
  */
object StringOPermute {

  def swapString(string: StringBuilder, acc: StringBuilder = StringBuilder.newBuilder): StringBuilder =
    if(string.length == 2) acc.appendAll(List(string.charAt(1), string.head))
    else {
      acc.appendAll(List(string.charAt(1), string.head))
      swapString(string.delete(0, 2), acc)
    }

  def main(args: Array[String]): Unit = {
    val nOfTry: Int = io.StdIn.readInt()
    (0 until nOfTry) foreach { _ =>
      val input = io.StdIn.readLine()
      println(swapString(StringBuilder.newBuilder.append(input)).mkString)
    }
  }
}
