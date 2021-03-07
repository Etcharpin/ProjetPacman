package Outils.Boucleur;

import javafx.application.Platform;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;

import java.util.ArrayList;
import java.util.List;

public abstract class Boucleur implements Runnable, Observable {
    protected boolean actif;
    private List<InvalidationListener> lesObserveurs = new ArrayList<>();

    @Override
    public void addListener(InvalidationListener listener){
        lesObserveurs.add(listener);
    }

    @Override
    public void removeListener(InvalidationListener listener){
        lesObserveurs.remove(listener);
    }

    protected void beep(){
        lesObserveurs.forEach(o-> Platform.runLater(()-> o.invalidated(this)));
    }

    public boolean isActif(){
        return actif;
    }

    public  void setActif(boolean actif){
        this.actif=actif;
    }
}
