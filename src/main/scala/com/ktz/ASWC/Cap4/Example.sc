import scala.language.higherKinds

import cats.data.Reader

def double(a: Int): Int = a * 2

val doubleReader: Reader[Int, Int] = Reader(double)

val double2: Int => Int = doubleReader.run

double(10)
double2(20)


val add1Reader: Reader[Int, Int] = Reader((x: Int) => x + 1)

val flatMapExampleReader: Reader[Int, (Int, Int)] = for {
  x <- doubleReader
  y <- add1Reader
} yield (x, y)

val flatMapExample: Int => (Int, Int) = flatMapExampleReader.run

flatMapExample(10)

val sub5Reader: Reader[Int, Int] = Reader((a: Int) => a -5)

val sequencingExampleReader: Reader[Int, (Int, Int)] = for {
  x <- doubleReader
  y <- if(x > 20) sub5Reader else add1Reader
} yield (x, y)

val sequencingExample: Int => (Int, Int) = sequencingExampleReader.run

sequencingExample(5)

sequencingExample(15)

import cats.data.Reader

import cats.syntax.applicative._

final case class Database(users: Map[Int, String], password: Map[String, String])


type DatabaseReader[A] = Reader[Database, A]

def findUsername(userId: Int): DatabaseReader[Option[String]] = Reader {
  (database: Database) =>
    database.users.get(userId)
}

def checkPassword(username: String, password: String): DatabaseReader[Boolean] = Reader {
  (database: Database) =>
    database.password.get(username).contains(password)
}

def checkLogin(userId: Int, password: String): DatabaseReader[Boolean] = for {
  username <- findUsername(userId)
  passwordOk <- username.map(checkPassword(_, password)).getOrElse(false.pure[DatabaseReader])
} yield passwordOk

val program: Database => Boolean = checkLogin(123, "secret").run

program(Database(Map(123 -> "noel", 321 -> "dave"), Map("noel" -> "shhh", "dave" -> "secret")))

program(Database(Map(123 -> "noel", 321 -> "dave"), Map("noel" -> "secret", "dave" -> "secret")))



import cats.data.State

val a = State[Int, String]{state =>
  (state, s"The state is $state")
}

val (state, result) = a.run(10).value

val state1 = a.runS(10).value

val result1 = a.runA(10).value

val step1 = State[Int, String]{ num =>
  val ans = num + 1
  (ans, s"Result of step1: $ans")
}

val step2 = State[Int, String]{ num =>
  val ans = num * 2
  (ans, s"Result of step2: $ans")
}

val both = for {
  a <- step1
  b <- step2
} yield (a, b)

val(state2, result2) = both.run(20).value

val step01 = State.get[Int]

val step02 = State.set[Int](30)

val step03 = State.pure[Int, String]("Result")

val step04 = State.inspect[Int, String](_ + "!")

val step05 = State.modify[Int](_ + 1)

val (state3, result3) = step01.run(10).value

val (state4, result4) = step02.run(10).value

val (state5, result5) = step03.run(10).value

val (state6, result6) = step04.run(10).value

val (state7, result7) = step05.run(10).value

import State._

val program1: State[Int, (Int, Int, Int)] = for {
  a <- get[Int]
  _ <- set[Int](a + 1)
  b <- get[Int]
  _ <- modify[Int](_ + 1)
  c <- inspect[Int, Int](_ * 1000)
} yield (a, b, c)

val (state8, result8) = program1.run(1).value

