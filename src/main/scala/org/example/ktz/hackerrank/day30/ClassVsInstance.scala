package org.example.ktz.hackerrank.day30

object ClassVsInstance {
  class Person {

    var age: Int = 0

    def this(initialAge:Int) = {
      // Add some more code to run some checks on initialAge
      this()
      age = if(initialAge < 0) {
        println("Age is not valid, setting age to 0.")
        0
      } else initialAge
    }

    def amIOld(): Unit = {
      // Do some computations in here and print out the correct statement to the console
      val result = if(age < 13) "You are young." else if(age >= 13 && age < 18 ) "You are a teenager." else "You are old."
      println(result)
    }

    def yearPasses(): Unit = {
      // Increment the age of the person in here
      age += 1
    }

  }
}
