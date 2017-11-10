package org.example.ktz.gof.chap3.absctractfactory

import org.example.ktz.gof.chap3._

class StandardMazeFactory extends MazeFactory[StandardRoom, StandardWall, StandardDoor] {
  def MakeMaze(): Maze[StandardRoom] = new Maze[StandardRoom] {}


  def MakeWall(): StandardWall = new StandardWall


  def MakeRoom(roomNo: Int): StandardRoom = new StandardRoom(roomNo)


  def MakeDoor(room1No: Int, room2No: Int): StandardDoor = new StandardDoor(room1No, room2No)
}
