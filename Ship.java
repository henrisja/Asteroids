   import java.awt.*;
	   
   public class Ship
   {
      private double myDir;
      private double myX;
      private double myY;
      private double myDx;
      private double myDy;
      private double t;    
      private double a = 0;
      private int b;
      private double c = 0;
      private double spot1X, spot1Y, spot2X, spot2Y, spot3X, spot3Y;
      
    //private fields, all ints, for a Bumper
    //hint: the "location" of the bumper begins at its top left corner.      
    
   
   
     //constructors
      public Ship()         //default constructor
      {
         myX = 500;
         myY = 400;
         myDir = 270; 
         myDx = 0;
         myDy = 0;
      }
      public Ship(double x, double y, double dir)
      {
         myX = x;
         myY = y;
         myDir = dir;
         myDx = 0;
         myDy = 0;   
      }
      
     // accessor methods  (one for each field)
      public double getX()
      {
         return myX;
      }
      public double getY()
      {
         return myY;
      }
      public double getDir()
      {
         return myDir;
      }
      public double getdx()
      {
         return myDx;
      }
      public double getdy()
      {
         return myDy;
      }
   // modifier methods  (one for each field)
      public void setX(double x)
      {
         myX = x;
      }
      public void setY(double y)
      {
         myY = y;
      }
      public void setDir(double dir)
      {
         myDir = dir;
      }
      public void setDx(double dx)
      {
         myDx = dx;
      }
      public void setDy(double dy)
      {
         myDy = dy;
      }
      
     // instance methods
     // chooses a random (x,y) location.  Bumper stays entirely in the window.
    		
   			   
      public void draw(Graphics myBuffer) 
      {
         spot1X = (getX() + (Math.cos((getDir()) * Math.PI/180) * 15));
         spot1Y = (getY() + (Math.sin((getDir()) * Math.PI/180) * 15));
         spot2X = (getX() + (Math.cos((getDir()+210) * Math.PI/180) * 15));
         spot2Y = (getY() + (Math.sin((getDir()+210) * Math.PI/180) * 15));
         spot3X = (getX() + (Math.cos((getDir()+150) * Math.PI/180) * 15));
         spot3Y = (getY() + (Math.sin((getDir()+150) * Math.PI/180) * 15));
         myBuffer.setColor(Color.WHITE);
         int xPoints[] = {(int)spot1X, (int)spot2X, (int)spot3X};
         int yPoints[] = {(int)spot1Y, (int)spot2Y, (int)spot3Y};
         myBuffer.fillPolygon(xPoints, yPoints, 3); 
      }
      
      public double getSpot1X()
      {
         return spot1X;
      }
      public double getSpot1Y()
      {
         return spot1Y;
      }
      public double getSpot2X()
      {
         return spot2X;
      }
      public double getSpot2Y()
      {
         return spot2Y;
      }
      public double getSpot3X()
      {
         return spot3X;
      }
      public double getSpot3Y()
      {
         return spot3Y;
      } 
   		
     
      public void turnLeft()
      {
         setDir(getDir() - 3);
      	     
      }
      public void turnRight()
      {
         setDir(getDir() + 3);
      }
      public void thrust()
      {
         setDx(getdx() + (0.05*(Math.cos(getDir()*Math.PI/180))));
         setDy(getdy() + (0.05*(Math.sin(getDir()*Math.PI/180))));
      }
      public void update()
      {
         setX(getX()+ myDx);
         setY(getY()+ myDy);  
         if(getX()<=0)
            setX(1000);
         else if(getX()>=1000)
            setX(0);
         if(getY()<=0)
            setY(800);
         else if(getY()>= 800)
            setY(0);  
      }  
   }
		
