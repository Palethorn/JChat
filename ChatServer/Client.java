import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Client {
	public Socket socket;
	public Socket receiver;
	private DataInputStream input;
	private DataOutputStream output;

	public Client(Socket client) {
		this.socket = client;
		try {
			input = new DataInputStream(this.socket.getInputStream());
		} catch (IOException e) {
			System.out.println("Failed retrieving input stream.\n");
			System.exit(-1);
		}
	}

	public void closeBuffer() {
		try {
			this.input.close();
		} catch (IOException e) {
			System.out.println("Couldn't close stream.\n");
		}
	}

	public int readHeader() {
		try {
			String id = input.readUTF();
			System.out.println(id);
			return Integer.parseInt("100");
		} catch (NumberFormatException e) {
			System.out.println("Cannot parse header.\n");
			return -1;
		} catch (IOException e) {
			System.out.println("Cannot read header.\n");
			return -1;
		}
	}

	public void setReceiver(Client receiver) {
		this.receiver = receiver.socket;
		try {
			this.output = new DataOutputStream(this.receiver.getOutputStream());
		} catch (IOException e) {
			System.out.println("Failed retrieving output stream.\n");
		}
	}

	public void sendMessage() {
		String line;
		try {
			while (true) {
				line = this.input.readUTF();
				this.output.writeUTF(line);
			}
		} catch (IOException e) {
			System.out.println("Error sending message.\n");
		}
	}
}