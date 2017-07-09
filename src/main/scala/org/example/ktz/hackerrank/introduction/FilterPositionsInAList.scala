package org.example.ktz.hackerrank.introduction

/**
  * Created by knigh on 2017-07-09.
  */
object FilterPositionsInAList {
  def f(arr:List[Int]):List[Int] = arr.zipWithIndex.filter(result => result._2 % 2 != 0).map(_._1)
}
