package renderers

import models.MazeGrid

/**
  *
  * @author Josh Klun (jklun@nerdery.com)
  */
class PdfMazeRenderer extends MazeRenderer {

  override def render(grid: MazeGrid): Array[Byte] = {
    Array[Byte]()
  }
}
