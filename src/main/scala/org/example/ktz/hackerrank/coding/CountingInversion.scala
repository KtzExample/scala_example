package org.example.ktz.hackerrank.coding

object CountingInversion {
  var inversionMap: Map[Int, Int] = Map[Int, Int]()

  def countInversions(arr: List[Int], nOfInverse: Int = 0): Int =  {
    if(arr.isEmpty) {
      nOfInverse
    } else {
      inversionMap = decreaseBigger(inversionMap, arr.head)
      if(inversionMap.get(arr.head).isDefined){
        countInversions(arr.tail, nOfInverse + inversionMap(arr.head))
      } else {
        val nOfSmaller = arr.tail.count(_ < arr.head)
        inversionMap = inversionMap + (arr.head -> nOfSmaller)
        countInversions(arr.tail, nOfInverse + nOfSmaller)
      }
    }
  }

  def decreaseBigger(hashMap: Map[Int, Int], target: Int): Map[Int, Int] = {
    hashMap.map(keyN => if(keyN._1 > target) keyN._1 -> (keyN._2 - 1) else keyN)
  }

  def main(args: Array[String]) {
    val sc = new java.util.Scanner (System.in)
    var t = sc.nextInt()
    var a0 = 0
    while(a0 < t){
      var n = sc.nextInt()
      var arr = new Array[Int](n)
      for(arr_i <- 0 to n-1) {
        arr(arr_i) = sc.nextInt()
      }
      val result = countInversions(arr.toList)
      println(result)
      a0+=1
      inversionMap = Map.empty
    }
  }
}
