package app.ChatClient;
import java.util.ArrayList;

import nu.xom.*;

public class Singleton
{
    private static Singleton instance;
    public boolean connectWindowOpen;
    public boolean contactListOpen;
    public ArrayList<app.ChatClient.interfaces.MessageEventListeners> messageEventSubscribers;
    public ArrayList<app.ChatClient.interfaces.ConnectEventListeners> connectEventSubscribers;
    public ArrayList<app.ChatClient.interfaces.KeyEventListeners> keyEventSubscribers;
    
    private Singleton()
    {
        connectWindowOpen = false;
        contactListOpen = false;
        connectEventSubscribers = new ArrayList<app.ChatClient.interfaces.ConnectEventListeners>();
        messageEventSubscribers = new ArrayList<app.ChatClient.interfaces.MessageEventListeners>();
        keyEventSubscribers = new ArrayList<app.ChatClient.interfaces.KeyEventListeners>();
    }
    public static Singleton Instance()
    {
        if(instance == null)
        {
            instance = new Singleton();
        }
        return instance;
    }
    public void registerEvent(app.ChatClient.interfaces.ConnectEventListeners c)
    {
        connectEventSubscribers.add(c);
    }
    public void registerEvent(app.ChatClient.interfaces.MessageEventListeners m)
    {
        messageEventSubscribers.add(m);
    }
    public void registerEvent(app.ChatClient.interfaces.KeyEventListeners k)
    {
        keyEventSubscribers.add(k);
    }
    public void dispatchBeforeConnectEvent()
    {
        for(int i = 0; i < connectEventSubscribers.size(); i++)
        {
            connectEventSubscribers.get(i).BeforeConnect();
        }
    }
    public void dispatchConnectEvent()
    {
        for(int i = 0; i < connectEventSubscribers.size(); i++)
        {
            connectEventSubscribers.get(i).OnConnect();
        }
    }
    public void dispatchConnectionCompleteEvent(Object o)
    {
        for(int i = 0; i < connectEventSubscribers.size(); i++)
        {
            connectEventSubscribers.get(i).OnConnectComplete(o);
        }
    }
    public void dispatchMessageReceived(String message)
    {
        for(int i = 0; i < messageEventSubscribers.size(); i++)
        {
            messageEventSubscribers.get(i).messageReceived(message);
        }
    }
    public void dispatchMessageSent(Document doc)
    {
        for(int i = 0; i < messageEventSubscribers.size(); i++)
        {
            messageEventSubscribers.get(i).sendMessage(doc);
        }
    }
    public void keyReleased(Integer k)
    {
        for(int i = 0; i < keyEventSubscribers.size(); i++)
        {
            keyEventSubscribers.get(i).keyReleased(k);
        }
    }
}
