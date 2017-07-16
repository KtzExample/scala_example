class Person(val name: String, val age: Int, val weight: Int)

object Person {
  def apply(name: String, age: Int, weight: Int): Person = new Person(name, age, weight)

  def unapply(arg: Person): Option[(String, Int, Int)] =
    if(arg.age >= 20) Some(arg.name, arg.age, arg.weight)
    else None
}

val young: Person = Person("Martin Junior", 14, 50)
val old: Person = Person("Martin", 29, 70)


def PatternMatching(person: Person): Unit = person match {
  case Person(name, age, weight) => println(s"name: $name, age: $age, weight: $weight")
  case _ => println(s"${person.name} is under 20")
}

PatternMatching(young)

PatternMatching(old)


class SecretPerson(val name: String, val age: Int, val weight: Int)

object SecretPerson {
  def apply(name: String, age: Int, weight: Int): SecretPerson = new SecretPerson(name, age, weight)

  def unapply(arg: SecretPerson): Option[(String, Int)] =
    if(arg.age >= 20) Some(arg.name, arg.age)
    else None
}

def PatternMatchingSecret(secretPerson: SecretPerson): Unit = secretPerson match {
  case SecretPerson(name, age) => println(s"name: $name, age: $age")
  case _ => println(s"${secretPerson.name} is under 20")
}

val secretYoung: SecretPerson = SecretPerson("Martin Junior", 14, 50)
val secretOld: SecretPerson = SecretPerson("Martin", 29, 70)

PatternMatchingSecret(secretYoung)
PatternMatchingSecret(secretOld)


case class CasePerson(name: String, age: Int, weight: Int)
object CasePerson {
//  Compile Error - unapply is defined twice
//  def unapply(arg: CasePerson): Option[(String, Int)] =
//    if(arg.age >= 20) Some(arg.name, arg.age)
//    else None
}


val caseYoung: CasePerson = CasePerson("Martin Junior", 14, 50)
val caseOld: CasePerson = CasePerson("Martin", 29, 70)

def PatternMatchingCase(casePerson: CasePerson): Unit = casePerson match {
//  case CasePerson(name, age) => println(s"name: $name, age: $age")  compile error: wrong number of arguments
  case CasePerson(name, age, weight) => println(s"name: $name, age: $age, weight: $weight")
  case _ => println(s"${casePerson.name} is under 20")
}