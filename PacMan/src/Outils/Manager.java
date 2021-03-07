package Outils;

import Class.*;
import Outils.Boucleur.Boucleur;
import Outils.Boucleur.BoucleurSimple;
import Outils.Collisionneur.Collisionneur;
import Outils.Collisionneur.CollisionneurSimple;
import Outils.Createur.Createur;
import Outils.Createur.CreateurSimple;
import Outils.Deplaceur.Deplaceur;
import Outils.Deplaceur.DeplaceurSimple;
import Outils.GestionFichier.Lecteur;
import Outils.GestionFichier.LecteurSimple;
import Outils.IA.IAFantome;
import Outils.Persistance.FileUtilSimple;
import Outils.Persistance.SerializerSimple;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;
import javafx.scene.input.KeyCode;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Manager implements InvalidationListener{

    private Carte carte = new Carte();
    private Pacman PacmanEncours;
    private Collisionneur collisionneur;
    private Deplaceur deplaceur;
    private Createur createur = new CreateurSimple();
    private Lecteur lecteur = new LecteurSimple();
    private Boucleur leboucleur = new BoucleurSimple();
    protected Random alea = new Random();
    private Classement clss = new Classement();
    private boolean gameOver=false;
    private boolean jouer = false;

    private int pts=0;
    private String filename = "resultat.json";
    private StringProperty nomJoueur=new SimpleStringProperty("");
        public StringProperty NomJoueurProperty(){return nomJoueur;}
        public void setNomJoueur(String n){nomJoueur.set(n);}

    public Manager(){
        try {
            carte = lecteur.lectureFichier("map1");
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
        String filecontent = FileUtilSimple.readFile(filename);
        if(filecontent.equals("")) filecontent = "{}";
        JsonObject json = JsonParser.parseString(filecontent).getAsJsonObject();
        JsonArray array;
        if(json.has("Joueur")){
            array = json.get("Joueur").getAsJsonArray();
        }
        else{
            array = new JsonArray();
            array.add(SerializerSimple.serialize(new Joueur("Boby",986)));
            array.add(SerializerSimple.serialize(new Joueur("Quentin",746)));
            array.add(SerializerSimple.serialize(new Joueur("Hugo",436)));
            array.add(SerializerSimple.serialize(new Joueur("Etienne",122)));
        }
        List<Joueur> lejoueurs = new ArrayList<Joueur>();
        array.forEach(jsonElement -> lejoueurs.add(SerializerSimple.deseririalize(jsonElement.getAsString())));

        JsonArray jsonArray = new JsonArray();
        lejoueurs.forEach(joueur -> jsonArray.add(SerializerSimple.serialize(joueur)));
        json.add("Joueur",jsonArray);
        FileUtilSimple.saveFile(filename, json.toString());
        for(Joueur j : lejoueurs){
            clss.ajouterJoueur(j);
        }

        carte.setPacMan(createur.creerPacman(carte,20,120,"/image/pacman_right.png"));
        PacmanEncours = carte.getPacMan();
        leboucleur.setActif(true);
        collisionneur=new CollisionneurSimple(carte);
        deplaceur=new DeplaceurSimple(collisionneur);
        leboucleur.addListener(this);
        leboucleur.setActif(true);
        new Thread(leboucleur).start();

    }

    public ObservableList<Entite> getLesEntites(){
        return carte.getLesEntites();
    }


    public void Deplacer(KeyCode key){
        switch (key) {
            case Z:
                PacmanEncours.setImage("/image/pacman_up.png");
                deplaceur.deplacerHaut(PacmanEncours);
                break;
            case Q:
                PacmanEncours.setImage("/image/pacman_left.png");
                deplaceur.deplacerGauche(PacmanEncours);
                break;
            case S:
                PacmanEncours.setImage("/image/pacman_down.png");
                deplaceur.deplacerBas(PacmanEncours);
                break;
            case D:
                PacmanEncours.setImage("/image/pacman_right.png");
                deplaceur.deplacerDroite(PacmanEncours);
                break;
        }
    }
    public Classement getClss(){
        return clss;
    }

    public void Deplacer(KeyCode key, Entite e){
            if(jouer){
                switch (key) {
                    case Z:
                        deplaceur.deplacerHaut(e);
                        break;
                    case Q:
                        deplaceur.deplacerGauche(e);
                        break;
                    case S:
                        deplaceur.deplacerBas(e);
                        break;
                    case D:
                        deplaceur.deplacerDroite(e);
                        break;
                }
            }
    }
    public Pacman getPacmanEncour(){
        return PacmanEncours;
    }
    public Carte getCarte(){return carte;}

    public void invalidated(Observable observable){
        for(Entite e : carte.getLesEntites()){
            if(e.getClass() == Fantome.class){
                Deplacer(IAFantome.Direction(e),e);
            }
        }
    }

    public void stopBoucleur(){
        leboucleur.setActif(false);
    }

    public void reInitGame(){
        for(Entite e : carte.getLesEntites()){
            e.setVisible(true);
            getPacmanEncour().setX(20);
            getPacmanEncour().setY(120);
            getPacmanEncour().setImage("/image/pacman_right.png");
            carte.reinitBulle();
            if(e.getClass() == Fantome.class){
                ((Fantome) e).setMangeable(false);
                if(e.getX() < 60 && e.getY() < 140){
                    e.setX(80);
                    e.setY(20);
                }
            }
        }
        jouer = true;
        gameOver=false;
    }

    public void setPoint(){
        pts= carte.getScore()*BullePoint.points;
    }

    public int getPts(){return pts;}

    public void finJeu(){
            jouer = false;
            gameOver = true;
    }

    public boolean isOver(){return gameOver;}

    public void saveScore(){
            Joueur joueur=new Joueur(nomJoueur.get(),pts);
            clss.ajouterJoueur(joueur);
            clss.trierClass();
            JsonArray jsonArray = new JsonArray();
            for(Joueur j : clss.getlesJoueurs()){
                jsonArray.add(SerializerSimple.serialize(j));
            }
            String filecontent = FileUtilSimple.readFile(filename);
            JsonObject json = JsonParser.parseString(filecontent).getAsJsonObject();
            json.add("Joueur",jsonArray);
            FileUtilSimple.saveFile(filename, json.toString());
    }
}
