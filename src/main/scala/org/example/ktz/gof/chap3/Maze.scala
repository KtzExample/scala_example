package org.example.ktz.gof.chap3

trait Room extends MapSite {

  val roomNo: Int

  protected val sides: Map[Direction, MapSite] = Map.empty

  def GetSide(direction: Direction): Option[MapSite] = sides.get(direction)

  def SetSide(direction: Direction, mapSite: MapSite): Room

  override def Enter(): Unit = println("Entering Room")
}

trait Wall extends MapSite {
  override def Enter(): Unit = print("It's Wall!")
}

trait Door extends MapSite {
  val room1: Int
  val room2: Int
  protected val isOpened: Boolean = false

  def OtherSideFrom(room: Room)(implicit maze: Maze): Room =
    if(room.roomNo == room1) maze.RoomNo(room2).get else maze.RoomNo(room1).get

  override def Enter(): Unit = println("It's Door!")
}

trait Maze {
  protected val rooms: Map[Int, Room] = Map.empty

  def AddRoom(room: Room): Maze

  def RoomNo(roomNo: Int): Option[Room] = rooms.get(roomNo)
}