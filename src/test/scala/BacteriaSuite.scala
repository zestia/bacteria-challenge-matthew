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

}
