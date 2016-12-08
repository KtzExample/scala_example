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