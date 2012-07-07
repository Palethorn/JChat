package app.ChatClient;

import java.io.*;
import java.net.*;

import javax.swing.DefaultListModel;
import javax.swing.JList;

import nu.xom.*;

import app.ChatClient.interfaces.*;

public class Client extends Socket implements MessageEventListeners {
	DataInputStream input;
	DataOutputStream output;
	Reader reader;
	Singleton singleton;
	Builder parser;
	Document doc;
	public Client(String machine, int port) throws UnknownHostException, IOException
	{
		super(machine, port);
		parser = new Builder();
		singleton = Singleton.Instance();
		singleton.registerEvent(this);
		System.out.println("Connected:\nLocal:" + this.getLocalAddress()
				+ ":" + this.getLocalPort() + "\nRemote:"
				+ this.getInetAddress() + ":" + this.getPort() + "\n");
		if(!this.acquireStreams())
		{
			System.exit(-1);
		}
	}

	public void sendHeaders(String header) {
		try {
			output.writeUTF(header);
		} catch (IOException e) {
			System.out.println("Couldn't send headers.\n");
			System.exit(-1);
		}
		readResponse();
		setReader();
	}
	public void readResponse()
	{
        JList usersList;
        DefaultListModel model = new DefaultListModel();
	    try
        {
            String response = input.readUTF();
            try
            {
                Document ack = parser.build(response, null);
                Element root = ack.getRootElement();
                System.out.println(root.getAttribute("status").getValue());
                String status = root.getAttribute("status").getValue();
                if(status.compareTo("ok") == 0)
                {
                    Elements userList = root.getChildElements("user");
                    for(int i = 0; i < userList.size(); i++)
                    {
                        model.add(i, userList.get(i).getValue());
                    }
                }
            }
            catch(ValidityException e)
            {
                
            }
            catch(ParsingException e)
            {
                
            }
            
        }
        catch(IOException e)
        {
            e.printStackTrace(); 
        }
	    usersList = new JList(model);
	    singleton.dispatchConnectionCompleteEvent(usersList);
	}
	public void setReader()
	{
        Thread t = new Thread(new Reader(input));
        t.start();
	}
	public boolean acquireStreams() {
		try {
			input = new DataInputStream(this.getInputStream());
			output = new DataOutputStream(this.getOutputStream());
		} catch (IOException e) {
			System.out.println("Couldn't acquire steams.\n");
			return false;
		}
		return true;
	}
	@Override
	public void sendMessage(Document d)
	{
	    try
        {
            output.writeUTF(d.toXML());
        }
        catch(IOException e)
        {
            
        }
	}
    @Override
    public void messageReceived(String message)
    {
        
    }
}
