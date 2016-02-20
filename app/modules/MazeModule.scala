package modules

import com.google.inject.AbstractModule
import maze.{MazeBuilder, RecursiveBacktrackBuilder}
import renderers.{PdfMazeRenderer, MazeRenderer}

/**
  *
  * @author Josh Klun (jklun@nerdery.com)
  */
class MazeModule extends AbstractModule {

  override def configure(): Unit = {
    bind(classOf[MazeBuilder]).to(classOf[RecursiveBacktrackBuilder])
    bind(classOf[MazeRenderer[Array[Byte]]]).to(classOf[PdfMazeRenderer])
  }
}
