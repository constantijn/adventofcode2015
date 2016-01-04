import scala.collection.mutable

object Day22 extends App {

  case class Spell(name:String, cost:Int)

  val magicMissile = Spell("Magic Missile", 53)
  val drain = Spell("Drain", 73)
  val shield = Spell("Shield", 113)
  val poison = Spell("Poison", 173)
  val recharge = Spell("Recharge", 229)
  val spells = Seq(magicMissile, drain, shield, poison, recharge)

  val bossStartingHp = 51
  val bossDamage = 9

  val spellSequences = new mutable.HashSet[Seq[Spell]]()

  val start = System.currentTimeMillis

  var spellSeq = Seq.empty[Spell]

  for(spell1 <- spells; spell2 <- spells; spell3 <- spells; spell4 <- spells; spell5 <- spells;
      spell6 <- spells; spell7 <- spells; spell8 <- spells; spell9 <- spells; spell10 <- spells) {

//    spellSeq = Seq(spell1)
//    if(canBeatBossWith(spellSeq)) spellSequences += spellSeq
//    spellSeq =  Seq(spell1, spell2)
//    if(canBeatBossWith(spellSeq)) spellSequences += spellSeq
//    spellSeq =  Seq(spell1, spell2, spell3)
//    if(canBeatBossWith(spellSeq)) spellSequences += spellSeq
//    spellSeq =  Seq(spell1, spell2, spell3, spell4)
//    if(canBeatBossWith(spellSeq)) spellSequences += spellSeq
//    spellSeq =  Seq(spell1, spell2, spell3, spell4, spell5)
//    if(canBeatBossWith(spellSeq)) spellSequences += spellSeq
//    spellSeq =  Seq(spell1, spell2, spell3, spell4, spell5, spell6)
//    if(canBeatBossWith(spellSeq)) spellSequences += spellSeq
    spellSeq =  Seq(spell1, spell2, spell3, spell4, spell5, spell6, spell7)
    if(canBeatBossWith(spellSeq)) spellSequences += spellSeq
    spellSeq =  Seq(spell1, spell2, spell3, spell4, spell5, spell6, spell7, spell8)
    if(canBeatBossWith(spellSeq)) spellSequences += spellSeq
    spellSeq =  Seq(spell1, spell2, spell3, spell4, spell5, spell6, spell7, spell8, spell9)
    if(canBeatBossWith(spellSeq)) spellSequences += spellSeq
    spellSeq =  Seq(spell1, spell2, spell3, spell4, spell5, spell6, spell7, spell8, spell9, spell10)
    if(canBeatBossWith(spellSeq)) spellSequences += spellSeq
//    spellSeq =  Seq(spell1, spell2, spell3, spell4, spell5, spell6, spell7, spell8, spell9, spell10, spell11)
//    if(canBeatBossWith(spellSeq)) spellSequences += spellSeq
  }

  val orderedSpellSequences = spellSequences.toSeq.sortBy(_.map(_.cost).sum)

  //println(orderedSpellSequences.map(_.map(_.cost).sum))

  println("Loading: " + ((System.currentTimeMillis - start) / 1000))

  def canBeatBossWith(spells:Seq[Spell]): Boolean = {

    var myHp = 50
    var myMana = 500
    var bossHp = bossStartingHp

    var shieldTurns = 0
    var poisonTurns = 0
    var rechargeTurns = 0

    var spellIndex = 0

    while(spellIndex < spells.length) {
      // my turn

      //hard mode
      myHp -= 1
      if (myHp <= 0) return false
      //end hard mode

      shieldTurns -= 1
      if (poisonTurns > 0) bossHp -= 3
      poisonTurns -= 1
      if (rechargeTurns > 0) myMana += 101
      rechargeTurns -= 1

      if (bossHp <= 0) return true

      val nextSpell = spells(spellIndex)
      spellIndex += 1

      myMana -= nextSpell.cost
      if (myMana < 0) return false // can't cast next spell

      if (nextSpell == magicMissile) { bossHp -= 4 }
      if (nextSpell == drain) { bossHp -= 2; myHp += 2 }
      if (nextSpell == shield) {
        if(shieldTurns > 0) return false
        shieldTurns = 6
      }
      if (nextSpell == poison) {
        if(poisonTurns > 0) return false
        poisonTurns = 6
      }
      if (nextSpell == recharge) {
        if(rechargeTurns > 0) return false
        rechargeTurns = 5
      }

      //boss turn
      shieldTurns -= 1
      if (poisonTurns > 0) bossHp -= 3
      poisonTurns -= 1
      if (rechargeTurns > 0) myMana += 101
      rechargeTurns -= 1

      if (bossHp <= 0) return true

      myHp -= (if(shieldTurns > 0) bossDamage - 7 else bossDamage)
      if (myHp <= 0) return false
    }

    return false
  }

  println(orderedSpellSequences.headOption.map(_.map(_.cost).sum))


}
