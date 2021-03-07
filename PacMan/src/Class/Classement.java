package Class;

import javafx.beans.Observable;
import javafx.beans.property.ListProperty;
import javafx.beans.property.ReadOnlyListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Classement {

    Comparator<Joueur> comp = Comparator.comparingInt(Joueur::getPoint);
    private ObservableList<Joueur> lesJoueursObs = FXCollections.observableArrayList();
    private ListProperty<Joueur> lesJoueurs = new SimpleListProperty<>(lesJoueursObs);
        public ObservableList<Joueur> getlesJoueurs(){return lesJoueurs.get();}
        public ReadOnlyListProperty<Joueur> lesJoueursProperty(){return lesJoueurs;}
        public void setLesJoueurs(ObservableList<Joueur> lesJoueurs){ this.lesJoueurs.set(lesJoueurs);}

    public void ajouterJoueur(Joueur j) {
        lesJoueursObs.add(j);
    }

    public void trierClass(){
            comp = comp.reversed();
            FXCollections.sort(lesJoueursObs, comp);
    }
}
