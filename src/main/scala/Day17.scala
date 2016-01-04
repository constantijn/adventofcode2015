import com.sun.tools.corba.se.idl.Noop

import scala.collection.mutable

object Day17 extends App {

  val fileName = "src/main/resources/day17_input.txt"
  val data = scala.io.Source.fromFile(fileName).getLines().buffered

  case class Container(index:Int, capacity:Int)

  val containers = data.zipWithIndex.map(tuple => Container(tuple._2, tuple._1.toInt)).toArray.toSeq

  var solutions = new mutable.HashSet[Set[Container]]()

  def calc(nogLeft: Int, containersLeft:Seq[Container], usedContainers:Set[Container]):Unit = {
    if (nogLeft == 0) { solutions.add(usedContainers); return }
    if (nogLeft < 0) { return }
    for (i <- 0 until containersLeft.length) {
      val pickedContainer = containersLeft(i)
      val newContainers = containersLeft diff Seq(pickedContainer)
      calc(nogLeft - pickedContainer.capacity, newContainers, usedContainers + pickedContainer)
    }
  }

  calc(150, containers, Set.empty)

  val lengths = new mutable.HashMap[Int, Int]()

  solutions.foreach(set => {
    val size = set.size
    lengths.put(size, lengths.getOrElse(size, 0) +1)
  })

  println(lengths)

}
