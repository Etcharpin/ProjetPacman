package Class;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.io.Serializable;

public class Joueur implements Serializable {


    private String pseudo;
    private int point;

    public Joueur(String pseudo, int point){

        this.pseudo = pseudo;
        this.point = point;
    }

    public int getPoint() {
        return point;
    }

    @Override
    public String toString(){
        return pseudo + "                          " + point;    }
}
