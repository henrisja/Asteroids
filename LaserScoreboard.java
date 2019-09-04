import javax.swing.*;
import java.awt.*;
public class LaserScoreboard extends JPanel
{
   private JLabel label1;
   private int x, y;
   private int count = 6;
   private int Array[];  
   public LaserScoreboard()
   {
      setLayout(new GridLayout(1, 2));
         			
      label1 = new JLabel("Ammo: ");
      //label1.setHorizontalAlignment(SwingConstants.LEFT);
      label1.setFont(new Font("Serif", Font.BOLD, 30));  
      label1.setForeground(Color.WHITE);
      add(label1);
   }
    
   public int updateUp()
   {
      label1.setForeground(Color.WHITE);
      label1.setFont(new Font("Serif", Font.BOLD, 30));
      if(count >= 12)
         count = 12;
      else 
         count += 3;
      label1.setText("Ammo: " + count);
      return count;  
      
   }
   public int updateDown()
   {
      label1.setForeground(Color.WHITE);
      label1.setFont(new Font("Serif", Font.BOLD, 30));
      if(count == 0)
         count = 0;
      else 
         count --;
      label1.setText("Ammo: " + count);
      return count;
      
   }
   public void dis()
   {
      label1.setText("Ammo: " + count);
   }
   public int getCount()
   {
      return count;
   }
   public void setRed()
   {
      label1.setForeground(Color.RED.brighter());
      label1.setFont(new Font("Serif", Font.BOLD, 50));
      label1.setText("AMMO EMPTY");
   }
}

