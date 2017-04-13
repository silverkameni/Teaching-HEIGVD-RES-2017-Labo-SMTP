# HEIGVD-RES-2017-Labo-SMTP
 
Auteur: Zacharie Nguefack && Silver Kameni
Date: 2017-04-13
[Liens du repos] (https://github.com/silverkameni/Teaching-HEIGVD-RES-2017-Labo-SMTP)

## OBJECTIFS
Le but de ce laboratoire était de Développer une application client SMTP qui joue automatiquement des farces sur des groupes d'adresses e-mail (liste des victimes).
Il existe un fichier que l'utilisateur peut modifier pour définir une liste d'adresses e-mail (victims.utf8).
Un autre fichier qui peut être édité pour spécifier une liste de messages de farce (définir une liste de messages électroniques) délimités l'un de l'autre par la séquence "** @@@ **". Ce courrier sera envoyé à tous les destinataires du groupe, à partir de l'adresse de l'expéditeur du groupe. En d'autres termes, les victimes destinataires seraient amenées à croire que la victime expéditrice leur a envoyé le courrier.
Le troisième fichier peut également être édité par l'utilisateur, il contient l'adresse du serveur, le nombre de groupes qu'il souhaite former dans sa liste d'adresses de courrier électronique et d'autres propriétés de configuration. Dans chaque groupe de victimes, il devrait y avoir 1 émetteur Et au moins 2 destinataires (c'est-à-dire que la taille minimale pour un groupe est de 3).

## Choix 
Dans ce laboratoire, nous avons développé une application client (TCP) en Java. Cette application client utilise l'API Socket pour communiquer avec un serveur SMTP. Notre code est en fait une ** implémentation partielle du protocole SMTP **.
Les objectifs de ce laboratoire sont les suivants:
  1 - Faire des expériences pratiques pour se familiariser avec le ** protocole SMTP **, c'est-à-dire pouvoir utiliser un outil de ligne de commande pour communiquer avec un serveur SMTP ** et envoyer des messages bien formés au serveur, en envoyant des courriels à l'adresse de votre choix.

  2 – Permettre de ** Voir comment il est facile d'envoyer des e-mails forgés **, qui semblent être envoyés par certaines personnes, mais en réalité, sont émis par des utilisateurs malveillants.
  
  3 - Comprendre ce que  signifie ** mettre en œuvre le protocole SMTP ** et pouvoir envoyer des messages électroniques, en travaillant directement sur l'API Socket, il convient de noter que l'utilisation de la bibliothèque SMTP n'était pas autorisée .

  4 – Nous initiez aux notions de ** test double ** et ** mock mock server **, qui sont utiles lors du développement et du test d'une application client-serveur. Le ** serveur simulateur ** simule un serveur smtp et empêche la saturation du réseau par les mails multiples.

  5 – Nous devions ** Concevoir un modèle orienté objet simple ** pour mettre en œuvre les exigences fonctionnelles et spécification de la tâche. La diffusion sur Internet proposée par notre professeur a été d'une grande aide. Vous pouvez parcourir les liens suivants vers les webémissions.
  [Youtube Webcast part 1] (https://youtu.be/ot-bDyqgTtk)
  [Youtube Webcast part 2] (https://youtu.be/Nj34XicS6JM)
  [Youtube Webcast part 3] (https://youtu.be/LoFKsT9Rj10)
  [Youtube Webcast part 4] (https://youtu.be/OrSdRCt_6YQ)


## Description de la mise en œuvre

Nous avons choisi de définir une classe MailSmtp qui joue le rôle de notre client smtp. Cette classe contient une classe interne appelée smtpException qui découle d'une exception dont le rôle consiste à lancer  une exception à chaque fois que nous avons une réponse négative du serveur, par ce que nous enregistrons la dernière commande envoyée par l'utilisateur. Cela détermine pourquoi l'exception a été lancée.

Nous avons un paquetage(package) de configuration dans lequel nous avons une classe nommée ConfigurationManager dont le rôle est de lire le contenu des fichiers de configuration (configuration.properties).

Dans le package model.mail, nous avons 3 classes.
La classe Group est une structure de données qui recevra des instances de classe Person également trouvée dans ce paquet. Les groupes de personnes (victims) sont stockés dans cette structure de données sous la forme d'une liste.
La classe Person définit une personne en mettant à disposition ses informations personnelles (nom, prénom et adresse).
La classe Mail à elle seule possède toutes les informations d'en-tête nécessaires pour envoyer un courrier. Les membres du champ sont initialisés à partir des fichiers de configuration.
Dans le modèle package prank
Nous avons 2 classes.
La classe PrankGenerator qui génère un groupe de personnes et une liste de farces (prank).
La classe Prank contient toutes les informations requises pour effectuer un mail (emetteur et destinataires)


## Installation et utilisation d'un serveur SMTP simplifié

Nous avons utilisé le serveur MockMock. La configuration est disponible [ici] (https://github.com/tweakers-dev/MockMock). Un fichier Jar vous est fourni en téléchargement, vous aurez juste a le dezipper sur votre ordinateur.En procédant ainsi, les fichiers de programmes mockmock sont transférés sur votre ordinateur. Maintenant, nous pouvons exécuter le serveur simulé en ouvrant un shell bash dans le répertoire « target » et en exécutant la commande « java -jar MockMock-1.4.0.one-jar.jar ». Cela va exécuter MockMock sur les ports par défaut 25 (pour SMTP) et 8282 (l'interface Web). 
Une fois qu'il a démarré, vous pouvez utiliser votre navigateur Internet pour naviguer vers http:// localhost:8282.
Pour exécuter MockMock sur un autre port, vous pouvez le démarrer avec le paramètre * -p * pour indiquer le ** port SMTP ** et le * -h * pour indiquer le ** port HTTP **. par exemple
`Java -jar MockMock.jar -p 2525 -h 8080`
Cela exécutera MockMock sur ** SMTP ** port 2525 et le port http 8080.

_ ** Qu'est-ce qu'un serveur Mock? ** _
Il s'agit d'une application qui offre les services d'un ** Serveur SMTP ** sur votre machine locale lorsqu'il est en cours d'exécution. Le serveur s'exécute sur un port local et un service http sur un autre.

