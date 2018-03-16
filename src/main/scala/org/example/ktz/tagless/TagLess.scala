package org.example.ktz.tagless

import cats.{Applicative, FlatMap, Id, Monad, Parallel}

trait KVStore[F[_], V] {
  def get(key: String): F[V]
  def put(key: String, value: V): F[Unit]
}


object KVStore {
  import cats.implicits._

  def program[F[_]: FlatMap](kv: KVStore[F, Int]): F[Int] = {
    for {
      _ <- kv.put("a", 10)
      _ <- kv.put("b", 11)
      result <- kv.get("a")
    } yield result
  }

  def programPar[M[_]: Monad](kv: KVStore[M, Int])(implicit parallel: Parallel[M, M]): M[Int] = {
    (kv.put("a", 10), kv.put("b", 11), kv.get("a"))
      .parMapN((_, _, a) => a)
  }
}