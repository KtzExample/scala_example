package org.example.ktz.etc
import cats.implicits._
import cats.~>
/**
  * Created by ktz on 2017. 7. 9..
  */
object NatrualTransform {
  implicit val transform = new (Option ~> List) {
    override def apply[A](fa: Option[A]): List[A] = fa.toList
  }

  val list: List[Int] = transform(Some(1))
}
