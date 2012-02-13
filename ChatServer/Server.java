import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.Hashtable;

import org.w3c.dom.Document;

public class Server extends ServerSocket
{
    DataInputStream input;
    PrintStream output;
    ClientWorker w;
    Client socket;
    String root;
    Dom dom;
    Document users;
    Hashtable<Integer, Client> usersTable;
    ArrayList<Client> connections;
    int id;

    public Server(String address, int port) throws IOException
    {
        super();
        setUsersList();
        setCommandInterface();
        this.bind(new InetSocketAddress(address, port));
        connections = new ArrayList<Client>();
        System.out.println("Server started:\n" + this.getInetAddress() + ":"
                + this.getLocalPort());
        id = 0;
        printCommands();
        startConnections();
    }
    public void printCommands()
    {
        System.out.println("Available commands:");
        System.out.println("0. Exit");
    }
    public void setUsersList()
    {
        dom = new Dom();
        root = new String("<users></users>");
        dom.prepareDoc(root);
        usersTable = new Hashtable<Integer, Client>();
    }
    public void setCommandInterface()
    {
        Thread ci = new Thread(new CommandInterface());
        ci.start();
    }
    public void startConnections()
    {
        while(this.listen())
        {
            w = new ClientWorker(socket);
            Thread t = new Thread(w);
            t.start();
        }
    }

    public boolean listen()
    {
        try
        {
            socket = new Client(this.accept(), users, id, usersTable);
            id++;
            System.out.println("Accepted:" + socket.socket.getInetAddress()
                    + ":" + socket.socket.getPort());
            this.usersTable.put(id, socket);
            return true;
        }
        catch(IOException e)
        {
            System.out.println("Connection not accepted.\n");
            return false;
        }
    }

    public boolean pairConnections()
    {
        return true;
    }
}