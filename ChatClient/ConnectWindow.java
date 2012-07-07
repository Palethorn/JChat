package app.ChatClient;
import java.awt.GridLayout;
import java.io.IOException;
import java.net.UnknownHostException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JFrame;
import javax.swing.JTextField;



public class ConnectWindow extends JFrame implements app.ChatClient.interfaces.ConnectEventListeners
{
    private static final long serialVersionUID = 1L;
    GridLayout layout;
    JLabel hostLabel,
            portLabel,
            usernameLabel;
    JTextField hostInput,
               portInput,
               usernameInput;
    JButton connect;
    Singleton singleton;
    public ConnectWindow()
    {
        singleton = Singleton.Instance();
        singleton.registerEvent(this);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Connect to server...");
        
        layout = new GridLayout(4, 1);
        
        hostLabel = new JLabel("Host:");
        portLabel = new JLabel("Port:");
        usernameLabel = new JLabel("Username:");
        
        hostInput = new JTextField();
        portInput = new JTextField();
        usernameInput = new JTextField();
        
        connect = new JButton("Connect");
        
        this.setLayout(layout);
        
        this.add(hostLabel);
        this.add(hostInput);
        
        this.add(portLabel);
        this.add(portInput);
        
        this.add(usernameLabel);
        this.add(usernameInput);
        
        this.add(connect);
        this.setResizable(false);
        this.setLocation(128, 128);
        this.pack();
        this.setVisible(true);
        
        this.setSize(320, 120);
        
        this.connect.addMouseListener(new MouseConnectEvent());
    }
    public void OnConnect()
    {
        try
        {
            Client c = new Client(hostInput.getText(), Integer.parseInt(portInput.getText()));
            String header = String.format("<user_login><user>%s</user></user_login>", usernameInput.getText());
            System.out.println(header);
            c.sendHeaders(header);
        }
        catch(NumberFormatException e)
        {
            System.out.println("Invalid port.");
        }
        catch(UnknownHostException e)
        {
            System.out.println(String.format("Uknown host: %s", hostInput.getText()));
        }
        catch(IOException e)
        {
            System.out.println(String.format("Cannot connect to: %s", hostInput.getText()));
        }
    }
    @Override
    public void OnConnectComplete(Object o)
    {
        this.setVisible(false);
    }
    @Override
    public void BeforeConnect()
    {
        
    }
}
