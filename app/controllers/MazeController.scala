package controllers

import javax.inject.Inject

import maze.MazeBuilder
import models.MazeGrid
import play.api.libs.iteratee.Enumerator
import play.api.mvc._
import renderers.MazeRenderer

class MazeController @Inject()(builder: MazeBuilder, renderer: MazeRenderer[Array[Byte]]) extends Controller {

  private val defaultSize = 20

  def index = Action { request =>

    def fetchParam(name: String, default: Int) = request
      .getQueryString("w")
      .map(_.toInt)
      .getOrElse(default)

    val width = fetchParam("w", defaultSize)
    val height = fetchParam("h", defaultSize)
    val grid = MazeGrid.createEmpty(
      width = width,
      height = height,
      startX = fetchParam("sx", 0),
      startY = fetchParam("sy", 0),
      endX = fetchParam("ex", width - 1),
      endY = fetchParam("ey", height - 1)
    )
    val maze = builder.build(grid)
    val binaryData = renderer.render(maze)

    Result(ResponseHeader(200), Enumerator[Array[Byte]](binaryData))
  }

}