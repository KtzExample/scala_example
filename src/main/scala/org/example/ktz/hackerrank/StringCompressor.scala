package org.example.ktz.hackerrank

/**
  * Created by ktz on 17. 7. 6.
  */
object StringCompressor {

  case class charCounter(letter: Char, count: Int) {
    def ++ : charCounter = charCounter(letter, count + 1)

    override def toString: String =
      if(count == 1) letter.toString
      else letter.toString + count
  }

  class compressor(input: String) {
    private val charArr = input.toCharArray

    import scala.annotation.tailrec
    @tailrec
    private def compressLoop(input: Array[Char], lastChar: charCounter, acc: String): String =
      if(input.isEmpty) acc + lastChar
      else if(input.head == lastChar.letter) compressLoop(input.tail, lastChar++, acc)
      else compressLoop(input.tail, charCounter(input.head, 1), acc + lastChar)

    private def foldChar(input: Array[Char]): String =
      input.par.tail.map(charCounter(_, 1)).foldLeft(List(charCounter(input.head, 1))){ (acc, charC) =>
        if(acc.last.letter == charC.letter) acc.slice(0, acc.length - 1) :+ (acc.last++)
        else acc :+ charC
      }.par.map(_.toString).fold("")(_ + _)

    def compress: String = compressLoop(charArr.tail, charCounter(charArr.head, 1), "")

    def foldCompress: String = foldChar(charArr)

  }

  def main(args: Array[String]): Unit = {
    val input: String = scala.io.StdIn.readLine()
    val compressor = new compressor(input)
    System.out.println(compressor.foldCompress)
  }
}
