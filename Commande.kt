class Commande {


    private val listPlat = mutableListOf<Plat>()

    fun ajouterPlat(plat: Plat){
        listPlat.add(plat)
        println("Plat ajouté : ${plat.nom} (${plat.quantite}) - ${plat.prix} FCFA l'unité")
    }

    fun afficherDetailsCommande(){
        var a= 1
        for (i in listPlat){
            println("$a- ${i.nom} -Quantité : ${i.quantite} - Total : ${i.prix * i.quantite} ")
        }
    }

    fun calculerTotal(): Double{
        var sum = 0.0
        for (i in listPlat){
            sum += i.prix*i.quantite
        }
        return sum
    }

    fun appliquerReduction(): Double{
        var montantreduit = 1.0
        var montant = calculerTotal()
        if (montant > 15000.0){
             montantreduit = montant - montant*0.05
        }
        return montantreduit

    }

    fun verification():Boolean{
        if (calculerTotal() > 15000.0){
            return true
        }
        return false
    }




}