// Classe représentant un plat
class Plat(val nom: String, val prix: Double, val quantite: Int) {

    fun afficherDetails(): String {
        return "$nom - Quantité : $quantite - Prix unitaire : $prix FCFA"
    }
}

// Classe représentant une commande
class Commande {
    private val plats = mutableListOf<Plat>()

    // Ajouter un plat à la commande
    fun ajouterPlat(plat: Plat) {
        plats.add(plat)
        println("Plat ajouté : ${plat.nom} (${plat.quantite} portions) - ${plat.prix} FCFA l'unité")
    }

    // Calculer le total de la commande
    fun calculerTotal(): Double {
        return plats.sumOf { it.prix * it.quantite }
    }

    // Appliquer une réduction si le total dépasse 15 000 FCFA
    fun appliquerReduction(): Double {
        val total = calculerTotal()
        return if (total > 15000) total * 0.95 else total
    }

    // Afficher les détails de chaque plat dans la commande
    fun afficherCommande() {
        if (plats.isEmpty()) {
            println("Aucun plat dans la commande.")
        } else {
            println("\nDétails de la commande :")
            plats.forEach { plat ->
                println(plat.afficherDetails())
            }
        }
    }
}

// Fonction principale
fun main() {
    val commande = Commande()
    var choix: Int

    do {
        println("\n================ MENU ===================")
        println("1. Ajouter un plat")
        println("2. Afficher les détails de la commande")
        println("3. Calculer le montant total avant et après réduction")
        println("4. Quitter")
        println("========================================")
        print("Entrez votre choix : ")
        choix = readln().toIntOrNull() ?: 0

        when (choix) {
            1 -> {
                println("Entrez le nom du plat :")
                val nom = readln()
                println("Entrez le prix unitaire du plat (en FCFA) :")
                val prix = readln().toDouble() ?: 0.0
                println("Entrez la quantité :")
                val quantite = readln().toInt() ?: 0

//
            }
            2 -> {
                commande.afficherCommande()
            }
            3 -> {
                val totalAvantReduction = commande.calculerTotal()
                println("\nMontant total avant réduction : ${"%.2f".format(totalAvantReduction)} FCFA")
                val totalApresReduction = commande.appliquerReduction()
                println("Montant total après réduction : ${"%.2f".format(totalApresReduction)} FCFA")
            }
            4 -> println("Merci d'avoir utilisé notre service. À bientôt !")
            else -> println("Choix invalide. Veuillez réessayer.")
        }
    } while (choix != 4)
}
