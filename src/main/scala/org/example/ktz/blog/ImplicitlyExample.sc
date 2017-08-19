implicit val optionInt: Option[Int] = Some(1)
implicit val optionBoolean: Option[Boolean] = Some(true)

def getImplicitOptionInt(implicit oInt: Option[Int]): Int = oInt.get
def getImplicitOptionBoolean(implicit oBoolean: Option[Boolean]): Boolean = oBoolean.get

getImplicitOptionInt
getImplicitOptionBoolean

def getImplicitlyOptionA[A: Option]: A = implicitly[Option[A]].get

getImplicitlyOptionA[Int]
getImplicitlyOptionA[Boolean]

trait Parent {
  def sayHello: String
}

trait Son extends Parent {
  override def sayHello: String = "Hello! I'm Son!"
}

trait Daughter extends Parent {
  override def sayHello: String = "Hello! I'm Daughter!"
}

def sayHello[A <: Parent](a: A): Unit = println(a.sayHello)

sayHello(new Son {})

sayHello(new Daughter {})