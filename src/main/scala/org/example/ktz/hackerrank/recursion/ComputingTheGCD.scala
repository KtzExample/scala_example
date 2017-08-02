package org.example.ktz.hackerrank.recursion

/**
  * Created by ktz on 2017. 7. 9..
  */
object ComputingTheGCD {
  def gcd(x: Int, y: Int): Int =
    if(x % y == 0) y
    else gcd(y, x % y)


  /**This part handles the input/output. Do not change or modify it **/
  def acceptInputAndComputeGCD(pair:List[Int]) = {
    println(gcd(pair.head,pair.reverse.head))
  }

  def main(args: Array[String]) {
    /** The part relates to the input/output. Do not change or modify it **/
    acceptInputAndComputeGCD(readLine().trim().split(" ").map(x=>x.toInt).toList)

  }
}
