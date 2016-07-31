package ASWC.Cap2

/**
  * Created by ktz on 16. 7. 31.
  */
object andorSyntax {
  implicit class AndOrOps[A](value : A) {
    def &&&(valueToCompare : A)(implicit andable : andable[A]) = andable.and(value, valueToCompare)
    def |||(valueToCompare : A)(implicit orable : orable[A]) = orable.or(value, valueToCompare)
  }
}
