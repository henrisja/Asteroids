   import javax.swing.*;
   import java.awt.*;
   import java.io.*;      //the File class
   import java.util.*;    //the Scanner class

   public class ClassicScoreboard extends JPanel
   {
      private JLabel label1, label2;
      private int x, y;
      private int count = 0;  
      private int NUMITEMS;    
      private int MAX;
      private int max = 0;
      public ClassicScoreboard()
      {
         
         try{
            //FileWriter fstream = new FileWriter("scores.txt",true);
            System.setOut(new PrintStream(new FileOutputStream("scores.txt",true)));
         
         
         }
            catch (Exception e) {System.out.println("ERROR");}
      
         setLayout(new GridLayout(1, 2));
         x = 0;
         y = 0;
      	
         label1 = new JLabel("Score: ");
         //label1.setHorizontalAlignment(SwingConstants.LEFT);
         label1.setFont(new Font("Serif", Font.BOLD, 30));  
         label1.setForeground(Color.WHITE);
         add(label1);
      	
         label2 = new JLabel("High: ");
         label2.setHorizontalAlignment(SwingConstants.RIGHT);
         label2.setFont(new Font("Serif", Font.BOLD, 30));  
         label2.setForeground(Color.WHITE);
         add(label2);
      //////////////////   
              /////////////////
      }
      public void updateRoid()
      {     
         count += 20;
         label1.setText("Score: " + count);
         arrayDO(); 
         label2.setText("High:"+MAX);
         System.out.println(""+count);
      
      }
      public void updateDie()
      {
             
         count -= 100;
         label1.setText("Score: " + count);
         arrayDO();
         label2.setText("High: "+MAX);
         System.out.println(""+count);
      
      }
   	
      public void updateOut()
      {
             
         count -= 5;
         label1.setText("Score: " + count);			
         arrayDO();  
         label2.setText("High: "+MAX);
         System.out.println(""+count);
      
      }
   
      public void arrayDO()
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
               
         }
            catch (Exception e){};       
      }
   
   }