import scala.concurrent.Future

val mEither: Either[Int, String] = Left(1)


val a = Future(Some(1))
val b = Future(Some(2))

val eventualSomeInt: Future[Some[Int]] = a.flatMap(c => b)