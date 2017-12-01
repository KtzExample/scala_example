case class Cell[A](a: A) {
  def flatMap[B](f: A => Cell[B]): Cell[B] = f(a)

  def pure[B](a: B): Cell[B] = Cell(a)

  def map[B](f: A => B): Cell[B] = pure(f(a))

  def withFilter(f: A => Boolean): Cell[A] = if(f(a)) this else ???

  def foreach(f: A => Unit): Unit = f(a)
}

for {
  a <- Cell { 1 }
  b <- Cell { 2 } if b == 2
} yield a + b

case class CellMonad[A](a: A) {
  def flatMap[B](f: A => CellMonad[B]): CellMonad[B] = f(a)

  def pure[A](x: A): CellMonad[A] = CellMonad(a)
}