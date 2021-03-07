package Outils.Createur;
import Class.Carte;
import Class.Pacman;
import Class.Fantome;
import Class.Case;
import Class.Fruit;
import Class.BullePoint;

import java.util.Random;

public abstract class Createur {
    public abstract Pacman creerPacman(Carte c, double x, double y,String image);
    public abstract Fantome creerFantome(Carte c,double x,double y,String image);
    public abstract Case creerMur(Carte c,double x,double y);
    public abstract Fruit creerFruit(Carte c,double x,double y);
    public abstract BullePoint creerBulle(Carte c,double x,double y);
}
