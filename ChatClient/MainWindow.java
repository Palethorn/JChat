import javax.swing.JFrame;

public class MainWindow extends JFrame
{
    private static final long serialVersionUID = 1L;
    WelcomeWindow welcomeWin;
    public MainWindow()
    {
        welcomeWin = new WelcomeWindow();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        //this.setVisible(true);
        this.setSize(640, 480);
    }
}
