package view;
import Launch.Step;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class FenetreAccueil {

    @FXML
    public void clicScore(javafx.event.ActionEvent actionEvent) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/FenetreResultat.fxml"));
        Scene scene2= new Scene(root,800,600);
        Stage window = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        window.setScene(scene2);
        window.show();
    }

    @FXML
    public void clicJouer(javafx.event.ActionEvent actionEvent) throws Exception {
        Step.getManager().reInitGame();
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/FenetreJeu.fxml"));
        Scene scene2= new Scene(root,805,600);
        Stage window = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        window.setScene(scene2);
        window.show();
    }

    public void clicQuitter(ActionEvent actionEvent) {
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        stage.close();
    }
}
