fun main() {
 val commande = Commande()

 println("Bienvenue dans le système de gestion de commande de mami makala !!!!! 🍽️")
 var continuer = true

 while (continuer) {
  println("\nEntrez les détails du plat :")

  print("Nom du plat : ")
  val nom = readln()

  print("Prix unitaire (FCFA) : ")
  val prix = readln().toDouble()

  print("Quantité : ")
  val quantite = readln().toInt()


  val plat = Plat(nom, prix, quantite)
  commande.ajouterPlat(plat)

  println("\nVoulez-vous ajouter un autre plat ? (oui/non)")
  val reponse = readln().trim().lowercase()
  if (reponse != "oui") {
   continuer = false
  }
 }


 println("\nDétails de la commande 📋 :")
 commande.afficherDetailsCommande()
 val totalAvantReduction = commande.calculerTotal()
 val totalApresReduction = commande.appliquerReduction()

 if (commande.verification()) {
  println("Montant total après réduction : ${totalApresReduction.toInt()} FCFA 🏷️")
 } else {
  println("Aucune réduction appliquée. Total à payer : ${totalAvantReduction.toInt()} FCFA 💵")
 }
}
