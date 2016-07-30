package ASWC.Ex1_1_4

/**
  * Created by ktz on 16. 7. 31.
  */
final case class Cat(name : String, age : Int, color : String)

object Cat {
  import PrintSyntax._
  import PrintDefaults._
  implicit val catPrintable = new Printable[Cat] {
    override def format(valueToString: Cat): String = {
      val name = valueToString.name
      val age = valueToString.age.format
      val color = valueToString.color
      s"$name is a $age year-old $color cat."
    }
  }
}

object Main extends App {
  import PrintSyntax._
  val cat = Cat("minchul", 5, "RED")
  cat.print
}