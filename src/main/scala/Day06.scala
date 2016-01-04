
object Day06 extends App {

  val fileName = "src/main/resources/day06_input.txt"
  val data = scala.io.Source.fromFile(fileName).getLines().buffered

  val grid = new Array[Array[Int]](1000)
  for (i <- 0 to 999 ) grid(i) = new Array[Int](1000)

  data.foreach( line => {
    var words = line.split(" ")
    if (words(0) == "turn") words = words.slice(1, words.length)

    val instruction = words(0)
    val from = words(1).split(",").map(_.toInt)
    val to = words(3).split(",").map(_.toInt)

    for(x <- from(0) to to(0); y <- from(1) to to(1)) {
      instruction match {
        case "on" => grid(x)(y) = grid(x)(y) + 1
        case "off" => grid(x)(y) = Math.max(grid(x)(y) - 1, 0)
        case "toggle" => grid(x)(y) = grid(x)(y) + 2
      }
    }
  })

  println((for(x <- 0 to 999; y <- 0 to 999) yield grid(x)(y)).sum)
}
