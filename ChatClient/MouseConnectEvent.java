package app.ChatClient;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseConnectEvent implements MouseListener
{
    Singleton singleton;
    public MouseConnectEvent()
    {
        singleton = Singleton.Instance();
    }
    @Override
    public void mouseClicked(MouseEvent e)
    {
        singleton.dispatchConnectEvent();
    }

    @Override
    public void mouseEntered(MouseEvent e)
    {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mouseExited(MouseEvent e)
    {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mousePressed(MouseEvent e)
    {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mouseReleased(MouseEvent e)
    {
        // TODO Auto-generated method stub
        
    }

}
