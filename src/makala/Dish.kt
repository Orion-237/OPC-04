package makala

class Dish(var name: String, var price: Double, var qty: Int = 1) {
    fun details(): String = "$name - Price: $price XAF Qty: $qty Total: ${total()}"
    fun total() = price * qty
}