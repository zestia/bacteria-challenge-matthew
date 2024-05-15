import scala.annotation.switch
def INPUT_FILE = "input.txt"

@main def techTest(): Unit =
  Bacteria.runSimulation(FileReader.readLines(INPUT_FILE))