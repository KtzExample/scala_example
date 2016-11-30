import cats.data.State
type CalcState[A] = State[List[Int], A]

def operator(func: (Int, Int) => Int): CalcState[Int] = State[List[Int], Int]{
  case a :: b :: tail =>
    val ans = func(a, b)
    (ans :: tail, ans)
  case _ =>
    sys.error("Fail!")
}

def operand(num: Int): CalcState[Int] = State[List[Int], Int]{oldStack =>
  (num :: oldStack, num)
}

def evalOne(sym: String): CalcState[Int] = sym match {
  case "+" => operator(_ + _)
  case "-" => operator(_ - _)
  case "*" => operator(_ * _)
  case "/" => operator(_ / _)
  case num => operand(num.toInt)
}

val program = for {
  _ <- evalOne("1")
  _ <- evalOne("2")
  ans <- evalOne("+")
} yield ans

program.runA(Nil).value

evalOne("42").runA(Nil).value

import cats.syntax.applicative._

def evalAll(input: List[String]): CalcState[Int] = input.foldLeft(0.pure[CalcState[Int]]){(a, b) =>
  a flatMap(_ => evalOne(b))
}