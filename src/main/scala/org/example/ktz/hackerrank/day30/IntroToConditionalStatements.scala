package org.example.ktz.hackerrank.day30

object IntroToConditionalStatements {
  val Weird: String = "Weird"
  val NotWeird: String = "Not Weird"

  val input: Int = scala.io.StdIn.readInt()
  val result: String = if(input % 2 == 1) Weird
  else if(input >= 2 && input <= 5) NotWeird
  else if(input >= 6 && input <= 20) Weird
  else NotWeird
  println(result)
}
