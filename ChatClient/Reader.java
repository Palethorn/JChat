import java.io.DataInputStream;
import java.io.IOException;

public class Reader implements Runnable {
	DataInputStream input;
	public Reader(DataInputStream input) {
		this.input = input;
	}
	public boolean readMessage()
	{
		try {
			System.out.println(input.readUTF());
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
