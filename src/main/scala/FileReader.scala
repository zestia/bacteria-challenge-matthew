import scala.io.Source
import scala.util.Using

object FileReader {
  def readLines(filename: String): List[(Int, Int)] = {
    val source = Source.fromFile(filename);
    try {
      Using(Source.fromFile(filename)) { source =>
        source
          .getLines()
          .toList
          .map(_.split(","))
          .collect { 
            case Array(x, y) if x != "end" =>
              (
                x.trim.toInt,
                y.trim.toInt
              ) 
          }
      }.getOrElse(
        List.empty
      ) 
    } finally {
      source.close()
    }
  }
}
