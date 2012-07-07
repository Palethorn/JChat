package app.ChatServer;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Hashtable;

import nu.xom.*;

public class Client
{
    public Socket socket;
    private Integer id;
    private String username;
    private Document users;
    private Hashtable<Integer, Client> usersTable;
    private DataInputStream input;
    private DataOutputStream output;
    private Builder parser;
    Singleton singleton;
    public Client(Socket client, Document users, Integer id,
            Hashtable<Integer, Client> usersTable)
    {
        singleton = Singleton.getInstance();
        this.socket = client;
        this.users = users;
        this.usersTable = usersTable;
        this.id = id;
        parser = new Builder();
        try
        {
            input = new DataInputStream(this.socket.getInputStream());
            output = new DataOutputStream(this.socket.getOutputStream());
            readHeader();
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
            try
            {
                Document req = parser.build(header, null);
                Element root = req.getRootElement();
                Element user = root.getFirstChildElement("user");
                username = user.getValue();
                Element newUser = new Element("user");
                newUser.addAttribute(new Attribute("id", id.toString()));
                newUser.appendChild(username);
                users.getRootElement().appendChild(newUser);
                System.out.println(users.toXML());
                this.sendUserList();
            }
            catch(ValidityException e)
            {
                singleton.errorMessage("String is malformed");
            }
            catch(ParsingException e)
            {
                singleton.errorMessage("Unable to parse document");
            }
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
            output.writeUTF(users.toXML());
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
            Document mess = parser.build(message, null);
            Element root = mess.getRootElement();
            usersTable.get(Integer.parseInt(root.getAttribute("to").getValue())).sendMessage(message);
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