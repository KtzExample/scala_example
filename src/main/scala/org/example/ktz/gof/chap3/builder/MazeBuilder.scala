package org.example.ktz.gof.chap3.builder

import org.example.ktz.gof.chap3._

class MazeBuilder[GameT <: GameType] {

  type RoomNo = Int

  var oMaze: Option[Maze[GameT]] = None
  var rooms: Map[RoomNo, Room[GameT]] = Map.empty

  def BuildMaze(): MazeBuilder[GameT] = {
    oMaze = Some(new Maze[GameT])
    this
  }

  def BuildWall(roomNo: RoomNo, direction: Direction): MazeBuilder[GameT] = {
    rooms = rooms.map {
      case (no, room) if roomNo == no => roomNo -> room.SetSide(direction, new Wall[GameT])
      case (no, room) => no -> room
    }

    this
  }

  def BuildRoom(roomNo: Int): MazeBuilder[GameT] = {
    rooms = rooms + (roomNo -> new Room[GameT](roomNo))

    this
  }

  def BuildDoor(
   room1No: Int,
   room1Direction: Direction,
   room2No: Int,
   room2Direction: Direction
  ): MazeBuilder[GameT] = {
    (rooms.get(room1No), rooms.get(room2No)) match {
      case (Some(room1), Some(room2)) =>
        rooms = rooms.map {
          case (no, room) if no == room1No => no -> room.SetSide(room1Direction, new Door[GameT](room1No, room2No))
          case (no, room) if no == room2No => no -> room.SetSide(room2Direction, new Door[GameT](room2No, room1No))
          case (no, room) => no -> room
        }
    }

    this
  }

  def GetMaze(): Option[Maze[GameT]] =
    oMaze.map(maze => rooms.foldLeft (maze) ((maze, r) => maze.AddRoom(r._2)))
}
