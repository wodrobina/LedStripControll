package View;

import java.awt.Color;

import Controller.MainViewController;
import Objects.ColourSelectPane;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
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
        txtFieldBrightless.setText(String.valueOf(sliderBrightless.getValue()));
        mainViewController.setBrightles(sliderBrightless.getValue());
        
      }
    });

    layoutGridPane.add(colourSelectPane.getColorRectOpacityContainer(), 1, 2); // inserts into main view pane colour
                                                                               // select pane
    // colour pane listener
    EventHandler<MouseEvent> rectMouseHandler = event -> {
      int rgb = Color.HSBtoRGB(colourSelectPane.getHue().getValue().floatValue()/360,
          colourSelectPane.getSaturation().getValue().floatValue()/100, (float) sliderBrightless.getValue()/100);
      Color.getColor(String.valueOf(rgb));
      Color color = new Color(rgb);
      System.out.println(color);
      txtFieldRed.setText(String.valueOf(color.getRed()));
      txtFieldGreen.setText(String.valueOf(color.getGreen()));
      txtFieldBlue.setText(String.valueOf(color.getBlue()));
      txtFieldWhite.setText(String.valueOf(100 - colourSelectPane.getSaturation().getValue().intValue()));
      mainViewController.setColurs(color.getRed(), color.getGreen(), color.getBlue(),
          100 - colourSelectPane.getSaturation().getValue().intValue());
      
    };
    layoutGridPane.setOnMouseDragged(rectMouseHandler);
    layoutGridPane.setOnMousePressed(rectMouseHandler);
  }

  public String getPortNumber(){
	return txtFieldPort.getText();
	  
  }
  
  public String getIpAdress(){
	return txtFieldIpAdress.getText();
	  
  }



}
