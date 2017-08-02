package org.example.ktz.hackerrank.recursion

/**
  * Created by ktz on 2017. 7. 9..
  */
object FibonacciNumbers {

  def fibonacci(x:Int):Int = {
    def loop(n: Int, acc: (Int, Int)): Int =
      if(n == 1) acc._1 + acc._2
      else loop(n - 1, (acc._2, acc._1 + acc._2))

    if(x == 1) 0
    else if(x == 2) 1
    else loop(x - 2, (0, 1))
  }

  def main(args: Array[String]) {
    /** This will handle the input and output**/
    println(fibonacci(readInt()))

  }
}
