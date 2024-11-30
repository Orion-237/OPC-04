import makala.*


fun greetings(message: String?) {
    if (message != null) {
        val l = 5
        val banner = 2*l + message.length
        println("=".repeat(banner))
        println("-".repeat(l)+message+"-".repeat(l))
        println("=".repeat(banner))
    }
}
fun showMenu(options: List<String>, choiceExpected: Boolean = true): Int?{
    options.forEachIndexed { i, option ->
        println("\t${i+1}. $option")
    }
    var choice: Int? = null
    if (choiceExpected) {
        println("\t${options.size+1}. Back")
        var valid = false
        do{
            print("\n\tEnter a choice:")
            try {
                choice = readln().toInt()
                if (choice !in 1..options.size+1) {
                    throw IllegalArgumentException("Invalid choice")
                }
                valid = true
            } catch (e: Exception) {
                println("Invalid input")
            }
        }while (!valid )
    }
    return if (choice != null && choice<=options.size) choice else null
}
// To implement the behaviour of a menu
fun menu(
    banner: String?,
    optionsUpdate: (() -> MutableList<String>),
    callback: ((Int?) -> Unit)? = null,
    subtitle: String? = null,
    ){
    var choice: Int? = null
    do {
        val options = optionsUpdate()
        greetings(banner)
        if (subtitle != null) {
            println(subtitle)
        }
        if (callback != null){
            choice = showMenu(options)
            // Do what is supposed to be done per choice
            callback(choice)
        }else{
            showMenu(options, false)
        }
    }while(choice != null)
}
fun modifyFoodInMenu(foodMenu: MutableMap<String,Double>){
    menu("Which one to modify?", { foodMenu.map { "${it.key}: ${it.value} XAF" }.toMutableList() },{ choice2 ->
        if (choice2 != null) {
            val oldEntry = foodMenu.entries.elementAt(choice2-1)
            print("Enter the new name(${oldEntry.key}): ")
            var name = readln()
            if (name.isBlank()) {
                name = oldEntry.key
            }
            try {
                print("Enter the new price(${oldEntry.value}): ")
                val pricetxt = readln()
                var price: Double = oldEntry.value
                if (pricetxt.isNotBlank()){
                    price = pricetxt.toDouble()
                    if (price < 0) {
                        throw IllegalArgumentException("Price cannot be negative")
                    }

                    println("${oldEntry.key}->$name")
                    println("${oldEntry.value}->$price")
                }
                // Remove the old element if everything went Smoothly
                foodMenu.remove(oldEntry.key)
                // Add a new element with the new values if everything went Smoothly
                foodMenu[name] = price
            }catch (e: IllegalArgumentException){
                println(e.message)
            }
            catch (e:Exception){
                println("Invalid input")
                println("No change operated")
            }
        }
    })
}
fun removeFoodInMenu(foodMenu: MutableMap<String,Double>){
    menu("Which one to remove?", { foodMenu.map { "${it.key}: ${it.value} XAF" }.toMutableList() }, { choice2 ->
        if (choice2 != null){
            foodMenu.remove(foodMenu.keys.elementAt(choice2-1))
        }
    })
}
fun addFoodInMenu(foodMenu: MutableMap<String,Double>){
    try {
        print("Enter the name: ")
        val name = readln()
        if (name.isBlank()) {throw IllegalArgumentException("Empty name")}
        print("Enter the price: ")
        val price = readln().toDouble()
        if (price < 0) throw IllegalArgumentException("Price cannot be negative")
        // Append the new Item
        foodMenu[name] = price
        println("Dish added in menu ${name}: $price")
    }catch (e: IllegalArgumentException){
        println(e.message)
        println("Nothing added")
    }
    catch (e:Exception){
        println("Invalid input")
        println("Nothing added")
    }
    menu("New Available Menu", { foodMenu.map { "${it.key}: ${it.value} XAF" }.toMutableList()})
}

