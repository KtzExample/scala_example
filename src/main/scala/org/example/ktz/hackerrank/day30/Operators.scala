package org.example.ktz.hackerrank.day30

object Operators extends App{
  val (mealCost, tipPercent, taxPercent) = (io.StdIn.readDouble(), io.StdIn.readDouble(), io.StdIn.readDouble())

  println(s"The total meal cost is ${math.round(mealCost + (mealCost * tipPercent / 100) + (mealCost * taxPercent / 100))} dollars.")
}
