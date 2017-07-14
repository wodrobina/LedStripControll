package View;

import java.awt.Color;
import java.util.Optional;
import java.util.regex.Pattern;

import Controller.MainViewController;
import Objects.ColourSelectPane;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.util.Pair;

/**
 * Created by awrob on 2016-11-02.
 */
public class MainViewImpl implements MainView {

  private MainViewController mainViewController;
  private ColourSelectPane colourSelectPane;
  @FXML
  private AnchorPane colorPane;
  @FXML
  private GridPane layoutGridPane;
  @FXML
  private TextField txtFieldRed;
  @FXML
  private TextField txtFieldGreen;
  @FXML
  private TextField txtFieldBlue;
  @FXML
  private TextField txtFieldWhite;
  @FXML
  private TextField txtFieldBrightless;
  @FXML
  private Slider sliderBrightless;
  @FXML
  private TextField txtFieldIpAdress;
  @FXML
  private TextField txtFieldPort;
  
  @Override
  public GridPane getLayoutGridPane() {
    return layoutGridPane;
  }

  @Override
  public ColourSelectPane getColourSelectPane() {
    return colourSelectPane;
  }

  @FXML
  public void initialize() {
    this.colourSelectPane = new ColourSelectPane();
    this.mainViewController = MainViewController.getInstance();
    // slider listener
    sliderBrightless.valueProperty().addListener(new ChangeListener<Object>() {

      @Override
      public void changed(ObservableValue<?> arg0, Object arg1, Object arg2) {
        if(isFullAdressSet()){
          txtFieldBrightless.setText(String.valueOf(sliderBrightless.getValue()));
          mainViewController.setBrightles(sliderBrightless.getValue());
        }
      }
    });

    // inserts into main view pane colour select pane
    layoutGridPane.add(colourSelectPane.getColorRectOpacityContainer(), 1, 2);

    // colour pane listener
    EventHandler<MouseEvent> rectMouseHandler = event -> {
      if(isFullAdressSet()){
        int rgb = Color.HSBtoRGB(colourSelectPane.getHue().getValue().floatValue()/360,
                colourSelectPane.getSaturation().getValue().floatValue()/100, (float) sliderBrightless.getValue()/100);
        Color.getColor(String.valueOf(rgb));
        Color color = new Color(rgb);
        txtFieldRed.setText(String.valueOf(color.getRed()));
        txtFieldGreen.setText(String.valueOf(color.getGreen()));
        txtFieldBlue.setText(String.valueOf(color.getBlue()));
        txtFieldWhite.setText(String.valueOf(100 - colourSelectPane.getSaturation().getValue().intValue()));
        mainViewController.setColurs(color.getRed(), color.getGreen(), color.getBlue(),
                100 - colourSelectPane.getSaturation().getValue().intValue());

      }
    };
    layoutGridPane.setOnMouseDragged(rectMouseHandler);
    layoutGridPane.setOnMousePressed(rectMouseHandler);
  }

  private boolean isIpAdressSet(){
    if(Pattern.matches("\\b(?:\\d{1,3}\\.){3}\\d{1,3}\\b", getIpAdress())){
      return true;
    }
    return false;
  }

  private boolean isPortSet(){
    if(Pattern.matches("\\b\\d{1,5}\\b", getPortNumber())){
      return true;
    }
    return false;
  }

  private boolean isFullAdressSet(){
    boolean isIpAdressSet = isIpAdressSet();
    boolean isPortSet = isPortSet();

    if(!isIpAdressSet & !isPortSet){
      Dialog<Pair<String, String>> dialog = new Dialog<>();
      dialog.setTitle("Enter ip adress and port");
      ButtonType adressButonType = new ButtonType("OK", ButtonBar.ButtonData.APPLY);
      dialog.getDialogPane().getButtonTypes().addAll(adressButonType, ButtonType.CANCEL);

      GridPane grid = new GridPane();
      grid.setHgap(10);
      grid.setVgap(10);
      grid.setPadding(new Insets(20, 150, 10, 10));

      TextField ipAdress = new TextField();
      ipAdress.setPromptText("Ip Adress");
      ipAdress.setText("192.168.0.1");
      TextField port = new TextField();
      port.setPromptText("Port");
      port.setText("80");

      grid.add(new Label("Ip Adress:"), 0, 0);
      grid.add(ipAdress, 1, 0);
      grid.add(new Label("Port:"), 0, 1);
      grid.add(port, 1, 1);

      dialog.getDialogPane().setContent(grid);
      dialog.setResultConverter(dialogButton -> {
        if (dialogButton == adressButonType) {
          return new Pair<>(ipAdress.getText(), port.getText());
        }
        return null;
      });

      Optional<Pair<String, String>> result = dialog.showAndWait();

      result.ifPresent(values -> {
        txtFieldIpAdress.setText(values.getKey());
        txtFieldPort.setText(values.getValue());
      });
      return isIpAdressSet() && isPortSet() ? true : false;
    }
    else if (!isIpAdressSet){

        TextInputDialog dialog = new TextInputDialog("192.168.0.1");
        dialog.setTitle("Enter ip adress");
        dialog.setContentText("Please enter ip adress:");
        Optional<String> result = dialog.showAndWait();
        result.ifPresent(value ->  txtFieldIpAdress.setText(value));

        return isIpAdressSet();
    }
    else if (!isPortSet){
        TextInputDialog dialog = new TextInputDialog("80");
        dialog.setTitle("Enter port number");
        dialog.setContentText("Please enter port number:");
        Optional<String> result = dialog.showAndWait();
        result.ifPresent(value ->  txtFieldPort.setText(value));

        return isPortSet();
    }

    return true;
  }

  @Override
  public String getPortNumber(){
    return txtFieldPort.getText();
  }
  
  @Override
  public String getIpAdress(){
    return txtFieldIpAdress.getText();
  }

}
