package Objects;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.*;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Created by awrob on 2016-11-04.
 */
public class Server implements Runnable{
	DatagramSocket datagramSocket;
	private byte[] buffer;
	private static Server instance = null;
	private ConcurrentLinkedQueue<String[]> messagesToSend = new ConcurrentLinkedQueue();
	private int sendingInterval = 5;

	private Server() {
		// prevent from creating default constructor
	}

	public static synchronized Server getInstance() {
		if (instance == null) {
		      instance = new Server();
		}
		return instance;
	}

	public void sendString(String message, String ipAdress, String port){
		String[] queueValue = new String[3];
		queueValue[0] = message;
		queueValue[1] = ipAdress;
		queueValue[2] = port;
		messagesToSend.add(queueValue);
	}

	@Override
	public void run() {
		while(true){
			for(Iterator<String[]> iterator = messagesToSend.iterator(); iterator.hasNext();){
				String[] queueValue = iterator.next();
				sendMessageToAdress( queueValue[0], queueValue[1], Integer.valueOf(queueValue[2]));
				messagesToSend.remove();
			}
			try {
				Thread.sleep(sendingInterval);
			} catch (InterruptedException e) {
				return;
			}
		}
	}

	private void sendMessageToAdress(String message, String ipAdress, int port){
		try {
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

	public int getSendingInterval() {
		return sendingInterval;
	}

	public void setSendingInterval(int sendingInterval) {
		this.sendingInterval = sendingInterval;
	}
}
