import cats.kernel.Monoid
import cats.implicits.catsKernelStdMonoidForString

val result = Monoid[String].combineAll(List("a", "b", "cc"))

import cats.instances.int.catsKernelStdGroupForInt
import cats.instances.map.catsKernelStdMonoidForMap

val scores = List(Map("Joe" -> 12, "Kate" -> 21), Map("Joe" -> 10))
val totals = Monoid[Map[String, Int]].combineAll(scores)