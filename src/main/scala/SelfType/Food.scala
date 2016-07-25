package SelfType

/**
  * Created by ktz on 16. 7. 26.
  */
abstract class Food(val name : String) {
  override def toString: String = name
}

object Apple extends Food("Apple")
object Orange extends Food("Orange")
object Cream extends Food("Cream")
object Sugar extends Food("Sugar")

class Recipe(val name : String, val ingredient : List[Food], val instruction : String){
  override def toString: String = name
}

trait FoodsComponent {
  val foods : Foods
  trait Foods {
    def allFoods : List[Food]
    def foodNamed(name : String) = allFoods.find(_.name == name)
  }
}

trait RecipesComponent {
  val recipes : Recipes
  trait Recipes {
    def allRecipes : List[Recipe]
  }
}

abstract class DatabaseComponent extends FoodCategoriesComponent with FoodsComponent with RecipesComponent {
  val database : Database
  trait Database extends FoodCategories with Food with Recipes
}

trait SimpleFoodsComponents extends FoodsComponent with FoodCategoriesComponent{
  val foods = new Object with SimpleFoods
  val foodCategories = foods
  trait SimpleFoods extends Foods with FoodCategories{
    object Pear extends Food("Pear")
    def allFoods = List(Apple, Pear)
    def allCategories = Nil
  }
}

trait SimpleDatabaseComponent extends DatabaseComponent with SimpleFoodsComponents with SimpleRecipesComponent {
  val database = new Database with SimpleFoods with SimpleRecipes
}

trait BrowserComponent {
  this : DatabaseComponent =>
  val browser : Browser
  trait Browser {
    def recipesUsing(food : Food)
    def displayCategory(category : database.FoodCategory)
  }
}

trait SimpleRecipesComponent extends RecipesComponent {
  this : SimpleFoodsComponents =>
  val recipes = new Object with SimpleRecipes

  trait SimpleRecipes extends Recipes {
    object FruitSalad extends Recipe(
      "fruit salad",
      List(Apple, foods.Pear),
      "Mix it all together"
    )
    def allRecipes = List(FruitSalad)
  }

  def allRecipes = List(FruitSalad)
}

trait FoodCategoriesComponent {
  val foodCategories : FoodCategories
  trait FoodCategories {
    case class FoodCategory(name : String, foods : List[Food])
    def allCategories : List[FoodCategory]
  }
}