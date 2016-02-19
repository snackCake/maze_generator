package maze

import models.MazeGrid

/**
  *
  * @author Josh Klun (jklun@nerdery.com)
  */
trait MazeBuilder {

  def build(emptyGrid: MazeGrid): MazeGrid
}
