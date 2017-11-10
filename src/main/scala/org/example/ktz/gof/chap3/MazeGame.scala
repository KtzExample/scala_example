package org.example.ktz.gof.chap3

import org.example.ktz.gof.chap3.absctractfactory.{EnchantedMazeFactory, MazeFactory, StandardMazeFactory}
import org.example.ktz.gof.chap3.builder.{EnchantedMazeBuilder, MazeBuilder, StandardMazeBuilder}

class MazeGame {
  def createMaze[RoomT <: Room, WallT <: Wall, DoorT <: Door[RoomT, Maze[RoomT]]](mazeFactory: MazeFactory[RoomT, WallT, DoorT]): Maze[RoomT] = {
    mazeFactory.MakeMaze().AddRoom(
      mazeFactory.MakeRoom(1)
        .SetSide[RoomT](North, mazeFactory.MakeWall())
        .SetSide[RoomT](East, mazeFactory.MakeDoor(1, 2))
        .SetSide[RoomT](South, mazeFactory.MakeWall())
        .SetSide[RoomT](West, mazeFactory.MakeWall())
    ).AddRoom(
      mazeFactory.MakeRoom(2)
        .SetSide[RoomT](North, mazeFactory.MakeWall())
        .SetSide[RoomT](East, mazeFactory.MakeWall())
        .SetSide[RoomT](South, mazeFactory.MakeWall())
        .SetSide[RoomT](West, mazeFactory.MakeDoor(1, 2))
    )
  }

  def createMaze[RoomT <: Room](mazeBuilder: MazeBuilder[RoomT]): Maze[RoomT] = {
    mazeBuilder
      .BuildMaze()
      .BuildRoom(1)
      .BuildWall(1, North)
      .BuildWall(1, South)
      .BuildWall(1, West)
      .BuildRoom(2)
      .BuildWall(1, North)
      .BuildWall(1, South)
      .BuildWall(1, East)
      .BuildDoor(1, East, 2, West)
      .GetMaze().get
  }
}

object MazeGame extends App {

  private val game = new MazeGame

  // Factory

  val standardMazeFactory: StandardMazeFactory = new StandardMazeFactory
  val enchantedMazeFactory: EnchantedMazeFactory = new EnchantedMazeFactory

  val standardMazeByAbstractFactory: Maze[StandardRoom] = game.createMaze(standardMazeFactory)

  val enchantedMazeByAbstractFactory: Maze[EnchantedRoom] = game.createMaze(enchantedMazeFactory)

  standardMazeByAbstractFactory.RoomNo(1).get.Enter()

  enchantedMazeByAbstractFactory.RoomNo(1).get.Enter()


  // Builder

  val standardMazeByBuilder: Maze[StandardRoom] = game.createMaze(new StandardMazeBuilder)
  val enchantedMazeByBuilder: Maze[EnchantedRoom] = game.createMaze(new EnchantedMazeBuilder)

  standardMazeByBuilder.RoomNo(1).get.Enter()
  enchantedMazeByBuilder.RoomNo(1).get.Enter()
}
