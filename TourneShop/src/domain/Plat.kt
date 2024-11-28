package domain

class Plat {

    var nom : String? = null
    var prix : Double? = null
    var quantite : Int? = null

    constructor(nom: String?, prix: Double?, quantite: Int?) {
        this.nom = nom
        this.prix = prix
        this.quantite = quantite
    }

    constructor(nom: String?, prix: Double?){
        this.nom = nom
        this.prix = prix
    }


}