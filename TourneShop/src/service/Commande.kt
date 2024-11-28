package service

import domain.Plat

class Commande : CommandeInterface {

    var listePlat = mutableListOf<Plat>()
    var listeConso = mutableListOf<Double>()
    var TotalConso : Double = 0.0
    var reduction  = 5

    override fun ajouterPlat(plat : Plat){
        listePlat.add(plat)
    }

    override fun afficherDetailsCommande(){
        println("Détails de la commande \uD83D\uDCCB :")
        var i : Int = 1
        if(!listePlat.isEmpty()){
            for (plat in listePlat){
                val result = plat.quantite!! * plat.prix!!
                listeConso.add(result)
                println("$i. ${plat.nom} - Quantité : ${plat.quantite} - Total : $result")
            }
        }
    }

    override fun calculerTotal(): Double {
        if(!listeConso.isEmpty()){
            TotalConso = listeConso.sum()
            return TotalConso
        }
        return TotalConso
    }

    override fun appliquerReduction(): Double{
        if(TotalConso > 10000.0){
            return (TotalConso * reduction) / 100
        }
        return TotalConso
    }


}