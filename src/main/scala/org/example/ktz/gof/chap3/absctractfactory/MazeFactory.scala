package org.example.ktz.gof.chap3.absctractfactory

import org.example.ktz.gof.chap3._

trait MazeFactory {
  def MakeMaze(): Maze

  def MakeWall(): Wall

  def MakeRoom(roomNo: Int): Room

  def MakeDoor(room1No: Int, room2No: Int): Door
}
