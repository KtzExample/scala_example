import java.time.Instant

import cats.Applicative
import cats.effect.IO
import scala.concurrent.duration._

import scala.concurrent.Await

val async10 = IO.async[Int](cb => {
  Thread.sleep(1000)
  println((Instant.now()).toString)
  cb(Right(10))
})

val async20 = IO.async[Int](cb => {
  Thread.sleep(1000)
  println((Instant.now()).toString)
  cb(Right(20))
})

val async30 = Applicative[IO].map2(async10, async20)(_ + _)

async30.unsafeRunSync()

import monix.eval.Task
import monix.execution.Scheduler.Implicits.global

val task10 = Task{
  Thread.sleep(1000)
  println((Instant.now()).toString)
  10
}
val task20 = Task{
  Thread.sleep(1000)
  println((Instant.now()).toString)
  20
}

val task30 = Applicative[Task].map2(task10, task20)(_ + _)

import monix.eval.Task.nondeterminism
val await = Await.result(task30.runAsync, 3 second)