
def Hello(l: List[Int]): Int = l.length

implicit def int2List(int: Int): List[Int] = List(int)

Hello(1)

"Martin" sayHello

implicit class Hellable(str: String) {
  def sayHello: String =  s"Hello! $str"
}


def Hello(i: Int): Int = 1
def Hi(i: Int): Boolean = i == 1

Hello _ andThen Hi _