import shapeless._

case class Employee(name: String, number: Int, manager: Boolean)
case class IceCream(name: String, numCherries: Int, inCone: Boolean)


val iceCreamGen = Generic[IceCream]

val genericEmployee: String :: Int :: Boolean :: HNil = Generic[Employee].to(Employee("Dave", 123, false))

val genericIceCream: String :: Int :: Boolean :: HNil = Generic[IceCream].to(IceCream("Sundae", 1, false))

iceCreamGen.from(genericEmployee)

case class Red()
case class Amber()
case class Green()

type Light = Red :+: Amber :+: Green :+: CNil

val red: Light = Inl(Red())
val green: Light = Inr(Inr(Inl(Green())))


sealed trait Shape
final case class Rectangle(width: Double, height: Double) extends Shape
final case class Circle(radius: Double) extends Shape

//val gen = Generic[Shape]
