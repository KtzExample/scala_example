import cats.data.{OptionT, Xor, XorT}

import scala.concurrent.Future

type Error = String

type ErrorOr[A] = Error Xor A

type ErrorOptionOr[A] = OptionT[ErrorOr, A]

import cats.syntax.applicative._

val result1 = 41.pure[ErrorOptionOr]

val result2 = result1.flatMap(x => (x + 1).pure[ErrorOptionOr])
