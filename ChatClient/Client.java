import java.io.*;
import java.net.*;

public class Client extends Socket {
	DataInputStream input;
	DataOutputStream output;
	String textInput;
	//Writer writer;
	//Reader reader;
	BufferedReader console;

	public Client(String machine, int port) throws UnknownHostException,
			IOException {
		super(machine, port);
		System.out.println("Connected:\nLocal:" + this.getLocalAddress()
				+ ":" + this.getLocalPort() + "\nRemote:"
				+ this.getInetAddress() + ":" + this.getPort() + "\n");
		if(!this.acquireStreams())
		{
			System.exit(-1);
		}
	}

	public void sendHeaders() {
		try {
			output.writeUTF("100");
		} catch (IOException e) {
			System.out.println("Couldn't send headers.\n");
			System.exit(-1);
		}
	}
	public boolean acquireStreams() {
		try {
			input = new DataInputStream(this.getInputStream());
			output = new DataOutputStream(this.getOutputStream());
			Thread t = new Thread(new Reader(input));
			t.start();
			t = new Thread(new Writer(output));
			t.start();
		} catch (IOException e) {
			System.out.println("Couldn't acquire steams.\n");
			return false;
		}
		console = new BufferedReader(new InputStreamReader(System.in));
		return true;
	}
}
