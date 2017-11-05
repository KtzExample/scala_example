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
) extends Door {

  override def Enter(): Unit = println("It's Standard Door!")
}

class StandardMaze(
  override protected val rooms: Map[Int, Room] = Map.empty
) extends Maze {
  override def AddRoom(room: Room) = new StandardMaze(rooms + (room.roomNo -> room))
}