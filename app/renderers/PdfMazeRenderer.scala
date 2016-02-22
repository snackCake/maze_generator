package renderers

import java.io.{ByteArrayOutputStream, File}

import models.MazeGrid
import org.apache.pdfbox.pdmodel.PDPageContentStream.AppendMode
import org.apache.pdfbox.pdmodel.font.PDType0Font
import org.apache.pdfbox.pdmodel.{PDDocument, PDPageContentStream}

/**
  *
  * @author Josh Klun (jklun@nerdery.com)
  */
class PdfMazeRenderer extends MazeRenderer {

  override def render(grid: MazeGrid): Array[Byte] = {
    val doc = PDDocument.load(new File("conf/asteroids.pdf"))
    val page = doc.getPage(0)
    val font = PDType0Font.load(doc, new File("conf/gridfont.ttf"))
    val mazeContent = new PDPageContentStream(doc, page, AppendMode.APPEND, false)
    var height = 590.0f
    val fontSize = 22
    val mazeString = grid.convertToString
    mazeString.split('\n').foreach { line =>
      mazeContent.beginText()
      mazeContent.setFont(font, fontSize)
      mazeContent.newLineAtOffset(140.0f, height)
      mazeContent.showText(line)
      mazeContent.endText()
      height -= fontSize
    }
    mazeContent.close()
    val byteStream = new ByteArrayOutputStream()
    doc.save(byteStream)
    val bytes = byteStream.toByteArray
    doc.close()
    bytes
  }
}
