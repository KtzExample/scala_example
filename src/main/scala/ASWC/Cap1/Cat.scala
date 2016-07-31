package ASWC.Cap1

import cats.Show
import cats.Eq

/**
  * Created by ktz on 16. 7. 31.
  */
final case class Cat(name : String, age : Int, color : String)

object Cat {
  implicit val catShow = Show.show[Cat]{cat =>
    import cats.std.string._
    import cats.std.int._
    import cats.syntax.show._
    val name = cat.name.show
    val age = cat.age.show
    val color = cat.color.show
    s"$name is a $age year-old $color cat"
  }
  implicit val catEq = Eq.instance[Cat]{(cat1, cat2) =>
    import cats.std.string._
    import cats.std.int._
    import cats.syntax.eq._
    cat1.name === cat2.name && cat1.age === cat2.age && cat1.color === cat2.color
  }
}

object Main extends App {
  import Cat._
  import cats.syntax.show._
  import cats.syntax.eq._
  import PrintDefaults._
  import PrintSyntax._
  import cats.syntax.option._
  import cats.std.option._

  val cat = Cat("minchul", 5, "RED")
  println(cat.show)

  val cat1 = Cat("Garfield", 35, "orange and black")
  val cat2 = Cat("Heathcliff", 30, "orange and black")

  (cat1 === cat2).print
  (cat1.some === None).print
}