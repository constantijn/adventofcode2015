object Day20 extends App {

  val myNumber = 36000000
  var houses = new Array[Long](myNumber +1)

  for(elf <- 1 to myNumber / 10 ) {
    var lastHouse = elf
    while(lastHouse <= elf * 50 && lastHouse < houses.length) {
      houses(lastHouse) += elf * 11
      lastHouse += elf
    }
  }

  for(i <- 1 to myNumber) {
    if (houses(i) >= myNumber) {
      println(i + " - " + houses(i))
      System.exit(0)
    }
  }

  println("end")


}
