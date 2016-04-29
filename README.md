# projetTAGL
# Fonctionnalités fournies
 Tout d'abord, nous avons implémenté un serveur qui a pour but de stocker la table de hachage. La communication entre le client et le serveur se fait grâce à une socket. 
 Une fois le serveur lancé, plusieurs clients peuvent se connecter en même temps pour travailler sur leurs données.
 Les données manipulées dans la table de hachage peuvent être de différents types (double, int, string...). C'est le serveur qui s'occupe de parser au moment où le client entre sa ligne de commande.
 
# Fonctionnalités validées par les tests unitaires
Nous avons effectués des tests unitaires sur chacune des fonctions de hachage. Ces tests ont été faits sur des types différents à savoir double, int et String.
Pour le côté connexion client/serveur nous n'avons pas effecué de tests unitaires.

# Manuel utilisateur
 Lancer le serveur sur un terminal.
 Sur un autre  terminal, lancer une interface client.
 Pour manipuler la table de hachage, il suffit d'entrer les ligne de commande directement sur le temrinal client.
 La syntaxe est la suivante : nomFonction [argument1] [argument2] ... [argument n]
 L'utilisateur a la possibilité de se servir des fonctions suivantes :
 
 ## Partie une valeur par clé :
 
 set cle valeur
 Ajoute la valeur associée à la clé.
 Si la clé existait déja, l'ancienne valeur sera écrasée.
 
 get cle
 Affiche la valeur associée à la clé donnée.
 Si la clé n'existe pas, un message s'affiche pour le préciser.
 
 del cle
 Supprime de la table de hachage la liste associée à la clé donnée.
 
 incr cle
 Incrémente la valeur associée à la clé.
 Si la clé n'est pas un nombre ou que la clé n'existe pas l'utilisateur est prévenu par un message.
 
 ##Partie une liste par clé :
 
 rPush cle valeur
 Stocke la valeur grâce à sa clé. 
 Si la clé existe déja dans la table de hachage, la valeur associée sera ajoutée à la fin de la liste correspondant à la clé.
 Sinon une nouvelle liste sera créé pour cette clé.
 
 lPush cle valeur
 Stocke la valeur grâce à sa clé. 
 Si la clé existe déja dans la table de hachage, la valeur associée sera ajoutée au debut de la liste correspondant à la clé.
 Sinon une nouvelle liste sera créé pour cette clé.
 
 rPop cle
 Supprime et affiche le dernier élément de la liste associée à la clé.
 Si la clé n'existe pas, un message est affiché.
 
 lPop cle
 Supprime et affiche le premier élément de la liste associée à la clé.
 Si la clé n'existe pas, un message est affiché.
 
 lRange cle debut fin
 Affiche la liste des valeurs pour une clé donnée entre les indices debut et fin de la liste associée à cette clé.
 
 lLen cle
 Affiche la taille de la liste associée à la clé donnée.
 
 En cas d'erreur de saisie de la part de l'utilisateur, un message avertit l'utilisateur.
 
 
# Feedback sur Travis +( commentaires suggestions projet)

Projet de TAGL 2016
