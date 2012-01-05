import java.io.IOException;
import java.net.UnknownHostException;

public class Main {
	static Client client;
	public static void main(String[] args) {
		try {
			client = new Client("mitsuko", 1500);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Couldn't connect.\n");
			e.printStackTrace();
			System.exit(-1);
		}
	}
}
