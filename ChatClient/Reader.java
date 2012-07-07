package app.ChatClient;
import java.io.DataInputStream;
import java.io.IOException;

public class Reader implements Runnable {
	DataInputStream input;
	Singleton singleton;
	public Reader(DataInputStream input) {
	    singleton = Singleton.Instance();
		this.input = input;
	}
	public boolean readMessage()
	{
		try {
			singleton.dispatchMessageReceived(input.readUTF());
			return true;
		} catch (IOException e) {
			System.out.println("Couldn't retrieve message.\n");
			return false;
		}
	}
	public void run() {
		while(readMessage());
	}
}
