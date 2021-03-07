package Launch;

import Outils.Manager;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.awt.*;

public class Step extends Application {
    private static Manager manager = new Manager();
    private static Stage stage;

    @Override
    public void start(Stage stage) throws Exception {
        this.stage=stage;
        Parent root= FXMLLoader.load(getClass().getResource("/fxml/FenetreAccueil.fxml"));

        stage.setTitle("PacMan");
        stage.resizableProperty().set(false);
        Scene scene = new Scene(root,800,600);
        scene.getStylesheets().add(getClass().getResource("/CSS/style.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }

    public void stop() throws Exception{
        manager.stopBoucleur();
        super.stop();
    }

    public static Manager getManager(){
        return manager;
    }

    public static void finJeu(){

        manager.setPoint();
        manager.finJeu();
        try {
            Parent root = FXMLLoader.load(Step.class.getResource("/fxml/FenetreGameOver.fxml"));
            Scene scene2 = new Scene(root, 800, 600);
            stage.setOpacity(0.5);
            stage.setScene(scene2);
            stage.show();
        }
        catch (Exception e){}
    }
}
