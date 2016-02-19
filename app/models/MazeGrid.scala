package models

/**
  *
  * @author Josh Klun (jklun@nerdery.com)
  */
case class MazeGrid(width: Int, height: Int, startX: Int, startY: Int, endX: Int, endY: Int) {
  val grid: Seq[Seq[MazeCell]] = Vector.tabulate(width, height) { (i, j) =>
    MazeCell(x = i, y = j)
  }
  def startCell = grid(startX)(startY)
  def endCell = grid(endX)(endY)
}
