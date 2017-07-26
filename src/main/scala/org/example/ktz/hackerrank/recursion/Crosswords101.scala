package org.example.ktz.hackerrank.recursion
/*
object Crosswords101 {

  case class RichChar(char: Option[Char], x: Int, y: Int)

  object RichChar {
    def apply(x: Int, y: Int): RichChar = new RichChar(None, x, y)
  }

  case class RichString(chars: List[RichChar], conditions: List[List[RichChar] => Boolean])

  trait Direction

  case object DownDirection extends Direction

  case object RightDirection extends Direction

  def directionsGot(current: RichChar)(implicit canvas: List[List[Char]]): List[Direction] =
    (if(canvas(current.y)(current.x + 1) == '-') List(RightDirection) else Nil)
      .++(if(canvas(current.y + 1)(current.x) == '-') List(DownDirection) else Nil)

  def hasDownDirection(current: RichChar)(implicit canvas: List[List[Char]]): Boolean =
    directionsGot(current).contains(DownDirection)

  def hasRightDirection(current: RichChar)(implicit canvas: List[List[Char]]): Boolean =
    directionsGot(current).contains(RightDirection)


  def scanWords(acc: RichString, direction: Option[Direction])(implicit canvas: List[List[Char]]): List[RichString] = direction match {
    case Some(directionVal) if directionVal == DownDirection =>
      val current = acc.chars.head
//      val newConditions: List[List[RichChar] => Boolean] =  if(hasRightDirection(current)) (chars)


      if(hasDownDirection(current)) scanWords(acc.copy(RichChar(current.x, current.y + 1) :: acc.chars), direction)
      else List(acc.copy(conditions = (chars => chars.length == acc.chars.length) :: acc.conditions))

    case Some(directionVal) if directionVal == RightDirection =>
    case None =>
      val current = acc.chars.head
      (if(hasDownDirection(current))
        scanWords(acc.copy(RichChar(current.x, current.y + 1) :: acc.chars), Some(DownDirection))
      else Nil) ++ (if(hasRightDirection(current))
        scanWords(acc.copy(RichChar(current.x + 1, current.y) :: acc.chars), Some(RightDirection))
      else Nil)
  }


  def main(args: Array[String]): Unit = {

  }
}
*/

object Crosswords101 {
  val NumberOfColumn = 10
  val NumberOfRaw = 10

  case class WordNode(word: Option[String], wordLength: Int, crossWord: Map[Int, WordNode])

  def splitWord(): List[String] = io.StdIn.readLine().split(";").toList

  def extractSpace(canvas: List[List[Char]]): WordNode = ???

  def makeWordTree(words: List[String], wordTree: WordNode): WordNode =
    words.foldLeft(wordTree)((tree, word) => positioningWord(word, tree))

  def positioningWord(word: String, wordTree: WordNode): WordNode = ???

  def fillWord(canvas: List[List[Char]], wordTree: WordNode): List[Char] = ???

  def printCanvas(canvas: List[Char]): String = ???

  def main(args: Array[String]): Unit = {
    def readInput(n: Int, acc: List[List[Char]] = List.empty): List[List[Char]] =
      if(n == 0) acc
      else readInput(n - 1, acc :+ io.StdIn.readLine().toList)

    val input: List[List[Char]] = readInput(NumberOfRaw)

    val words: List[String] = splitWord()

    val wordTree: WordNode = makeWordTree(words, extractSpace(input))

    val result = fillWord(input, wordTree)

    println(printCanvas(result))
  }
}