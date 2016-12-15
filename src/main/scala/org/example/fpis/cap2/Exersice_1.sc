import scala.annotation.tailrec

def fib(n: Int): Int = {
  @tailrec
  def loop(nums: List[Int], n: Int): Int =
    if(n == 1)
      nums.head
    else{
      loop((nums.head + nums.tail.head) :: nums, n - 1)
    }
  loop(List(1, 0), n)
}

fib(5)

def isSorted[A](as: Array[A], ordered: (A, A) => Boolean): Boolean = {
  @tailrec
  def loop(as: Array[A], ordered: (A, A) => Boolean): Boolean =
    if(as.length == 2)
      ordered(as.head, as.last)
    else
      if (ordered(as.head, as.tail.head))
        loop(as.tail, ordered)
      else
        false
  loop(as, ordered)
}

isSorted[Int](Array(1,2,4,3,5), _ <= _)

val predicate4: (Int, Int) => Boolean = new ((Int, Int) => Boolean) {
  override def apply(v1: Int, v2: Int): Boolean = v1 <= v2
}

def curring[A, B, C](f: (A, B) => C): A => (B => C) = a => f.curried(a)

def uncurry[A, B, C](f: A => B => C): (A, B) => C =
  (a, b) => f(a)(b)