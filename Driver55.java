import javax.swing.JFrame;
import java.awt.*;
public class Driver55
{
   public static void main(String[] args)
   {
      JFrame frame = new JFrame("Asteroids");
      //Dimension screen = new Dimension();   
      //screen = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
      frame.setSize(1000, 800);
      frame.setLocation(175, 150);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setContentPane(new MainMenuPanel());
      frame.setVisible(true);
   }
}
