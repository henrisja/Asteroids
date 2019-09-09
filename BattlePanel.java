import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.util.ArrayList; 
import java.util.Iterator; 
import java.applet.*;
import java.net.*;
import java.io.*;
import javax.swing.JOptionPane;

public class BattlePanel extends JPanel
{
   private static final Color BACKGROUND = new Color(204, 204, 204);
   private BufferedImage myImage;
   private Graphics myBuffer;
   private Timer timer, timer1;
   private Ship jim, jonah;
   private Asteroid roid;
   private int x = 0;
   private boolean up = false;
   private boolean right = false;
   private boolean left = false;
   private boolean space = false;
   private boolean f = false;
   private boolean shift = false;
   private boolean a = false;
   private boolean w = false;
   private boolean d = false;
   private ArrayList<Asteroid> array = new ArrayList<Asteroid>();
   private ArrayList<Laser> jimArr = new ArrayList<Laser>();
   private ArrayList<Laser> jonahArr = new ArrayList<Laser>();
   private ArrayList<FollowRoid> arra = new ArrayList<FollowRoid>();
   private int numroid = 0;
   private int t;
   private BattleScoreboard scoreboard;
   private AudioClip laser, booster;
   private String m, n;
   
   public BattlePanel()
   {
      setLayout(new BorderLayout());
      
      scoreboard = new BattleScoreboard();
      scoreboard.setBackground(Color.BLACK);
      scoreboard.setOpaque(false);
      add(scoreboard, BorderLayout.NORTH);
         
      myImage =  new BufferedImage(1000, 800, BufferedImage.TYPE_INT_RGB);
      myBuffer = myImage.getGraphics();      
      jim = new Ship(100, 400, 0);
      jonah = new Ship(900, 400, 180); 
      timer = new Timer(8, new Listener1());
      timer.start();  
      timer1 = new Timer(120, new Listener2());
      timer1.start();
      addKeyListener(new Key());
      setFocusable(true);
      asteroid();
      try
      {
         laser = Applet.newAudioClip(new File("LASER1.wav").toURI().toURL());
         booster = Applet.newAudioClip(new File("boosters.wav").toURI().toURL());
      }
         catch (Exception e) {System.out.println("ERROR");}
      String n = (
         JOptionPane.showInputDialog( "Type in the 1st players name."));
      scoreboard.name1(n);
      String m = (
         JOptionPane.showInputDialog("Type in the 2nd players name."));
      scoreboard.name2(m);
      
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
      Laser laze = new Laser((int)jim.getX(), (int)jim.getY(), jim.getDir());
      jimArr.add(laze);
      laze.setRadius(7.5);
      laze.fire(jim.getdx(), jim.getdy());
   }    
   public void laser1()
   {
      Laser lazer = new Laser((int)jonah.getX(), (int)jonah.getY(), jonah.getDir());
      jonahArr.add(lazer);
      lazer.setRadius(7.5);
      lazer.fire(jonah.getdx(), jonah.getdy());
   }
   public void paintComponent(Graphics g)
   {
      g.drawImage(myImage, 0, 0, getWidth(), getHeight(), null);
   }
   	
   public double distanceJim(double x1, double y1, double x2, double y2)
   {
      return(Math.sqrt((Math.pow(x2 - x1, 2) + Math.pow(y2 - y1,2))));	
   }
   public double distanceJonah(double x1, double y1, double x2, double y2)
   {
      return(Math.sqrt((Math.pow(x2 - x1, 2) + Math.pow(y2 - y1,2))));	
   }
   
