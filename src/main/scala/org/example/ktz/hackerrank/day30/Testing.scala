package org.example.ktz.hackerrank.day30


object Testing {
  import scala.util.Random

  private def makeLate: Int = Random.nextInt(1000) + 1
  private def makeEarly: Int = Random.nextInt(1000) - 1000

  private def randomBetween(from: Int, until: Int): Int = Random.nextInt(until - from) + from

  def makeTestCase(pass: Boolean): Unit = {
    val n = Random.nextInt(198) + 3
    val k = Random.nextInt(n) + 1
    val nEarlyStudent = if(pass) randomBetween(k, n) else randomBetween(0, k)

    val studentTime: List[Int] = List.fill[Int](nEarlyStudent)(makeEarly) ++ List.fill[Int](n - nEarlyStudent)(makeLate)
    println(s"$n $k")
    println(studentTime.foldLeft("")((str, time) => str + s"$time ").trim)
  }

  def main(args: Array[String]) {
    println(5)
    List(true, false, true, false, true).foreach(makeTestCase)
  }
}
