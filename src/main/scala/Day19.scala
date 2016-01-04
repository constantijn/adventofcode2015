import scala.collection.mutable

object Day19 extends App {

  val fileName = "src/main/resources/day19_input.txt"
  val data = scala.io.Source.fromFile(fileName).getLines().buffered.toArray.toSeq
  val medicine = "CRnCaCaCaSiRnBPTiMgArSiRnSiRnMgArSiRnCaFArTiTiBSiThFYCaFArCaCaSiThCaPBSiThSiThCaCaPTiRnPBSiThRnFArArCaCaSiThCaSiThSiRnMgArCaPTiBPRnFArSiThCaSiRnFArBCaSiRnCaPRnFArPMgYCaFArCaPTiTiTiBPBSiThCaPTiBPBSiRnFArBPBSiRnCaFArBPRnSiRnFArRnSiRnBFArCaFArCaCaCaSiThSiThCaCaPBPTiTiRnFArCaPTiBSiAlArPBCaCaCaCaCaSiRnMgArCaSiThFArThCaSiThCaSiRnCaFYCaSiRnFYFArFArCaSiRnFYFArCaSiRnBPMgArSiThPRnFArCaSiRnFArTiRnSiRnFYFArCaSiRnBFArCaSiRnTiMgArSiThCaSiThCaFArPRnFArSiRnFArTiTiTiTiBCaCaSiRnCaCaFYFArSiThCaPTiBPTiBCaSiThSiRnMgArCaF"
  val molecules = new mutable.HashSet[String]()
  val replacements = new mutable.HashSet[(String, String)]()

  data.foreach(line => {
    val parts = line.split(" => ")
    replacements.add(parts(0), parts(1))
  })

  replacements.foreach(tuple => {
    var index = 0
    while(index >= 0) {
      index = medicine.indexOf(tuple._1, index+1)
      if(index > -1) {
        val (before, after) = medicine.splitAt(index)
        molecules.add(before + tuple._2 + after.substring(tuple._1.length))
      }
    }
  })

  println(molecules.size)

}
