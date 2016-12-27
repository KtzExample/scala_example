package inherit

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

/**
  * Created by ktz on 2016. 12. 23..
  */
trait Parent[A] {
  protected val mPhase: String
  def sayHello(a : Int): Future[A]
}

class Sun(override val mPhase: String) extends Parent[Option[Int]] {
  override def sayHello(a: Int): Future[Option[Int]] = Future(Some(a))
}

object Main extends App {
  new Sun("aaa").sayHello(1)
}