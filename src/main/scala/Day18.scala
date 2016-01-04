object Day18 extends App {

  val fileName = "src/main/resources/day18_input.txt"
  val data = scala.io.Source.fromFile(fileName).getLines().buffered.toArray.toSeq
  val gridSize = 100
  val edge = gridSize - 1

  def newGrid()= {
    val empty = new Array[Array[Boolean]](gridSize)
    for (i <- 0 until gridSize ) empty(i) = new Array[Boolean](gridSize)
    empty
  }

  var currentGrid = newGrid()
  var nextGrid = newGrid()

  for (i <- 0 until gridSize; j <- 0 until gridSize) {
    currentGrid(i)(j) = data(i).charAt(j) == '#'
  }
  currentGrid(0)(0) = true
  currentGrid(0)(edge) = true
  currentGrid(edge)(0) = true
  currentGrid(edge)(edge) = true

  def updateGrid() = {
    for (i <- 0 until gridSize; j <- 0 until gridSize) {
      var currentLight = currentGrid(i)(j)
      var neighbourCount = 0

      if (i > 0    && j > 0    && currentGrid(i - 1)(j - 1)) neighbourCount += 1
      if (i > 0    && j < edge && currentGrid(i - 1)(j + 1)) neighbourCount += 1
      if (i < edge && j > 0    && currentGrid(i + 1)(j - 1)) neighbourCount += 1
      if (i < edge && j < edge && currentGrid(i + 1)(j + 1)) neighbourCount += 1
      if (i > 0                && currentGrid(i - 1)(j)    ) neighbourCount += 1
      if (i < edge             && currentGrid(i + 1)(j)    ) neighbourCount += 1
      if (            j > 0    && currentGrid(i)    (j - 1)) neighbourCount += 1
      if (            j < edge && currentGrid(i)    (j + 1)) neighbourCount += 1

      nextGrid(i)(j) = neighbourCount == 3 || (neighbourCount == 2 && currentLight)
    }

    nextGrid(0)(0) = true
    nextGrid(0)(edge) = true
    nextGrid(edge)(0) = true
    nextGrid(edge)(edge) = true

    currentGrid = nextGrid
    nextGrid = newGrid()

    //printGrid()
  }

  def printGrid() = {
    for (i <- 0 until gridSize) {
      for (j <- 0 until gridSize) {
        if(currentGrid(i)(j)) print('#') else print('.')
      }
      println()
    }
    println()
  }

  //printGrid()

  (0 until 100).foreach(i => updateGrid())

  var count = 0
  for (i <- 0 until gridSize; j <- 0 until gridSize) {
    if (currentGrid(i)(j)) count += 1
  }

  println(count)


}
