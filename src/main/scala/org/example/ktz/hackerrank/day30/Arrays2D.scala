package org.example.ktz.hackerrank.day30

object Arrays2D extends App{
  import scala.collection.immutable

  val length = 6

  def getInput(nInput: Int = 6, acc: List[List[Int]] = List.empty): List[List[Int]] =
    if(nInput == 0) acc.reverse
    else getInput(nInput - 1, scala.io.StdIn.readLine().split(" ").map(_.toInt).toList :: acc)

  def getHourglassesSum(x: Int, y: Int, canvas: List[List[Int]]): Int =
    if(x + 2 >= length || y + 2 >= length) 0
    else {
      canvas(y)(x) + canvas(y)(x + 1) + canvas(y)(x + 2) +
        canvas(y + 1)(x + 1) +
        canvas(y + 2)(x) + canvas(y + 2)(x + 1) + canvas(y + 2)(x + 2)
    }


  val canvas: List[List[Int]] = getInput()
  val result: immutable.Seq[Int] = for {
    x <- 0 to length - 3
    y <- 0 to length -3
  } yield getHourglassesSum(x, y, canvas)

  println(result.max)
}
