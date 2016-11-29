package Controller;

import Objects.LedStrip;
import Objects.Server;
import View.MainView;

/**
 * Created by awrob on 14.11.2016.
 */
public class MainViewController {

  private static MainViewController instance = null;
  private MainView mainView;
  private LedStrip ledStrip;
  private static Server server;
  
  private MainViewController() {
// prevent from creating default constructor
  }

  public static synchronized MainViewController getInstance() {
    if (instance == null) {
      instance = new MainViewController();
      server = Server.getInstance();
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
    colourChange(String.valueOf((int) ledStrip.getBrightless()));
  }

  public void setBrightles(double brightless) {
    ledStrip.setBrightless(brightless);
    colourChange(String.valueOf((int) ledStrip.getBrightless()));
  }

  public void colourChange(String brightless) {
    ledStrip.setIpAdress(mainView.getIpAdress());
    ledStrip.setIpAdress(mainView.getPortNumber());
	  server.sendString(composeMessage( ledStrip.getRed(),  ledStrip.getGreen(),  ledStrip.getBlue(),  ledStrip.getWhite(),  (int)ledStrip.getBrightless()), ledStrip.getIpAdress(), ledStrip.getPort());
  }
  
  private String composeMessage(int red, int green, int blue, int saturation, int brightless){
	  saturation = (int)2.55 * saturation;
	  String message = leadingZero(Integer.toString(red)) + leadingZero(Integer.toString(green)) + leadingZero(Integer.toString(blue)) + leadingZero(Integer.toString(saturation)) + leadingZero(Integer.toString( brightless)) ;
	  return message;
  }
  
  private String leadingZero(String value){
	  switch (value.length()) {

	    case 0:
	        return "000";
	    case 1:
	    	return "00"+value;
	    case 2:
	    	return "0"+value;
	    case 3:
	    	return value;
	  }
	return value;
  }
  
  
}
