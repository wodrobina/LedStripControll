
import Objects.Server;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


/**
 * Created by awrob on 2016-10-30.
 */
public class ProgramStart extends Application{

  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage stage) throws Exception {
    try {
      Runnable runnableServer = Server.getInstance();
      Thread serverThread = new Thread(runnableServer);
      serverThread.setDaemon(true);
      serverThread.start();

      // Initialize Loader
      FXMLLoader loader = new FXMLLoader();

      // load fxml
      loader.setLocation(getClass().getResource("MainView.fxml"));
      AnchorPane root = (AnchorPane) loader.load();

      // set main view interface
      View.MainView mainView = loader.getController();

      // controller
      Controller.MainViewController mainViewController = Controller.MainViewController.getInstance();
      mainViewController.setLedStrip(new Objects.LedStrip());
      mainViewController.setMainView(mainView);
      MyCustomColorPicker mccp = new MyCustomColorPicker();
      Scene scene = new Scene(root);
      stage.setMinHeight(400);
      stage.setMinWidth(800);
      stage.setTitle("LED Strip Controller");
      stage.sizeToScene();
      stage.setScene(scene);
      stage.show();

    } catch (Exception e) {
      System.out.println(e);
    }
  }
}
