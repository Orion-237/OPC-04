class Order {
    private val dishes: MutableList<Dish> = mutableListOf()
    private var total: Double = 0.0
    private val id = hashCode().toString(36).uppercase()

    fun addDish(dish: Dish){
        println("\nNew dish added: ${dish.name} - Quantity: x${dish.qty} - Price: ${dish.price}frs per unit")
        dishes.add(dish)
    }

    fun displayOrderDetails(){
        println("Order  details:")
        for ((i, dish) in dishes.withIndex()){
            println("${i+1}. ${dish.name} - Quantity: ${dish.qty} - Total: ${dish.qty*dish.price}frs")
        }
    }

    fun calculateTotal(): Double{
        total =  dishes.sumOf { dish: Dish -> dish.price*dish.qty }
        return total
    }

    fun addDiscount(){
        val previous = calculateTotal()
        println("\nThe total before discount is $previous frs")

        total = if (previous > 15000) previous - 0.05*previous else previous
        println("\nThe total after discount is $total frs")
    }

    override fun toString(): String {
        return "Order (id = $id): Number of dishes = ${dishes.size}\t" + "Total: $total frs"
    }
}