package org.example.ktz.gof.chap3.absctractfactory

import org.example.ktz.gof.chap3.{EnchantedDoor, EnchantedRoom, EnchantedWall, Maze}

class EnchantedMazeFactory extends MazeFactory[EnchantedRoom, EnchantedWall, EnchantedDoor] {
  implicit val spell: String = "Hello World!"


  override def MakeMaze(): Maze[EnchantedRoom] = new Maze[EnchantedRoom] {}

  override def MakeWall(): EnchantedWall = new EnchantedWall()


  override def MakeRoom(roomNo: Int): EnchantedRoom = new EnchantedRoom(roomNo)


  override def MakeDoor(room1No: Int, room2No: Int): EnchantedDoor = new EnchantedDoor(room1No, room2No)

}
