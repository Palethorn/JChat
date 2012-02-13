import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JFrame;
import javax.swing.JTextField;


public class WelcomeWindow extends JFrame
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
    public WelcomeWindow()
    {
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
    }
}
