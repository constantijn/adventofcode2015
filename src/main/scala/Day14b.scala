import java.util

import scala.collection.mutable

object Day14b extends App {

  case class Racer(name:String, speed:Int, duration:Int, rest:Int)

  val fileName = "src/main/resources/day14_input.txt"
  val data = scala.io.Source.fromFile(fileName).getLines().buffered

  val raceLength = 2503
  val points = new mutable.HashMap[String, Int]()

  val racers = data.map( line => {
    val fields = line.split(' ')
    val name = fields(0)
    val speed = fields(3).toInt
    val duration = fields(6).toInt
    val rest = fields(13).toInt
    Racer(name, speed, duration, rest)
  }).toSeq

  def winnerAtSecond(i:Int) = {
    var winners = new util.ArrayList[Racer]()
    var winnerDistance = 0

    racers.foreach(racer => {
      val runAndRest = racer.duration + racer.rest
      val leftoverSeconds = i % runAndRest
      val runSeconds = i / runAndRest * racer.duration + Math.min(leftoverSeconds, racer.duration)
      val distance = runSeconds * racer.speed

      if (distance > winnerDistance) {
        winnerDistance = distance
        winners = new util.ArrayList[Racer]()
        winners.add(racer)
      } else if (distance == winnerDistance) {
        winners.add(racer)
      }
    })

    winners.toArray[Racer](new Array[Racer](0)).toSeq
  }

  for (i <- 1 to raceLength) {
    winnerAtSecond(i).foreach(winner => {
      points.put(winner.name, points.getOrElse(winner.name, 0) + 1)
    })
  }

  println(points)
}
