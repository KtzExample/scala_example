import cats.Eval

var counter = 0

def increment(): Int = {
  counter += 1
  counter
}

Eval.now(increment())

val later = Eval.later(increment())

later.value

later.value

val always = Eval.always(increment())

always.value

always.value

def sum(num: Long): Long = {
  if(num > 0) {
    sum(num - 1) + num
  } else 0
}

def sumEval(num: Long): Eval[Long] = {
  if(num > 0) {
    Eval.defer(sumEval(num - 1).map(_ + num))
  } else Eval.now(0)
}

sumEval(30000).value