package Stub;

import Class.Joueur;
import Class.Classement;
import Class.Carte;
import Outils.Createur.Createur;
import Outils.Createur.CreateurSimple;

import java.util.List;

public class Stub {
    public static Classement creerclassement(){
        Classement ret = new Classement();
        ret.ajouterJoueur(new Joueur("P1",100));
        ret.ajouterJoueur(new Joueur("P2",75));
        ret.ajouterJoueur(new Joueur("P3",99));
        ret.ajouterJoueur(new Joueur("P4",20));

        return ret;
    }

}
