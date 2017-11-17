package org.example.ktz.gof.chap3

trait MapSite[GameT <: GameType] {
  def Enter()(implicit actionOperator: ActionOperator[GameT]): Unit
}

class Room[GameT <: GameType](val roomNo: Int, protected val sides: Map[Direction, MapSite[GameT]] = Map.empty[Direction, MapSite[GameT]]) extends MapSite[GameT] {

  def GetSide(direction: Direction): Option[MapSite[GameT]] = sides.get(direction)

  def SetSide(direction: Direction, mapSite: MapSite[GameT]): Room[GameT] =
    new Room[GameT](roomNo, sides + (direction -> mapSite))

  override def Enter()(implicit actionOperator: ActionOperator[GameT]): Unit = actionOperator.action()

}

class Wall[GameT <: GameType] extends MapSite[GameT] {
  override def Enter()(implicit actionOperator: ActionOperator[GameT]): Unit =
    print(s"It's ${actionOperator.getGameType} Wall!")

}

class Door[GameT <: GameType](val room1: Int, val room2: Int, protected val isOpened: Boolean = false) extends MapSite[GameT] {

  def OtherSideFrom(room: Room[GameT])(implicit maze: Maze[GameT]): Room[GameT] =
    if(room.roomNo == room1) maze.RoomNo(room2).get else maze.RoomNo(room1).get

  override def Enter()(implicit actionOperator: ActionOperator[GameT]): Unit =
    println(s"It's ${actionOperator.getGameType} Door!")
}

class Maze[GameT <: GameType] {

  protected var rooms: Map[Int, Room[GameT]] = Map.empty

  def AddRoom(room: Room[GameT]): Maze[GameT] =  {
    rooms += room.roomNo -> room

    this
  }

  def RoomNo(roomNo: Int): Option[Room[GameT]] = rooms.get(roomNo)
}

trait ActionOperator[GameT <: GameType] {
  def getGameType: String

  def action(): Unit
}

object ActionOperator {
  implicit val standardActionOperator: ActionOperator[Standard] = new ActionOperator[Standard] {
    override def action(): Unit =
      println("Entering Room")

    override def getGameType = "Standard"
  }

  implicit val enchantedActionOperator: ActionOperator[Enchanted] = new ActionOperator[Enchanted] {
    override def action(): Unit =
      println("You cannot enter. You have to tell Spell!")

    override def getGameType = "Enchanted"
  }

  implicit def SpellToActionOperator(spell: Spell): ActionOperator[Enchanted] = new ActionOperator[Enchanted] {
    override def action(): Unit = {
      println(s"Entering Room - Spell: ${spell.spell}")
    }

    override def getGameType = "Enchanted"
  }
}

case class Spell(spell: String)

sealed trait GameType

trait Standard extends GameType

trait Enchanted extends GameType