
object Day01 extends App {

  val fileName = "src/main/resources/day01_input.txt"
  val data = scala.io.Source.fromFile(fileName).mkString.toCharArray.toSeq

  var position = 0
  var currentFloor = 0

  data.foreach( char => {
    position = position + 1
    if( char == '(') {
      currentFloor = currentFloor + 1
    } else {
      currentFloor = currentFloor - 1
      if(currentFloor < 0) {
        println(position)
        System.exit(0)
      }

    }

  })

}
