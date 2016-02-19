package maze

import models.{MazeCell, MazeGrid}

/**
  *
  * @author Josh Klun (jklun@nerdery.com)
  */
class RecursiveBacktrackBuilder extends MazeBuilder {

  def findUnvisited(currentCell: MazeCell, grid: MazeGrid): Set[MazeCell] = {
    var unvisitedNeighbors = Set[MazeCell]()
    if (currentCell.x > 0) {
      unvisitedNeighbors += grid.grid(currentCell.x - 1)(currentCell.y)
    }
    if (currentCell.y > 0) {
      unvisitedNeighbors += grid.grid(currentCell.x)(currentCell.y - 1)
    }
    if (currentCell.x < grid.width - 1) {
      unvisitedNeighbors += grid.grid(currentCell.x + 1)(currentCell.y)
    }
    if (currentCell.y > grid.height - 1) {
      unvisitedNeighbors += grid.grid(currentCell.x)(currentCell.y + 1)
    }
    unvisitedNeighbors
  }

  def visit(grid: MazeGrid, currentCell: MazeCell): MazeGrid = {
    val unvisited = findUnvisited(currentCell, grid)
    val updatedCell = if (unvisited.nonEmpty) {
      currentCell.copy(visited = true, )
    } else {
      currentCell.copy(visited = true)
    }
    grid.grid(currentCell.x).updated(currentCell.y, updatedCell)
  }

  override def build(emptyGrid: MazeGrid): MazeGrid = visit(emptyGrid, emptyGrid.startCell)
}
