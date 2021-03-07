package Outils.IA;

import Class.Entite;
import Launch.Step;
import Class.Pacman;
import Outils.Collisionneur.Collisionneur;
import Outils.Collisionneur.CollisionneurSimple;
import javafx.scene.input.KeyCode;

import java.util.Random;

import static java.lang.StrictMath.sqrt;
import static java.lang.StrictMath.abs;

public class IAFantome {
    private static Random alea= new Random();
    private static Collisionneur collisionneur=new CollisionneurSimple(Step.getManager().getCarte());

    public static KeyCode Direction(Entite fantome){
        Pacman pac= Step.getManager().getPacmanEncour();
        KeyCode[] ret={KeyCode.Q, KeyCode.S};
        KeyCode dir;
        double difY=(pac.getY()-fantome.getY())/20;
        double difX=(pac.getX()-fantome.getX())/20;
        if(sqrt(carre(difX)+carre(difY))<=10){
            if(difX<0){ret[0]=KeyCode.Q;}
            else{ret[0]=KeyCode.D;}

            if(difY<0){ret[1]=KeyCode.Z;}
            else{ret[1]=KeyCode.S;}

            /*Cas plus loin en X*/
            if(abs(difX)>=abs(difY)) {
                dir=attaque(ret[0],ret[1], fantome);
                if(!Step.getManager().getPacmanEncour().isBuff())
                    return dir;
                else
                    return inverseur(dir);
            }
            else{
                dir=attaque(ret[1],ret[0], fantome);
                if(!Step.getManager().getPacmanEncour().isBuff())
                    return dir;
                else
                    return inverseur(dir);
            }
        }
        else {
            int rdm= alea.nextInt(4);
            switch (rdm){
                case 0:
                    return KeyCode.Q;
                case 1:
                    return KeyCode.S;
                case 2:
                    return KeyCode.Z;
                case 3:
                    return KeyCode.D;
            }
        }
        return KeyCode.Z;
    }

    private static double carre(double d) {
        return d*d;
    }

    private static KeyCode attaque(KeyCode principale, KeyCode secondaire, Entite e){
        double[] coo={e.getX(), e.getY()};
        coo=interprete(principale, coo);

        if(collisionneur.isPassable(coo, e.getClass())){
            return principale;
        }
        else{
            return secondaire;
        }
    }

    private static double[] interprete(KeyCode dir, double[] coo){

        switch (dir) {
            case Z:
                coo[1]-=20;
                break;
            case Q:
                coo[0]-=20;
                break;
            case S:
                coo[1]+=20;
                break;
            case D:
                coo[0]+=20;
                break;
        }
        return coo;
    }

    private static KeyCode inverseur(KeyCode key){
        switch (key){
            case Z:
                return KeyCode.S;
            case Q:
                return KeyCode.D;
            case S:
                return KeyCode.Z;
            case D:
                return KeyCode.Q;
        }
        return null;
    }
}
