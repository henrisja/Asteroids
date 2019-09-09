import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.image.*;
   
public class Laser
{
   private double myDir;
   private double myX;
   private double myY;
   private double myDx;
   private double myDy;
   private double myRadius;
   private double myDiameter;
   public boolean remove = false;    
   private ImageIcon laser;
   
   //private fields, all ints, for a Bumper
   //hint: the "location" of the bumper begins at its top left corner.      
    
   
   
   //constructors
   public Laser()         //default constructor
   {
      myX = 500;
      myY = 400;
      myDir = 270; 
      myDx = 0;
      myDy = 0;
      myRadius = 5;
      myDiameter = 10;
   }
   public Laser(double x, double y, double dir)
   {
      myX = x;
      myY = y;
      myDir = dir;
      myDx = 0;
      myDy = 0;   
      myRadius = 5;
      myDiameter = 10;
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
	public double getRadius()
	{
		return myRadius;
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
	public void setRadius(double r)
	{
		myRadius = r;
	}
      
   // instance methods
   // chooses a random (x,y) location.  Bumper stays entirely in the window.
    		
   			   
   public void fire(double startDx, double startDy)
   {
      setDx((7*(Math.cos(getDir() * Math.PI/180)) + startDx));
      setDy((7*(Math.sin(getDir() * Math.PI/180)) + startDy));
   }     
   public void update() 
   {
      setX(getX() + getdx());
      setY(getY() + getdy());
   }
   public void draw(Graphics myBuffer) 
   {
      myBuffer.setColor(Color.WHITE);
      myBuffer.fillOval((int)(getX() - myRadius), (int)(getY()-myRadius), 10, 10);
      //laser = new ImageIcon("LaserRay.png");
      //myBuffer.drawImage(laser.getImage(), (int)(getX())-5, (int)(getY())-5, 15, 15,  null);
      
   }
}