import scala.collection.mutable

object Day03 extends App {

  val fileName = "src/main/resources/day03_input.txt"
  val data = scala.io.Source.fromFile(fileName).mkString.toCharArray.toSeq

  val visits = new mutable.HashSet[(Int,Int)]()

  var x1 = 0
  var y1 = 0
  var x2 = 0
  var y2 = 0

  var robot = false;

  visits.add((x1,y1))

  data.foreach(_ match {
    case '^' => {
      if(!robot) { x1 += 1; visits.add((x1,y1)) }
      else { x2 += 1; visits.add((x2,y2)) }
      robot = !robot
    }
    case 'v' => {
      if(!robot) { x1 -= 1; visits.add((x1,y1)) }
      else { x2 -= 1; visits.add((x2,y2)) }
      robot = !robot
    }
    case '>' => {
      if(!robot) { y1 += 1; visits.add((x1,y1)) }
      else { y2 += 1; visits.add((x2,y2)) }
      robot = !robot
    }
    case '<' => {
      if(!robot) { y1 -= 1; visits.add((x1,y1)) }
      else { y2 -= 1; visits.add((x2,y2)) }
      robot = !robot
    }
    case _ =>
  })

  println(visits.size)

}
