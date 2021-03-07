package Class;

import Outils.GestionFichier.LecteurSimple;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;


public class Carte {
    private int nbBulles;
    private int nbreinit;

    public int getNbBulles() {
        return nbBulles;
    }

    public void plusBulle(){
        nbBulles = nbBulles +1;
        nbreinit = nbreinit +1;
    }

    public void reinitBulle (){
        nbBulles = nbreinit;
    }
    public void moinsBulle(){
        nbBulles = nbBulles -1;
    }

    private ObservableList<Entite> lesEntites = FXCollections.observableArrayList();

    public void ajouterEntite(Entite e){
        lesEntites.add(e);
    }
    private Pacman pacman;

    public ObservableList<Entite> getLesEntites(){
        return FXCollections.unmodifiableObservableList(lesEntites);
    }

    public void supprimerEntité(Entite e){
        System.out.println(lesEntites.remove(e));
    }

    public void mangerFantom(Fantome f){
        f.setImage("/image/red_ghost.png");
        f.setX(420);
        f.setY(260);
    }
    public Pacman getPacMan(){ return pacman; }

    public void setPacMan(Pacman pac){ pacman=pac;}

    public int getScore(){ return nbreinit-nbBulles;}

}