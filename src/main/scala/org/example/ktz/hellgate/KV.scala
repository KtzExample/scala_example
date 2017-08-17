import java.util.Date

import cats.free.Free
import cats.{Monad, ~>}

import scala.collection.mutable

sealed trait KVStoreA[A]
case class Put[T](key: String, value: T) extends KVStoreA[Unit]
case class Get[T](key: String) extends KVStoreA[Option[T]]
case class Delete(key: String) extends KVStoreA[Unit]
case class Gets[T](key: List[String]) extends KVStoreA[List[T]]

object KV extends App{
  type KVStore[A] = Free[KVStoreA, A]

  import cats.free.Free.liftF

  def put[T](key: String, value: T): KVStore[Unit] =
    liftF[KVStoreA, Unit](Put(key, value))

  def get[T](key: String): KVStore[Option[T]] =
    liftF[KVStoreA, Option[T]](Get(key))

  def delete(key: String): KVStore[Unit] =
    liftF[KVStoreA, Unit](Delete(key))

  def update[T](key: String, f: T => T): KVStore[Unit] =
    for {
      maybeV <- get[T](key)
      _ <- maybeV.map(v => put[T](key, f(v))).getOrElse(Free.pure(()))
    } yield ()


  def program: KVStore[Option[Int]] = for {
    _ <- put("wild-cats", 2)
    _ <- update[Int]("wild-cats", _ + 12)
    _ <- put("tame-cats", 5)
    n <- get[Int]("wild-cats")
    _ <- delete("tame-cats")
  } yield n

  type Id[A] = A

  def impureCompiler: KVStoreA ~> Id =
    new(KVStoreA ~> Id) {
      println("new transformer" + new Date)
      val kvs = mutable.Map.empty[String, Any]

      override def apply[T](fa: KVStoreA[T]) = fa match {
        case Put(key: String, value: T) =>
          kvs.put(key, value)
          ()
        case Get(key: String) =>
          kvs.get(key).map(_.asInstanceOf[T])
        case Delete(key) =>
          kvs.remove(key)
          ()
      }
    }

  import scala.concurrent.ExecutionContext.Implicits.global
  import scala.concurrent.Future
  import cats.implicits._
  import scala.concurrent.Await
  import scala.concurrent.duration._


  def futureCompiler[M[_] : Monad]: KVStoreA ~> M =
    new(KVStoreA ~> M) {
      println("new transformer" + new Date)
      val kvs = mutable.Map.empty[String, Any]

      override def apply[T](fa: KVStoreA[T]) = fa match {
        case Put(key: String, value: T) =>
          Monad[M].pure {
            kvs.put(key, value)
            ()
          }
        case Get(key: String) =>
          Monad[M].pure(kvs.get(key).map(_.asInstanceOf[T]))
        case Delete(key) =>
          Monad[M].pure {
            kvs.remove(key)
            ()
          }
      }
    }

  private val result: Option[Int] = program.foldMap(impureCompiler)

  println(result.get)

  private val mResult: Future[Option[Int]] = program.foldMap(futureCompiler[Future])

  println(Await.result(mResult, 10 second))
}

