package addressapp;

import addressapp.model.Person;
import addressapp.view.PersonEditController;
import addressapp.view.PersonOverviewController;
import java.io.IOException;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author Howard
 */


public class MainApp extends Application {

    private Stage primaryStage;
    private BorderPane rootLayout;
    private ObservableList<Person> personData = FXCollections.observableArrayList();

    /**
     * Default constructor
     */
    public MainApp() {
        personData.add(new Person("gg","hh"));
        personData.add(new Person("gg1","hh1"));
        personData.add(new Person("gg2","hh2"));
        personData.add(new Person("gg3","hh3"));
    }

    public ObservableList<Person> getList() {
        return personData;
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("AddressApp");
        this.primaryStage.getIcons().add(new Image("file:resources/icon.png"));
        initRootLayout();

        showPersonOverview();
    }

    /**
     * Initializes the root layout.
     */
    public void initRootLayout() {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/RootLayout.fxml"));
            rootLayout = (BorderPane) loader.load();

            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Shows the person overview inside the root layout.
     */
    public void showPersonOverview() {
        try {
            // Load person overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/PersonOverview.fxml"));
            AnchorPane personOverview = (AnchorPane) loader.load();
            //link controller to FXML, and give it the access to the main
            PersonOverviewController controller = loader.getController();
            controller.setMainApp(this);
            // Set person overview into the center of root layout.
            rootLayout.setCenter(personOverview);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Opens a dialog to edit person's detail
     * @param person object to be edited
     * @return true if clicked OK
     */
    public boolean showEditDialog(Person person){
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/PersonEdit.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            //Create a new Stage
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Edit Person");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            PersonEditController controller = loader.getController();
            controller.setStage(dialogStage);
            controller.setPerson(person);

            dialogStage.showAndWait();
            return controller.isOkClicked();
        }catch(IOException e){
            e.printStackTrace();
            return false;
        }
    }
    /**
     * Returns the main stage.
     * @return
     */
    public Stage getPrimaryStage() {
        return primaryStage;
    }


}