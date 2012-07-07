package app.ChatClient;
import java.io.IOException;
import java.util.Hashtable;

import javax.swing.JFrame;
import javax.swing.JList;

import nu.xom.*;

import app.ChatClient.interfaces.*;

public class MainWindow extends JFrame implements ConnectEventListeners, MessageEventListeners
{
    private static final long serialVersionUID = 1L;
    ConnectWindow connectWindow;
    Integer myId;
    Singleton singleton;
    Hashtable<Integer, Conversation> conversations;
    public MainWindow()
    {
        singleton = Singleton.Instance();
        singleton.registerEvent((MessageEventListeners)this);
        singleton.registerEvent((ConnectEventListeners)this);
        connectWindow = new ConnectWindow();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setSize(640, 480);
    }
    public void OnConnect()
    {
        
    }
    public void OnConnectComplete(Object o)
    {
        JList jlist = (JList)o;
        this.add(jlist);
        this.setVisible(true);
    }
    @Override
    public void BeforeConnect()
    {
        
    }
    @Override
    public void sendMessage(Document doc)
    {
        
    }
    @Override
    public void messageReceived(String message)
    {
        try
        {
            Document doc = (new Builder()).build(message);
            Element root = doc.getRootElement();
            Integer i = Integer.parseInt(root.getAttribute("from").getValue());
            Conversation conv;
            if(conversations.containsKey(i))
            {
                conv = conversations.get(i);
            }
            else
            {
                conv = new Conversation(i, myId, "", "");
                conversations.put(i, conv);
            }
            conv.updateConversation(doc);
        }
        catch(ValidityException e)
        {
        }
        catch(ParsingException e)
        {
        }
        catch(IOException e)
        {
        }
    }
}
