package view;
import Outils.Manager;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ListChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.input.KeyEvent;
import Class.Entite;
import Launch.Step;
import Class.Fantome;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class FenetreJeu {
    @FXML
    public Pane PaneCarte;
    public Manager manager = Step.getManager();

    public void initialize(){
        for(Entite entite : manager.getLesEntites()){
            updateCarte(entite);
        }
        manager.getLesEntites().addListener((ListChangeListener.Change<? extends Entite> c)->{
            c.next();
            System.out.println("Listner");
            /*Ajout d'elements*/
            for(Entite entite : c.getAddedSubList()){
                updateCarte((entite));
            }

            /*Suppression d'elements*/
            for (Entite entite : c.getRemoved()){
                Iterator<Node> literateur = PaneCarte.getChildren().iterator();
                while (literateur.hasNext()){
                    Node nod = literateur.next();
                    if(nod.getUserData() == entite){
                        literateur.remove();
                        break;
                    }
                }
                for(Node node : PaneCarte.getChildren()){
                    if(entite.getX()==node.getLayoutX() && entite.getY()==node.getLayoutY()){
                        PaneCarte.getChildren().remove(node);
                        break;
                    }
                }
            }
        });
    }

    private void updateCarte(Entite entite){
        ImageView entiteAAfficher = new ImageView();
        entiteAAfficher.setUserData(entiteAAfficher);
        entiteAAfficher.setImage(new Image(getClass().getResource(entite.getImage()).toExternalForm()));
        if(entite.getClass() == Fantome.class) {
            ((Fantome) entite).mangeableProperty().addListener(new ChangeListener<Boolean>() {
                @Override
                public void changed(ObservableValue<? extends Boolean> observableValue, Boolean aBoolean, Boolean t1) {
                    if (t1) {
                        entiteAAfficher.setImage(new Image(getClass().getResource(((Fantome)entite).getImg()).toExternalForm()));
                    } else {
                        entiteAAfficher.setImage(new Image(getClass().getResource(((Fantome)entite).getImage()).toExternalForm()));
                    }
                }

            });
        }
        entiteAAfficher.visibleProperty().bind(entite.visibleProperty());
        entiteAAfficher.layoutXProperty().bind(entite.XProperty());
        entiteAAfficher.layoutYProperty().bind(entite.YProperty());
        entiteAAfficher.setFitHeight(Entite.getMaxHeight());
        entiteAAfficher.setFitWidth(Entite.getMaxWidth());
        PaneCarte.getChildren().add(entiteAAfficher);
    }


    @FXML
    public void clicRetour(ActionEvent actionEvent) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/FenetreAccueil.fxml"));
        Scene scene2= new Scene(root,800,600);
        Stage window = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        scene2.getStylesheets().add(Step.class.getResource("/CSS/style.css").toExternalForm());
        window.setScene(scene2);
        window.show();
    }

    public void handleOnKeyPressed(KeyEvent keyEvent) {
        manager.Deplacer(keyEvent.getCode());
        updateCarte(manager.getPacmanEncour());
        if(manager.getCarte().getNbBulles() == 0){
            System.out.printf("Victoire");
        }
    }
}
