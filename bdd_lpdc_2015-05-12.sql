# ************************************************************
# Sequel Pro SQL dump
# Version 4096
#
# http://www.sequelpro.com/
# http://code.google.com/p/sequel-pro/
#
# Hôte: localhost (MySQL 5.6.22)
# Base de données: bdd_lpdc
# Temps de génération: 2015-05-11 23:41:10 +0000
# ************************************************************


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


# Affichage de la table _possede
# ------------------------------------------------------------

DROP TABLE IF EXISTS `_possede`;

CREATE TABLE `_possede` (
  `id_Produit` int(11) NOT NULL,
  `id_Type_Contenant` int(11) NOT NULL,
  PRIMARY KEY (`id_Produit`,`id_Type_Contenant`),
  KEY `FK__Possede_id_Type_Contenant` (`id_Type_Contenant`),
  CONSTRAINT `FK__Possede_id_Produit` FOREIGN KEY (`id_Produit`) REFERENCES `produit` (`id_Produit`),
  CONSTRAINT `FK__Possede_id_Type_Contenant` FOREIGN KEY (`id_Type_Contenant`) REFERENCES `type_contenant` (`id_Type_Contenant`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `_possede` WRITE;
/*!40000 ALTER TABLE `_possede` DISABLE KEYS */;

INSERT INTO `_possede` (`id_Produit`, `id_Type_Contenant`)
VALUES
	(1,1),
	(3,1),
	(2,2);

/*!40000 ALTER TABLE `_possede` ENABLE KEYS */;
UNLOCK TABLES;


# Affichage de la table commande
# ------------------------------------------------------------

DROP TABLE IF EXISTS `commande`;

CREATE TABLE `commande` (
  `id_Commande` int(11) NOT NULL AUTO_INCREMENT,
  `dateH_Commande` datetime NOT NULL,
  `heure_Livraison` datetime NOT NULL,
  `livraison` varchar(30) DEFAULT NULL,
  `id_User` int(11) NOT NULL,
  `id_Etat` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_Commande`),
  KEY `id_Etat` (`id_Etat`),
  KEY `FK_Commande_id_User` (`id_User`),
  CONSTRAINT `FK_Commande_id_User` FOREIGN KEY (`id_User`) REFERENCES `user` (`id_User`),
  CONSTRAINT `fk_Etat` FOREIGN KEY (`id_Etat`) REFERENCES `etat_commande` (`id_Etat`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `commande` WRITE;
/*!40000 ALTER TABLE `commande` DISABLE KEYS */;

INSERT INTO `commande` (`id_Commande`, `dateH_Commande`, `heure_Livraison`, `livraison`, `id_User`, `id_Etat`)
VALUES
	(2,'2015-05-06 11:46:00','2015-05-06 13:00:00','Livraison',1,2);

/*!40000 ALTER TABLE `commande` ENABLE KEYS */;
UNLOCK TABLES;


# Affichage de la table commande_contient
# ------------------------------------------------------------

DROP TABLE IF EXISTS `commande_contient`;

CREATE TABLE `commande_contient` (
  `nombre_produit` int(11) NOT NULL,
  `id_Commande` int(11) NOT NULL,
  `id_Produit` int(11) NOT NULL,
  PRIMARY KEY (`id_Commande`,`id_Produit`),
  KEY `FK__contient_id_Produit` (`id_Produit`),
  CONSTRAINT `FK__contient_id_Commande` FOREIGN KEY (`id_Commande`) REFERENCES `commande` (`id_Commande`),
  CONSTRAINT `FK__contient_id_Produit` FOREIGN KEY (`id_Produit`) REFERENCES `produit` (`id_Produit`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `commande_contient` WRITE;
/*!40000 ALTER TABLE `commande_contient` DISABLE KEYS */;

INSERT INTO `commande_contient` (`nombre_produit`, `id_Commande`, `id_Produit`)
VALUES
	(1,2,2);

/*!40000 ALTER TABLE `commande_contient` ENABLE KEYS */;
UNLOCK TABLES;


# Affichage de la table etat_commande
# ------------------------------------------------------------

DROP TABLE IF EXISTS `etat_commande`;

CREATE TABLE `etat_commande` (
  `id_Etat` int(11) NOT NULL AUTO_INCREMENT,
  `libelle_Etat` varchar(100) NOT NULL,
  PRIMARY KEY (`id_Etat`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `etat_commande` WRITE;
/*!40000 ALTER TABLE `etat_commande` DISABLE KEYS */;

INSERT INTO `etat_commande` (`id_Etat`, `libelle_Etat`)
VALUES
	(1,'A Valider'),
	(2,'En Cours'),
	(3,'En Attente'),
	(4,'Terminée');

/*!40000 ALTER TABLE `etat_commande` ENABLE KEYS */;
UNLOCK TABLES;


# Affichage de la table fabrique
# ------------------------------------------------------------

DROP TABLE IF EXISTS `fabrique`;

CREATE TABLE `fabrique` (
  `id_Traiteur` int(11) NOT NULL,
  `id_Produit` int(11) NOT NULL,
  PRIMARY KEY (`id_Traiteur`,`id_Produit`),
  KEY `FK_Fabrique_id_Produit` (`id_Produit`),
  CONSTRAINT `FK_Fabrique_id_Produit` FOREIGN KEY (`id_Produit`) REFERENCES `produit` (`id_Produit`),
  CONSTRAINT `FK_Fabrique_id_Traiteur` FOREIGN KEY (`id_Traiteur`) REFERENCES `traiteur` (`id_Traiteur`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `fabrique` WRITE;
/*!40000 ALTER TABLE `fabrique` DISABLE KEYS */;

INSERT INTO `fabrique` (`id_Traiteur`, `id_Produit`)
VALUES
	(1,1),
	(1,3),
	(2,3),
	(1,4),
	(2,4),
	(1,5),
	(2,5),
	(1,6),
	(1,7);

/*!40000 ALTER TABLE `fabrique` ENABLE KEYS */;
UNLOCK TABLES;


# Affichage de la table menu
# ------------------------------------------------------------

DROP TABLE IF EXISTS `menu`;

CREATE TABLE `menu` (
  `nb_personne` int(11) NOT NULL,
  `id_Produit` int(11) NOT NULL,
  `prix` float DEFAULT NULL,
  `designation` varchar(50) DEFAULT NULL,
  `description` text,
  PRIMARY KEY (`id_Produit`),
  CONSTRAINT `FK_Menu_id_Produit` FOREIGN KEY (`id_Produit`) REFERENCES `produit` (`id_Produit`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `menu` WRITE;
/*!40000 ALTER TABLE `menu` DISABLE KEYS */;

INSERT INTO `menu` (`nb_personne`, `id_Produit`, `prix`, `designation`, `description`)
VALUES
	(1,2,18,'Menu du Jour','Préparé à base de produits frais du marché.');

/*!40000 ALTER TABLE `menu` ENABLE KEYS */;
UNLOCK TABLES;


# Affichage de la table menu_contient
# ------------------------------------------------------------

DROP TABLE IF EXISTS `menu_contient`;

CREATE TABLE `menu_contient` (
  `id_Produit_Menu` int(11) NOT NULL,
  `id_Produit_Plat` int(11) NOT NULL,
  PRIMARY KEY (`id_Produit_Menu`,`id_Produit_Plat`),
  KEY `FK_contient_id_Produit_1` (`id_Produit_Plat`),
  CONSTRAINT `FK_contient_id_Produit` FOREIGN KEY (`id_Produit_Menu`) REFERENCES `produit` (`id_Produit`),
  CONSTRAINT `FK_contient_id_Produit_1` FOREIGN KEY (`id_Produit_Plat`) REFERENCES `produit` (`id_Produit`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `menu_contient` WRITE;
/*!40000 ALTER TABLE `menu_contient` DISABLE KEYS */;

INSERT INTO `menu_contient` (`id_Produit_Menu`, `id_Produit_Plat`)
VALUES
	(2,1);

/*!40000 ALTER TABLE `menu_contient` ENABLE KEYS */;
UNLOCK TABLES;


# Affichage de la table panier
# ------------------------------------------------------------

DROP TABLE IF EXISTS `panier`;

CREATE TABLE `panier` (
  `id_Panier` int(11) NOT NULL AUTO_INCREMENT,
  `dateTime` datetime NOT NULL,
  `id_User` int(11) NOT NULL,
  PRIMARY KEY (`id_Panier`),
  KEY `id_User` (`id_User`),
  CONSTRAINT `fk_User` FOREIGN KEY (`id_User`) REFERENCES `user` (`id_User`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `panier` WRITE;
/*!40000 ALTER TABLE `panier` DISABLE KEYS */;

INSERT INTO `panier` (`id_Panier`, `dateTime`, `id_User`)
VALUES
	(1,'2015-05-06 11:46:00',4);

/*!40000 ALTER TABLE `panier` ENABLE KEYS */;
UNLOCK TABLES;


# Affichage de la table panier_contient
# ------------------------------------------------------------

DROP TABLE IF EXISTS `panier_contient`;

CREATE TABLE `panier_contient` (
  `nombre_produit` int(11) NOT NULL,
  `id_Panier` int(11) NOT NULL,
  `id_Produits` int(11) NOT NULL,
  PRIMARY KEY (`id_Panier`,`id_Produits`),
  KEY `id_Panier` (`id_Panier`),
  KEY `id_Produits` (`id_Produits`),
  CONSTRAINT `fk_panier` FOREIGN KEY (`id_Panier`) REFERENCES `panier` (`id_Panier`),
  CONSTRAINT `fk_prodpanier` FOREIGN KEY (`id_Produits`) REFERENCES `produit` (`id_Produit`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `panier_contient` WRITE;
/*!40000 ALTER TABLE `panier_contient` DISABLE KEYS */;

INSERT INTO `panier_contient` (`nombre_produit`, `id_Panier`, `id_Produits`)
VALUES
	(3,1,3),
	(1,1,7);

/*!40000 ALTER TABLE `panier_contient` ENABLE KEYS */;
UNLOCK TABLES;


# Affichage de la table plat
# ------------------------------------------------------------

DROP TABLE IF EXISTS `plat`;

CREATE TABLE `plat` (
  `id_Produit` int(11) NOT NULL,
  `nb_Personne` int(11) NOT NULL,
  `est_Chaud` tinyint(1) NOT NULL,
  `prix` float DEFAULT NULL,
  `designation` varchar(50) DEFAULT NULL,
  `description` text,
  `id_Type_Cuisine` int(11) NOT NULL,
  `poster_path` varchar(250) DEFAULT NULL,
  PRIMARY KEY (`id_Produit`),
  KEY `FK_Plat_id_Type_Cuisine` (`id_Type_Cuisine`),
  CONSTRAINT `FK_Plat_id_Produit` FOREIGN KEY (`id_Produit`) REFERENCES `produit` (`id_Produit`),
  CONSTRAINT `FK_Plat_id_Type_Cuisine` FOREIGN KEY (`id_Type_Cuisine`) REFERENCES `type_cuisine` (`id_Type_Cuisine`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `plat` WRITE;
/*!40000 ALTER TABLE `plat` DISABLE KEYS */;

INSERT INTO `plat` (`id_Produit`, `nb_Personne`, `est_Chaud`, `prix`, `designation`, `description`, `id_Type_Cuisine`, `poster_path`)
VALUES
	(1,1,1,15,'Magret de Canard','Elevé en France',1,'/static/images/magret.jpg'),
	(3,1,0,8.5,'Oeufs en Couille d\'Âne','Oeufs meurette revisité',1,'/static/images/oeufsmeurette.jpg'),
	(4,1,0,15,'Tartare de Canard','Coupé au couteau',1,'/static/images/tartare.jpg'),
	(5,1,1,14,'Cuisse de Canard Confite',NULL,1,'/static/images/cuisse.jpeg'),
	(6,1,0,9.5,'Salade de Magret Fumé','Magret Fumé, Chèvre-Miel & Oeuf poché',1,'/static/images/salade.jpg'),
	(7,1,1,14,'Burger de Canard','Salade, Tomate, Tomme de Savoie, et confiture d\'oignons',1,'/static/images/burger.jpg');

/*!40000 ALTER TABLE `plat` ENABLE KEYS */;
UNLOCK TABLES;


# Affichage de la table produit
# ------------------------------------------------------------

DROP TABLE IF EXISTS `produit`;

CREATE TABLE `produit` (
  `id_Produit` int(11) NOT NULL AUTO_INCREMENT,
  `designation_Produit` varchar(50) NOT NULL,
  `prix` float NOT NULL,
  PRIMARY KEY (`id_Produit`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `produit` WRITE;
/*!40000 ALTER TABLE `produit` DISABLE KEYS */;

INSERT INTO `produit` (`id_Produit`, `designation_Produit`, `prix`)
VALUES
	(1,'',0),
	(2,'',0),
	(3,'',0),
	(4,'',0),
	(5,'',0),
	(6,'',0),
	(7,'',0),
	(8,'',0),
	(9,'',0),
	(10,'',0);

/*!40000 ALTER TABLE `produit` ENABLE KEYS */;
UNLOCK TABLES;


# Affichage de la table traiteur
# ------------------------------------------------------------

DROP TABLE IF EXISTS `traiteur`;

CREATE TABLE `traiteur` (
  `id_Traiteur` int(11) NOT NULL AUTO_INCREMENT,
  `nom_Traiteur` varchar(30) NOT NULL,
  `adresse` varchar(100) NOT NULL,
  `tel_Traiteur` varchar(12) NOT NULL,
  `poster_path` varchar(250) DEFAULT NULL,
  PRIMARY KEY (`id_Traiteur`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `traiteur` WRITE;
/*!40000 ALTER TABLE `traiteur` DISABLE KEYS */;

INSERT INTO `traiteur` (`id_Traiteur`, `nom_Traiteur`, `adresse`, `tel_Traiteur`, `poster_path`)
VALUES
	(1,'Au Vilain Petit Canard','41 rue Franklin 69002 Lyon','0478623477','/static/images/vilaincanard.png'),
	(2,'Pignol','17 rue Émile Zola - 69002 Lyon','04 78 37 39 ','/static/images/pignol.jpg');

/*!40000 ALTER TABLE `traiteur` ENABLE KEYS */;
UNLOCK TABLES;


# Affichage de la table type_contenant
# ------------------------------------------------------------

DROP TABLE IF EXISTS `type_contenant`;

CREATE TABLE `type_contenant` (
  `id_Type_Contenant` int(11) NOT NULL AUTO_INCREMENT,
  `designation_Type_Contenant` varchar(100) NOT NULL,
  PRIMARY KEY (`id_Type_Contenant`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `type_contenant` WRITE;
/*!40000 ALTER TABLE `type_contenant` DISABLE KEYS */;

INSERT INTO `type_contenant` (`id_Type_Contenant`, `designation_Type_Contenant`)
VALUES
	(1,'Boite biodégradable'),
	(2,'Sac en papier biodégradable');

/*!40000 ALTER TABLE `type_contenant` ENABLE KEYS */;
UNLOCK TABLES;


# Affichage de la table type_cuisine
# ------------------------------------------------------------

DROP TABLE IF EXISTS `type_cuisine`;

CREATE TABLE `type_cuisine` (
  `id_Type_Cuisine` int(11) NOT NULL AUTO_INCREMENT,
  `designation_Type_Cuisine` varchar(100) NOT NULL,
  PRIMARY KEY (`id_Type_Cuisine`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `type_cuisine` WRITE;
/*!40000 ALTER TABLE `type_cuisine` DISABLE KEYS */;

INSERT INTO `type_cuisine` (`id_Type_Cuisine`, `designation_Type_Cuisine`)
VALUES
	(1,'Française');

/*!40000 ALTER TABLE `type_cuisine` ENABLE KEYS */;
UNLOCK TABLES;


# Affichage de la table user
# ------------------------------------------------------------

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id_User` int(11) NOT NULL AUTO_INCREMENT,
  `nom` varchar(30) NOT NULL,
  `prenom` varchar(30) NOT NULL,
  `date_Naissance` date NOT NULL,
  `adresse` varchar(100) NOT NULL,
  `telephone` varchar(12) NOT NULL,
  `email` varchar(40) NOT NULL,
  `password` varchar(30) NOT NULL,
  PRIMARY KEY (`id_User`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;

INSERT INTO `user` (`id_User`, `nom`, `prenom`, `date_Naissance`, `adresse`, `telephone`, `email`, `password`)
VALUES
	(1,'COUTURIER','AurÃ©lien','1992-08-26','','0612345678','aurelien.couturier@epsi.fr','coucou'),
	(2,'MURE','SÃ©bastien','1992-08-26','','0612345678','sebastien.mure@epsi.fr','coucou'),
	(3,'SEMPE','Jean-Baptiste','1992-08-26','','0612345678','jeanbaptiste.sempe@epsi.fr','coucou'),
	(4,'THIRY','Nicolas','1992-08-26','','0612345678','nicolas.thiry@epsi.fr','coucou');

/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;



/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
