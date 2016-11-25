package Objects;

import java.net.DatagramPacket;

/**
 * Created by awrob on 2016-11-04.
 */
public class Server implements Runnable {

  @Override
  public void run() {
    while (true) {
      DatagramPacket packet = socket.receive();
      new Thread(new Responder(socket, packet)).start();
    }
  }

}
