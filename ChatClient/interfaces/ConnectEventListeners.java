package app.ChatClient.interfaces;

public interface ConnectEventListeners
{
    public void BeforeConnect();
    public void OnConnect();
    public void OnConnectComplete(Object o);
}
