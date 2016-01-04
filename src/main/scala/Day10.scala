object Day10 extends App {

  def calc(input:String): String = {
    val result = new StringBuilder()
    var lastChar = '0'
    var lastCharCount = 0
    for (i <- 0 until input.length) {
      val currentChar = input.charAt(i)
      if(lastChar == '0' || lastChar == currentChar) {
        lastCharCount += 1
      } else {
        result.append(lastCharCount)
        result.append(lastChar)

        lastCharCount = 1
      }
      lastChar = currentChar
    }
    result.append(lastCharCount)
    result.append(lastChar)
    result.toString()
  }

  def calcLoop(runs:Int, input:String):String = {
    if(runs == 1) calc(input)
    else calcLoop(runs -1, calc(input))
  }

  println(calcLoop(50, "1113222113").length)

}
