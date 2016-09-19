package ASWC.Cap4

import cats.data.Xor

/**
  * Created by ktz on 16. 9. 19.
  */
sealed trait Result[+A]

final case class Success[A](value : A) extends Result[A]

final case class Warning[A](value : A, message : String) extends Result[A]

final case class Failure(message : String) extends Result[Nothing]


object Exercise_4_2Main extends App {
  import cats.Monad
  implicit val resultMonad = new Monad[Result] {
    override def pure[A](x: A): Result[A] = Success(x)

    override def flatMap[A, B](fa: Result[A])(f: (A) => Result[B]): Result[B] = fa match {
      case Success(value) => f(value)
      case Warning(value, message1) => f(value) match {
        case Success(value) => Warning(value, message1)
        case Warning(value, message2) => Warning(value, s"$message1, $message2")
        case Failure(message2) => Failure(s"$message1, $message2")
      }
      case Failure(message) => Failure(message)
    }
  }

  def success[A](value : A) : Result[A] = Success(value)
  def warning[A](value : A, message : String) : Result[A] = Warning(value, message)
  def failure[A](message : String) : Result[A] = Failure(message)

  import cats.syntax.functor._
  import cats.syntax.flatMap._

  println(warning(100, "Message1") flatMap (x => Warning(x * 2, "Message2")))
  println(warning(10, "Too low") map (_ - 5))

  println(for {
    a <- success(1)
    b <- warning(2, "Message1")
    c <- warning(a + b, "Messag2")
  } yield c * 10)


  case class User(username : String, password : String)
  sealed trait LoginError
  final case class UserNotFound(username : String) extends LoginError
  final case class PasswordIncorrect(username : String) extends LoginError
  trait UnexpectedError extends LoginError
  type LoginResult = LoginError Xor User


  def handleError(error: LoginError) : Unit = error match {
    case UserNotFound(u) => println(s"User Not Found $u")
    case PasswordIncorrect(u) => println(s"Password incorrect $u")
    case _ : UnexpectedError => println(s"Unexpected Error")
  }

  import cats.syntax.xor._

  val result1 : LoginResult = User("dave", "passw0rd").right
  val result2 : LoginResult = UserNotFound("dave").left

  result1.fold(handleError, println)
  result2.fold(handleError, println)
}