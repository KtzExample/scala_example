package org.example.ktz.learnfp

import java.time.LocalDateTime


object Typeclass {

  trait Base {
    def id: Int
    def createdAt: LocalDateTime
  }

  case class Foo(id: Int, createdAt: LocalDateTime, name: String)
  case class Bar(primaryKey: Int, yymmdd: LocalDateTime, name: String)

  trait TC[A] {
    def id(a: A): Int
    def createdAt(a: A): LocalDateTime
  }

  implicit val fooInstance: TC[Foo] = new TC[Foo] {
    override def id(a: Foo): Int = a.id

    override def createdAt(a: Foo): LocalDateTime = a.createdAt
  }

  implicit val barInstance: TC[Bar] = new TC[Bar] {
    override def id(a: Bar): Int = a.primaryKey

    override def createdAt(a: Bar): LocalDateTime = a.yymmdd
  }
}
