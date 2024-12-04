import java.text.DecimalFormat

fun main() {
    val commande = Commande()
    val format = DecimalFormat("0.#")

    commande.ajouterPlat(Plat("Eru", 1000.0, 3))
    commande.ajouterPlat(Plat("Poulet pané", 3500.0, 1))
    commande.ajouterPlat(Plat("Koki", 2500.0, 2))

    commande.afficherDetailsCommande()

    println("\nMontant total avant reduction : ${format.format(commande.calculerTotal())} FCFA\n")
    commande.appliquerReduction()

    println("Ajout d'un plat supplémentaire...")

    commande.ajouterPlat(Plat("Mbongo Porc", 5000.0, 2))

    println("\nMontant total avant reduction : ${format.format(commande.calculerTotal())} FCFA")
    commande.appliquerReduction()
}