package org.example.ktz.gof.chap3

trait Room extends MapSite {

  val roomNo: Int

  protected val sides: Map[Direction, MapSite] = Map.empty

  def GetSide(direction: Direction): Option[MapSite] = sides.get(direction)

  def SetSide[RoomT <: Room](direction: Direction, mapSite: MapSite): RoomT

  override def Enter(): Unit = println("Entering Room")
}

trait Wall extends MapSite {
  override def Enter(): Unit = print("It's Wall!")
}

trait Door[RoomT <: Room, MazeT <: Maze[RoomT]] extends MapSite {
  val room1: Int
  val room2: Int
  protected val isOpened: Boolean = false

  def OtherSideFrom(room: RoomT)(implicit maze: MazeT): RoomT =
    if(room.roomNo == room1) maze.RoomNo(room2).get else maze.RoomNo(room1).get

  override def Enter(): Unit = println("It's Door!")
}

trait Maze[RoomT <: Room] {
  protected var rooms: Map[Int, RoomT] = Map.empty

  def AddRoom(room: RoomT): Maze[RoomT] =  {
    rooms += room.roomNo -> room

    this
  }

  def RoomNo(roomNo: Int): Option[RoomT] = rooms.get(roomNo)
}