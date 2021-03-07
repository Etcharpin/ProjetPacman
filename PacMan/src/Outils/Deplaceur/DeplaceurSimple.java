package Outils.Deplaceur;
import Class.Entite;
import Outils.Collisionneur.Collisionneur;

public class DeplaceurSimple extends Deplaceur {


    public DeplaceurSimple(Collisionneur c) {
        super(c);
        vitesseMvt=20;
    }

    @Override
    public boolean deplacerHaut(Entite e) {
        double[] coo={e.getX(), e.getY()-vitesseMvt};
        Class c=e.getClass();
        if(collisionneur.isPassable(coo,c)){
            e.setY(coo[1]);
            return true;
        }
        return false;
    }

    @Override
    public boolean deplacerGauche(Entite e) {
        double[] coo={e.getX()-vitesseMvt,e.getY()};
        Class c=e.getClass();
        if(collisionneur.isPassable(coo,c)) {
            e.setX(coo[0]);
            return true;
        }
        return false;
    }

    @Override
    public boolean deplacerBas(Entite e) {
        double[] coo={e.getX(), e.getY()+vitesseMvt};
        Class c=e.getClass();
        if(collisionneur.isPassable(coo,c)){
            e.setY(coo[1]);
            return true;
        }
        return false;
    }

    @Override
    public boolean deplacerDroite(Entite e) {
        double[] coo = {e.getX() + vitesseMvt, e.getY()};
        Class c=e.getClass();
        if (collisionneur.isPassable(coo,c)) {
            e.setX(coo[0]);
            return true;
        }
        return false;
    }
}
