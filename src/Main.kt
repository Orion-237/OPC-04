//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
fun greetings(){
    val message = "📝Welcome to Mami Makala's place😋"
    val l = 5
    val banner = 2*l + message.length
    println("=".repeat(banner))
    println("-".repeat(l)+message+"-".repeat(l))
    println("=".repeat(banner))
}
fun main() {
    greetings()
}