object Bacteria {
  
  val directions = List(
    (1, 0), (-1, 0), (0, 1), (0, -1),  // right, left, down, up
    (1, 1), (-1, -1), (1, -1), (-1, 1) // diagonals
  )

  // Get all alive neighbours
  def getNeighbourCount(cell: (Int, Int), liveCells: Set[(Int, Int)]): Int =
    directions.count { case (dx, dy) => liveCells.contains((cell._1 + dx, cell._2 + dy)) }

  // Get all surrounding dead cells of alive cells
  def findDeadNeighbours(liveCells: Set[(Int, Int)]): Set[(Int, Int)] =
    liveCells.flatMap { case (x, y) =>
      directions.map { case (dx, dy) => (x + dx, y + dy) }
    }
    .filterNot(liveCells.contains) // Remove duplicates
    .filter { case (x, y) => x >= 0 && y >= 0 } // Filter out negative values

  def simulateStep(liveCells: Set[(Int, Int)]): Set[(Int, Int)] = {
    val deadNeighbours = findDeadNeighbours(liveCells)

    val newAlive = deadNeighbours.filter { cell =>
      getNeighbourCount(cell, liveCells) == 3
    }

    val surviving = liveCells.filter { cell =>
      val count = getNeighbourCount(cell, liveCells)
      count == 2 || count == 3
    }

    surviving ++ newAlive
  }

  def runSimulation(initialCells: List[(Int, Int)]): Set[(Int, Int)] = {
    val liveCells = initialCells.toSet
    val result:Set[(Int, Int)] = simulateStep(liveCells)
    val unit = printCells(result)
    result
  }

  def sortCells(cellsList: List[(Int, Int)]): List[(Int, Int)] = {
    cellsList.sortBy(value => (value._1, value._2))
  }

  def printCells(cells: Set[(Int, Int)]): Unit = {
    val orderedList = sortCells(cells.toList)
    orderedList.foreach { case (x, y) => println(s"$x $y") }
    println("end")
  }
}
