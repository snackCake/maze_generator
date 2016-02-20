package models

/**
  *
  * @author Josh Klun (jklun@nerdery.com)
  */
case class MazeGrid(width: Int,
                    height: Int,
                    startX: Int,
                    startY: Int,
                    endX: Int,
                    endY: Int,
                    grid: Seq[Seq[MazeCell]]) {
  def startCell = grid(startX)(startY)
  def endCell = grid(endX)(endY)
}

object MazeGrid {

  def createEmpty(width: Int,
                  height: Int,
                  startX: Int,
                  startY: Int,
                  endX: Int,
                  endY: Int): MazeGrid =
    MazeGrid(width = width,
      height = height,
      startX = startX,
      startY = startY,
      endX = endX,
      endY = endY,
      grid = Vector.tabulate(width, height) { (i, j) =>
        MazeCell(x = i, y = j)
      })
}