import scala.collection.immutable.HashMap

object Day15 extends App {

  case class Ingredient(name:String, capacity:Int, durability:Int, flavor:Int, texture:Int, calories:Int )

  val fileName = "src/main/resources/day15_input.txt"
  val data = scala.io.Source.fromFile(fileName).getLines().buffered
  var maxScore = 0

  val ingredients = data.map( line => {
    val fields = line.split(' ')
    val name = fields(0)
    val capacity = fields(2).substring(0, fields(2).length-1).toInt
    val durability = fields(4).substring(0, fields(4).length-1).toInt
    val flavor = fields(6).substring(0, fields(6).length-1).toInt
    val texture = fields(8).substring(0, fields(8).length-1).toInt
    val calories = fields(10).toInt
    Ingredient(name, capacity, durability, flavor, texture, calories)
  }).toSeq

  def scoreRecipe(recipe:HashMap[Ingredient, Int]) = {
    val calories = Math.max(0, recipe.map(entry => entry._1.calories * entry._2).sum)
    if(calories == 500) {
      val capacity = Math.max(0, recipe.map(entry => entry._1.capacity * entry._2).sum)
      val durability = Math.max(0, recipe.map(entry => entry._1.durability * entry._2).sum)
      val flavor = Math.max(0, recipe.map(entry => entry._1.flavor * entry._2).sum)
      val texture = Math.max(0, recipe.map(entry => entry._1.texture * entry._2).sum)

      val score = capacity * durability * flavor * texture
      maxScore = Math.max(maxScore, score)
    }

  }

  def calc(currentRecipe:HashMap[Ingredient, Int], spaceLeft:Int, ingredientsLeft: Seq[Ingredient]):Unit = {
    if (ingredientsLeft.size == 1) {
      val finishedRecipe = currentRecipe + (ingredientsLeft.head -> spaceLeft)
      scoreRecipe(finishedRecipe)
    } else {
      for(i <- 0 to spaceLeft) {
        val newRecipe = currentRecipe + (ingredientsLeft.head -> i)
        calc(newRecipe, spaceLeft - i, ingredientsLeft.tail )
      }
    }
  }

  calc(HashMap.empty, 100, ingredients)

  println(maxScore)
}
