import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Hashtable;

import org.w3c.dom.Element;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

public class Client
{
    public Socket socket;
    private Integer id;
    private String username;
    private Document users;
    private Hashtable<Integer, Client> usersTable;
    private DataInputStream input;
    private DataOutputStream output;
    private Dom dom;
    public Client(Socket client, Document users, Integer id,
            Hashtable<Integer, Client> usersTable)
    {
        this.socket = client;
        this.users = users;
        this.usersTable = usersTable;
        this.id = id;
        dom = new Dom();
        try
        {
            input = new DataInputStream(this.socket.getInputStream());
            output = new DataOutputStream(this.socket.getOutputStream());
        }
        catch(IOException e)
        {
            System.out.println("Failed retrieving streams.\n");
        }
    }

    public void closeBuffer()
    {
        try
        {
            this.input.close();
            this.output.close();
        }
        catch(IOException e)
        {
            System.out.println("Couldn't close streams.\n");
        }
    }

    public void readHeader()
    {
        try
        {
            String header = input.readUTF();
            dom.prepareDoc(header);
            username = dom.attributeValue("user", "username").get(0);
            dom.addNode(users, "user", id.toString(), username);
        }
        catch(IOException e)
        {
            System.out.println("Cannot read header.\n");
        }
    }
    public void sendUserList()
    {
        try
        {
            output.writeUTF(users.toString());
        }
        catch(IOException e)
        {
        }
    }
    public void readMessage()
    {
        try
        {
            String message = this.input.readUTF();
            dom.prepareDoc(message);
            Node nl = dom.byTag("message").item(0);
            Integer to = Integer.parseInt(((Element)nl).getAttribute("to"));
            Client receiver = usersTable.get(to);
            receiver.sendMessage(message);
        }
        catch(Exception e)
        {
            
        }
    }
    public void sendMessage(String message)
    {
        try
        {
            this.output.writeUTF(message);
        }
        catch(IOException e)
        {
            System.out.println("Error sending message.\n");
        }
    }
}