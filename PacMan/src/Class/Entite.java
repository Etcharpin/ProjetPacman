package Class;

import javafx.beans.property.*;

public abstract class Entite{
    protected static int maxWidth=20;
    protected static int maxHeight=20;

    private DoubleProperty x = new SimpleDoubleProperty();
        public double getX(){return x.get();}
        public DoubleProperty XProperty(){return x;}
        public void setX(double X){ this.x.set(X);}
    private DoubleProperty y = new SimpleDoubleProperty();;
        public double getY(){return y.get();}
        public DoubleProperty YProperty(){return y;}
        public void setY(double Y){ this.y.set(Y);}

     private BooleanProperty visible = new SimpleBooleanProperty();
        public boolean isVisible() {return visible.get();}
        public BooleanProperty visibleProperty() {return visible;}
        public void setVisible(boolean visible) {this.visible.set(visible);}

    protected String image;

    public String getImage() {
       if(isVisible()) {
           return image;
       }
       else
           return "";
    }

    public Entite(double x, double y, String image){
        this.x.set(x);
        this.y.set(y);
        this.image=image;
        this.setVisible(true);
    }

    public void setImage(String image) {
        this.image = image;
    }

    public static int getMaxHeight() {
        return maxHeight;
    }

    public static int getMaxWidth() {
        return maxWidth;
    }
}
