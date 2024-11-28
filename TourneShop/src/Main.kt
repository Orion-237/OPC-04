import domain.Plat
import service.Commande
import service.CommandeInterface

data class User(val nom : String = "goube", val pwd : String = "12345")

fun optionChoix(){
    println(
        "\t=====================================")
    println("   \t \uD83D\uDE04 Menus     ")
    println("\t=====================================\n")
    println("\n \t1.  Passer une commande. \uD83D\uDECD\uFE0F \n" +
            "\t2.  Afficher les détails de la commande  \uD83D\uDCAF.\n" +
            "\t3.  Afficher le montant total avant et après réduction. \uD83C\uDF7D\uFE0F \n" +
            "\t99- Quitter le programme")
}
fun listePlats(listePlat : MutableList<Plat>){
    println(
        "\t=====================================")
    println("   \t \uD83D\uDE04 Liste de plat disponible     ")
    println("\t=====================================\n")

    var i = 1
    println("\tN | nom plat | prix plat \n" )
    for (plat in listePlat){
        println("\t ${i} | ${plat.nom} | ${plat.prix}")
        i++
    }
}

fun main(){
    // Affichage du message de bienvenue
    println("\n\n" +
            "\n" +
            "\n\t=====================================")
    println("   \t \uD83D\uDC4B Bienvenue a TourneShop     ")
    println("\t=====================================\n")

    //Liste actuelle des plat disponible
    var listePlat = mutableListOf<Plat>(Plat("Eru - fufu",1000.0),
        Plat("Okok - baton",1000.0),
        Plat("Okok - manioc",1100.0),
        Plat("oeuf - baton",1000.0),
        Plat("spagheti - saute",1800.0))

    //il existe un sous menus 45 ou on peut ce connecter avec les identifiant goube:12345
    var user = User()

    var commande : CommandeInterface = Commande()

    //commande.ajouterPlat()
    var continu = 2
    do {
        optionChoix()
        println("\tEntrez votre choix :")
        try{
            var choix = readLine()?.toInt() ?: throw NumberFormatException("\t \uD83D\uDE22 Entrée vide")
            when(choix){
                1 -> {
                    var continu1 = 2
                    do{
                        println(
                            "\t=====================================")
                        println("   \t \uD83D\uDCE5 Choisir un plat    ")
                        println("\t=====================================\n")

                        listePlats(listePlat)
                        println("\tEntrez le numero du plat :")
                        try {
                            var nplat = readLine()?.toInt() ?: throw NumberFormatException("\t \uD83D\uDE22 Entrée vide")
                            println("\tEntrez la quantite :")
                            var qtn = readLine()?.toInt() ?: throw NumberFormatException("\t \uD83D\uDE22 Entrée vide")
                            commande.ajouterPlat(Plat(listePlat[nplat-1].nom,listePlat[nplat-1].prix,qtn))
                            println("Plat ajouté : ${listePlat[nplat-1].nom} ($qtn portions) - ${listePlat[nplat-1].prix} FCFA l'unité \uD83C\uDF5B \n")
                        }catch (e: NumberFormatException){
                            println("\t Erreur : \uD83D\uDE21 Vous devez entrer un choix valide.")
                        }
                        println("Voulez vous ajouter un nouveau plat a la commande : \n" +
                                "2. oui et 1. non")
                        try{
                            continu1 = readLine()?.toInt() ?: throw NumberFormatException("\t \uD83D\uDE22 Entrée vide")
                        }catch (e: NumberFormatException){
                            println("\t Erreur : \uD83D\uDE21 Vous devez entrer un choix valide.")
                        }
                    }while (continu1 == 2)

                }

                2 -> {
                    commande.afficherDetailsCommande()
                    println("Montant total avant réduction : ${commande.calculerTotal()} FCFA \uD83D\uDCB5  \n")
                }

                3 ->{
                    println("Montant total avant réduction : ${commande.calculerTotal()} FCFA \uD83D\uDCB8  \n")
                    println("Montant total après réduction : ${commande.appliquerReduction()} FCFA \uD83C\uDFF7\uFE0F\n")
                }

                45 -> {
                    println("\n\n" +
                            "\n" +
                            "\n\t=====================================")
                    println("   \t \uD83D\uDE0E Bienvenue administrateur de TourneShop     ")
                    println("\t=====================================\n")
                    var continu2 = 2

                    do {
                        println("\tConnectez vous")
                        println("\tEntrez votre nom :")
                        var nom = readLine() ?: throw NumberFormatException("\t \uD83D\uDE22 Entrée vide")
                        println("\tEntrez votre mot de passe :")
                        var pwde = readLine() ?: throw NumberFormatException("\t \uD83D\uDE22 Entrée vide")

                        if(user.nom == nom && user.pwd == pwde  ){
                            println("\tAjuter un plat dans le menu des plats \uD83D\uDCCB ")
                            println("\tEntrez le nombre de plat a ajouter :")
                            try{
                                var nbrplat = readLine()?.toInt() ?: throw NumberFormatException("\t \uD83D\uDE22 Entrée vide")
                                for(i in 0..nbrplat-1){
                                    println("\tNom du plat :")
                                    var nomPlat = readLine() ?: throw NumberFormatException("\t \uD83D\uDE22 Entrée vide")
                                    println("\tprix du plat :")
                                    var prixPlat = readLine()?.toDouble() ?: throw NumberFormatException("\t \uD83D\uDE22 Entrée vide")
                                    listePlat.add(Plat(nomPlat,prixPlat))
                                }

                            }catch (e: NumberFormatException){
                                println("\t Erreur : \uD83D\uDE21 Vous devez entrer un choix valide.")
                            }

                        }else{
                            println("Erreur lors de la connexion")
                        }

                    }while (continu2 == 2)


                }

                99 -> {
                    continu = 99
                }
                else -> {
                    println("\t \uD83D\uDE22 Choix non disponible")
                }

            }
        }catch (e: NumberFormatException){
            println("\t Erreur : \uD83D\uDE21 Vous devez entrer un choix valide.")
        }


    }while (continu == 2)


}