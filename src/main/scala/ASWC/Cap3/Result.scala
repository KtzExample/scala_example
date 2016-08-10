package ASWC.Cap3

/**
  * Created by ktz on 16. 8. 10.
  */
trait Result[+A]
final case class Success[A](value : A) extends Result[A]
final case class Warning[A](value : A, message : String) extends Result[A]
final case class Failure(message : String) extends Result[Nothing]

object ResultMain extends App {
  import cats.Functor
  import cats.syntax.functor._

  implicit val resultFuntor = new Functor[Result] {
    override def map[A, B](fa: Result[A])(f: (A) => B): Result[B] = fa match {
      case Success(value) => Success(f(value))
      case Warning(value, message) => Warning(f(value), message)
      case Failure(message) => Failure(message)
    }
  }

  def success[A](value : A) : Result[A] = Success(value)
  def warning[A](value : A, message : String) : Result[A] = Warning(value, message)
  def failure(message : String) : Result[Nothing] = Failure(message)

  success(100) map (_ * 2)
}