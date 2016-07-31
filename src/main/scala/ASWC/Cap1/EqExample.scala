package ASWC.Cap1

/**
  * Created by ktz on 16. 7. 31.
  */
object EqExample extends App{
  import PrintDefaults._
  import PrintSyntax._
  import cats.Eq
  import cats.std.int._
  val eqInt = Eq[Int]
  eqInt.eqv(123,123).print
  eqInt.eqv(123,234).print

  import cats.syntax.eq._
  (123 === 123).print
  (123 =!= 234).print

  import cats.std.option._
  import cats.syntax.option._

  ((Some(1) : Option[Int]) === (None : Option[Int])).print

  (1.some === None).print
  (2.some =!= None).print

  import java.util.Date
  import cats.std.long._
   implicit val dateEq = Eq.instance[Date] {(date1, date2) =>
     date1.getTime === date2.getTime
   }

  (new Date(1) === new Date(1)).print
}
