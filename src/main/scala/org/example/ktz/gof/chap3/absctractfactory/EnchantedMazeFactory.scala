package org.example.ktz.gof.chap3.absctractfactory

import org.example.ktz.gof.chap3.{EnchantedDoor, EnchantedMaze, EnchantedRoom, EnchantedWall}

class EnchantedMazeFactory extends MazeFactory {
  implicit val spell: String = "Hello World!"


  override def MakeMaze(): EnchantedMaze = new EnchantedMaze

  override def MakeWall(): EnchantedWall = new EnchantedWall()


  override def MakeRoom(roomNo: Int): EnchantedRoom = new EnchantedRoom(roomNo)


  override def MakeDoor(room1No: Int, room2No: Int): EnchantedDoor = new EnchantedDoor(room1No, room2No)

}
