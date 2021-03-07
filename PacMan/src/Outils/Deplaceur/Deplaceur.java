package Outils.Deplaceur;
import Class.Entite;
import Outils.Collisionneur.Collisionneur;
import Outils.Collisionneur.CollisionneurSimple;

public abstract class Deplaceur {
    protected int vitesseMvt;
    protected Collisionneur collisionneur;

    public Deplaceur(Collisionneur c){
        collisionneur=c;
    }

    public abstract boolean deplacerHaut(Entite e);
    public abstract boolean deplacerGauche(Entite e);
    public abstract boolean deplacerBas(Entite e);
    public abstract boolean deplacerDroite(Entite e);
}
