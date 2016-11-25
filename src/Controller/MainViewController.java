package Controller;

import Objects.LedStrip;
import View.MainView;

/**
 * Created by awrob on 14.11.2016.
 */
public class MainViewController {

  private static MainViewController instance = null;
  private MainView mainView;
  private LedStrip ledStrip;

  private MainViewController() {

  }

  public static synchronized MainViewController getInstance() {
    if (instance == null) {
      instance = new MainViewController();
    }
    return instance;
  }

  public void setMainView(MainView mainView) {
    if (instance == null) {
      instance = new MainViewController();
    }
    this.mainView = mainView;
  }
  

  public void setLedStrip(LedStrip ledStrip) {
    if (instance == null) {
      instance = new MainViewController();
    }
    this.ledStrip = ledStrip;
  }

  public void setColurs(int red, int green, int blue, int white) {
    ledStrip.setRed(red);
    ledStrip.setGreen(green);
    ledStrip.setBlue(blue);
    ledStrip.setWhite(white);
  }

  public void setBrightles(double brightless) {
    ledStrip.setBrightless(brightless);
  }


}
