package service

import domain.Plat

interface CommandeInterface {

    fun ajouterPlat(plat : Plat)
    fun afficherDetailsCommande()
    fun calculerTotal(): Double
    fun appliquerReduction(): Double
}