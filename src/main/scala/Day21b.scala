import scala.collection.mutable

object Day21b extends App {

  val bossStartingHp = 100
  val bossDamage = 8
  val bossArmor = 2

  case class Item(name:String, cost:Int, damage:Int, armor:Int)

  val weaponsFile = "src/main/resources/day21_weapons.txt"
  val weapons = scala.io.Source.fromFile(weaponsFile).getLines().buffered.toArray.toSeq.map(line => {
    val parts = line.split("\\W+")
    Item(parts(0), parts(1).toInt, parts(2).toInt, parts(3).toInt)
  })

  val armorFile = "src/main/resources/day21_armor.txt"
  val armors = scala.io.Source.fromFile(armorFile).getLines().buffered.toArray.toSeq.map(line => {
    val parts = line.split("\\W+")
    Item(parts(0), parts(1).toInt, parts(2).toInt, parts(3).toInt)
  })

  val ringsFile = "src/main/resources/day21_rings.txt"
  val rings = scala.io.Source.fromFile(ringsFile).getLines().buffered.toArray.toSeq.map(line => {
    val parts = line.split("\\W+")
    Item(parts(0), parts(1).toInt, parts(2).toInt, parts(3).toInt)
  })

  val outfits = mutable.HashSet[Set[Item]]()

  for (weapon <- weapons) {
    outfits.add(Set(weapon))
  }

  for (weapon <- weapons; armor <- armors) {
    outfits.add(Set(weapon, armor))
  }

  for (weapon <- weapons; armor <- armors; ring1 <- rings) {
    outfits.add(Set(weapon, armor, ring1))
  }

  for (weapon <- weapons; armor <- armors; ring1 <- rings; ring2 <- rings) {
    outfits.add(Set(weapon, armor, ring1, ring2))
  }

  for (weapon <- weapons; ring1 <- rings) {
    outfits.add(Set(weapon, ring1))
  }

  for (weapon <- weapons; ring1 <- rings; ring2 <- rings) {
    outfits.add(Set(weapon, ring1, ring2))
  }

  val costSortedOutfits = outfits.toSeq.map(_.toSeq).sortBy(_.map(_.cost).sum).reverse

  def looseToBossWith(outfit:Seq[Item]): Boolean = {
    val myDamage = outfit.map(_.damage).sum
    val myArmor = outfit.map(_.armor).sum

    var myHp = 100
    var bossHp = bossStartingHp

    while(true) {
      bossHp -= Math.max(1, myDamage - bossArmor)
      if (bossHp <= 0) return false
      myHp -= Math.max(1, bossDamage - myArmor)
      if (myHp <= 0) return true
    }

    throw new RuntimeException("shouldn't happen")
  }

  for(outfit <- costSortedOutfits) {
    if(looseToBossWith(outfit)) {
      println(outfit)
      println(outfit.map(_.cost).sum)
      System.exit(0)
    }
  }

}
