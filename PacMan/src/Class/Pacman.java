package Class;

public class Pacman extends Entite {

    private boolean buff=false;
    private int count = 0;


  public Pacman(double x,double y,String img){
        super(x,y,img);
    }

    public boolean isBuff() {
        return buff;
    }

    public void setBuff(boolean buff) {
        this.buff = buff;
    }

    public int getCount(){
      return count;
    }

    public void ajoutCount(){
      count = count +1;
    }

    public void resetCount(){
      count = 0;
    }


}
