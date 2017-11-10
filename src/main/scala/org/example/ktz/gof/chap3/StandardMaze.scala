package org.example.ktz.gof.chap3

class StandardRoom(
  override val roomNo: Int,
  override protected val sides: Map[Direction, MapSite] = Map.empty
) extends Room {

  override def Enter(): Unit = println("Entering Standard Room")

  override def SetSide(direction: Direction, mapSite: MapSite): StandardRoom =
    new StandardRoom(roomNo, sides + (direction -> mapSite))
}

class StandardWall() extends Wall {

  override def Enter(): Unit = print("It's Wall!")
}

class StandardDoor(
  val room1: Int,
  val room2: Int,
  override protected val isOpened: Boolean = false
) extends Door[StandardRoom, Maze[StandardRoom]] {

  override def Enter(): Unit = println("It's Standard Door!")
}