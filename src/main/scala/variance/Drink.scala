package variance

/**
  * Created by ktz on 16. 9. 1.
  */
sealed trait Drink

trait SoftDrink extends Drink
class Cola extends SoftDrink
class TonicWater extends SoftDrink

trait Juice extends Drink
class OrangeJuice extends Juice
class AppleJuice extends Juice

class VendingMachine[+A] {
  // .. don't worry about implementation yet
}

object DrinkMain extends App {
  def install(softDrinkVM: VendingMachine[SoftDrink]): Unit = {
    // Installs the soft drink vending machine
  }
}