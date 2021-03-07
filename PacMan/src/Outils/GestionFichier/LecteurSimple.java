package Outils.GestionFichier;
import Class.Carte;
import Class.Case;
import Outils.Createur.CreateurSimple;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class LecteurSimple extends Lecteur {

    public  LecteurSimple(){
        createur=new CreateurSimple();
    }
    @Override
    public Carte lectureFichier(String nom) throws IOException {
        Carte carte = new Carte();
        double x=0, y=0;
        int c = 0;

        String Path=new File("").getAbsoluteFile() + "/ressources/Map/" +nom;
        File f= new File(Path);
        BufferedReader b= new BufferedReader(new FileReader(f));
        while ((c= b.read()) != -1){
            char ch=(char) c;
            switch (ch){
                case '0':
                    x+=20;
                    break;
                case '1':
                    createur.creerMur(carte,x,y);
                    x+=20;
                    break;
                case '2':
                    createur.creerBulle(carte,x,y);
                    carte.plusBulle();
                    x+=20;
                    break;
                case '3':
                    createur.creerFruit(carte,x,y);
                    carte.plusBulle();
                    x+=20;
                    break;
                case '4':
                    createur.creerFantome(carte,x,y,"/image/red_ghost.png");
                    break;
                case 'L':
                    y+=20;
                    x=0;
                    break;
            }
        }
        return carte;
    }
}
