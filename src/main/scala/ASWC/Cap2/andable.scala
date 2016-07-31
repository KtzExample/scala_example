package ASWC.Cap2

import cats.Monoid

/**
  * Created by ktz on 16. 7. 31.
  */
trait andable[A] extends Monoid[A] {
  def and(x : A, y : A) : A = combine(x, y)
}
trait orable[A] extends Monoid[A] {
  def or(x : A, y : A) : A = combine(x, y)
}
