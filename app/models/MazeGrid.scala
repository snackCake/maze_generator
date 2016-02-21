package models

import scala.collection.mutable.ArrayBuffer

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
                    grid: ArrayBuffer[ArrayBuffer[MazeCell]]) {

  def startCell = grid(startX)(startY)

  def endCell = grid(endX)(endY)

  def convertToString: String =
    grid.map { row =>
      row.map {
        case MazeCell(false, false, false, false, _, _, _) => '\u256C'
        case MazeCell(true, false, false, false, _, _, _) => '\u2566'
        case MazeCell(false, true, false, false, _, _, _) => '\u2563'
        case MazeCell(true, true, false, false, _, _, _) => '\u2557'
        case MazeCell(false, false, true, false, _, _, _) => '\u2569'
        case MazeCell(true, false, true, false, _, _, _) => '\u2550'
        case MazeCell(false, true, true, false, _, _, _) => '\u255D'
        case MazeCell(true, true, true, false, _, _, _) => '\u255B'
        case MazeCell(false, false, false, true, _, _, _) => '\u2560'
        case MazeCell(true, false, false, true, _, _, _) => '\u2554'
        case MazeCell(false, true, false, true, _, _, _) => '\u2551'
        case MazeCell(true, true, false, true, _, _, _) => '\u2556'
        case MazeCell(false, false, true, true, _, _, _) => '\u255A'
        case MazeCell(true, false, true, true, _, _, _) => '\u2552'
        case MazeCell(false, true, true, true, _, _, _) => '\u2559'
        case MazeCell(true, true, true, true, _, _, _) => '\u25A1'
      }.mkString
    }.mkString("\n")
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
      grid = ArrayBuffer.tabulate(height, width) { (i, j) =>
        MazeCell(x = j, y = i)
      })
}