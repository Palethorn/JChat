package app.ChatServer;
class ClientWorker implements Runnable
{
    Client client;

    ClientWorker(Client client)
    {
        this.client = client;
    }

    public void run()
    {
        client.readMessage();
    }
}