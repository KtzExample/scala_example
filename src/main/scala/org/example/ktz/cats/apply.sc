import cats._

implicit val optionApply: Apply[Option] = new Apply[Option] {

  override def ap[A, B](f: Option[(A) => B])(fa: Option[A]): Option[B] =
    fa.flatMap(a => f.map(ff => ff(a)))

  override def map[A, B](fa: Option[A])(f: (A) => B): Option[B] =
    fa.map(f)
}

val addArity2 = (a: Int, b: Int) ⇒ a + b
Apply[Option].ap2(Some(addArity2))(Some(1), Some(2))
Apply[Option].ap2(Some(addArity2))(Some(1), None)
val addArity3 = (a: Int, b: Int, c: Int) ⇒ a + b + c
Apply[Option].ap3(Some(addArity3))(Some(1), Some(2), Some(3))

import cats.implicits._
val option2 = Option(1) |@| Option(2)
val option3 = option2 |@| Option.empty[Int]

option2 map addArity2
option3 map addArity3

option2 apWith Some(addArity2)
option3 apWith Some(addArity3)
option2.tupled
option3.tupled