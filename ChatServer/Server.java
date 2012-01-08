import java.io.*;
import java.net.*;
import java.util.ArrayList;

public class Server extends ServerSocket {
	DataInputStream input;
	PrintStream output;
	ClientWorker w;
	Client socket;
	ArrayList<Client> connections;
	int id;
	public Server(String address,int port) throws IOException {
		super();
		this.bind(new InetSocketAddress(address, port));
		connections = new ArrayList<Client>();
		System.out.println("Server started:\n" + this.getInetAddress() + ":"
				+ this.getLocalPort());
		id = 0;
		startConnections();
	}
	public void startConnections()
	{
		while(this.listen())
		{
			pairConnections();
			w = new ClientWorker(socket);
			Thread t = new Thread(w);
			t.start();
		}
	}
	public boolean listen()
	{
		try {
			socket = new Client(this.accept());
			id++;
			System.out.println("Accepted:" + socket.socket.getInetAddress() + ":" + socket.socket.getPort());
			this.connections.add(socket);
			return true;
		} catch (IOException e) {
			System.out.println("Connection not accepted.\n");
			return false;
		}
	}
	public boolean pairConnections()
	{
		if(id == 2)
		{
			connections.get(1).setReceiver(connections.get(0));
			connections.get(0).setReceiver(connections.get(1));
		}
		return true;
	}
}
