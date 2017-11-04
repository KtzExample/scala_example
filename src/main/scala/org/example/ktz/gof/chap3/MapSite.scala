package org.example.ktz.gof.chap3

sealed trait Direction

object North extends Direction
object South extends Direction
object East extends Direction
object West extends Direction

trait MapSite {
  def Enter(): Unit
}

class Room(roomNo: Int) extends MapSite {

  private val sides: List[MapSite] = ???

  def GetSide(direction: Direction): MapSite = ???

  def SetSide(direction: Direction, mapSite: MapSite): Room = ???

  override def Enter(): Unit = ???
}

class Wall extends MapSite {
  override def Enter(): Unit = ???
}

class Door(room1: Room,room2: Room) extends MapSite {

  private val isOpend: Boolean = ???

  def OtherSideFrom(room: Room): Room = ???

  override def Enter(): Unit = ???
}

class Maze {
  def AddRoom(room: Room): Maze = ???
  def RoomNo(roomNo: Int): Room = ???
}