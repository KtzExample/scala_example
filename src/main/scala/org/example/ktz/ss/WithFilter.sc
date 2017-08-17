import scala.collection.generic.FilterMonadic
import scala.collection.immutable

val value: FilterMonadic[Int, immutable.IndexedSeq[Int]] = (1 to 10).withFilter(i => i % 2 == 0)
(1 to 10).filter(i => i % 2 == 0)