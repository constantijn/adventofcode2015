
object Day16 extends App {

  val fileName = "src/main/resources/day16_input.txt"
  val data = scala.io.Source.fromFile(fileName).getLines().buffered

  val solution = Map(
    "children" -> 3,
    "cats" -> 7,
    "samoyeds" -> 2,
    "pomeranians" -> 3,
    "akitas" -> 0,
    "vizslas" -> 0,
    "goldfish" -> 5,
    "trees" -> 3,
    "cars" -> 2,
    "perfumes" -> 1
  )

  def isValid(prop:String, value:Int):Boolean = {
    prop match {
      case "cats" => value > solution("cats")
      case "trees" => value > solution("trees")
      case "pomeranians" => value < solution("pomeranians")
      case "goldfish" => value < solution("goldfish")
      case key:String => value == solution(key)
    }
  }

  data.foreach( line => {
    val fields = line.split(' ')
    val whichSue = fields(1)
    val prop1 = fields(2).replaceAll(":", "")
    val val1 = fields(3).replaceAll(",", "").toInt
    val prop2 = fields(4).replaceAll(":", "")
    val val2 = fields(5).replaceAll(",", "").toInt
    val prop3 = fields(6).replaceAll(":", "")
    val val3 = fields(7).toInt

    if(isValid(prop1, val1) && isValid(prop2, val2) && isValid(prop3, val3)) {
      println(whichSue)
    }

  })


}
