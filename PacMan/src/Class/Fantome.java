package Class;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

import java.util.Random;

public class Fantome extends Entite {

    private String imga;
    protected Random rdm = new Random();
    private BooleanProperty mangeable = new SimpleBooleanProperty();
        public boolean isMangeable() {return mangeable.get();}
        public BooleanProperty mangeableProperty() {return mangeable;}
        public void setMangeable(boolean mangeable) {this.mangeable.set(mangeable);}

    public Fantome(double x, double y, String img){
        super(x,y,img);
        this.mangeable.set(false);
    }

    public String getImage(){

            int rdc = rdm.nextInt(3);
        switch (rdc) {
            case 0:
                imga = "/image/red_ghost.png";
                break;
            case 1:
                imga = "/image/orange_ghost.png";
                break;
            case 2:
                imga = "/image/pink_ghost.png";
                break;
        }
            return imga;}

    public String getImg(){ return "/image/eatable_ghost.png";}
}
