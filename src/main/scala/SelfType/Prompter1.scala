package SelfType

/**
  * Created by ktz on 16. 7. 26.
  */
trait Prompter1 {
  val prompt = "> "
  val greeting = "Hello World"

  def printGreeting() : Unit ={
    println(prompt + greeting)
  }
}

trait Prompter2 {
  this : GreetingProvider =>
  val prompt = "> "

  def printGreeting() : Unit = {
    println(this.prompt + this.greeting)
  }
}

trait GreetingProvider {
  val greeting = "Hello World"
}

object Prompter1Main extends App {
  val prompter1 = new Object with Prompter1
  prompter1.printGreeting()
  val prompter2 = new Prompter2 with GreetingProvider
  prompter2.printGreeting()
  val prompter2backwards = new GreetingProvider with Prompter2
  prompter2backwards.printGreeting()
}