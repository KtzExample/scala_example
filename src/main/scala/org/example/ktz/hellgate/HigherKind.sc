trait Foo[C[_]] {
  def create(i: Int): C[Int]
}

// List는 타입 생성자이다

// List -> Int -> List[Int]

object FooList extends Foo[List] {
  def create(i: Int): List[Int] = List(i)
}

type EitherString[R]  = Either[String, R]

object FooEither extends Foo[EitherString] {
  override def create(i: Int): Either[String, Int] = Right(i)
}

object FooEitherLambda extends Foo[({type l[A]=Either[String, A]})#l] {
  override def create(i: Int): Either[String, Int] = Right(i)
}

object FooEitherProjection extends Foo[Either[String, ?]] {
  override def create(i: Int): Either[String, Int] = Right(i)
}


trait Terminal[C[_]] {
  def read: C[String]
  def write(t: String): C[Unit]
}

type Now[X] = X

object TerminalSync extends Terminal[Now] {
  def read: String = ???
  def write(t: String) = ???
}

import cats.Monad

import scala.concurrent.Future
object TerminalAsync extends Terminal[Future] {
  def read: Future[String] = ???
  def write(t: String): Future[Unit] = ???
}

// C를 Context르 생각할수 있다.
// 이유는 실행의 context를 지금(Now)할것인가? 나중에 할것인가(Future)인가를 이야기 해주기 때문이다..

// 지금은 C[String]을 가지고 할수 있는게 없다..
// C[_]을 감싸는 값이 필요하다.

trait Execution[C[_]] {
  def doAndThen[A, B](c: C[A])(f: A => C[B]): C[B]
  def create[B](b: B): C[B]
}

// 그리고
def echo[C[_]](t: Terminal[C], e: Execution[C]): C[String] = {
  e.doAndThen(t.read) {in : String=>
    e.doAndThen(t.write(in)) { _: Unit =>
      e.create(in)
    }
  }
}

object Execution {
  implicit class Ops[A, C[_]](c: C[A]) {
    def flatMap[B](c: C[A])(f: A => C[B])(implicit e: Execution[C]): C[B] =
      e.doAndThen(c)(f)

    def map[B](f: A => B)(implicit e: Execution[C]): C[B]= flatMap(c)(a => pure(f(a)))

    def pure[B](b: B)(implicit e: Execution[C]): C[B] = e.create(b)
  }
}


//def newEcho[C[_]](t: Terminal[C], e: Execution[C]): C[String] = {
//  import Execution._
//  implicit val ex: Execution[C] = e
//  for {
//    in <- t.read
//    _ <- t.write(in)
//  } yield in
//}