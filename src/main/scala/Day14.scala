
object Day14 extends App {

  val fileName = "src/main/resources/day14_input.txt"
  val data = scala.io.Source.fromFile(fileName).getLines().buffered

  val raceLength = 2503
  var maxDistance = 0

  data.foreach( line => {
    val fields = line.split(' ')
    val name = fields(0)
    val speed = fields(3).toInt
    val duration = fields(6).toInt
    val rest = fields(13).toInt

    val runAndRest = duration + rest
    val leftoverSeconds = raceLength % runAndRest
    val runSeconds = raceLength / runAndRest * duration + Math.min(leftoverSeconds, duration)
    val distance = runSeconds * speed

    maxDistance = Math.max(maxDistance, distance)

    println(s"$name ran $distance")
  })

  println(s"Max: $maxDistance")

}
