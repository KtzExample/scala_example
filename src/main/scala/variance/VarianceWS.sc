trait Item

trait PlasticItem extends Item

case class PlasticBottle() extends PlasticItem

trait PaperItem extends Item

case class NewsPaper() extends PaperItem

class Garbage[-A]

def setGarbageCanForPlastic(gc : Garbage[PlasticItem]) : Unit = println(gc.toString)

setGarbageCanForPlastic(new Garbage[PlasticItem])

setGarbageCanForPlastic(new Garbage[Item])

def PaperItemToItem(gc : Garbage[PaperItem]) : Garbage[Item] = gc