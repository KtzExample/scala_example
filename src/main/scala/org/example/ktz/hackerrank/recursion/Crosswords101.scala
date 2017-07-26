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