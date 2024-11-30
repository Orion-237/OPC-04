package makala

class Order(var name: String = "No Name") {
    companion object {
        var i = 0
        fun nextOrderNumber() = ++i
        var discountPercentage = 0.05
    }
    var id = "CMD${nextOrderNumber()}"
    val dishes: MutableList<Dish> = mutableListOf()

    fun details(): MutableList<String> {
        val result: MutableList<String> = mutableListOf()
        dishes.forEach { dish ->
            result += "\t${dish.details()}"
        }
        val totalPrice = totalPrice()
        result += "\tTOTAL_PRICE : $totalPrice XAF"
        if (totalPrice > 15000) {
            result += "\tTOTAL_PRICE after discount: ${discountedPrice()} XAF"
        }
        return result
    }
    fun detailsBrief(): String = "📝Order $id by $name"
    fun addDish(dish: Dish) {
        dishes.add(dish)
    }
    fun totalPrice(): Double = dishes.sumOf { it.total() }
    fun discountedPrice(): Double = discountPercentage*totalPrice()
}