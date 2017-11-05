package org.example.ktz.gof.chap3

import org.example.ktz.gof.chap3.absctractfactory.{EnchantedMazeFactory, MazeFactory, StandardMazeFactory}

class MazeGame {
  def createMaze(mazeFactory: MazeFactory): Maze = {
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
}

object MazeGame extends App {

  val standardMazeFactory: StandardMazeFactory = new StandardMazeFactory
  val enchantedMazeFactory: EnchantedMazeFactory = new EnchantedMazeFactory

  private val game = new MazeGame

  val standardMaze: Maze = game.createMaze(standardMazeFactory)

  val enchantedMaze = game.createMaze(enchantedMazeFactory)

  standardMaze.RoomNo(1).get.Enter()

  enchantedMaze.RoomNo(1).get.Enter()
}
