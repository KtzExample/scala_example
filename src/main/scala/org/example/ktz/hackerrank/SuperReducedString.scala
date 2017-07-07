package org.example.ktz.hackerrank

/**
  * Created by ktz on 17. 7. 7.
  */
object SuperReducedString {
  case class strAcc(stringBuilder: StringBuilder, last: Option[Char], reduced: Boolean, reducedOnce: Boolean = false) {
    def += (char: Char): strAcc = last match {
      case Some(lastElem) =>
        (lastElem == char, reduced) match {
          case (true, false) => strAcc (stringBuilder, None, true, true)
          case (true, true) => strAcc (stringBuilder.append(lastElem), Some(char), false, reducedOnce)
          case (false, _) => strAcc (stringBuilder.append(lastElem), Some(char), false, reducedOnce)
        }
      case None => strAcc(stringBuilder, Some(char), false, reducedOnce)
    }

    override def toString: String = {
      val str = stringBuilder.toString() + last.getOrElse("")
      if(str.isEmpty) "Empty String"
      else str
    }
  }

  def main(args: Array[String]) {
    val sc = new java.util.Scanner (System.in)
    var reducedOnce: Boolean = true
    var result: strAcc = null
    var s = sc.next()
    while(reducedOnce) {
      result = s.tail.foldLeft(strAcc(new StringBuilder(), Some(s.head), false))((acc, char) => acc += char)
      reducedOnce = result.reducedOnce
      s = result.toString
    }
    System.out.print(result)
  }
}
