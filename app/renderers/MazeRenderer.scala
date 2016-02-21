package renderers

import models.MazeGrid

/**
  *
  * @author Josh Klun (jklun@nerdery.com)
  */
trait MazeRenderer {

  def render(grid: MazeGrid): Array[Byte]
}
