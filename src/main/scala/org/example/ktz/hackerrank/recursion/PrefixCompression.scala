package org.example.ktz.hackerrank.recursion

object PrefixCompression {

  def extractPrefix(input1: String, input2: String, acc: StringBuilder = StringBuilder.newBuilder): String =
    if(input1.isEmpty || input2.isEmpty || input1.head != input2.head) acc.mkString
    else extractPrefix(input1.tail, input2.tail, acc.append(input1.head))


  def readInput(): (String, String) = {
    val input1 = scala.io.StdIn.readLine()
    val input2 = scala.io.StdIn.readLine()
    (input1, input2)
  }

  def main(args: Array[String]): Unit = {
    val (input1, input2) = readInput()
    val prefix = extractPrefix(input1, input2)
    println(s"${prefix.length} $prefix")
    println(s"${input1.length - prefix.length} ${input1.substring(prefix.length)}")
    println(s"${input2.length - prefix.length} ${input2.substring(prefix.length)}")
  }
}
