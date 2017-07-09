package org.example.ktz.hackerrank.Introduction

/**
  * Created by knigh on 2017-07-09.
  */
object SumOfOddElements {
  def f(arr:List[Int]):Int = arr.filter(_ % 2 != 0).sum
}
