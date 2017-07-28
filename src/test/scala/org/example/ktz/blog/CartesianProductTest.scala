package org.example.ktz.blog


import cats.syntax.all._
import cats.instances.future._
import org.scalatest._

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration._
import scala.concurrent.{Await, Future}

class CartesianProductTest extends FlatSpec with Matchers {

  def futureLong(): Future[Long] = Future {
    Thread.sleep(2000)
    println("Future Long Done!")
    2
  }

  def futureInt(long: Long): Future[Int] = Future{
    Thread.sleep(2000)
    println("Future Int Done!")
    long.toInt
  }

  def futureChar(long: Long): Future[Char] = Future{
    Thread.sleep(2000)
    println("Future Char Done!")
    long.toChar
  }

  "Concurrent" should "for comprehension" in {

    val forComp: (Int, Char) = Await.result(for {
      long <- futureLong()
      int <- futureInt(long)
      char <- futureChar(long)
    } yield (int, char), 5 second)
    // java.util.concurrent.TimeoutException: Futures timed out after [5 seconds]
  }

  "Concurrent" should "for comprehension2" in {

    val longVal: Future[Long] = futureLong()

    val intVal: Future[Int] = for {
      long <- futureLong()
      int <- futureInt(long)
    } yield int

    val charVal: Future[Char] = for {
      long <- futureLong()
      char <- futureChar(long)
    } yield char

    val forComp: (Int, Char) = Await.result(for {
      int <- intVal
      char <- charVal
    } yield (int, char), 5 second)

    // Work well :)
  }

  "Concurrent" should "cartesian" in {

    val cartesion: (Int, Char) = Await.result(for {
      long <- futureLong()
      (int, char) <- (futureInt(long) |@| futureChar(long)).tupled
    } yield (int, char), 5 second)

    // Work well :)
  }
}
