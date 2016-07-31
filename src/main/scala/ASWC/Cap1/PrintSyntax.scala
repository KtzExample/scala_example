package ASWC.Cap1

/**
  * Created by ktz on 16. 7. 31.
  */
object PrintSyntax {
  implicit class PrintOps[A](value : A) {
    def format(implicit printable: Printable[A]) : String = printable.format(value)
    def print(implicit printable: Printable[A]) : Unit = println(printable.format(value))
  }
}
