package Class;

public class BullePoint extends Entite {

    public static final int points=15;

    public BullePoint(double x,double y,String img){
        super(x,y,img);
    }
    public int getPoints() {
        return points;
    }
}
