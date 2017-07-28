import cats.instances.list._
import cats.syntax.all._

val ints: List[Int] = List(1, 2, 3, 4)
val chars: List[Char] = List('a', 'b', 'c')

(ints |@| chars).tupled
(chars |@| ints).tupled

(ints |@| chars).map(_ + _.toString)