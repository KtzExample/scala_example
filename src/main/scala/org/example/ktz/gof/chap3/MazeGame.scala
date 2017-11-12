package org.example.ktz.gof.chap3

import org.example.ktz.gof.chap3.factory._
import org.example.ktz.gof.chap3.builder._

class MazeGame {
  def createMazeWithFactory[GameT <: GameType]: Maze[GameT] = {
    val mazeFactory = new MazeFactory[GameT]

    mazeFactory.MakeMaze().AddRoom(
      mazeFactory.MakeRoom(1)
        .SetSide(North, mazeFactory.MakeWall())
        .SetSide(East, mazeFactory.MakeDoor(1, 2))
        .SetSide(South, mazeFactory.MakeWall())
        .SetSide(West, mazeFactory.MakeWall())
    ).AddRoom(
      mazeFactory.MakeRoom(2)
        .SetSide(North, mazeFactory.MakeWall())
        .SetSide(East, mazeFactory.MakeWall())
        .SetSide(South, mazeFactory.MakeWall())
        .SetSide(West, mazeFactory.MakeDoor(1, 2))
    )
  }

  def createMazeWithBuilder[GameT <: GameType]: Maze[GameT] = {
    val mazeBuilder = new MazeBuilder[GameT]

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

  // Abstract Factory

  println("\nMade By Factory\n")


  val standardMazeByAbstractFactory: Maze[Standard] = game.createMazeWithFactory[Standard]

  val enchantedMazeByAbstractFactory: Maze[Enchanted] = game.createMazeWithFactory[Enchanted]

  standardMazeByAbstractFactory.RoomNo(1).get.Enter()

  enchantedMazeByAbstractFactory.RoomNo(1).get.Enter()


  // Builder

  println("\nMade By Builder\n")

  val standardMazeByBuilder: Maze[Standard] = game.createMazeWithBuilder[Standard]
  val enchantedMazeByBuilder: Maze[Enchanted] = game.createMazeWithBuilder[Enchanted]

  standardMazeByBuilder.RoomNo(1).get.Enter()
  enchantedMazeByBuilder.RoomNo(1).get.Enter()

  println("\nAfter cast a spell\n")
  enchantedMazeByBuilder.RoomNo(1).get.Enter()(Spell("Hello world!"))
}
