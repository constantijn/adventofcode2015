
object Day02 extends App {

  val fileName = "src/main/resources/day02_input.txt"
  val data = scala.io.Source.fromFile(fileName).getLines().buffered

  var totalPaper = 0

  data.foreach( line => {
    val dimensions = line.split('x').map(_.toInt)
    val xy = dimensions(0) * dimensions(1)
    val yz = dimensions(1) * dimensions(2)
    val xz = dimensions(0) * dimensions(2)
    val extra = Math.min(xy, Math.min(yz, xz))

    totalPaper = totalPaper + 2 * (xy + yz + xz)
    totalPaper = totalPaper + extra

  })

  println(totalPaper)

}
