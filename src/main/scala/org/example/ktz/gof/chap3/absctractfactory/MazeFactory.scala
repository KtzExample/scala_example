package org.example.ktz.gof.chap3.absctractfactory

import org.example.ktz.gof.chap3._

trait MazeFactory[RoomT <: Room, WallT <: Wall, DoorT <: Door[RoomT, Maze[RoomT]]] {
  def MakeMaze(): Maze[RoomT]

  def MakeWall(): WallT

  def MakeRoom(roomNo: Int): RoomT

  def MakeDoor(room1No: Int, room2No: Int): DoorT
}
