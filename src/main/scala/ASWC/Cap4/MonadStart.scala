package ASWC.Cap4

/**
  * Created by ktz on 16. 8. 10.
  */
object MonadStart extends App{
  import scala.language.higherKinds
  def flatMap[F[_], A, B](value : F[A])(func : A => F[B]) : F[B] = ???
  def pure[F[_], A](value : A) : F[A] = ???
  def map[F[_], A, B](value : F[A])(func : A => B) = flatMap(value)(a => pure(func(a)))
}
