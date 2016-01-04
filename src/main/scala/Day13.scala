import scala.collection.mutable

object Day13 extends App {

  val fileName = "src/main/resources/day13_input.txt"
  def data = scala.io.Source.fromFile(fileName).getLines().buffered

  var peopleMap = new mutable.HashMap[String, Int]()
  def peopleIndex(name:String):Int = {
    peopleMap.getOrElse(name, {
      val newIndex = peopleMap.size
      peopleMap.put(name, newIndex)
      newIndex
    })
  }

  // add self
  peopleIndex("Self")

  data.foreach( line => {
    val parts = line.split(" ")
    peopleIndex(parts(0))
    peopleIndex(parts(10).substring(0, parts(10).length -1))
  })

  val matrix = new Array[Array[Int]](peopleMap.size)
  for (i <- 0 until peopleMap.size ) matrix(i) = new Array[Int](peopleMap.size)

  data.foreach( line => {
    val parts = line.split(" ")
    val fromIndex = peopleIndex(parts(0))
    val toIndex = peopleIndex(parts(10).substring(0, parts(10).length -1))

    matrix(fromIndex)(toIndex) = parts(3).toInt
    if (parts(2).equals("lose")) matrix(fromIndex)(toIndex) = -matrix(fromIndex)(toIndex)

  })

  var maxHappy = Integer.MIN_VALUE

  def calculateRoute(startIndex:Int, currentDistance:Int, currentIndex:Int, remainingDestinations:Seq[Int]):Unit = {
    if (remainingDestinations.length == 1) {
      val lastIndex = remainingDestinations(0)
      val result = currentDistance + matrix(currentIndex)(lastIndex) + matrix(lastIndex)(currentIndex) + matrix(lastIndex)(startIndex) + matrix(startIndex)(lastIndex)
      maxHappy = Math.max(maxHappy, result)
    } else {
      for(i <- 0 until remainingDestinations.length) {
        val nextIndex = remainingDestinations(i)
        calculateRoute(startIndex, currentDistance + matrix(currentIndex)(nextIndex) + matrix(nextIndex)(currentIndex), nextIndex, remainingDestinations.filter(_ != nextIndex))
      }
    }
  }

  for(i <- 0 until peopleMap.size) {
    calculateRoute(i, 0, i, (0 until peopleMap.size).filter(_ != i) )
  }

  println(maxHappy)

}
