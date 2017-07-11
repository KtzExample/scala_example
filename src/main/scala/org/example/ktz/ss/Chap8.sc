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


// 8.7 클로져
var more = 1

val addMore = (n: Int) => n + more

addMore(10)

more = 2    // more가 바뀌면 아래도 바뀐다. -> 변수의 값을 갖는게(포획) 아닌, 변수 자체를 갖는다.

addMore(10)

var result = 0

list.foreach(result += _)

result    // 변수 바깥에도 영향을 준다.

// 8.8 특별한 형태의 함수 호출
def printAll[A](a: A*): Unit = a.foreach(println)

printAll(10, 11, 12, 13)
printAll(list: _*)
printAll(list)

def printSeq(a: Int*): Seq[Int] = a   // 분명 a는 Seq[Int]이다.

//printSeq(Seq[Int](1,2,3,4))   // 그러나, Seq[Int]는 대입할 수 없다.

case class Hello(a: Int, b: Int, c: Int)

Hello(1,2,3).copy(c = 10)   // 변수이름으로 부분 적용할 수 있다.

def default(n: Int = 10): Int = n + 1

default()
default(1)


// 8.9 꼬리 재귀

def addAllWhile(n: Int): List[Int] = {
  var result: List[Int] = List.empty
  var i = n
  while (i > 0) {
    result = result :+ i
    i -= 1
  }

  result
}

@tailrec
def addRecursion(n: Int, acc: List[Int] = List.empty): List[Int] =
  if(n <= 0) acc
  else addRecursion(n - 1, acc :+ n)

addAllWhile(10)

addRecursion(10)