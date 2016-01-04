
object Day25 extends App {

  def locationToCodeNr(row:Int, col:Int): Int = (1 to col).sum + (col until (col+row-1)).sum

  def codeNrtoCode(codeNr:Int) = {
    var result = 20151125L
    for( i <- 1 until codeNr) result = result * 252533L % 33554393L
    result
  }

  println(codeNrtoCode(locationToCodeNr(2947, 3029)))

}
