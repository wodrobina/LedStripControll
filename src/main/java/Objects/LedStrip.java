package Objects;

/**
 * Created by awrob on 2016-11-02.
 */
public class LedStrip {
  private int red;
  private int green;
  private int blue;
  private int white;
  
  private double brightless;
  private String ipAdress;
  private String port;
  	
  public LedStrip(){
	  
  }

	public LedStrip(String ipAdress, String port) {
		super();
		this.ipAdress = ipAdress;
		this.port = port;
	}

	public void setPort(String port) {
		this.port = port;
	}
	
	public int getRed() {
	    return red;
	  }
	
	  public void setRed(int red) {
	    this.red = red;
	  }
	
	  public int getGreen() {
	    return green;
	  }
	
	  public void setGreen(int green) {
	    this.green = green;
	  }
	
	  public int getBlue() {
	    return blue;
	  }
	
	  public void setBlue(int blue) {
	    this.blue = blue;
	  }
	
	  public int getWhite() {
	    return white;
	  }
	
	  public void setWhite(int white) {
	    this.white = white;
	  }
	
	  public double getBrightless() {
	    return brightless;
	  }
	
	  public void setBrightless(double brightless) {
	    this.brightless = brightless;
	  }
  
	  public String getIpAdress() {
		return ipAdress;
	}
		
		public void setIpAdress(String ipAdress) {
			this.ipAdress = ipAdress;
		}
		
		public String getPort() {
			return port;
		}


}
