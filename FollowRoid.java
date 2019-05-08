   import java.awt.*;
   import javax.swing.*;
   import java.awt.event.*;
   import java.awt.image.*;
   public class FollowRoid
   {
      public boolean remove;
      private double myX;   
      private double myY;
      private double myDiameter;
      private Color myColor; 
      private double myRadius;
      private ImageIcon asteroid;
   
      public FollowRoid()     
      {
         int x = (int)(Math.random() * 4 + 1);      
         if(x == 1)
         {
            myX = 100;
            myY = 100;
         }
         if(x == 2)
         {
            myX = 900;
            myY = 100;
         }
         if(x == 3)
         {
            myX = 100;
            myY = 700;
         }
         if(x == 4)
         {
            myX = 900;
            myY = 700;
         }
         myDiameter = 100;
         myColor = Color.WHITE;
         myRadius = myDiameter/2;
      }
      public FollowRoid(double x, double y, double d, Color c)
      {
         myX = x;
         myY = y;
         myDiameter = d;
         myColor = c;
         myRadius = d/2;
      }   
   
      public double getX() 
      { 
         return myX;
      }
      public double getY()      
      { 
         return myY;
      }
      public double getDiameter() 
      { 
         return myDiameter;
      }
      public Color getColor() 
      { 
         return myColor;
      }
      public double getRadius() 
      { 
         return myRadius;
      }
   
      public void setX(double x)
      {
         myX = x;
      } 
      public void setY(double y)
      {
         myY = y;
      } 
      public void setColor(Color c)
      {
         myColor = c;
      }
      public void setDiameter(double d)
      {
         myDiameter = d;
         myRadius = d/2;
      }
      public void setRadius(double r)
      {
         myRadius = r;
         myDiameter = 2*r;
      }
      public void draw(Graphics myBuffer) 
      {
         //myBuffer.setColor(Color.RED.brighter());
        // myBuffer.drawOval((int)(getX() - getRadius()), (int)(getY()-getRadius()), (int)getDiameter(), (int)getDiameter());
         asteroid = new ImageIcon("asteroidfollow.png");
         myBuffer.drawImage(asteroid.getImage(), (int)(getX())-55, (int)(getY())-55, 125, 120,  null);
  
	   }
      public void follow(Ship s)
      {
         if(getX() < s.getX())
            setX(getX() + 1);
         if(getX() > s.getX())
            setX(getX() - 1);
         if(getY() > s.getY())
            setY(getY() -1);
         if(getY() < s.getY())
            setY(getY() + 1);
      }
      public void update()
      {
         setX(getX());
         setY(getY());   
         if(getX() < -1)
            setX(1000);
         if(getX() > 1002)
            setX(0);
         if(getY() < -1)
            setY(800);
         if(getY() > 802)
            setY(0);       
      }
   }  
