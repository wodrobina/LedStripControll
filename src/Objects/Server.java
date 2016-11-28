package Objects;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

import Controller.MainViewController;

/**
 * Created by awrob on 2016-11-04.
 */
public class Server {
	DatagramSocket datagramSocket;
	private byte[] buffer;
	private static Server instance = null;
	
	  private Server() {
		// prevent from creating default constructor
		  }

		  public static synchronized Server getInstance() {
		    if (instance == null) {
		      instance = new Server();
		    }
		    return instance;
		  }
	  
	 public void sendString(String message, String ipAdress, int port){
		try {
			ipAdress = "10.20.0.2";
			port = 8888;
			System.out.println(message);
			datagramSocket = new DatagramSocket();
			buffer = message.getBytes();
		 
			InetAddress receiverAddress = InetAddress.getByName(ipAdress);

     		DatagramPacket packet = new DatagramPacket(
     		        buffer, buffer.length, receiverAddress, port);
     		datagramSocket.send(packet);
     		datagramSocket.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	 }
}
