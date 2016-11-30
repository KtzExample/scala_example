val x = {
  println("Computing X")
  1 + 1
}

x

x

def y = {
  println("Computing Y")
  1 + 1
}

y

y


lazy val z = {
  println("Computing Z")
  1 + 1
}

z

z

import cats.Eval

val now = Eval.now(1 + 2)

val later = Eval.later(3 + 4)

val always = Eval.always(5 + 6)


val x1 = Eval.now{
  println("Computing X1")
  1 + 1
}

x1.value

x1.value

val y1 = Eval.always {
  println("Computing Y")
  1 + 1
}

y1.value

y1.value

val z1 = Eval.later {
  println("Computing Z")
  1 + 1
}

z1.value

z1.value

