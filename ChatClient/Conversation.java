package app.ChatClient;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextPane;

import nu.xom.*;

import app.ChatClient.interfaces.*;

public class Conversation extends JFrame implements KeyEventListeners
{
    private static final long serialVersionUID = 1L;
    private Integer receiverId;
    private Integer myId;
    private String receiverUsername;
    private JTextArea message;
    private JTextPane conversation;
    private Singleton singleton;
    public Conversation(Integer receiverId, Integer myId, String receiverUsername, String title)
    {
        singleton = Singleton.Instance();
        singleton.registerEvent(this);
        this.receiverId = receiverId;
        this.myId = myId;
        this.receiverUsername = receiverUsername;
        this.setTitle(title);
        GridLayout layout = new GridLayout(2, 1);
        layout.setHgap(20);
        this.setLayout(layout);
        message = new JTextArea();
        message.setMargin(new Insets(5, 5, 5, 5));
        conversation = new JTextPane();
        conversation.setEditable(false);
        conversation.setMargin(new Insets(5, 5, 5, 5));
        this.setLocation(128, 128);
        this.add(conversation);
        this.add(message);
        message.addKeyListener(new KeyListener()
        {

            @Override
            public void keyPressed(KeyEvent arg0)
            {
                singleton.keyReleased(arg0.getKeyCode());
            }

            @Override
            public void keyReleased(KeyEvent arg0)
            {
                
            }

            @Override
            public void keyTyped(KeyEvent arg0)
            {
            }
             
        });
        this.pack();
        this.setSize(512, 350);
        this.setVisible(true);
    }
    public int getId()
    {
        return receiverId;
    }
    public void updateConversation(Document doc)
    {
            Element root = doc.getRootElement();
            Elements txt = root.getChildElements("text");
            String text;
            if(root.getAttribute("from").getValue() == receiverId.toString())
            {
                text = conversation.getText() + "\n" + receiverUsername + txt.get(0).getValue();
            }
            else
            {
                text = conversation.getText() + "\n" + "Me: " + txt.get(0).getValue();
            }
            conversation.setText(text);
    }
    @Override
    public void keyReleased(Integer k)
    {
        if(k == KeyEvent.VK_ENTER)
        {
            Document doc;
            Element root = new Element("message");
            root.addAttribute(new Attribute("to", receiverId.toString()));
            root.addAttribute(new Attribute("from", myId.toString()));
            Element txt = new Element("text");
            String text = message.getText();
            txt.appendChild(text);
            root.appendChild(txt);
            doc = new Document(root);
            this.updateConversation(doc);
            message.setText("");
            singleton.dispatchMessageSent(doc);
        }
    }
}
