package Outils.GestionFichier;
import Class.Carte;
import Outils.Createur.Createur;

import java.io.IOException;

public abstract class Lecteur {
    protected Createur createur;

    public abstract Carte lectureFichier(String path) throws IOException;
}
