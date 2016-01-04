
object Day02b extends App {

  val fileName = "src/main/resources/day02_input.txt"
  val data = scala.io.Source.fromFile(fileName).getLines().buffered

  var totalRibbon = 0

  data.foreach( line => {
    val dimensions = line.split('x').map(_.toInt).sorted

    totalRibbon += 2 * (dimensions(0) + dimensions(1))
    totalRibbon += dimensions(0) * dimensions(1) * dimensions(2)


  })

  println(totalRibbon)

}
