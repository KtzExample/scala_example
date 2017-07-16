package org.example.ktz.hackerrank.recursion

/**
  * Created by ktz on 17. 7. 17.
  */
object ConvexHull {

  case class Point(x: Int, y: Int) {
    import scala.math._

    def angleOf(point: Point): Double = atan2(point.y - this.y, point.x - this.x)

    def isLeftSideOf(from: Point, to: Point): Boolean =
      ((to.x - from.x)*(this.y - from.y) - (this.x - from.x) * (to.y - from.y)) > 0

    def distanceBetween(to: Point): Double = sqrt(pow(to.x - this.x, 2) + pow(to.y - this.y, 2))
  }
  object Point {
    def apply(str: String): Point = {
      val input = str.split(" ").map(_.toInt)
      this.apply(input(0), input(1))
    }
  }

  def sortByAngle(points: List[Point], pivot: Point): List[Point] =
    points.sortBy(point => pivot angleOf point)

  def getPivot(points: List[Point]): Point = points.minBy(_.y)

  def getConvexList(points: List[Point]): List[Point] = {
    def loop(points: List[Point], acc: List[Point]): List[Point] =
      if(points.isEmpty) acc
      else {
        if(points.head isLeftSideOf(acc.tail.head, acc.head)) loop(points.tail, points.head :: acc)
        else loop(points.tail, points.head :: acc.tail)
      }

    val pivot = getPivot(points)
    val pointsWithoutPivot = sortByAngle(points.filter(_ != pivot), pivot)

    loop(pointsWithoutPivot.tail, pivot :: pointsWithoutPivot.head :: Nil)
  }

  def getPerimeter(points: List[Point], first: Point, acc: Double = 0): Double =
    if(points.length == 1) points.head distanceBetween first
    else getPerimeter(points.tail, first, acc + points.head.distanceBetween(points.tail.head))

  def getConvexHullPerimeter(points: List[Point]): Double = {
    val convexList: List[Point] = getConvexList(points)
    getPerimeter(convexList, convexList.head)
  }


  def main(args: Array[String]) {
    def getInput(n: Int, acc: List[Point] = List.empty): List[Point] =
      if(n <= 0) acc
      else getInput(n - 1, Point(io.StdIn.readLine()) :: acc)

    println(getConvexHullPerimeter(getInput(io.StdIn.readInt())))
  }
}