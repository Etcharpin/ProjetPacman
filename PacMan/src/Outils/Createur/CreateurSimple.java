package Outils.Createur;

import Class.Carte;
import Class.Pacman;
import Class.Fantome;
import Class.Case;
import Class.Fruit;
import Class.BullePoint;
public class CreateurSimple extends Createur{

    @Override
    public Pacman creerPacman(Carte c,double x,double y,String img) {
        Pacman ret=new Pacman(x,y,img);
        c.ajouterEntite(ret);
        return ret;
    }

    @Override
    public Fantome creerFantome(Carte c,double x,double y,String image) {
        Fantome ret=new Fantome(x,y,image);
        c.ajouterEntite(ret);
        return ret;
    }

    @Override
    public Case creerMur(Carte c,double x,double y) {
        Case ret=new Case(x,y,"/image/mur.png");
        c.ajouterEntite(ret);
        return ret;
    }

    @Override
    public Fruit creerFruit(Carte c,double x,double y) {
        Fruit ret = new Fruit(x,y,"/image/big_gum.png");
        c.ajouterEntite(ret);
        return ret;
    }

    @Override
    public BullePoint creerBulle(Carte c,double x,double y) {
        BullePoint ret=new BullePoint(x,y,"/image/small_gum.png");
        c.ajouterEntite(ret);
        return ret;
    }
}
