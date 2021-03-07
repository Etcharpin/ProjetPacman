package view;

import Launch.Step;
import javafx.beans.Observable;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class FenetreGameOver {
    public Text nbPoint;
    public TextField Nom;
    public Button buttonSave;


    public void initialize() {
        nbPoint.setText("Score: "+Step.getManager().getPts());
        Nom.textProperty().bindBidirectional(Step.getManager().NomJoueurProperty());
    }

    public void clicRejouer(ActionEvent actionEvent) throws Exception {
        Step.getManager().reInitGame();
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/FenetreJeu.fxml"));
        Scene scene2= new Scene(root,800,600);
        Stage window = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        window.setScene(scene2);
        window.show();
    }

    public void clicQuitter(ActionEvent actionEvent) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/FenetreAccueil.fxml"));
        Scene scene2= new Scene(root,800,600);
        Stage window = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        scene2.getStylesheets().add(Step.class.getResource("/CSS/style.css").toExternalForm());
        window.setScene(scene2);
        window.show();
    }

    public void Enregistrer(ActionEvent actionEvent) {
        Stage window = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Step.getManager().saveScore();
        buttonSave.setDisable(true);
        Nom.setDisable(true);
    }
}
