import java.util.Random

object Day19b extends App {

  val fileName = "src/main/resources/day19_input.txt"
  val data = scala.io.Source.fromFile(fileName).getLines().buffered.toArray.toSeq
  val medicine = "CRnCaCaCaSiRnBPTiMgArSiRnSiRnMgArSiRnCaFArTiTiBSiThFYCaFArCaCaSiThCaPBSiThSiThCaCaPTiRnPBSiThRnFArArCaCaSiThCaSiThSiRnMgArCaPTiBPRnFArSiThCaSiRnFArBCaSiRnCaPRnFArPMgYCaFArCaPTiTiTiBPBSiThCaPTiBPBSiRnFArBPBSiRnCaFArBPRnSiRnFArRnSiRnBFArCaFArCaCaCaSiThSiThCaCaPBPTiTiRnFArCaPTiBSiAlArPBCaCaCaCaCaSiRnMgArCaSiThFArThCaSiThCaSiRnCaFYCaSiRnFYFArFArCaSiRnFYFArCaSiRnBPMgArSiThPRnFArCaSiRnFArTiRnSiRnFYFArCaSiRnBFArCaSiRnTiMgArSiThCaSiThCaFArPRnFArSiRnFArTiTiTiTiBCaCaSiRnCaCaFYFArSiThCaPTiBPTiBCaSiThSiRnMgArCaF"

  val dataSize = data.length
  val replacements = new Array[(String, String)](dataSize)
  val random = new Random()

  var dataIndex = 0
  data.foreach(line => {
    val parts = line.split(" => ")
    replacements(dataIndex) = (parts(1), parts(0))
    dataIndex += 1
  })

  def calc(round:Int, molecule:String):Int = {
    if("e".equals(molecule)) return round
    val replacement = replacements(random.nextInt(dataSize))
    val indexes = 0.until(molecule.length).filter(molecule.startsWith(replacement._1, _))
    if (indexes.length > 0) {
      val index = indexes(random.nextInt(indexes.length))
      val (before, after) = molecule.splitAt(index)
      val newMolecule = before + replacement._2 + after.substring(replacement._1.length)
      calc(round +1, newMolecule)
    } else {
      calc(round, molecule)
    }

  }

  println(calc(0, medicine))


}
