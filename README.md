
## Mission Décryptage : Casse-tête Enigma

**Alerte Rouge !**  Le destin de Carbon est entre vos mains. Nos agents ont intercepté des messages codés cruciaux provenant d'une machine Enigma ennemie. Heureusement, ils ont réussi à mettre la main sur une machine et l'ont reproduite virtuellement en Java. Votre mission, si vous l'acceptez : décrypter ce messages et révéler les plans ennemis !

Voici le contenu du message : 


## Implémentation de la méthode `turingDecrypt`

**Objectif:**

L'objectif de cet exercice est d'implémenter la méthode `turingDecrypt` qui utilise une technique inspirée du travail d'Alan Turing pour décrypter un message chiffré par Enigma en utilisant un "crib" (un mot ou une phrase supposée être présente dans le texte clair).

**Etapes:**

1. **Comprendre le code existant:**
    - Familiarisez-vous avec les classes `Rotor` et `Reflector` et leur fonctionnement.
    - Analysez les méthodes `encrypt`, `rotorForward`, `rotorBackward`, `rotorRotate` et `reflectorReflect` pour comprendre la logique de chiffrement/déchiffrement de base d'Enigma.

2. **Implémenter la méthode `turingDecrypt`:**
    - La méthode prend en entrée le texte chiffré et le "crib".
    - **Implémenter la suppression des caractères spéciaux:** Supprimez tous les caractères non alphabétiques du texte chiffré et du crib.
    - **Implémenter la recherche des positions possibles du crib:** Trouvez toutes les positions possibles du crib dans le texte chiffré en vérifiant qu'il n'y a pas de chevauchement de lettres identiques entre le crib et le texte chiffré à chaque position.
    - **Implémenter la génération des combinaisons de positions initiales des rotors:** Générez toutes les combinaisons possibles de positions initiales des rotors (chaque rotor peut prendre 26 positions).
    - Pour chaque position possible du crib et pour chaque combinaison de positions de rotors:
        - Déchiffrez le texte en utilisant la méthode `decrypt` existante.
        - **Implémenter la vérification du crib:** Vérifiez si le texte clair déchiffré contient le crib à la position attendue.
    - Si aucune combinaison ne correspond, retournez "Aucune combinaison".

**Conseils:**

- Commencez par implémenter la suppression des caractères spéciaux et la recherche des positions possibles du crib.
- Ensuite, implémentez la génération des combinaisons de positions de rotors.
- Enfin, intégrez le déchiffrement et la vérification du crib pour chaque combinaison.


