package org.example.ktz.gof.chap3.factory

import org.example.ktz.gof.chap3._

class MazeFactory[GameT <: GameType] {
  def MakeMaze(): Maze[GameT] = new Maze[GameT]

  def MakeWall(): Wall[GameT] = new Wall[GameT]

  def MakeRoom(roomNo: Int): Room[GameT] = new Room[GameT](roomNo)

  def MakeDoor(room1No: Int, room2No: Int): Door[GameT] = new Door[GameT](room1No, room2No)
}
