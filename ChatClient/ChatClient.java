package app.ChatClient;
import javax.swing.SwingUtilities;
public class ChatClient
{
    static Client client;

    public static void main(String [] args)
    {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new MainWindow();
            }
        });
    }
}
