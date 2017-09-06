import shapeless._
import shapeless.tag.@@

// phantom
trait PageTag
trait SizeTag

type Page = Int @@ PageTag
type Size = Int @@ SizeTag

val size = 10
val page = 1
def getPage(size: Int, page: Int) = size * page
// 잘돌아감, 그러나 줄리엣이 반성문 써야함
getPage(page, size)

/*
val page1 = tag[PageTag][Int](1)
val size1 = tag[SizeTag][Int](10)
def getPage(size: Size, page: Page) = size * page
getPage(size1, page1)  // works
getPage(page1, size1)  // 컴파일 안됨, 반성문은 안쓰게 컴파일러가 도와줌
//
println(page)
// Page type은 컴파일후 사라지고 런타임에 Int 임
println(page.getClass) // => class java.lang.Integer
val int : Int = page
val p: Page = int  // 안됨
*/