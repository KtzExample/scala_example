package org.example.ktz.hackerrank.day30

object RunningTimeAndComplexity extends App {

  def readNumbers(n: Int, acc: List[Int] = List.empty): List[Int] =
    if(n == 0) acc
    else readNumbers(n - 1, acc :+ scala.io.StdIn.readInt())

  def getPrime(nextNum: Int, arr: List[Either[Boolean, Int]]): List[Boolean] =
    if(arr.forall(_.isLeft))
      arr.map(_.left.get)
    else
      getPrime(nextNum + 1, arr.map {
        case Left(isPrint) =>
          Left(isPrint)
        case Right(num) =>
          if (math.sqrt(num) < nextNum)
            Left(true)
          else if (num % nextNum == 0)
            Left(false)
          else
            Right(num)
      })

  val nInput: Int = scala.io.StdIn.readInt()
  val numbers: List[Int] = readNumbers(nInput)


  val initVal: List[Either[Boolean, Int]] = numbers.map(num => {
    if(num == 1)
      Left(false)
    else
      Right[Boolean, Int](num)
  })


  val result: List[Boolean] = getPrime(2, initVal)

  result.foreach(r => if(r) println("Prime") else println("Not prime"))
}
