class BacteriaSuite extends munit.FunSuite {
  test("should return live neighbours next to cells") {
    val actual = Bacteria.getNeighbourCount((1, 2), Set((0, 1), (3, 1)))
    assertEquals(actual, 1)
  }

  test("should return dead neighbours") {
    val expected =
      Set((0, 0), (0, 1), (0, 2),
          (1, 0),         (1, 2),
          (2, 0), (2, 1), (2, 2))
    val actual = Bacteria.findDeadNeighbours(Set((1, 1)))
    assertEquals(expected, actual)
  }

  test("should not return negative values") {
    val expected = Set((0, 1), (1, 0), (1, 1));
    val actual = Bacteria.findDeadNeighbours(Set((0, 0)))
    assertEquals(expected, actual)
  }

  test("should return expected values from test input") {
    val expected = Set[(Int, Int)](
      (2, 1),
      (2, 2),
      (2, 3),
      (1000000002, 1000000001),
      (1000000002, 1000000002),
      (1000000002, 1000000003)
    )
    val test = List[(Int, Int)](
      (1, 2),
      (2, 2),
      (3, 2),
      (1000000001, 1000000002),
      (1000000002, 1000000002),
      (1000000003, 1000000002)
    )
    val actual = Bacteria.runSimulation(test)
    assertEquals(expected, actual)
  }

  test("should print in correct order") {
    val expected = List[(Int, Int)](
      (2, 1),
      (2, 2),
      (2, 3),
      (1000000002, 1000000001),
      (1000000002, 1000000002),
      (1000000002, 1000000003)
    )
    val test = List[(Int, Int)](
      (1000000002, 1000000002),
      (2, 3),
      (2, 1),
      (1000000002, 1000000001),
      (2, 2),
      (1000000002, 1000000003)
    )
    val actual = Bacteria.sortCells(test);

    assertEquals(expected, actual);
  }

  test("RULE 1 - Any live bacteria cell with fewer than two live neighbours dies, as if caused by under-population") {
     val test = List[(Int, Int)]((1, 2))
     val actual = Bacteria.runSimulation(test);
     assertEquals(Set.empty, actual);
  }

  test("RULE 2 - Any live bacteria cell with two neighbours lives on to the next generation.") {
    val test = List[(Int, Int)]((1, 2),(1,3))
    val actual = Bacteria.runSimulation(test);
    assertEquals(Set.empty, actual);
  }

  test("RULE 2 - Any live bacteria cell with three neighbours lives on to the next generation.") {
    val test = List[(Int, Int)]((1, 2),(1,3), (0,3))
    val actual = Bacteria.runSimulation(test);
    assertEquals(true, actual.contains((1,2)));
  }

  test("RULE 3 - Any live bacteria cell with more than three live neighbours dies, as if by overcrowding") {
    val test = List[(Int, Int)]((1, 2),(1,3), (0,3))
    val actual = Bacteria.runSimulation(test);
    assertEquals(true, actual.contains((1,2)));
  }

  test("RULE 4 - Any dead bacteria cell with exactly three live neighbours becomes a live bacteria cell, as if by reproduction") {
    val test = List[(Int, Int)]((1, 2),(1,3), (0,3))
    val expected = Set((1,2), (1,3), (0,2), (0,3))
    val actual = Bacteria.runSimulation(test);
    assertEquals(expected, actual);
  }

  test("RULE 4 - negative values") {
    val test = List[(Int, Int)]((0,0),(0,1),(0,2))
    val expected = Set((1,1), (0,1))
    val actual = Bacteria.runSimulation(test);
    assertEquals(expected, actual)  
    
  }

}