   private class Listener1 implements ActionListener
   {
      public void actionPerformed(ActionEvent e)
      {
         //background        
         myBuffer.setColor(Color.BLACK);
         myBuffer.fillRect(0,0,1000,800); 
         //.5% chance of new Asteroid        
         if(Math.random() < .008 && numroid < 7)
         {
            asteroid();
         }
         if(Math.random() < .005 && numroid < 7)
         {
            followroid();
         }
         	         	 
         	        
         for (FollowRoid follo : arra)
         {
            collide2(follo, jim);
            collide2(follo, jonah);
            if(distanceJim(jim.getX(), jim.getY(), follo.getX(), follo.getY()) < distanceJonah(jonah.getX(), jonah.getY(), follo.getX(), follo.getY()))          
               follo.follow(jim);
            if(distanceJonah(jonah.getX(), jonah.getY(), follo.getX(), follo.getY()) < distanceJim(jim.getX(), jim.getY(), follo.getX(), follo.getY()))
               follo.follow(jonah);          
            follo.update();
            follo.draw(myBuffer);
         }
         
         Iterator<FollowRoid> itr3 = arra.iterator();
         while(itr3.hasNext())
         {
            FollowRoid follo = itr3.next();
            if(follo.remove == true)
            {
               itr3.remove();
            }
         }
         //checks asteroid array list for collide + updates + draws        
         for (Asteroid a : array)
         {
            collide(a, jim);
            collide(a, jonah);
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
         for (Laser laze : jimArr)
         {
            collide(laze, array);
            collide(laze, jonah);
            collide1(laze, arra);
            laze.update();
            laze.draw(myBuffer);
         }
         for (Laser lazer : jonahArr)
         {
            collide(lazer, array);
            collide(lazer, jim);
            collide1(lazer, arra);
            lazer.update();
            lazer.draw(myBuffer);
         }
         //removes lasers when they collide w/ asteroids
         Iterator<Laser> itr1 = jimArr.iterator();
         while(itr1.hasNext())
         {
            Laser la = itr1.next();
            if(la.remove == true)
            {
               itr1.remove();
            }
         }
         Iterator<Laser> itr2 = jonahArr.iterator();
         while(itr2.hasNext())
         {
            Laser lase = itr2.next();
            if(lase.remove == true)
            {
               itr2.remove();
            }
         }
         	
         		
         //key commands for ship and laser       
         if(left == true)
            jim.turnLeft();
         if(right == true)
            jim.turnRight();
         if(up == true)
            jim.thrust(); 
         if(a == true)
            jonah.turnLeft();
         if(d == true)
            jonah.turnRight();
         if(w == true)
            jonah.thrust();
         
         jim.update();
         jonah.update();        
         jim.draw(myBuffer);
         jonah.draw(myBuffer);        
         repaint();
      }
   }
   private class Listener2 implements ActionListener
   {
      public void actionPerformed(ActionEvent e)
      {				
         if(shift == true)
            laser();
         if(space == true)
            laser1();
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
         }
         if(e.getKeyCode() == KeyEvent.VK_SHIFT)
         {
            shift = true;
         }
         if(e.getKeyCode() == KeyEvent.VK_A)
         {
            a = true;
         }
         if(e.getKeyCode() == KeyEvent.VK_D)
         {
            d = true;
         }
         if(e.getKeyCode() == KeyEvent.VK_W)
         {
            w = true;
         }
         if(e.getKeyCode() == KeyEvent.VK_SPACE)
         {
            space = true;
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
         if(e.getKeyCode() == KeyEvent.VK_SHIFT)
         {
            shift = false;
         }
         if(e.getKeyCode() == KeyEvent.VK_A)
         {
            a = false;
         }
         if(e.getKeyCode() == KeyEvent.VK_D)
         {
            d = false;
         }
         if(e.getKeyCode() == KeyEvent.VK_W)
         {
            w = false;
         }
         if(e.getKeyCode() == KeyEvent.VK_SPACE)
         {
            space = false;
         }
      }
      
   }
   private void collide(Laser l, ArrayList<Asteroid> a)
   {
      for (Asteroid b : array)
      {
         double dist = distance(l.getX(), l.getY(), b.getX(), b.getY());
         if(dist < (7.5 + b.getRadius()))
         {
            b.remove = true;
            l.remove = true;
            if(jimArr.contains(l))
               scoreboard.updateRoidJim();
            if(jonahArr.contains(l))
               scoreboard.updateRoidJonah();
         }
      }    
   }
   private double distance(double x1, double y1, double x2, double y2)
   {
      return Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
   }
   private void collide1(Laser laser, ArrayList<FollowRoid> foll)
   {
      for (FollowRoid follo : arra)
      {
         double dist = distance11(laser.getX(), laser.getY(), follo.getX(), follo.getY());
         if(dist < (7.5 + follo.getRadius()))
         {
            follo.remove = true;
            laser.remove = true;
            if(jimArr.contains(laser))
               scoreboard.updateRoidJim();
            if(jonahArr.contains(laser))
               scoreboard.updateRoidJonah();
         }
      }
   }
   private double distance11(double x1, double y1, double x2, double y2)
   {
      return Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
   }
    
   private void collide2(FollowRoid follow, Ship ship)
   {
      double d4 = distance10(follow.getX(), follow.getY(), ship.getSpot1X(), ship.getSpot1Y());
      if(d4 <= follow.getRadius())
      {
         if(ship == jim)        
         {
            ship.setX(100);
            ship.setY(400);
            ship.setDx(0);
            ship.setDy(0);
            ship.setDir(0);
            scoreboard.updateDieJim();
         }  
         if(ship == jonah)        
         {
            ship.setX(900);
            ship.setY(400);
            ship.setDx(0);
            ship.setDy(0);
            ship.setDir(180);
            scoreboard.updateDieJonah();
         }         	
         follow.remove = true;
      }
      double d5 = distance10(follow.getX(), follow.getY(), ship.getSpot2X(), ship.getSpot2Y());
      if(d5 <= follow.getRadius())
      {
         if(ship == jim)        
         {
            ship.setX(100);
            ship.setY(400);
            ship.setDx(0);
            ship.setDy(0);
            ship.setDir(0);
            scoreboard.updateDieJim();
         }   
         if(ship == jonah)        
         {
            ship.setX(900);
            ship.setY(400);
            ship.setDx(0);
            ship.setDy(0);
            ship.setDir(180);
            scoreboard.updateDieJonah();
         }   
         follow.remove = true;
         	
      }
      double d6 = distance10(follow.getX(), follow.getY(), ship.getSpot3X(), ship.getSpot3Y());
      if(d6 <= follow.getRadius())
      {
         if(ship == jim)        
         {
            ship.setX(100);
            ship.setY(400);
            ship.setDx(0);
            ship.setDy(0);
            ship.setDir(0);
            scoreboard.updateDieJim();
         } 
         if(ship == jonah)        
         {
            ship.setX(900);
            ship.setY(400);
            ship.setDx(0);
            ship.setDy(0);
            ship.setDir(180);
            scoreboard.updateDieJonah();
         }     
         follow.remove = true;
      }
   }
   private double distance10(double x1, double y1, double x2, double y2)
   {
      return(Math.sqrt((Math.pow(x2 - x1, 2) + Math.pow(y2 - y1,2))));	
   }
    
