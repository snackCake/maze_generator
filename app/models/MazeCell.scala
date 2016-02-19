package models

/**
  *
  * @author Josh Klun (jklun@nerdery.com)
  */
case class MazeCell(top: Boolean = true,
                    right: Boolean = true,
                    bottom: Boolean = true,
                    left: Boolean = true,
                    visited: Boolean = false,
                    x: Int,
                    y: Int)
