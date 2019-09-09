   import javax.swing.*;
   import java.awt.*;
   import java.awt.event.*;
   import java.awt.image.*;
   import java.util.ArrayList; 
   import java.util.Iterator; 
   import java.applet.*;
   import java.net.*;
   import java.io.*;
   public class AsteroidsPanel extends JPanel
   {
      private static final Color BACKGROUND = new Color(204, 204, 204);
      private BufferedImage myImage;
      private Graphics myBuffer;
      private Timer timer, timer1;
      private Ship jim;
      private Asteroid roid;
      private int x = 0;
      private boolean up = false;
      private boolean right = false;
      private boolean left = false;
      private boolean space = false;
      private boolean f = false;
      private ArrayList<Asteroid> array = new ArrayList<Asteroid>();
      private ArrayList<Laser> arr = new ArrayList<Laser>();
      private ArrayList<FollowRoid> arra = new ArrayList<FollowRoid>();
      private int numroid = 0;
      private int t;
      private ClassicScoreboard scoreboard1;
      private LaserScoreboard scoreL;
      private AudioClip laser, booster;
         
      public AsteroidsPanel()
      {
         setLayout(new BorderLayout());
      
         scoreboard1 = new ClassicScoreboard();
         scoreboard1.setBackground(Color.BLACK);
         scoreboard1.setOpaque(false);
         add(scoreboard1, BorderLayout.NORTH);     
         
         scoreL = new LaserScoreboard();
         scoreL.setBackground(Color.BLACK);
         scoreL.setOpaque(false);
         add(scoreL, BorderLayout.SOUTH);
      	
         myImage =  new BufferedImage(1000, 800, BufferedImage.TYPE_INT_RGB);
         myBuffer = myImage.getGraphics();
         scoreL.dis();
         jim = new Ship();
         timer = new Timer(8, new Listener1());
         timer.start();  
         timer1 = new Timer(100, new Listener2());
         timer1.start();
         addKeyListener(new Key());
         setFocusable(true);
         asteroid();
         try
         {
            AudioClip music = Applet.newAudioClip(new File("music.wav").toURI().toURL());
            music.loop();
            laser = Applet.newAudioClip(new File("LASER1.wav").toURI().toURL());
            booster = Applet.newAudioClip(new File("boosters.wav").toURI().toURL());
         }
            catch (Exception e) {System.out.println("ERROR");}
      
      }
      public void asteroid()
      {
         Asteroid temp = new Asteroid();
         array.add(temp);
         temp.move();
      }
      public void followroid()
      {
         FollowRoid temp1 = new FollowRoid();
         arra.add(temp1);
      }
      public void laser()
      {
         if(scoreL.getCount() == 0)
         {
            scoreL.setRed();
            return;
         }
         else
         {
            Laser laze = new Laser((int)jim.getX(), (int)jim.getY(), jim.getDir());
            arr.add(laze);
            laze.fire(jim.getdx(), jim.getdy());
            scoreL.updateDown();
         }
      }    
      public void paintComponent(Graphics g)
      {
         g.drawImage(myImage, 0, 0, getWidth(), getHeight(), null);
      }
          
      private class Listener1 implements ActionListener
      {
         public void actionPerformed(ActionEvent e)
         {
         	//background        
            myBuffer.setColor(Color.BLACK);
            myBuffer.fillRect(0,0,1000,800); 
         	//.5% chance of new Asteroid        
            if(Math.random() < .0025 && numroid < 7)
            {
               asteroid();
            }
            if(Math.random() < .0005 && numroid < 7)
            {
               followroid();
            }
         	//key commands for ship and laser       
            if(left == true)
               jim.turnLeft();
            if(right == true)
               jim.turnRight();
            if(up == true)
               jim.thrust(); 
         	//checks collisions on follow roid moves it too
            for (FollowRoid follo : arra)
            {
               collide2(follo, jim);
               follo.follow(jim);
               follo.update();
               follo.draw(myBuffer);
            }
         	//removes followroids
            Iterator<FollowRoid> itr2 = arra.iterator();
            while(itr2.hasNext())
            {
               FollowRoid fol = itr2.next();
               if(fol.remove == true)
               {
                  itr2.remove();
               }
            }
         	//checks asteroid array list for collide + updates + draws        
            for (Asteroid a : array)
            {
               collide(a, jim);
               a.update();
               a.draw(myBuffer);
            }
         	//removes asteroids when they hit the ship
            Iterator<Asteroid> itr = array.iterator(); 
            while(itr.hasNext()) 
            { 
               Asteroid a = itr.next(); 
               if(a.remove == true)
               { 
                  itr.remove(); 
               } 
            }
         	//checks laser array list for collision + draws + updates     	
            for (Laser l : arr)
            {
               collide(l, array);
               collide1(l, arra);
               l.update();
               l.draw(myBuffer);
            }
         	//removes lasers when they collide w/ asteroids
            Iterator<Laser> itr1 = arr.iterator();
            while(itr1.hasNext())
            {
               Laser la = itr1.next();
               if(la.remove == true)
               {
                  itr1.remove();
               }
            }
          	//removes lasers when the collide w/ follow roids  
            Iterator<Laser> itr3 = arr.iterator();
            while(itr3.hasNext())
            {
               Laser laserr = itr3.next();
               if(laserr.remove == true)
               {
                  itr3.remove();
               }
            }
            
            jim.update();
            jim.draw(myBuffer);
            repaint();
         }
      }
      private class Listener2 implements ActionListener
      {
         public void actionPerformed(ActionEvent e)
         {				
            if(space == true)
               laser();
         }
      }	
      private class Key extends KeyAdapter
      {
         public void keyPressed(KeyEvent e)
         {
            if(e.getKeyCode() == KeyEvent.VK_LEFT)
            {
               left = true;        
            }
            if(e.getKeyCode() == KeyEvent.VK_RIGHT)
            {
               right = true;
            }
            if(e.getKeyCode() == KeyEvent.VK_UP)
            {
               up = true;          
               booster.play();
               scoreL.updateUp();        
            }
            if(e.getKeyCode() == KeyEvent.VK_SPACE)
            {
               space = true;
               laser.play();        
            }
          
         }
         public void keyReleased(KeyEvent e)
         {
            if(e.getKeyCode() == KeyEvent.VK_LEFT)
            {
               left = false;
            }
            if(e.getKeyCode() == KeyEvent.VK_RIGHT)
            {
               right = false;
            }
            if(e.getKeyCode() == KeyEvent.VK_UP)
            {
               up = false;    
            }
            if(e.getKeyCode() == KeyEvent.VK_SPACE)
            {
               space = false;
               laser.stop();        
            }
         }
      
      }
      private void collide(Laser l, ArrayList<Asteroid> a)
      {
         for (Asteroid b : array)
         {
            double dist = distance(l.getX(), l.getY(), b.getX(), b.getY());
            if(dist < (5 + b.getRadius()))
            {
               b.remove = true;
               l.remove = true;
               scoreboard1.updateRoid();
            }
         }    
      }
      private void collide1(Laser laser, ArrayList<FollowRoid> foll)
      {
         for (FollowRoid follo : arra)
         {
            double dist = distance(laser.getX(), laser.getY(), follo.getX(), follo.getY());
            if(dist < (5 + follo.getRadius()))
            {
               follo. remove = true;
               laser.remove = true;
               scoreboard1.updateRoid();
            }
         }
      }
   		
      private double distance(double x1, double y1, double x2, double y2)
      {
         return Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
      }
    
      private void collide2(FollowRoid follow, Ship ship)
      {
         double d4 = distance10(follow.getX(), follow.getY(), ship.getSpot1X(), ship.getSpot1Y());
         if(d4 <= follow.getRadius())
         {
            ship.setX(500);
            ship.setY(400);
            ship.setDx(0);
            ship.setDy(0);
            ship.setDir(270);
            scoreboard1.updateDie();
            follow.remove = true;
         }
         double d5 = distance10(follow.getX(), follow.getY(), ship.getSpot2X(), ship.getSpot2Y());
         if(d5 <= follow.getRadius())
         {
            ship.setX(500);
            ship.setY(400);
            ship.setDx(0);
            ship.setDy(0);
            ship.setDir(270);
            scoreboard1.updateDie();
            follow.remove = true;
         	
         }
         double d6 = distance10(follow.getX(), follow.getY(), ship.getSpot3X(), ship.getSpot3Y());
         if(d6 <= follow.getRadius())
         {
            ship.setX(500);
            ship.setY(400);
            ship.setDx(0);
            ship.setDy(0);
            ship.setDir(270);
            scoreboard1.updateDie();
            follow.remove = true;
         }
      }
      private double distance10(double x1, double y1, double x2, double y2)
      {
         return(Math.sqrt((Math.pow(x2 - x1, 2) + Math.pow(y2 - y1,2))));	
      }
     
      private void collide(Asteroid a, Ship s)
      {
         double d1 = distance1(a.getX(), a.getY(), s.getSpot1X(), s.getSpot1Y());
         if(d1 <= a.getRadius())
         {
            s.setX(500);
            s.setY(400);
            s.setDx(0);
            s.setDy(0);
            s.setDir(270);
            scoreboard1.updateDie();
            a.remove = true;
         }
         double d2 = distance1(a.getX(), a.getY(), s.getSpot2X(), s.getSpot2Y());
         if(d2 <= a.getRadius())
         {
            s.setX(500);
            s.setY(400);
            s.setDx(0);
            s.setDy(0);
            s.setDir(270);
            scoreboard1.updateDie();
            a.remove = true;
         	
         }
         double d3 = distance1(a.getX(), a.getY(), s.getSpot3X(), s.getSpot3Y());
         if(d3 <= a.getRadius())
         {
            s.setX(500);
            s.setY(400);
            s.setDx(0);
            s.setDy(0);
            s.setDir(270);
            scoreboard1.updateDie();
            a.remove = true;
         }
      }
      private double distance1(double x1, double y1, double x2, double y2)
      {
         return(Math.sqrt((Math.pow(x2 - x1, 2) + Math.pow(y2 - y1,2))));	
      }
   }
