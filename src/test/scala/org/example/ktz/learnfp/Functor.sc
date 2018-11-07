import cats.effect.IO
//trait Functor[F[_]] {
//  def map[A, B](fa: F[A])(f: A => B): F[B]
//}
//
//object Functor {
//  implicit val listFunctor: Functor[List] = new Functor[List] {
//    override def map[A, B](fa: List[A])(f: A => B): List[B] =
//      fa.map(f)
//  }
//  implicit val cellFunctor: Functor[Cell] = new Functor[Cell] {
//    override def map[A, B](fa: Cell[A])(f: A => B): Cell[B] =
//      Cell(f(fa.a))
//  }
//
//  def apply[F[_]](implicit f: Functor[F]): Functor[F] = f
//
//
//  implicit def functorOps[F[_], A](fa: F[A]): FunctorSyntax[F, A] = new FunctorSyntax[F, A](fa)
//
//}
//
////class FunctorSyntax[F[_], A](fa: F[A]) {
////  def map[B](f: A => B)(implicit functor: Functor[F]): F[B] =
////    Functor[F].map(fa)(f)
////}
//
//Functor[List].map(List(1,2,3,4))(_.toString)
//
//
//case class Cell[A](a: A)
//
//import Functor._
//import cats.data.OptionT
//Functor[Cell].map(Cell(1))(_.toString)
//Cell(1).map(_.toString)
//

val a: Option[Int] = Some(10)
val b: Option[Int] = Some(20)

case class User(id: Int, accountId: Int)
case class UserBirthday(id: Int)

// talk
def findById(id: Int): Option[User] = {
  Some(User(id, id))
}

// account
def findByAccountId(id: Int): Option[UserBirthday] = {
  Some(UserBirthday(id))
}

val userId = 10
val intToMaybeBirthday = (findById _).andThen(_.flatMap(user => findByAccountId(user.id)))


trait MyMonad[F[_]] {
  def pure[A](a: A): F[A]
  def flatten[A](ffa: F[F[A]]): F[A] =
    flatMap[F[A], A](ffa, fa => fa)

  def map[A, B](fa: F[A], f: A => B): F[B] =
    flatMap[A, B](fa, a => pure(f(a)))

  def flatMap[A, B](fa: F[A], f: A => F[B]): F[B]
}

val optionMonad = new MyMonad[Option] {
  override def pure[A](a: A): Option[A] = Some(a)

  override def flatMap[A, B](fa: Option[A], f: A => Option[B]): Option[B] = fa.flatMap(f)
}

def foo: IO[String] = IO {
  val hello = "Hello"
  println(hello)
  hello
}

def bar: IO[String] = IO {
  val world = "World"
  println(world)
  world
}

def quz: IO[String] = IO {
  val martin = "Martin"
  println(martin)
  martin
}

val value = for {
  a <- foo
  b <- bar
  c <- quz
} yield a + b + c

value.unsafeRunSync()