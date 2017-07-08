package org.example.ktz.hackerrank

/**
  * Created by knigh on 2017-07-09.
  */
import scala.annotation.tailrec
object HugeGCD {
  @tailrec
  def gcd(A: BigInt, B: BigInt): BigInt =
    if(B == 0) A
    else gcd(B, A % B)

  def getInput(): List[BigInt] = scala.io.StdIn.readLine().split(" ").map(BigInt(_)).toList


  def main(args: Array[String]) {
    val inputAN: Int = scala.io.StdIn.readInt()
    val A: BigInt = getInput().product
    val inputBN: Int = scala.io.StdIn.readInt()
    val B: BigInt = getInput().product
    println(gcd(A, B) % 1000000007)
  }
}
