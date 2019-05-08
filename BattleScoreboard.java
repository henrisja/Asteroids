   import javax.swing.*;
   import java.awt.*;
   import java.util.*;
   import java.io.*;
   public class BattleScoreboard extends JPanel
   {
      private JLabel label1, label2;
      private int x, y;
      private int countJim = 0;
      private int countJonah = 0;
      private String name1, name2; 
      public BattleScoreboard()
      {
         setLayout(new GridLayout(1, 2));
         x = 0;
         y = 0;
         label1 = new JLabel(""+name1);
         //label1.setHorizontalAlignment(SwingConstants.LEFT);
         label1.setFont(new Font("Serif", Font.BOLD, 30));  
         label1.setForeground(Color.WHITE);
         add(label1);
         
         label2 = new JLabel(""+name2 );
         label2.setHorizontalAlignment(SwingConstants.RIGHT);
         label2.setFont(new Font("Serif", Font.BOLD, 30));
         label2.setForeground(Color.WHITE);
         add(label2);
      }
      public void updateRoidJim()
      {
         countJim += 20;
         label1.setText(name1+": " + countJim);
      }
      public void updateHitJim()
      {
         countJim += 100;
         label1.setText(name1+": " + countJim);
      }
      public void updateDieJim()
      {
         countJim -= 100;
         label1.setText(name1+": " + countJim);
      }
      public void updateRoidJonah()
      {
         countJonah += 20;
         label2.setText(name2+": " + countJonah);
      }
      public void updateHitJonah()
      {
         countJonah += 100;
         label2.setText(name2+": " + countJonah);
      }
      public void updateDieJonah()
      {
         countJonah -= 100;
         label2.setText(name2+": " + countJonah);
      } 
   	
      public void name1(String x)
      {
         name1 = x;
      }
      public void name2(String x)	
      {
         name2 = x;
      }
   }

