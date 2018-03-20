package org.example.ktz.hackerrank.day30

object BinaryNumbers extends App{
  case class Result(currentOne: Int, maxOne: Int) {
    def ++ : Result = Result(currentOne + 1, maxOne)

    def max: Int = math.max(currentOne, maxOne)
  }

  def getBinary(number: Int, result: Result = Result(0, 0)): Int =
    if(number == 1) (result++).max
    else {
      val quotient: Int = number / 2
      val remainder: Int = number % 2
      if(remainder == 1) getBinary(quotient, result++)
      else getBinary(quotient, Result(0, result.max))
    }


  println(getBinary(scala.io.StdIn.readInt()))

}
