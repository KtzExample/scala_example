case class Hello(value: String)

trait Printer[T] {
  def print(value: T): String
}

object Printer {
  implicit val IntPrinter: Printer[Int] = new Printer[Int] {
    override def print(value: Int): String = s"Type: Int - $value"
  }

  implicit val PersonPrinter: Printer[Hello] = new Printer[Hello] {
    override def print(value: Hello): String = s"Type: Hello - $value"
  }
}

def print[T](value: T)(implicit printer: Printer[T]): String = printer.print(value)

val int: Int = 1
val hello: Hello = Hello("ktz")

print(int)    // Type: Int - 1
print(hello)  // Type: Hello - Hello(ktz)

object InjectedObject {
  implicit val injectedIntPrinter: Printer[Int] = new Printer[Int] {
    override def print(value: Int): String = s"Injected - Type: Int - $value"
  }
}

import InjectedObject.injectedIntPrinter
print(int)    // Injected - Type: Int - 1