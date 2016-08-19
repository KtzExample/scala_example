package variance

/**
  * Created by ktz on 16. 8. 19.
  */
sealed abstract class VarianceList[+A] {
  val hello = List[Int]
  def head : A
  def ::[B >: A](x : B) : VarianceList[B]
}

trait Animal {
  val sound : String
}

class Dog extends Animal{
  override val sound: String = "BowWow"
}


trait Barkable[A <: Animal] {
  def Bark(animal: A) : String = animal.sound
}