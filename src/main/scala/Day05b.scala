import java.util

object Day05b extends App {

  val fileName = "src/main/resources/day05_input.txt"
  val data = scala.io.Source.fromFile(fileName).getLines().buffered

  var niceCount = 0

  data.foreach( line => {
    var lastChar = '.'
    var prevLastChar = '.'
    val pairs = new util.ArrayList[String]()
    var pairFound = false
    var rule2 = false

    pairs.add("##")


    line.toCharArray.foreach(char => {
      val pair = "" + lastChar + char
      if (pairs.subList(0, pairs.size -1).contains(pair)) pairFound = true
      pairs.add(pair)

      if(prevLastChar == char) rule2 = true

      prevLastChar = lastChar
      lastChar = char
    })

    if(pairFound && rule2) niceCount += 1

  })

  println(niceCount)

}
