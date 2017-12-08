import cats.Monad

import scala.collection.generic.CanBuildFrom
import scala.concurrent.{Await, Future}
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration._

object GenericCollection {
  def map[A, B, L[X] <: Iterable[X]]
  (list: L[A], f: A => B)
  (implicit cbf: CanBuildFrom[L[A], B, L[B]]): L[B] = {
    val builder = cbf(list)
    builder.sizeHint(list.size)
    list.foreach { x =>
      builder += f(x)
    }

    builder.result()
  }

  def mapF[A, B, L[X] <: Iterable[X]]
  (list: Future[L[A]], f: A => B)
  (implicit cbf: CanBuildFrom[L[A], B, L[B]]): Future[L[B]] = {
    list.map {l =>
      val builder = cbf(l)
      builder.sizeHint(l.size)
      l.foreach{x =>
        builder += f(x)
      }

      builder.result()
    }
  }
}


GenericCollection.map[Int, Int, List](List(1,2,3), a => a + 1)

Await.result(GenericCollection.mapF[Int, Int, List](Future(List(1,2,3)), a => a + 1), 2 second)

val seq: Seq[Int] = List(1,2,3,4).map(_ + 2)(collection.breakOut)

seq


