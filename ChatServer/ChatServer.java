package app.ChatServer;
import java.io.IOException;

public class ChatServer
{
    static Server server;

    public static void main(String [] args)
    {
        String [] params = {"100.200.1.2", "22222"};
        try
        {
            if(params.length > 1)
            {
                server = new Server(params[0], Integer.parseInt(params[1]));
            }
            else
            {
                System.out.println("Bind address and port must be specified.");
            }
        }
        catch(IOException e)
        {
            System.out.println("Couldn't start server.\n");
            e.printStackTrace();
            System.exit(-1);
        }
    }
}
