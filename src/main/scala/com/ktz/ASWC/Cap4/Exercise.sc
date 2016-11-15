import scala.language.higherKinds

def flatMap[F[_], A, B](value : F[A])(func : A => F[B]) : F[B] = ???
def pure[F[_], A](value : A) : F[A] = ???

def Map[F[_], A , B](value : F[A])(func : A => B) : F[B] =
  flatMap(value)(a => pure(func(a)))

