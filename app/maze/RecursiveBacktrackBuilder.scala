package maze

import models.{MazeCell, MazeGrid}

import scala.util.Random

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

  def selectNeighbor(unvisited: Set[MazeCell], currentCell: MazeCell): MazeCell =
    Random.shuffle(unvisited.toSeq).head

  def visit(grid: MazeGrid, currentCell: MazeCell): MazeGrid = {
    val unvisited = findUnvisited(currentCell, grid)
    val updatedCell = if (unvisited.nonEmpty) {
      val neighbor = selectNeighbor(unvisited, currentCell)
      updateCellWithVisit(currentCell, neighbor)
    } else {
      currentCell.copy(visited = true)
    }
    val updatedRow = grid.grid(currentCell.x).updated(currentCell.y, updatedCell)
    grid.copy(grid = grid.grid.updated(currentCell.x, updatedRow))
  }

  private def updateCellWithVisit(currentCell: MazeCell, neighbor: MazeCell): MazeCell = {
    if (neighbor.x < currentCell.x) {
      currentCell.copy(visited = true, left = false)
    } else if (neighbor.x > currentCell.x) {
      currentCell.copy(visited = true, right = false)
    } else if (neighbor.y < currentCell.y) {
      currentCell.copy(visited = true, top = false)
    } else {
      currentCell.copy(visited = true, bottom = false)
    }
  }

  override def build(emptyGrid: MazeGrid): MazeGrid = visit(emptyGrid, emptyGrid.startCell)


  private sealed trait Side
  private case object Top extends Side
  private case object Right extends Side
  private case object Bottom extends Side
  private case object Left extends Side

}
