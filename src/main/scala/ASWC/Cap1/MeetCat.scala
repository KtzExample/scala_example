package ASWC.Cap1

/**
  * Created by ktz on 16. 7. 31.
  */
object MeetCat {
  import cats.Show
  import cats.std.int._
  import cats.std.string._
  import cats.syntax.show._
  import java.util.Date

  implicit val dateShow = Show.show[Date]{date =>
    s"It`s ben ${date.getTime} millisecond since the epoch"
  }

  val intAsString : String = 123.show
  val stringAsString : String = "abc".show
}