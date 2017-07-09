package org.example.ktz.hackerrank.Introduction

/**
  * Created by knigh on 2017-07-09.
  */
object ArrayOfNElements extends App {
  def f(num:Int) : String = {
    val list = 0 until num
    list.tail.foldLeft(s"[${list.head}")((str, elem) => str + s", $elem") + "]"
  }

  println(f(readInt))
}