   private void collide(Laser las, Ship sh)
   {
      double d1 = distance2(las.getX(), las.getY(), sh.getSpot1X(), sh.getSpot1Y());
      if(d1 <= 7.5)
      {
         if(sh == jim)        
         {	
            sh.setX(100);
            sh.setY(400);
            sh.setDir(0);
            scoreboard.updateDieJim();
            scoreboard.updateHitJonah();
         }
         if(sh == jonah)
         {
            sh.setX(900);
            sh.setY(400);
            sh.setDir(180);
            scoreboard.updateDieJonah();
            scoreboard.updateHitJim();
         }        
         sh.setDx(0);
         sh.setDy(0);
         las.remove = true;
      }
      double d2 = distance2(las.getX(), las.getY(), sh.getSpot2X(), sh.getSpot2Y());
      if(d2 <= 7.5)
      {
         if(sh == jim)        
         {	
            sh.setX(100);
            sh.setY(400);
            sh.setDir(0);
            scoreboard.updateDieJim();
            scoreboard.updateHitJonah();
         }
         if(sh == jonah)
         {
            sh.setX(900);
            sh.setY(400);
            sh.setDir(180);
            scoreboard.updateDieJonah();
            scoreboard.updateHitJim();
         }        
         sh.setDx(0);
         sh.setDy(0);
         las.remove = true;
         
         	
      }
      double d3 = distance2(las.getX(), las.getY(), sh.getSpot3X(), sh.getSpot3Y());
      if(d3 <= 7.5)
      {
         if(sh == jim)        
         {	
            sh.setX(100);
            sh.setY(400);
            sh.setDir(0);
            scoreboard.updateDieJim();
            scoreboard.updateHitJonah();
         }
         if(sh == jonah)
         {
            sh.setX(900);
            sh.setY(400);
            sh.setDir(180);
            scoreboard.updateDieJonah();
            scoreboard.updateHitJim();
         }        
         sh.setDx(0);
         sh.setDy(0);
         las.remove = true;
      }
   }
   private double distance2(double x1, double y1, double x2, double y2)
   {
      return(Math.sqrt((Math.pow(x2 - x1, 2) + Math.pow(y2 - y1,2))));	
   }
   
   
   		
   private void collide(Asteroid a, Ship s)
   {
      double d1 = distance1(a.getX(), a.getY(), s.getSpot1X(), s.getSpot1Y());
      if(d1 <= a.getRadius())
      {
         if(s == jim)        
         {	
            s.setX(100);
            s.setY(400);
            s.setDir(0);
            scoreboard.updateDieJim();
         }
         if(s == jonah)
         {
            s.setX(900);
            s.setY(400);
            s.setDir(180);
            scoreboard.updateDieJonah();
         }        
         s.setDx(0);
         s.setDy(0);
         a.remove = true;
      }
      double d2 = distance1(a.getX(), a.getY(), s.getSpot2X(), s.getSpot2Y());
      if(d2 <= a.getRadius())
      {
         if(s == jim)        
         {	
            s.setX(100);
            s.setY(400);
            s.setDir(0);
            scoreboard.updateDieJim();
         }
         if(s == jonah)
         {
            s.setX(900);
            s.setY(400);
            s.setDir(180);
            scoreboard.updateDieJonah();
         }        
         s.setDx(0);
         s.setDy(0);
         a.remove = true;
         
         	
      }
      double d3 = distance1(a.getX(), a.getY(), s.getSpot3X(), s.getSpot3Y());
      if(d3 <= a.getRadius())
      {
         if(s == jim)        
         {	
            s.setX(100);
            s.setY(400);
            s.setDir(0);
            scoreboard.updateDieJim();
         }
         if(s == jonah)
         {
            s.setX(900);
            s.setY(400);
            s.setDir(180);
            scoreboard.updateDieJonah();
         }        
         s.setDx(0);
         s.setDy(0);
         a.remove = true;
      }
   }
   private double distance1(double x1, double y1, double x2, double y2)
   {
      return(Math.sqrt((Math.pow(x2 - x1, 2) + Math.pow(y2 - y1,2))));	
   }
}
