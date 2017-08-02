
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

val x = 1
StringBuilder.newBuilder.append("0123456").replace((7+1) / 2 - x, ((7+1) / 2) + x - 1,"a")

def toString(list : List[String]): String = list.foldLeft("")((acc, str) => acc + str + "\n")

val default = List.fill(32)(List.fill(63)('_').mkString)
toString(default)

def replace(n: Int, start: Int, strToReplace: String): String =
  StringBuilder.newBuilder.append(strToReplace).replace(start - n, start + n - 1, List.fill((n - 1) * 2 + 1)('1').mkString).toString()

val n = 1
val start = ((63 + 1) / 2) / 2

replace(n, start, default.last)

