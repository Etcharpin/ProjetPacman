package Outils.Collisionneur;
import Class.Carte;

public abstract class Collisionneur {
    protected Carte c;

    public Collisionneur(Carte c){
        this.c=c;
    }

    public abstract boolean isPassable(double[] coo, Class clas);
}