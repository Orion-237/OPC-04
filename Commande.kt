import java.text.DecimalFormat

class Commande {
    private val plats = mutableListOf<Plat>()
    val format = DecimalFormat("0.#")

    fun ajouterPlat(plat: Plat){
        plats.add(plat)
        println("Plat ajouté : ${plat.nom} (${plat.quantite} ${if (plat.quantite > 1) "portions" else "portion"}) - ${format.format(plat.prix)} FCFA l'unité")
    }

    fun afficherDetailsCommande() {
            println("\nDétails de la commande \uD83D\uDCCB :")
            plats.forEachIndexed { index, plat ->
                val totalPrixPlat = plat.prix * plat.quantite
                println("${index + 1}. ${plat.nom} - Quantité : ${plat.quantite} - Total : ${format.format(totalPrixPlat)} FCFA")
            }
    }

    fun calculerTotal(): Double{
        return plats.sumOf { it.prix * it.quantite }
    }

    fun appliquerReduction(): Double{
        val total = calculerTotal()
        if(total > 15000) {
            val reducedTotal: Double = (total * 0.95)
            println("Montant total après reduction : ${format.format(reducedTotal)} FCFA")
            return reducedTotal
        }
        return 0.0
    }

}