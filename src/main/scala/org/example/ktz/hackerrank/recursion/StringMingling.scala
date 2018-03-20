package org.example.ktz.hackerrank.recursion

/**
  * Created by ktz on 17. 7. 10.
  */
object StringMingling {

  def main(args: Array[String]): Unit = {
    val input1: String = scala.io.StdIn.readLine()
    val input2: String = scala.io.StdIn.readLine()

    println(input1.zip(input2).foldLeft("")((str, chars) => str + chars._1 + chars._2))
  }
}
