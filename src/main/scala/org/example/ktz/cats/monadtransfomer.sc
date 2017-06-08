import cats.Monad
import cats.data.EitherT
import monix.eval.Task

import scala.concurrent.Await

class GenerationException(number: Long, message: String) extends Exception(message)

object NumberProducer {
  def queryNextNumber: Task[Either[GenerationException, Long]] = Task {
    val source = Math.round(Math.random() * 100)
    if(source <= 80) Right(source)
    else Left(new GenerationException(source, "too Big!"))
  }
}

val num1TE = NumberProducer.queryNextNumber
val num2TE = NumberProducer.queryNextNumber

implicit val taksMonad = new Monad[Task] {

  override def flatMap[A, B](fa: Task[A])(f: (A) => Task[B]): Task[B] =
    fa.flatMap(f)

  override def tailRecM[A, B](a: A)(f: (A) => Task[Either[A, B]]): Task[B] = f(a) flatMap{
    case Left(a) => tailRecM(a)(f)
    case Right(b) => Task.now(b)
  }

  override def pure[A](x: A): Task[A] = Task.now(x)
}

val resultTET = for {
  num1 <- EitherT(num1TE)
  num2 <- EitherT(num2TE)
} yield num1 + num2

import scala.concurrent.duration._
import monix.execution.Scheduler.Implicits.global

val resultX = Await.result(resultTET.value.runAsync, 2 second)
println(s"Result: $resultX")