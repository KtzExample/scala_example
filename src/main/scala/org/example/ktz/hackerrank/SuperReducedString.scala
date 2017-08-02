package org.example.ktz.hackerrank

/**
  * Created by ktz on 17. 7. 7.
  */
object SuperReducedString {
  case class strAcc(stringBuilder: StringBuilder, lastOpt: Option[Char]) {
    def += (char: Char): strAcc = lastOpt match {
      case Some(last) =>
        if(last == char)
          if(stringBuilder.isEmpty)
            strAcc (stringBuilder, None)
          else
            strAcc (stringBuilder.init, Some(stringBuilder.last))
        else strAcc (stringBuilder.append(last), Some(char))

      case None => strAcc(stringBuilder, Some(char))
    }

    override def toString: String = {
      val str = stringBuilder.toString() + lastOpt.getOrElse("")
      if(str.isEmpty) "Empty String"
      else str
    }
  }

  def main(args: Array[String]) {
    val sc = new java.util.Scanner (System.in)
    var result: strAcc = null
    var s = sc.next()
    result = s.tail.foldLeft(strAcc(new StringBuilder(), Some(s.head)))((acc, char) => acc += char)
    s = result.toString
    System.out.print(result)
  }
}
