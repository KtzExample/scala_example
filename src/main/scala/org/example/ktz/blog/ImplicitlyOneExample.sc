implicit val string: String = "Hello"
implicit val boolean: Boolean = true
implicit val int: Int = 0

def getImplicitT[T](implicit t: T): T = t

getImplicitT[Int]
getImplicitT[String]
getImplicitT[Boolean]

type Id[A] = A

def getImplicitlyT[T : Id]: T = implicitly[Id[T]]

getImplicitlyT[Int]
getImplicitlyT[String]
getImplicitlyT[Boolean]