   import javax.swing.*;
   import java.awt.*;
   import java.awt.event.*;
   import java.awt.image.*;
   import javax.swing.JOptionPane;
   import java.util.*;
   import java.io.*;   
   public class MainMenuPanel extends JPanel
   {
      private JButton button1; 
      private JButton button2; 
      private JButton button3;
      private JButton button4;
      private JButton button5;
      private JLabel label;
      private AsteroidsPanel a;
      private BattlePanel b;    
      private int max, MAX, MAX1, temp, temp1;
     
      public MainMenuPanel()
      {
         
         setLayout(new BorderLayout());  
         button1 = new JButton("CLASSIC");
         button1.addActionListener(new Listener1());
         button1.setBackground(Color.BLACK);
         button1.setForeground(Color.WHITE);  
         button1.setBorderPainted(false);
       
         add(button1, BorderLayout.WEST);
         
         button2 = new JButton("BATTLE");
         button2.addActionListener(new Listener2());
         button2.setBackground(Color.BLACK);
         button2.setForeground(Color.WHITE);  
         button2.setBorderPainted(false);
         add(button2, BorderLayout.EAST);
      
         //ImageIcon instructions = new ImageIcon("instructions.png");
         button3 = new JButton("instructions");
         button3.addActionListener(new Listener3());
         button3.setBackground(Color.BLACK);
         button3.setForeground(Color.WHITE);  
         button3.setBorderPainted(false);
         add(button3,BorderLayout.SOUTH);
      
      
         //ImageIcon highscores = new ImageIcon("highscores.png");
         button4 = new JButton("highscore");
         button4.setBackground(Color.BLACK);
         button4.setForeground(Color.WHITE);  
         button4.addActionListener(new Listener4());
         button4.setBorderPainted(false);
         add(button4,BorderLayout.NORTH);
      
         ImageIcon title = new ImageIcon("title.PNG");
         button5 = new JButton(title);
         button5.setBackground(Color.BLACK);
         button5.setForeground(Color.WHITE);  
         button5.setBorderPainted(false);
        //button5.addActionListener(new Listener5()); 	
         
         add(button5, BorderLayout.CENTER );
      
      }
      
      
      public void paintComponent(Graphics g)
      {
         g.setColor(Color.BLACK);
         g.fillRect(0, 0, 1000, 800);
      }
   
      private class Listener1 implements ActionListener
      {
         public void actionPerformed(ActionEvent e)
         {
            remove(button1);
            remove(button2);
            remove(button3);
            remove(button4);
            remove(button5);
            a = new AsteroidsPanel();
            a.setSize(1000, 800);
            add(a);
            a.setVisible(true);
            a.requestFocus();
         	 
         
         	 
            repaint();
             
         }
      }
      private class Listener2 implements ActionListener
      {
         public void actionPerformed(ActionEvent e)
         {
            remove(button1);
            remove(button2);
            remove(button3);
            remove(button4);
            remove(button5);
            b = new BattlePanel();
            b.setSize(1000, 800);
            add(b);
            b.setVisible(true);
            b.requestFocus();
         	 
            repaint();
             
         }
      }
        
      private class Listener3 implements ActionListener
      {
         public void actionPerformed(ActionEvent e)
         {
            JOptionPane.showMessageDialog(null, "You have been selected to pilot a ship through the cosmos. You must avoid asteroids at all costs. Avoid the asteroids by doging and shooting them, survive as long as you can! \n In order to pilot your ship you will use the up arrow to move your ship in the direction it is facing. \n The left and right arrows will be used to spin your ship and the space button to launch a laser. \n If you choose BATTLE you can dogfight on the same keyboeard. \n  If you choose Battle then the left player uses W,A,D to move and space to launch a laser, the right player uses the arrows to move and shift to launch a laser. \n To quit hit end in JGrasp.\n Good luck pilot. \n Created by Jim Henris and Jonah Casebeer 8/7/2013 \n Special thanks to Nick, Carrie and Dr.Torbert.");
         }
      }
      private class Listener4 implements ActionListener
      {
         public void actionPerformed(ActionEvent e)
         { 
            
            try
            {
               Scanner infile = new Scanner( new File("scores.txt") );
               int count = 0;          
            
               while(infile.hasNext())
               {
                  int a = infile.nextInt();
                  count++;
               }       
               infile = new Scanner( new File("scores.txt") );	int[] array = new int[count];  
            
               for(int x = 0; x< count; x++)
               {
                  array[x] = infile.nextInt();
               }
               infile.close();
            
            
               for(int k = 1; k<array.length; k++)
               {
               
                  if (array[k]>array[max])
                     max = k;
                  else
                     max = max;
                  MAX =  array[max];
               }
               
              
               JOptionPane.showMessageDialog(null,"The high score is "+ MAX);            
            }
               catch (Exception a){};     
         }
      }
           
          
           
      
   }