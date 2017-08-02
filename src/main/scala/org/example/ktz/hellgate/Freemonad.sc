sealed trait Interact[A]

case class Ask(prompt: String) extends Interact[String]

case class Tell(msg: String) extends Interact[Unit]

trait Monad[M[_]] {

  def pure[A](a: A): M[A]

  def bind[A, B](m: M[A])(f: A => M[B]): M[B]
}

object Monad {

  def apply[M[_]: Monad]: Monad[M] = implicitly[Monad[M]]
}

private val value: Monad[Option] = Monad(new Monad[Option] {
  override def pure[A](a: A): Option[A] = Some(a)

  override def bind[A, B](m: Option[A])(f: (A) => Option[B]): Option[B] = ???
})

sealed trait ~>[F[_], G[_]] { self =>
  def apply[A](fm: F[A]): G[A]
}

sealed trait Free[F[_], A] {
  def flatMap[B](g: A => Free[F, B]): Free[F, B]
}


// return => 끝
case class Return[F[_], A](a: A) extends Free[F, A] {
  def flatMap[B](g: A => Free[F, B]): Free[F, B] = g(a)
}

// bind => flatMap
case class Bind[F[_], I, A](a: F[I], f: I => Free[F, A]) extends Free[F, A] {
  def flatMap[B](g: A => Free[F, B]): Free[F, B] = Bind[F, I, B](a, f andThen(_ flatMap g))
}