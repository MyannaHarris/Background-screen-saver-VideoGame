// ***********************************************
// Program Identification
// Name: myanna harris
// Class: java
// Date: 15-2-12
// File Location: H:\My Documents\java\
// ***********************************************

// ***********************************************
// Program Abstract
// This program reads in a textfile then creates a background according
// to what is in the textfile
// ***********************************************

// ***********************************************
// Program Variable Dictionary
// in the program
// ***********************************************
// 100 Version
// Lab28c.java

import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.JOptionPane;

public class Lab28c
{
	public static void main(String args[]) throws IOException
	{
		GfxApp gfx = new GfxApp();
		gfx.setSize(1024,768);
		gfx.addWindowListener(new WindowAdapter() {public void
		windowClosing(WindowEvent e) {System.exit(0);}});


		gfx.readFile();

		gfx.show();
	}
}

class GfxApp extends Frame
{
	int numRows = 36;  //  35 Rows are displayed.  The top row
                     //  (row 0) is hidden behind the title bar.
	int numCols = 50; //number of columns in the background
	String background[]; //holds the characters that are read in
	String fileName; //is the name of the file being read in

	public void paint(Graphics g)
	{
       for (int r = 0; r < numRows; r++)
           for (int c = 0; c < numCols; c++)
               switch(background[r].charAt(c))
               {
               	case '.' : drawSpace(g,r,c); break;
						case '=' : drawBeam(g,r,c); break;
						case '#' : drawLadder(g,r,c); break;
						case 'B' : drawBarrel(g,r,c); break;
						case 'H' : drawHammer(g,r,c); break;
              		default  : drawUnknown(g,r,c);
               }
	}

	public void readFile() throws IOException
	{
		background = new String[numRows]; //defines the number of columns in the background
		int x = 0; //counts the index of the background array
		fileName = "DK.dat";
		String inString = ""; //temporarily holds the characters being read in from the textfile
		BufferedReader inStream = new BufferedReader(new FileReader(fileName));
		while((inString = inStream.readLine()) != null)
		{
			background[x] = inString;
			x++;
		}
		inStream.close();
	}

	public void drawSpace(Graphics g,int y,int x)
	{
		int xCord = 10 + (x*20); //the xCoordinate that the character's position refers to
		int yCord = 10 + (y*20); //the yCoordinate that the character's position refers to

		g.setColor(Color.black);
		g.fillRect(xCord,yCord,20,20);
	}

	public void drawBeam(Graphics g,int y,int x)
	{
		int xCord = 10 + (x*20);
		int yCord = 10 + (y*20);

		g.setColor(Color.red);
		g.fillRect(xCord,yCord,20,20);
		g.setColor(Color.black);
		g.fillOval(xCord+5,yCord+5,10,10);

		g.setColor(Color.white);
		g.fillArc(xCord-1,yCord+1,2,2,90,-180);
		g.fillOval(xCord+4,yCord+1,2,2);
		g.fillOval(xCord+9,yCord+1,2,2);
		g.fillOval(xCord+14,yCord+1,2,2);

		g.fillArc(xCord-1,yCord+16,2,2,90,-180);
		g.fillOval(xCord+4,yCord+16,2,2);
		g.fillOval(xCord+9,yCord+16,2,2);
		g.fillOval(xCord+14,yCord+16,2,2);
	}

	public void drawLadder(Graphics g,int y,int x)
	{
		int xCord = 10 + (x*20);
		int yCord = 10 + (y*20);

		g.setColor(Color.black);
		g.fillRect(xCord,yCord,20,20);
		g.setColor(Color.white);
		g.fillRect(xCord,yCord,5,20);
		g.fillRect(xCord+15,yCord,5,20);
		g.fillRect(xCord+7,yCord+5,10,5);
	}

	public void drawBarrel(Graphics g,int y,int x)
	{
		int xCord = 10 + (x*20);
		int yCord = 10 + (y*20);

		g.setColor(Color.black);
		g.fillRect(xCord,yCord,20,20);

		g.setColor(new Color(153, 102, 0)); //brown
		g.fillRect(xCord+6,yCord,8,20);
		g.fillArc(xCord,yCord,12,20,90,180);
		g.fillArc(xCord+4,yCord,16,20,90,-180);
		g.setColor(new Color(200, 200, 200)); //grey
		g.fillRect(xCord+5,yCord,10,1);

		g.setColor(Color.black);
		Polygon tri1 = new Polygon(); //dark shadow
		tri1.addPoint(xCord+14,yCord+5);
		tri1.addPoint(xCord+14,yCord+17);
		tri1.addPoint(xCord+17,yCord+11);
		g.fillPolygon(tri1);

		g.setColor(Color.white);
		Polygon tri2 = new Polygon(); //light triangle
		tri2.addPoint(xCord+6,yCord+1);
		tri2.addPoint(xCord+6,yCord+17);
		tri2.addPoint(xCord+3,yCord+9);
		g.fillPolygon(tri2);
	}

	public void drawHammer(Graphics g,int y,int x)
	{
		int xCord = 10 + (x*20);
		int yCord = 10 + (y*20);

		g.setColor(Color.black);
		g.fillRect(xCord,yCord,20,20);

		g.setColor(Color.green);
		g.fillRect(xCord,yCord,20,10);
		g.setColor(new Color(0, 100, 0)); //dark green
		g.fillRect(xCord,yCord,20,1);
		g.fillRect(xCord+19,yCord,1,10);
		g.setColor(new Color(0, 150, 0)); //darker green
		g.fillRect(xCord,yCord+1,19,1);
		g.fillRect(xCord+18,yCord+1,1,9);
		g.setColor(Color.black);
		g.fillArc(xCord+14,yCord+2,8,7,90,180);

		g.setColor(Color.yellow);
		g.fillRect(xCord+7,yCord+10,5,10);
	}

	public void drawUnknown(Graphics g,int y,int x)
	{
		int xCord = 10 + (x*20);
		int yCord = 10 + (y*20);

		g.setColor(Color.yellow);
      g.drawString("?",xCord,yCord);
	}
}