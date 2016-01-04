object Day24 extends App {

  val fileName = "src/main/resources/day24_input.txt"
  val data = scala.io.Source.fromFile(fileName).getLines.buffered.map(_.toInt).toSeq

  def minQE(bagSize: Int, numPresents:Int) = {
    data.combinations(numPresents)
      .filter(_.sum == bagSize)
      .map(_.map(_.toLong))
      .map(_.product)
      .min
  }

  // manually increase the numPresents from 1 until it finds a solution
  println("3 split: " + minQE(data.sum / 3, 6))
  println("4 split: " + minQE(data.sum / 4, 4))
}
