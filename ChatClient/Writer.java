import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;


public class Writer implements Runnable {
	DataOutputStream output;
	String textInput;
	BufferedReader console;
	public Writer(DataOutputStream output)
	{
		this.output = output;
		console = new BufferedReader(new InputStreamReader(System.in));
	}
	public void run() {
		while(sendMessage());
	}
	public boolean sendMessage() {
		try {
			textInput = console.readLine();
		} catch (IOException e) {
			System.out.println("Couldn't read input.\n");
			return false;
		}
		try {
			output.writeUTF(textInput);
		} catch (IOException e) {
			System.out.println("Couldn't send message.\n");
			return false;
		}
		return true;
	}
}
