def sumOfPower(arr: List[Int], s: Int, t: Int, p: Int): Int = {
  arr.map(ele => math.pow(ele, p).toInt).reduce(_ + _)
}


sumOfPower((0 to 10).toList, 0, 3, 2)


def average(arr: List[Int]): Double = {
  val tuple = arr.map(l => (l, 1)).reduce {(l, r) => (l._1 + r._1, l._2 + l._2)}

  tuple._1 / tuple._2.toDouble
}


average((0 to 10).toList)