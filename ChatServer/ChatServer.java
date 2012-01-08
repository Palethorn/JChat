import java.io.IOException;

public class ChatServer {
	static Server server;
	public static void main(String[] args) {
		try {
			if(args.length > 1)
			{
				server = new Server(args[0], Integer.parseInt(args[1]));
			}
		} catch (IOException e) {
			System.out.println("Couldn't start server.\n");
			e.printStackTrace();
			System.exit(-1);
		}
	}
}
