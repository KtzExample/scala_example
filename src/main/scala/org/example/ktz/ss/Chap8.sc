import scala.annotation.tailrec

object LongLines {
  // private 함수
  private def privDef(name: String): String = s"Private $name"

  // Public 함수
  def pubDef(name: String): String = s"Public $name"

  // local 함수
  def listNum(numToList: Int): List[Int] = {
    @tailrec
    def loop(n: Int, acc: List[Int]): List[Int] = // 여기서만 사용
      if(n > numToList) acc
      else loop(n + 1, acc :+ n)

    loop(0, List.empty)
  }
}

// LongLines.privDefa // 사용 못함
LongLines.pubDef("Martin")  // 사용 가능

LongLines.listNum(10)

// 8.3 1급 계층 함수

def doItForMe(arg: Int, op: Int => Int): Int = op(arg)

doItForMe(1, intNum => {
  println("Call doItForMe")
  intNum + 10
})  // 여러줄을 넘기려면 중괄호를 사용한다.


val list = List(1,2,3,4,5)

list.map((ele: Int) => ele + 1)        // 각각의 Element를 매핑한다.
list.filter((ele: Int) => ele % 2 == 0)  // 해당 조건이 참인 것만 리턴한다.

// 8.4

// Target Typing
list.map(ele => ele + 1)        // 각각의 Element를 매핑한다.
list.filter(ele => ele % 2 == 0)  // 해당 조건이 참인 것만 리턴한다.

// 8.5
// Wildcard
list.map(_ + 1)        // 각각의 Element를 매핑한다.
list.filter(_ % 2 == 0)  // 해당 조건이 참인 것만 리턴한다.
list.foldLeft(10)(_ + _)   // 모든것을 더하고 + 10

import scala._ // === import scala.*

def printAll[A](a: A*): Unit = a.foreach(println)

printAll(10, 11, 12, 13)
printAll(list: _*)
printAll(list)

//8.6 부분 적용 함수

list.foreach(ele => println(ele))

list.foreach(println(_))

list.foreach(println)

list.foreach(println _)

def sum(a: Int, b: Int, c: Int): Int = a + b + c

sum(1, 2, 3)

// val a = sum  // 이건 안됨
val a = sum _
a(1, 2, 3)

a.apply(1, 2, 3)

val b = sum(1, _: Int, 3)
b(10)