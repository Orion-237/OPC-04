val orders: MutableList<Order> = mutableListOf()

fun main() {
    var stop = false
    while (!stop) {

        println("\n__________Welcome to MAMI MAKALA___________")
        println("\n1 - Save an order")
        println("2 - See Recent orders")
        println("3 - Exit")
        print("\nWhat do you wish to do ? ")

        val choice = readln().toInt()

        when (choice) {
            1 -> saveOrder()
            2 -> if (orders.isNotEmpty()) displayRecentOrders() else println("\nSorry. No order has been saved in the system yet...")
            3 -> {
                println("Oh no!! You really wanna leave us? (y/n)")
                if ('y' in readln().lowercase()){
                    stop = true
                    println("\nBye Bye")
                }
            }

            else -> println("Invalid selection. Please try again...")
        }
    }
}


fun saveOrder(){
    println("\nSure, let's do this!!")
    val order = Order()
    var name: String
    var price: Double
    var qty: Int

    while (true){
        println("\nEnter the selected dish")

        print("Name: ")
        name = readln()

        print("\nPrice per unit: ")
        price = readln().toDouble()

        print("\nQuantity: ")
        qty = readln().toInt()

        order.addDish(Dish(name, price, qty))

        print("\nDo you wish to add another dish? (y/n) ")
        if ( 'n' in readln().lowercase()) break
    }

    order.displayOrderDetails()
    order.addDiscount()

    orders.add(order)
}

fun displayRecentOrders(){
    print("------The latest orders are:------\n\n")
    for (order in orders){
        println(order.toString())
    }
}
