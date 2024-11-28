package makala

class Order(var name: String = "No Name") {
    companion object {
        var i = 0
        fun nextOrderNumber() = ++i
        var discountPercentage = 0.05
    }
    var id = "CMD${nextOrderNumber()}"
    val dishes: MutableList<Dish> = mutableListOf()

    fun details(): String {
        var result: String = "📝Details of order $id by $name:\n"
        dishes.forEachIndexed { i, dish ->
            result += "\t${i+1}. ${dish.details()}\n"
        }
        return result
    }
    fun addDish(dish: Dish) {
        dishes.add(dish)
    }
    fun totalPrice(): Double = dishes.sumOf { it.total() }
    fun discountedPrice(): Double = discountPercentage*discountedPrice()
}