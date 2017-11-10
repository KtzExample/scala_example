package org.example.ktz.gof.chap3.builder

import org.example.ktz.gof.chap3.{Direction, Maze, Room}

trait MazeBuilder[RoomT <: Room] {

  var maze: Option[Maze[RoomT]] = None
  var rooms: Map[Int, RoomT] = Map.empty

  def BuildMaze(): MazeBuilder[RoomT]

  def BuildWall(roomNo: Int, direction: Direction): MazeBuilder[RoomT]

  def BuildRoom(roomNo: Int): MazeBuilder[RoomT]

  def BuildDoor(room1No: Int, room1Direction: Direction, room2No: Int, room2Direction: Direction): MazeBuilder[RoomT]

  def GetMaze(): Option[Maze[RoomT]] = rooms.foldLeft(maze)((maze, r) => {
    val iterable: Option[Maze[RoomT]] = maze.map(_.AddRoom(r._2))
    iterable
  })
}
