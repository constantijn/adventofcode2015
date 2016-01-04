import java.security.MessageDigest

object Day04 extends App {

  val input = "iwrupvqb"

  val digest = MessageDigest.getInstance("MD5")
  def md5(input:String):String = digest.digest(input.getBytes).map("%02x".format(_)).mkString

  var count = -1

  while(true) {
    count += 1
    if(md5(input + count).startsWith("000000")) { print(count); System.exit(0)}
  }


}
