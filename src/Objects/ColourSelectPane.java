package Objects;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;

/**
 * Created by awrob on 2016-11-10.
 */
public class ColourSelectPane{

  private DoubleProperty hue = new SimpleDoubleProperty(0);
  private DoubleProperty saturation = new SimpleDoubleProperty(0);
  private DoubleProperty brightless = new SimpleDoubleProperty(0);
  private Pane colorRect;
  private final Pane whiteRectOverlay;
  private final Pane colorRectOverlayColour;
  private final Pane colorRectOpacityContainer = new StackPane();


    public ColourSelectPane(){

    // white - black pane
    whiteRectOverlay = new Pane();
    whiteRectOverlay.getStyleClass().add("color-rect");
    whiteRectOverlay.setBackground(new Background(new BackgroundFill(new LinearGradient(0, 0, 1, 0, true, CycleMethod.NO_CYCLE,
        new Stop(0, Color.rgb(255, 255, 255, 1)), new Stop(1, Color.rgb(255, 255, 255, 0))), CornerRadii.EMPTY, Insets.EMPTY)));

    // capture mouse X, Y
    EventHandler<MouseEvent> rectMouseHandler = event -> {
      final double x = event.getX();
      final double y = event.getY();
      hue.set(hueBorder(((y / whiteRectOverlay.getHeight()) * 360)));
      saturation.set(clamp(x / whiteRectOverlay.getWidth()) * 100);
    };

    // attach event handler to overlay so it will capture values from overlay
    whiteRectOverlay.setOnMouseDragged(rectMouseHandler);
    whiteRectOverlay.setOnMousePressed(rectMouseHandler);

    // colour pane
    colorRectOverlayColour = new Pane();
    colorRectOverlayColour.getStyleClass().add("color-bar");
    colorRectOverlayColour.setBackground(new Background(new BackgroundFill(
        colorRectLinearGradient(),
        CornerRadii.EMPTY, Insets.EMPTY)));

    colorRect = new StackPane();
    colorRect.getStyleClass().addAll("color-rect", "transparent-pattern");

    // add all overlays to overlay container
    colorRectOpacityContainer.getChildren().setAll(colorRectOverlayColour, whiteRectOverlay);
    colorRect.getChildren().setAll(colorRectOpacityContainer);

    }


  public Pane getColorRectOpacityContainer() {
    // return colorRectOpacityContainer;
    return colorRect;
    }

  public DoubleProperty getHue() {
    return hue;
  }

  public DoubleProperty getSaturation() {
    return saturation;
  }

  public DoubleProperty getBrightless() {
    return brightless;
  }

  private static LinearGradient colorRectLinearGradient() {
    double offset;
    Stop[] stops = new Stop[255];
    for (int x = 0; x < 255; x++) {
      offset = (1.0 / 255) * x;
      int h = (int) ((x / 255.0) * 360);
      stops[x] = new Stop(offset, Color.hsb(h, 1.0, 1.0));
    }
    return new LinearGradient(0f, 0f, 0f, 1f, true, CycleMethod.NO_CYCLE, stops);
  }


  static double clamp(double value) {
    return value < 0 ? 0 : value > 1 ? 1 : value;
  }

  static double hueBorder(double value) {
    return value < 0 ? 0 : value > 360 ? 360 : value;
  }
}
