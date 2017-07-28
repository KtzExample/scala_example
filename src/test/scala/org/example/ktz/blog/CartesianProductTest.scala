package org.example.ktz.blog


import cats.syntax.all._
import cats.instances.future._
import org.scalatest._

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration._
import scala.concurrent.{Await, Future}

class CartesianProductTest extends FlatSpec with Matchers {
  def futureInt(): Future[Int] = Future{
    Thread.sleep(2000)
    println("Future Int Done!")
    2
  }

  def futureChar(): Future[Char] = Future{
    Thread.sleep(2000)
    println("Future Char Done!")
    'a'
  }

  "Concurrent" should "for comprehension" in {

    val forComp: (Int, Char) = Await.result(for {
      int <- futureInt()
      char <- futureChar()
    } yield (int, char), 3 second)
  }

  "Concurrent" should "cartesian" in {

    val cartesion: (Int, Char) = Await.result(
      (futureInt() |@| futureChar()).tupled
      , 3 second)
  }
}
