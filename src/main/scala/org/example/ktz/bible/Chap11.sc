
val int: Int = -1

val long: Long = int
val long2: Long = int.toLong

val int2: Int = long.toInt

val absInt: Int = -1 abs  //RichInt

// 11.3 바닥 타입
//val i: Int = null   // Compile Error
val i: Integer = null

def divide(x: Int, y: Int): Int=
if(y != 0) x / y
else sys.error("divided by zero")

divide(1, 0)

// 11.4 자신만의 값 클래스 정의
