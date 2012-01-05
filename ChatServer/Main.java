import java.io.IOException;

public class Main {
	static Server server;
	public static void main(String[] args) {
		try {
			server = new Server(1500);
		} catch (IOException e) {
			System.out.println("Couldn't start server.\n");
			e.printStackTrace();
			System.exit(-1);
		}
	}
}
