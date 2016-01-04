
object Day08b extends App {

  val fileName = "src/main/resources/day08_input.txt"
  val data = scala.io.Source.fromFile(fileName).getLines().buffered

  var result = 0

  data.foreach( line => {
    result += 2 // extra quotes before and after
    line.toCharArray.foreach(char => {
      if (char == '\\' || char == '"') result += 1
    })

  })

  println(result)

}
