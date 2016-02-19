package renderers

import models.MazeGrid

/**
  *
  * @author Josh Klun (jklun@nerdery.com)
  */
trait MazeRenderer[T] {

  def render(grid: MazeGrid): T
}
