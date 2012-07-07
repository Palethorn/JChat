package app.ChatServer;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class CommandInterface implements Runnable
{
    public void run()
    {
        while(true)
        {
            this.read();
        }
    }
    public void read()
    {
        BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
            Integer commandNo;
            String command;
            try
            {
                command = console.readLine();
                commandNo = Integer.parseInt(command);
            }
            catch(IOException e)
            {
                commandNo = -1;
            }
            catch(NumberFormatException n)
            {
                commandNo = -1;
            }
        switch(commandNo)
        {
            case 0:
                System.out.println("Exiting...");
                System.exit(-1);
                break;
            default:
                System.out.println("Command not listed.");
        }
    }
}
