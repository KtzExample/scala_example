package org.example.ktz.blog

object ImplicitOrder extends App{
  implicit val implicitIntInstance1: Instance[String] = Instance("Implicit Instance in Same Conjure")

  def getString(implicit implicitInstance: Instance[String]): String = implicitInstance.value

  import Imported.implicitIntInstance1
  println(getString)
}

case class Instance[T](value: T)

object Instance {
  implicit val implicitIntInstance1: Instance[String] = Instance("Implicit Instance in Companion Object")
}

object Imported {
  implicit val implicitIntInstance1: Instance[String] = Instance("Implicit Instance in imported")
}