fun showFoodMenu(foodMenu: MutableMap<String,Double>){
    menu("Available Menu", { foodMenu.map { "${it.key}: ${it.value} XAF" }.toMutableList() })
    menu(null, { mutableListOf("Add", "Remove", "Modify") },{ choice1 ->
        when (choice1) {
            1 -> addFoodInMenu(foodMenu)
            2 -> removeFoodInMenu(foodMenu)
            3 -> modifyFoodInMenu(foodMenu)
        }
    }, "\n\tWhat do you want to do?")
}

fun editOrder(order: Order, foodMenu: MutableMap<String,Double>){
    menu(order.detailsBrief(), { foodMenu.map { "${it.key}: ${it.value} XAF" }.toMutableList() }, { choice ->
        if(choice != null){
            val item = foodMenu.entries.elementAt(choice - 1)
            val newDish = Dish(item.key, item.value)
            print("What Quantity(1): ")
            val txt = readln()
            if (txt.isNotBlank()){
                val qty: Int
                try{
                    qty = txt.toInt()
                    if(qty<1){
                        throw IllegalArgumentException("Invalid quantity")
                    }
                    newDish.qty = qty
                    order.addDish(newDish)
                    println("Dish added: ${newDish.name} (${newDish.qty} portions) - ${newDish.price} XAF per portion")
                }catch (e: NumberFormatException){
                    println("Not a number")
                }catch (e: IllegalArgumentException){
                    println("Quantity can not be less than or equal to 0")
                }
            }else{
                order.addDish(newDish)
                println("Dish added: ${newDish.name} (${newDish.qty} portions) - ${newDish.price} XAF per portion")
            }
        }
        menu(order.detailsBrief(), { order.details()})
    }, "\n\tWhat dish do you want to add?")
}

fun createOrder(orders: MutableList<Order>, foodMenu: MutableMap<String,Double>){
    print("Enter the name of client(No Name): ")
    val name = readln()
    var newOrder: Order
    if (name.isBlank()) {
        newOrder = Order()
    }else{
        newOrder = Order(name)
    }
    orders.add(newOrder)
    editOrder(newOrder, foodMenu)
    menu("Order Management", { orders.map { order -> order.detailsBrief() +"\n"+ order.details().joinToString(separator = "") { "\t" +it + "\n" } }.toMutableList() })
}
fun manageOrders(orders: MutableList<Order>, foodMenu: MutableMap<String, Double>){
    if (orders.isEmpty()){
        menu("Order Management", { mutableListOf("No orders right now") })
    }else{
        menu("Order Management", { orders.map { order -> order.detailsBrief() +"\n"+ order.details().joinToString { "\t" +it + "\n" } }.toMutableList() })
    }
    menu(null, { mutableListOf("Create order", "Remove order", "Modify order") },{ choice1 ->
        when (choice1) {
            1 -> {createOrder(orders, foodMenu)}
            2 -> {}
            3 -> {}
        }
    }, "\n\tWhat do you want to do?")
}
fun main() {
    val foodMenu = mutableMapOf(
        "Chicken DG" to 2000.0,
        "Taro and Yellow soup" to 1500.0,
        "Roasted foodMenu" to 1500.0,
        "Ndole" to 1500.0,
        "Eru" to 1500.0,
        "Okok" to 500.0,
        "Bassa-style Okok" to 500.0,
    )
    val orders: MutableList<Order> = mutableListOf()
    val mainOptions: MutableList<String> = mutableListOf(
        "Orders",
        "Food Menu",
    )
    var foodOrders: MutableList<Order> = mutableListOf()
    menu("📝Welcome to Mami Makala's place😋", { mainOptions }, { choice ->
        when (choice) {
            1 -> {manageOrders(orders, foodMenu)}
            2 -> {
                showFoodMenu(foodMenu)
            }
            else -> {}
        }
    })
}