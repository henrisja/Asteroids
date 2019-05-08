   import java.awt.*;
   import javax.swing.*;
   import java.awt.event.*;
   import java.awt.image.*;
   public class Asteroid
   {
      public boolean remove;
      private double myX;   
      private double myY;
      private double myDiameter;
      private Color myColor; 
      private double myRadius;
      private double dx;
      private double dy;
      private ImageIcon asteroid;
   
      public Asteroid()     
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
      public Asteroid(double x, double y, double d)
      {
         myX = x;
         myY = y;
         myDiameter = d;
         myColor = Color.WHITE;
         myRadius = d/2;
      }
   
      public void setdx(double x)        
      {
         dx = x;
      }
      public void setdy(double y)
      {
         dy = y;
      }
      public double getdx()             
      {
         return dx;
      }
      public double getdy()
      {
         return dy;
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
         //myBuffer.setColor(myColor);
         //Buffer.drawOval((int)(getX() - getRadius()), (int)(getY()-getRadius()), (int)getDiameter(), (int)getDiameter());
         asteroid = new ImageIcon("AsteroidSprite.png");
         myBuffer.drawImage(asteroid.getImage(), (int)(getX())-55, (int)(getY())-60, 110, 105,  null);
               
      
      }
      public void move()
      {
         dx = Math.random() * 3 - 3;
         dy = Math.random() * 3 - 3;   
                  
      }
      public void update()
      {
         setX(getX()+ dx);
         setY(getY()+ dy);   
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
