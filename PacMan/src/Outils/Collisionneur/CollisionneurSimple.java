package Outils.Collisionneur;
import Class.Carte;
import Class.Entite;
import Class.Case;
import Class.Pacman;
import Class.BullePoint;
import Class.Fruit;
import Class.Fantome;
import Launch.Step;
import Outils.Manager;
import javafx.application.Platform;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CollisionneurSimple extends Collisionneur{

    private int i;
    public CollisionneurSimple(Carte c) {
        super(c);
    }

    public boolean isPassable(double[] coordonnees, Class clas) {
        Entite e= new Pacman(coordonnees[0], coordonnees[1],"");
        if(coordonnees[0]<0 || coordonnees[0]>800 || coordonnees[1]<0 || coordonnees[1]>600){
            return false;
        }
        for (Entite e2: c.getLesEntites()){
            if(e2.getClass() == Case.class){
                if(testCollision(e2,e)){
                    return false;
                }
            }
        }
        if(clas == Pacman.class) {
            isPresentPac(e.getX(), e.getY());
        }
        if(clas==Fantome.class && testCollision(e, Step.getManager().getPacmanEncour())){
            if(!Step.getManager().isOver() && !Step.getManager().getPacmanEncour().isBuff()){
                Step.finJeu();
            }
        }
        return true;
    }

    public void isPresentPac(double x,double y){
        Iterator<Entite> literateur = c.getLesEntites().iterator();
        while (literateur.hasNext()){
            Entite e = literateur.next();
            if(e.getX() == x && e.getY() == y){
                if(e.getClass() == BullePoint.class || e.getClass() == Fruit.class) {
                    if(e.getClass() == Fruit.class && e.isVisible()){
                        c.getPacMan().setBuff(true);
                        for(Entite en : c.getLesEntites()){
                            if(en.getClass() == Fantome.class){
                                ((Fantome) en).setMangeable(true);
                            }
                        }
                        if(e.isVisible()) {
                            c.moinsBulle();
                        }
                        e.setVisible(false);
                        break;
                    }
                    else {
                        if(e.isVisible()) {
                            c.moinsBulle();
                        }
                        e.setVisible(false);

                        break;
                    }
                }
                if(e.getClass() == Fantome.class){
                    if(((Fantome) e).isMangeable()){
                        c.mangerFantom((Fantome) e);
                   }
                    else{
                        Step.finJeu();
                        break;
                   }

                }
            }
        }
    }

    private boolean testCollision(Entite e1, Entite e2){
        if (e1.getX() < e2.getX() + e2.getMaxHeight() &&
                e1.getX() + e1.getMaxHeight() > e2.getX() &&
                e1.getY() < e2.getY() + e2.getMaxHeight()&&
                e1.getMaxHeight()+ e1.getY() > e2.getY()){
            return true;
        }
        return false;
    }

}

