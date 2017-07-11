package org.example.ktz.hackerrank.introduction

/**
  * Created by knigh on 2017-07-09.
  */
object ListReplication extends App{
  def f(num:Int,arr:List[Int]):List[Int] = arr.foldLeft(List.empty[Int])((acc, elem) => acc ++ List.fill(num)(elem))
}
