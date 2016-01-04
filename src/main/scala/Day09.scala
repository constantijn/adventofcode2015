import scala.collection.mutable

object Day09 extends App {

  val fileName = "src/main/resources/day09_input.txt"
  def data = scala.io.Source.fromFile(fileName).getLines().buffered

  var cityMap = new mutable.HashMap[String, Int]()
  def cityIndex(city:String):Int = {
    cityMap.getOrElse(city, {
      val newIndex = cityMap.size
      cityMap.put(city, newIndex)
      newIndex
    })
  }

  data.foreach( line => {
    val parts = line.split(" ")
    cityIndex(parts(0))
    cityIndex(parts(2))
  })

  val matrix = new Array[Array[Int]](cityMap.size)
  for (i <- 0 until cityMap.size ) matrix(i) = new Array[Int](cityMap.size)

  data.foreach( line => {
    val parts = line.split(" ")
    val city1 = cityIndex(parts(0))
    val city2 = cityIndex(parts(2))
    matrix(city1)(city2) = parts(4).toInt
    matrix(city2)(city1) = parts(4).toInt
  })

  var shortestRoute = Integer.MIN_VALUE

  def calculateRoute(currentDistance:Int, currentCity:Int, remainingDestinations:Seq[Int]):Unit = {
    if (remainingDestinations.length == 1) {
      shortestRoute = Math.max(shortestRoute, currentDistance + matrix(currentCity)(remainingDestinations(0)))
    } else {
      for(i <- 0 until remainingDestinations.length) {
        val nextCity = remainingDestinations(i)
        calculateRoute(currentDistance + matrix(currentCity)(nextCity), nextCity, remainingDestinations.filter(_ != nextCity))
      }
    }
  }

  for(i <- 0 until cityMap.size) {
    calculateRoute(0, i, (0 until cityMap.size).filter(_ != i) )
  }

  println(shortestRoute)

}
