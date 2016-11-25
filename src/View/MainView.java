package View;

import Objects.ColourSelectPane;
import javafx.scene.layout.GridPane;

/**
 * Created by awrob on 14.11.2016.
 */
public interface MainView {

  public GridPane getLayoutGridPane();

  public ColourSelectPane getColourSelectPane();
}
