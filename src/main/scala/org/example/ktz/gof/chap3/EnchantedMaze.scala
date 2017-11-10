package org.example.ktz.gof.chap3

class EnchantedRoom(
  override val roomNo: Int,
  override protected val sides: Map[Direction, MapSite] = Map.empty
)(implicit spell: String) extends Room {
  override def Enter(): Unit = println(s"Entering Enchanted Room - Spell: $spell")

  override def SetSide(direction: Direction, mapSite: MapSite): EnchantedRoom = new EnchantedRoom(roomNo, sides + (direction -> mapSite))
}

class EnchantedWall() extends Wall {
  override def Enter(): Unit = print("It's Enchanted Wall!")
}

class EnchantedDoor(
  override val room1: Int,
  override val room2: Int,
  override protected val isOpened: Boolean = false
) extends Door[EnchantedRoom, Maze[EnchantedRoom]] {
  override def Enter(): Unit = println("It's Enchanted Door!")
}