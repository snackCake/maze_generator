package maze

import models.{MazeCell, MazeGrid}

import scala.collection.mutable
import scala.util.Random

/**
  *
  * @author Josh Klun (jklun@nerdery.com)
  */
class RecursiveBacktrackBuilder extends MazeBuilder {

  def findUnvisited(currentCell: MazeCell, grid: MazeGrid): Set[MazeCell] = {
    var neighbors = Set[MazeCell]()
    if (currentCell.x > 0) {
      neighbors += grid.grid(currentCell.y)(currentCell.x - 1)
    }
    if (currentCell.y > 0) {
      neighbors += grid.grid(currentCell.y - 1)(currentCell.x)
    }
    if (currentCell.x < grid.width - 1) {
      neighbors += grid.grid(currentCell.y)(currentCell.x + 1)
    }
    if (currentCell.y < grid.height - 1) {
      neighbors += grid.grid(currentCell.y + 1)(currentCell.x)
    }
    neighbors.filter(!_.visited)
  }

  def selectNeighbor(unvisited: Set[MazeCell], currentCell: MazeCell): MazeCell =
    Random.shuffle(unvisited.toSeq).head

  def visit(maze: MazeGrid, startCell: MazeCell) = {
    def updateCell(cell: MazeCell): MazeCell = {
      maze.grid(cell.y)(cell.x) = cell
      cell
    }
    def markVisited(cell: MazeCell): MazeCell = updateCell(cell.copy(visited = true))
    def existsUnvisitedCell = maze.grid.flatten.exists(!_.visited)

    var currentCell = startCell
    currentCell = markVisited(currentCell)
    val cellStack = mutable.Stack[MazeCell]()
    while (existsUnvisitedCell) {
      val unvisited = findUnvisited(currentCell, maze)
      if (unvisited.nonEmpty) {
        val neighbor = selectNeighbor(unvisited, currentCell)
        val (updatedCurrent, updatedNeighbor) = tearDownWall(currentCell, neighbor)
        updateCell(updatedCurrent)
        updateCell(updatedNeighbor)
        cellStack.push(updatedCurrent)
        currentCell = updatedNeighbor
      } else if (cellStack.nonEmpty) {
        currentCell = cellStack.pop()
      } else {
        throw new Exception("There are unvisited cells, but the cell stack is empty. Such confuse!")
      }
    }
  }

  private def tearDownWall(currentCell: MazeCell, neighbor: MazeCell): (MazeCell, MazeCell) =
    if (neighbor.x < currentCell.x) {
      currentCell.copy(visited = true, left = false) -> neighbor.copy(visited = true, right = false)
    } else if (neighbor.x > currentCell.x) {
      currentCell.copy(visited = true, right = false) -> neighbor.copy(visited = true, left = false)
    } else if (neighbor.y < currentCell.y) {
      currentCell.copy(visited = true, top = false) -> neighbor.copy(visited = true, bottom = false)
    } else {
      currentCell.copy(visited = true, bottom = false) -> neighbor.copy(visited = true, top = false)
    }

  override def build(emptyGrid: MazeGrid): MazeGrid = {
    visit(emptyGrid, emptyGrid.startCell)
    emptyGrid
  }
}
