implicit val a: Int = 1
implicit val b: String = "Martin"

//def Hello(implicit a : Int, b : String): String = s"Hello! $a, $b"
//
//println(Hello)

class Hello {
  def hi(implicit a : Hello): String = s"$a is Me!"
}

object Hello {
  implicit val a: Hello = new Hello
}

implicit val c: Hello = new Hello


(new Hello).hi