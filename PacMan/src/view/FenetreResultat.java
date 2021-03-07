package view;

import Class.Joueur;
import Class.Classement;
import Launch.Step;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class FenetreResultat {
    @FXML
    public Button boutonfermer;
    @FXML
    public Button boutonrejouer;

    @FXML
    private ListView<Joueur> lalisteJoueur;

    private Classement clas= Stub.Stub.creerclassement();

    public void initialize(){
        lalisteJoueur.itemsProperty().bind(Step.getManager().getClss().lesJoueursProperty());
    }

    @FXML
    public void clicRetour(ActionEvent actionEvent) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/FenetreAccueil.fxml"));
        Scene scene2= new Scene(root,800,600);
        Stage window = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        scene2.getStylesheets().add(Step.class.getResource("/CSS/style.css").toExternalForm());
        window.setScene(scene2);
        window.show();
    }

    public void clicJouer(ActionEvent actionEvent) throws IOException {
        Step.getManager().reInitGame();
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/FenetreJeu.fxml"));
        Scene scene2= new Scene(root,800,600);
        Stage window = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        window.setScene(scene2);
        window.show();
    }
}
