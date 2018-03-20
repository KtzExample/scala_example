package org.example.ktz.hackerrank.recursion


object SequenceFullOfColors {

  implicit class RichColorChar(char: Char) {
    def opposite: Char = char match {
      case 'R' => 'G'
      case 'G' => 'R'
      case 'Y' => 'B'
      case 'B' => 'Y'
    }
  }


  import scala.annotation.tailrec
  @tailrec
  def scanString(colors: List[Char], prefix: List[Char] = List.empty): Boolean =
    if(colors.isEmpty)
      prefix.isEmpty
    else {
      val head = colors.head
      if(prefix.contains(head))
        false
      else if(prefix.contains(head.opposite))
        scanString(colors.tail, prefix.filter(_ != head.opposite))
      else
        scanString(colors.tail, head :: prefix)
    }

  @tailrec
  def getInput(n: Int, acc: List[Boolean] = List.empty): List[Boolean] =
    if(n == 0) acc
    else getInput(n - 1, acc :+ scanString(scala.io.StdIn.readLine().toList))

  def main(args: Array[String]): Unit = {
    getInput(scala.io.StdIn.readInt()).foreach(result => println(result.toString.capitalize))
  }
}
