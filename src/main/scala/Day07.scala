import scala.collection.mutable

// NOTE: did 7B by adding an extra line to the input
object Day07 extends App {

  val fileName = "src/main/resources/day07_input.txt"
  val data = scala.io.Source.fromFile(fileName).getLines().buffered

  val MAX = 65536
  val wireMapping = new mutable.HashMap[String, String]()

  data.foreach( line => {
    val parts = line.split(" -> ")
    wireMapping.put(parts(1), parts(0))
  })

  def calc(wire:String):Int = {
    try {
      wire.toInt
    } catch {
      case e:NumberFormatException => {
        println("calculating wire [" + wire + "] with expression [" + wireMapping.get(wire) + "]")
        val instructionParts = wireMapping.get(wire).get.split(" ")
        instructionParts.length match {
          case 1 => {
            try {
              instructionParts(0).toInt
            } catch {
              case e: NumberFormatException => calc(instructionParts(0))
            }
          }
          case 2 => {
            val result = MAX - 1 - calc(instructionParts(1))
            wireMapping.put(wire, result.toString)
            result
          }
          case 3 => {
            val operator = instructionParts(1)
            val operand1 = instructionParts(0)
            val operand2 = instructionParts(2)

            val result = operator match {
              case "RSHIFT" => calc(operand1) >> operand2.toInt % MAX
              case "LSHIFT" => calc(operand1) << operand2.toInt % MAX
              case "AND" => calc(operand1) & calc(operand2) % MAX
              case "OR" => calc(operand1) | calc(operand2) % MAX
            }
            wireMapping.put(wire, result.toString)
            result
          }
        }
      }
    }
  }

  println(calc("a"))
}
