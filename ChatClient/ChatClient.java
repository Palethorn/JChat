import java.io.IOException;
import java.net.UnknownHostException;

public class ChatClient {
	static Client client;
	public static void main(String[] args) {
		try {
			if(args.length > 1)
			{
				client = new Client(args[0], Integer.parseInt(args[1]));
			}
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Couldn't connect.\n");
			e.printStackTrace();
			System.exit(-1);
		}
	}
}
