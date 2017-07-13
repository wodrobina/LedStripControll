import Objects.Server;
import org.junit.Test;

import java.util.regex.Pattern;

import static junit.framework.TestCase.assertSame;

/**
 * Created by wojodr on 13.07.2017.
 */
public class ServerTest {

    Server server = Server.getInstance();

    @Test
    public void testSendString(){
        server.sendString("As", "192.168.0.1", "80");

    }

    @Test
    public void testSingletonCreation(){
        Server serverInstance_no1 = Server.getInstance();
        Server serverInstance_no2 = Server.getInstance();
        assertSame(serverInstance_no1,serverInstance_no2);
    }

    @Test
    public void testRegexp(){
        System.out.println(Pattern.matches("\\b(?:\\d{1,3}\\.){3}\\d{1,3}\\b", "192.168.0.1"));
    }
}
