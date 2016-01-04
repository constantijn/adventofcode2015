
object Day05 extends App {

  val fileName = "src/main/resources/day05_input.txt"
  val data = scala.io.Source.fromFile(fileName).getLines().buffered

  val vowels = "aeiou".toCharArray
  val naughtyStrings = Seq("ab", "cd", "pq", "xy")
  var niceCount = 0

  data.foreach( line => {
    var vowelCount = 0
    var lastChar = '.'
    var doubleFound = false
    var instaNaughty = false

    line.toCharArray.foreach(char => {
      if (char == lastChar) doubleFound = true
      if (vowels.contains(char)) vowelCount += 1
      if (naughtyStrings.contains("" + lastChar + char)) instaNaughty = true
      lastChar = char
    })

    if (vowelCount >= 3 && doubleFound && !instaNaughty) niceCount += 1

  })

  println(niceCount)

}
