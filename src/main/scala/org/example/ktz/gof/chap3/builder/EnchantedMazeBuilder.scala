package org.example.ktz.gof.chap3.builder

import org.example.ktz.gof.chap3
import org.example.ktz.gof.chap3._

class EnchantedMazeBuilder extends MazeBuilder[EnchantedRoom] {
  implicit val spell: String = "Hello World!"

  override def BuildMaze(): MazeBuilder[EnchantedRoom] = {
    maze = Some(new Maze[EnchantedRoom] {})
    this
  }

  override def BuildWall(roomNo: Int, direction: chap3.Direction): MazeBuilder[EnchantedRoom] = {
    rooms.map {
      case (no, room) if no == roomNo => room.SetSide(direction, new EnchantedWall)
      case _ =>
    }

    this
  }

  override def BuildRoom(roomNo: Int): MazeBuilder[EnchantedRoom] = {
    rooms += roomNo -> new EnchantedRoom(roomNo)

    this
  }

  override def BuildDoor(room1No: Int, room1Direction: chap3.Direction, room2No: Int, room2Direction: chap3.Direction): MazeBuilder[EnchantedRoom] = {
    rooms.map {
      case (no, room) if no == room1No => room.SetSide(room1Direction, new EnchantedDoor(room1No, room2No))
      case (no, room) if no == room2No => room.SetSide(room2Direction, new EnchantedDoor(room2No, room1No))
      case _ =>
    }

    this
  }
}
