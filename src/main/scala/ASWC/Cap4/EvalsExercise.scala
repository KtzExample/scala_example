package ASWC.Cap4

import cats.{Eval, Monad}

/**
  * Created by ktz on 16. 9. 19.
  */

object EvalsExercise extends App{
  val greeting = Eval.always {
    println("Step 1")
    "Hello"
  } map { str =>
    println("Step 2")
    str + " world"
  }

  greeting.value
  greeting.value

  val ans = for {
    a <- Eval.now {println("Calculating A") ; 40}
    b <- Eval.always{println("Calculating B") ; 2}
  } yield {
    println("Adding A and B")
    a + b
  }

  println
  ans.value
  println
  ans.value

  val saying = Eval.always{println("Step 1") ; "The cat"}.
    map {str => println("Step 2"); str + " sat on"}.
    memoize.
    map{str => println("Step 3") ; str + " the mat"}

  saying.value
  println
  saying.value

  println

  import cats.Monad
  import cats.syntax.flatMap._
  import scala.language.higherKinds

  def stackDepth : Int = Thread.currentThread().getStackTrace.length

  def loopM[M[_] : Monad](m : M[Int], count : Int) : M[Int] = {
    println(s"Stack depth $stackDepth")
    count match {
      case 0 => m
      case n => m.flatMap{_ => loopM(m, n -1)}
    }
  }

  import cats.std.option._
  import cats.syntax.option._

  loopM(1.some, 5)
  println
  loopM(Eval.now(1), 5).value
}
