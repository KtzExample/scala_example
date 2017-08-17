import java.util.Date

import cats.free.Free
import cats.~>

import scala.collection.mutable

sealed trait KVStoreA[A]
case class Put[T](key: String, value: T) extends KVStoreA[Unit]
case class Get[T](key: String) extends KVStoreA[Option[T]]
case class Delete(key: String) extends KVStoreA[Unit]
case class Gets[T](key: List[String]) extends KVStoreA[List[T]]

object kv {
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
          println("Put")
          ()
        case Get(key: String) =>
          println("Get")
          kvs.get(key).map(_.asInstanceOf[T])
        case Delete(key) =>
          kvs.remove(key)
          println("Delete")
          ()
      }
    }

  private val result: Option[Int] = program.foldMap(impureCompiler)

  println(result.get)
}

