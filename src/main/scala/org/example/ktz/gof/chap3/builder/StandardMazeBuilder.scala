package org.example.ktz.gof.chap3.builder

import org.example.ktz.gof.chap3._

class StandardMazeBuilder extends MazeBuilder[StandardRoom] {

  override def BuildMaze(): MazeBuilder[StandardRoom] = {
    maze = Some(new Maze[StandardRoom] {})
    this
  }

  override def BuildWall(roomNo: Int, direction: Direction): MazeBuilder[StandardRoom] = {
    rooms.map {
      case (no, room) => if(no == roomNo) room.SetSide(direction, new StandardWall)
    }

    this
  }

  override def BuildRoom(roomNo: Int): MazeBuilder[StandardRoom] = {
    rooms += (roomNo -> new StandardRoom(roomNo))
    this
  }

  override def BuildDoor(room1No: Int, room1Direction: Direction, room2No: Int, room2Direction: Direction): MazeBuilder[StandardRoom] = {
    rooms.map {
      case (no, room) if no == room1No => room.SetSide(room1Direction, new StandardDoor(room1No, room2No))
      case (no, room) if no == room2No => room.SetSide(room2Direction, new StandardDoor(room2No, room1No))
    }

    this
  }
}
