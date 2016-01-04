import java.util

object Day11 extends App {

  def nextPassword(currentPassword:String):String = {
    require(currentPassword.length == 8)
    val chars = currentPassword.toCharArray
    chars(7) = (chars(7).toInt + 1).toChar

    for(i <- (0 to 7).reverse) {
      if (chars(i) == '{') { // the char after z is {
        chars(i) = 'a'
        chars(i - 1) = (chars(i - 1).toInt + 1).toChar
      }
    }

    chars.mkString
  }

  val badChars = Seq('i', 'o', 'l')

  def isValid(password:String) = {
    var rule1 = false
    var rule2 = true
    var rule3 = false
    var firstDoubleFoundAtIndex = Int.MaxValue

    val pairs = new util.ArrayList[String]()
    val chars = password.toCharArray
    var lastChar = '.'
    var prevLastChar = '.'

    for(i <- 0 to 7) {
      val currentChar = chars(i)

      if (currentChar.toInt == lastChar.toInt + 1 && currentChar == prevLastChar.toInt + 2) rule1 = true
      if (badChars.contains(currentChar)) rule2 = false
      if (currentChar == lastChar && i - firstDoubleFoundAtIndex >= 2)  rule3 = true
      if (lastChar == currentChar) firstDoubleFoundAtIndex = Math.min(i, firstDoubleFoundAtIndex)

      prevLastChar = lastChar
      lastChar = currentChar
    }

    rule1 && rule2 && rule3

  }

  def nextValidPassword(currentPassword:String): String = {
    var result = nextPassword(currentPassword)
    while(!isValid(result)) result = nextPassword(result)
    result
  }


  println(nextValidPassword(nextValidPassword("hxbxwxba")))

}
