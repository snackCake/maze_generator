import maze.RecursiveBacktrackBuilder
import org.junit.runner._
import org.specs2.mutable._
import org.specs2.runner._

@RunWith(classOf[JUnitRunner])
class RecursiveBacktrackBuilderSpec extends Specification {

  trait WithBuilder {
    val builder = new RecursiveBacktrackBuilder
  }

  "RecursiveBacktrackBuilder" should {

    "leave no cell unvisited" in new WithBuilder {
    }

  }
}
