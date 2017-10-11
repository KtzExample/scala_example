import java.time.Instant

import cats.Applicative
import cats.implicits._
import com.sun.net.httpserver.Authenticator.Success

import scala.concurrent.{Await, Future}
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration._
import scala.util.{Failure, Success}

def setFromDB(): Future[Int] = Future[Int] {
  println("setFromDB Start")
  Thread.sleep(5000)
  println("setFromDB End")
  5
}

def setFromCache(): Future[Int] = Future[Int] {
  println("setFromCache Start")
  Thread.sleep(1000)
  println("setFromCache End")
  1
}

def getBoth(): Future[Int] = {
  setFromDB().onComplete {
    case Success(_) =>
    case Failure(e) =>
      setFromCache()
  }
  setFromCache()
}

val result: Future[Int] = getBoth()

val start: Instant = Instant.now()

println(s"Await Start: ${start.toString}")

Await.result(result, 10 second)

val end: Instant = Instant.now()

println(s"Await End: ${end.toString} ")

println(s"gap: ${end.getEpochSecond - start.getEpochSecond}")
