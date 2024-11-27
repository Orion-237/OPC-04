# Gestion des Commandes au "Tourne Dos de Mami Makala"

## Contexte  
Mami Makala est célèbre pour ses plats locaux et tient un restaurant appelé **Tourne Dos**. Cette application Kotlin permet de gérer les commandes des clients, calculer le montant total et appliquer une réduction si nécessaire.

---

## Objectifs de l'exercice  
1. **Créer une classe `Plat`** avec les attributs suivants :
   - `nom` (String) : nom du plat (ex : "Ndolé", "Poisson braisé")
   - `prix` (Double) : prix unitaire du plat
   - `quantite` (Int) : quantité commandée

2. **Créer une classe `Commande`** avec les méthodes suivantes :
   - `ajouterPlat(plat: Plat)` : ajoute un plat à la commande.
   - `afficherDetailsCommande()` : affiche les détails de chaque plat (nom, quantité, prix total).
   - `calculerTotal(): Double` : calcule et retourne le montant total de la commande.
   - `appliquerReduction(): Double` : applique une réduction de 5 % si le montant total dépasse 15 000 FCFA.

3. **Créer une fonction `main`** pour :
   - Créer une commande.
   - Ajouter plusieurs plats.
   - Afficher les détails de la commande et le montant total avant et après réduction.

---

## Exemple de Sortie Attendue

```plaintext
Plat ajouté : Ndolé (2 portions) - 2000 FCFA l'unité
Plat ajouté : Poisson braisé (1 portion) - 3500 FCFA l'unité
Plat ajouté : Bananes plantains (3 portions) - 500 FCFA l'unité

Détails de la commande :
1. Ndolé - Quantité : 2 - Total : 4000 FCFA
2. Poisson braisé - Quantité : 1 - Total : 3500 FCFA
3. Bananes plantains - Quantité : 3 - Total : 1500 FCFA

Montant total avant réduction : 9000 FCFA

Ajout d'un plat supplémentaire...
Plat ajouté : Mbongo tchobi (2 portions) - 6000 FCFA l'unité

Montant total avant réduction : 21 000 FCFA
Montant total après réduction : 19 950 FCFA
