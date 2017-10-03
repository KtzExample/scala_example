trait Printable {
  def print: String
}

case class PrintableClass(value: String) extends Printable {
  override def print: String = s"value: ${value.toString}"
}

case class ValueCaseClass(value: String)


def test1(): Unit = {
  implicit def convertValueCaseClassToPrintable(valueCaseClass: ValueCaseClass): PrintableClass =
    PrintableClass(valueCaseClass.value)

  val printableClass1: PrintableClass = ValueCaseClass("Implicit Def") // by using convertValueCaseClassToPrintable

  println(printableClass1.print)
}

test1()

def test2(): Unit = {
  implicit class ConvertableValueCaseClass(valueCaseClass: ValueCaseClass) {
    def toPrintable: PrintableClass = PrintableClass(valueCaseClass.value)
  }

  val printableClass2: PrintableClass = ValueCaseClass("Implicit class").toPrintable // by using implicit class

  println(printableClass2.print)
}

test2()

def test3(): Unit = {
  def print(implicit printableClass: PrintableClass): Unit = {
    println(printableClass.print)
  }

  implicit val printableClass = PrintableClass("Implicit Val")

  print
}

test3()