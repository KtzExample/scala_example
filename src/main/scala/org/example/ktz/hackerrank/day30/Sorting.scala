package org.example.ktz.hackerrank.day30

object Sorting extends App {

  def sort(unSortedList: List[Int], nSortedTime: Int = 0): (List[Int], Int) = {
    def loop(intList: List[Int], nSorted: Int = 0): (List[Int], Int) =
      if(intList.size == 1) (intList, nSorted)
      else {
        if(intList.head > intList.tail.head) {
          val (list, n) = loop(intList.head :: intList.tail.tail, nSorted + 1)
          (intList.tail.head :: list, n)
        } else {
          val (list, n) = loop(intList.tail, nSorted)
          (intList.head :: list, n)
        }
      }

    val (list, n) = loop(unSortedList)

    if(n == 0) (list, nSortedTime)
    else sort(list, nSortedTime + n)
  }


  val nTry: Int = scala.io.StdIn.readInt()
  val unSortedList: List[Int] = scala.io.StdIn.readLine().split(" ").map(_.toInt).toList

  val (sortedList, nSortedTime) = sort(unSortedList)
  println(s"Array is sorted in $nSortedTime swaps.\n" +
    s"First Element: ${sortedList.head}\n" +
    s"Last Element: ${sortedList.last}")
}
