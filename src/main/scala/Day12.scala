import scala.util.matching.Regex

object Day12 extends App {

  val fileName = "src/main/resources/day12_input.txt"
  val data = scala.io.Source.fromFile(fileName).mkString

  val regex = new Regex("-?\\d+")

  var result = 0
  regex.findAllMatchIn(data).foreach(matcher => { println(matcher); result += matcher.toString.toInt })

  println(result)

}
