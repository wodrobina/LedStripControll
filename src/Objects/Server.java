package Objects;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.*;

/**
 * Created by awrob on 2016-11-04.
 */
public class Server implements Runnable{
	DatagramSocket datagramSocket;
	private byte[] buffer;
	private static Server instance = null;
	private HashMap<String, Queue> messagesToSend = new HashMap<>();

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

		if (messagesToSend.containsKey(ipAdress+":"+port)){
			Queue messages = messagesToSend.get(ipAdress+":"+port);
			messages.add(message);
		} else {
			Queue messages = new LinkedList();
			messages.add(message);
			messagesToSend.put(ipAdress+":"+port,messages);
		}
	}

	@Override
	public void run() {
		for(Map.Entry<String, Queue> entry : messagesToSend.entrySet()){
			for (Object value : entry.getValue()){
				String[] splitedToIpAdressAndPort = ((String) entry.getKey()).split(":");
				String ipAdress = splitedToIpAdressAndPort[0];
				int port = Integer.valueOf(splitedToIpAdressAndPort[1]);

				sendMessageToAdress((String) value, ipAdress, port);
			}
		}
	}

	private void sendMessageToAdress(String message, String ipAdress, int port){
		try {
			// System.out.println(message);
			datagramSocket = new DatagramSocket();
			buffer = message.getBytes();

			InetAddress receiverAddress = InetAddress.getByName(ipAdress);

			DatagramPacket packet = new DatagramPacket(buffer, buffer.length, receiverAddress, port);
			datagramSocket.send(packet);
			datagramSocket.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

//	TODO:
	private boolean isIpAdressCorrect(String ipAdress){
		boolean isCorrect = false;



		return isCorrect;
	}
}
