import scala.annotation.tailrec

def removeAt[T](n : Int, xs: List[T]): List[T] = {
  @tailrec
  def loop(index: Int, xs: List[T], acc: List[T]): List[T] = xs match {
    case List() => acc
    case _ :: rest if index == 0 => acc ++ rest
    case a :: rest => loop(index - 1, rest, acc :+ a)
  }

  loop(n, xs, List.empty)
}

removeAt(1, List('a', 'b', 'c', 'd'))


def merge[T](list1: List[T], list2: List[T])(implicit ord: Ordering[T]): List[T] = (list1, list2) match {
  case (Nil, Nil) => Nil
  case (l1, Nil) => l1
  case (Nil, l2) => l2
  case (x :: xs, y :: ys) =>
    if(ord.lt(x, y)) x :: merge(xs, list2)
    else y :: merge(list1, ys)
}

merge(List(1,3,5,7,9), List(2,4,6,8,10))

def squareList(xs: List[Int]): List[Int] = xs match {
  case Nil => Nil
  case y :: ys => y * y :: squareList(ys)
}

def squareListMap(xs: List[Int]): List[Int] =
  xs.map(a => a * a)

squareList(List(1,2,3,4,5))


squareListMap(List(1,2,3,4,5))

def pack(xs: List[Char]): List[List[Char]] = xs match {
  case Nil => Nil
  case x :: _ =>
    val (first, rest) = xs span (y => y == x)
    first :: pack(rest)
}

val hello =List('a', 'a', 'a', 'a', 'a', 'c', 'c', 'a', 'a', 'a', 'a', 'a')
pack(hello)




def encode(charList: List[Char]): List[(Char, Int)] =
  pack(charList).map(subList => (subList.head, subList.length))

encode(hello)