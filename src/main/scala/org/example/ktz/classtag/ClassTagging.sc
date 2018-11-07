import scala.reflect.ClassTag
import scala.reflect.api.TypeTags

trait Fruit {
  def toString: String
}

trait Apple extends Fruit {
  override def toString: String = "apple"
}
trait Orange extends Fruit {
  override def toString: String = "orange"
}

val ys: List[Fruit] = List(new Apple {}, new Orange {})
val zs: List[Apple] = List(new Apple {}, new Apple {})
val as: List[Orange] = List(new Orange {}, new Orange {})

def bar[A <: Fruit : ClassTag](xs: List[Fruit]): List[Fruit] = {
  xs.filter {
    case _: A => true
    case _ => false
  }
}

bar[Apple](ys)
bar[Orange](ys)

import scala.reflect.runtime.universe._
def quz[A <: List[Fruit]](xs: A)(implicit tag: TypeTag[A]): String = {
  xs match  {
    case _ if tag.tpe =:= typeOf[List[Apple]] => "apple"
    case _ if tag.tpe =:= typeOf[List[Orange]] => "orange"
    case _ => "unknown"
  }
}
quz(ys)

quz(zs)

def qux[A <: Fruit, B >: List[A]](xs: B)(implicit tag: WeakTypeTag[A]): String = {
  xs match  {
    case _ if tag.tpe =:= weakTypeOf[List[A]] => "matched"
    case _ => "unknown"
  }
}

qux[Apple, List[Fruit]](zs)
