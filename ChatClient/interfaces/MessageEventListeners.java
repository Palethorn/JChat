package app.ChatClient.interfaces;

import nu.xom.*;

public interface MessageEventListeners
{
    public void sendMessage(Document doc);
    public void messageReceived(String message);
}
