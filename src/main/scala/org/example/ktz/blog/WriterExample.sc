import cats.data.Writer

def greetW(name: String, logged: Boolean): Writer[List[String], String] =
  Writer(List("Compose a greeting"), {
    val userName = if(logged) name else "User"
    s"Hello $userName"
  })

def isLoggedW(name: String): Writer[List[String], Boolean] =
  Writer(List("Checking if user is logged in"), name.length == 3)

import cats.instances.list._

val name = "Joe"

val resultW: Writer[List[String], String] = for {
  logged <- isLoggedW(name)
  greeting <- greetW(name, logged)
} yield greeting

val (log, result) = resultW.run

val flatMapResult: Writer[List[String], String] = isLoggedW(name).flatMap(logged => greetW(name, logged))


import cats.data.WriterT
import cats.instances.future._
import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

def greetWFuture(name: String, logged: Boolean): WriterT[Future, List[String], String] =
  WriterT(Future(List("Compose a greeting"), {
    val userName = if(logged) name else "User"
    s"Hello $userName"
  }))

def isLoggedWFuture(name: String): WriterT[Future, List[String], Boolean] =
  WriterT(Future(List("Checking if user is logged in"), name.length == 3))

val resultWFuture: WriterT[Future, List[String], String] = for {
  logged <- isLoggedWFuture(name)
  greeting <- greetWFuture(name, logged)
} yield greeting

val futureResult: Future[(List[String], String)] = resultWFuture.run

import scala.concurrent.Await
import scala.concurrent.duration._

val (logAwait, resultAwait) = Await.result(futureResult, 2 second)