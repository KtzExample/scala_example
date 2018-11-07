package org.example.ktz.scalacheck

import org.scalacheck._
import org.scalacheck.Prop.{forAll, BooleanOperators}

object ScalaCheckExample extends Properties("String"){
  property("startWith") = forAll {(a: String, b: String) =>
    (a + b).startsWith(a)
  }

  val propSmallInteger: Prop = forAll(Gen.choose(0, 100)) { n =>
//    println(n)
    0 <= n && n <= 100
  }
  propSmallInteger.check()

  val propMakeList = forAll { n: Int => {
    (n >= 0 && n < 10000) ==> (List.fill(n)("").length == n)
  }}
  propMakeList.check()

  sealed trait Tree
  case class Node(leaf: Tree, right: Tree, v: Int) extends Tree
  case object Leaf extends Tree

  import org.scalacheck._
  import Gen._
  import Arbitrary.arbitrary

  val genLeaf = const(Leaf)

  val genNode = for {
    v <- arbitrary[Int]
    left <- genTree
    right <- genTree
  } yield Node(left, right, v)

  def genTree: Gen[Tree] = oneOf(genLeaf, genNode)

  println(genTree.sample)

  def matrix[T](g: Gen[T]): Gen[Seq[Seq[T]]] = Gen.sized(size => {
    val side = scala.math.sqrt(size).asInstanceOf[Int]
    Gen.listOfN(side, Gen.listOfN(side, g))
  })

  val smallEvenInteger: Gen[Int] = Gen.choose(0, 200).suchThat(_ % 2 == 0)


}
