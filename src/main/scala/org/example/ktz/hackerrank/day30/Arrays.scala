package org.example.ktz.hackerrank.day30

object Arrays extends App{
  val nInput: Int = scala.io.StdIn.readInt()
  val arrayInput: List[Int] = scala.io.StdIn.readLine().split(" ").map(_.toInt).toList

  assert(nInput == arrayInput.length)

  val result: String = arrayInput.reverse
    .foldLeft(StringBuilder.newBuilder)((strBuilder, int) => strBuilder.append(int + " ")).mkString.trim

  println(result)
}
