package org.example.ktz.hackerrank.day30

object RegEx extends App {
  case class Person(name: String, emailId: String, account: String)
  object Person {
    def apply(input: String): Person =
      Person(
        input.split(" ").head,
        input.split(" ")(1).split("@").head,
        input.split(" ")(1).split("@")(1)
      )
  }

  def getInput(n: Int, acc: List[Person] = Nil): List[Person] =
    if(n == 0) acc.sortWith(_.name < _.name)
    else getInput(n - 1, Person(scala.io.StdIn.readLine()) :: acc)

  val nInput: Int = scala.io.StdIn.readInt()

  getInput(nInput).filter(_.account == "gmail.com").map(_.name).foreach(println)
}
