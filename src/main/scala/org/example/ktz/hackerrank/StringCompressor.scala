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

    val stringBuilder = new StringBuilder

    import scala.annotation.tailrec
    @tailrec
    private def compressLoop(input: Array[Char], lastChar: charCounter): String =
      if(input.isEmpty) stringBuilder.append(lastChar).toString()
      else if(input.head == lastChar.letter) compressLoop(input.tail, lastChar++)
      else {
        stringBuilder.append(lastChar)
        compressLoop(input.tail, charCounter(input.head, 1))
      }

    def compress: String = compressLoop(charArr.tail, charCounter(charArr.head, 1))

  }

  def main(args: Array[String]): Unit = {
    val input: String = scala.io.StdIn.readLine()
    val compressor = new compressor(input)
    System.out.println(compressor.compress)
  }
}
