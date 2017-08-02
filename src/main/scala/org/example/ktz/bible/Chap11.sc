import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global
val int: Int = -1

int##

val long: Long = int
val long2: Long = int.toLong

val int2: Int = long.toInt

val hello: Future[Long] = Future[Int](1).map(aaa => 11)
val hi: List[Long] = (1 to 10).toList.map(aa => {
  import scala.Int.int2long
  int2long(aa)
})

implicit class RichPriv(a : Int)

val javaInt: Integer = new Integer(1)

// int hello = 1
// Integer a = 1
// Integer b = 1
// a == b -> false

val a: Int = 1
val b: Int = 1
a == b

new RichPriv(1)##

// val hiSeq: Seq[Long] = (1 to 10).map(aa => aa) //Compile Error

val absInt: Int = -1 abs  //RichInt

// val priv: Seq[RichPriv] = (1 to 10).map(aa => aa).toSeq  //Compile Error

val privFuture: Future[RichPriv] = Future[Int](1).map(aa => aa)

// 11.3 바닥 타입
//val i: Int = null   // Compile Error
val i: Integer = null

def divide(x: Int, y: Int): Int=
if(y != 0) x / y
else sys.error("divided by zero")

divide(1, 0)

// 11.4 자신만의 값 클래스 정의
