package org.example.ktz.hackerrank.day30

object NestedLogic extends App {

  case class ReturnDate(year: Int, month: Int, day: Int) {
    def compare(that: ReturnDate): (Boolean, Boolean, Boolean) =
      (year == that.year, month == that.month, day == that.day)
  }
  object ReturnDate {
    def apply(input: Array[Int]): ReturnDate = ReturnDate(input(2), input(1), input.head)
  }

  val actualReturned: ReturnDate = ReturnDate(io.StdIn.readLine().split(" ").map(_.toInt))
  val expectedReturned: ReturnDate = ReturnDate(io.StdIn.readLine().split(" ").map(_.toInt))

  val fine: Int = actualReturned compare expectedReturned match {
    case (false, _, _) =>
      val diff: Int = actualReturned.year - expectedReturned.year
      if(diff < 0) 0
      else 10000
    case (true, false, _) =>
      val diff: Int = actualReturned.month - expectedReturned.month
      if(diff < 0) 0
      else diff * 500
    case (true, true, false) =>
      val diff: Int = actualReturned.day - expectedReturned.day
      if(diff < 0) 0
      else diff * 15
    case (true, true, true) => 0
  }

  println(fine)
}
