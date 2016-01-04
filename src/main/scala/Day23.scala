
object Day23 extends App {

  case class Instruction(op:String, register:Char, offset:Int = 0)

  val fileName = "src/main/resources/day23_input.txt"
  val data = scala.io.Source.fromFile(fileName).getLines().buffered.toArray.toSeq

  var regA = 1L
  var regB = 0L

  val instructions = data.map(line => {
    val parts = line.split(" ")
    val op = parts(0)
    if (parts.length == 3) {
      Instruction(op, parts(1).charAt(0), parts(2).toInt)
    } else if ("jmp".equals(op)) {
      Instruction(op, ' ', parts(1).toInt)
    } else {
      Instruction(op, parts(1).charAt(0))
    }
  })

  def execute(instructionIndex:Int): Unit = {
    if(instructionIndex < 0 || instructionIndex >= instructions.length) return
    val instruction = instructions(instructionIndex)
    println(s"a $regA b $regB index ${instructionIndex + 1} instruction $instruction")

    if("inc".equals(instruction.op)) {
      instruction.register match {
        case 'a' => regA += 1
        case 'b' => regB += 1
      }
      return execute(instructionIndex + 1)
    }

    if("hlf".equals(instruction.op)) {
      instruction.register match {
        case 'a' => regA /= 2
        case 'b' => regB /= 2
      }
      return execute(instructionIndex + 1)
    }

    if("tpl".equals(instruction.op)) {
      instruction.register match {
        case 'a' => regA *= 3
        case 'b' => regB *= 3
      }
      return execute(instructionIndex + 1)
    }

    if("jmp".equals(instruction.op)) {
      return execute(instructionIndex + instruction.offset)
    }

    if("jie".equals(instruction.op)) {
      instruction.register match {
        case 'a' => if (regA % 2 == 0) return execute(instructionIndex + instruction.offset)
        case 'b' => if (regB % 2 == 0) return execute(instructionIndex + instruction.offset)
      }
      return execute(instructionIndex + 1)
    }

    if("jio".equals(instruction.op)) {
      instruction.register match {
        case 'a' => if (regA == 1) return execute(instructionIndex + instruction.offset)
        case 'b' => if (regB == 1) return execute(instructionIndex + instruction.offset)
      }
      return execute(instructionIndex + 1)
    }

    throw new IllegalArgumentException("Can't handle: " + instruction)


  }

  execute(0)

  println(s"RegA $regA RegB $regB")

}
