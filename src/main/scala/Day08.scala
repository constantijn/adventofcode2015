
object Day08 extends App {

  val fileName = "src/main/resources/day08_input.txt"
  val data = scala.io.Source.fromFile(fileName).getLines().buffered

  var totalChars = 0
  var totalMem = 0

  data.foreach( line => {
    totalChars += line.length
    var parsed = line.trim.substring(1, line.length-1)
    parsed = parsed.replaceAll("\\\\\"","#")
    parsed = parsed.replaceAll("\\\\\\\\","#")
    parsed = parsed.replaceAll("\\\\x[0-9abcdef][0-9abcdef]","#")
    totalMem += parsed.length

  })

  println(totalChars - totalMem)

}
