
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.JOptionPane;

public class Lab28c100
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
	int numCols = 50;
	String background[];
	String fileName;
	int marioStartc = 0;
	int marioStartr = 0;
	boolean first = true;

	public void paint(Graphics g)
	{
       for (int r = 0; r < numRows; r++)
           for (int c = 0; c < numCols; c++)
               switch(background[r].charAt(c))
               {
               	case '.' : drawSpace(g,r,c); break;
						case '=' : drawBeam(g,r,c); break;
						case '#' : drawLadder(g,r,c); break;
						case '%' : drawLadder(g,r,c); break;
						case 'B' : drawBarrel(g,r,c); break;
						case 'H' : drawHammer(g,r,c); break;
						case 'C' : drawCoin(g,r,c); break;
						case 'L' : drawCloud(g,r,c); break;
						case 'S' : drawTopTubeBeg(g,r,c); break;
						case 'T' : drawTopTube(g,r,c); break;
						case 'P' : drawTopTubeEnd(g,r,c); break;
						case 'E' : drawBaseTubeBeg(g,r,c); break;
						case '3' : drawBaseTubeEnd(g,r,c); break;
						case 'M' : drawMarioRight(g,r,c,first); marioStartc = c; marioStartr = r; break;
              		default  : drawUnknown(g,r,c);
               }
               moveMarioRight(g,marioStartr,marioStartc);
	}

	public void readFile() throws IOException
	{
		background = new String[numRows];
		int x = 0;
		fileName = "blank.dat";
		String inString = "";
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
		int xCord = 10 + (x*20);
		int yCord = 10 + (y*20);

		g.setColor(new Color(128,128,255)); //sky blue
		g.fillRect(xCord,yCord,20,20);
	}

	public void drawBeam(Graphics g,int y,int x)
	{
		int xCord = 10 + (x*20);
		int yCord = 10 + (y*20);

		g.setColor(Color.red);
		g.fillRect(xCord,yCord,20,20);
		g.setColor(new Color(128,128,255)); //sky blue
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

		g.setColor(new Color(128,128,255)); //sky blue
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

		g.setColor(new Color(128,128,255)); //sky blue
		g.fillRect(xCord,yCord,20,20);

		g.setColor(new Color(153, 102, 0)); //brown
		g.fillRect(xCord+6,yCord,8,20);
		g.fillArc(xCord,yCord,12,20,90,180);
		g.fillArc(xCord+4,yCord,16,20,90,-180);
		g.setColor(new Color(200, 200, 200)); //grey
		g.fillRect(xCord+5,yCord,10,1);

		g.setColor(Color.black);
		Polygon tri1 = new Polygon();
		tri1.addPoint(xCord+14,yCord+5);
		tri1.addPoint(xCord+14,yCord+17);
		tri1.addPoint(xCord+17,yCord+11);
		g.fillPolygon(tri1);

		g.setColor(Color.white);
		Polygon tri2 = new Polygon();
		tri2.addPoint(xCord+6,yCord+1);
		tri2.addPoint(xCord+6,yCord+17);
		tri2.addPoint(xCord+3,yCord+9);
		g.fillPolygon(tri2);
	}

	public void drawHammer(Graphics g,int y,int x)
	{
		int xCord = 10 + (x*20);
		int yCord = 10 + (y*20);

		g.setColor(new Color(128,128,255)); //sky blue
		g.fillRect(xCord,yCord,20,20);

		g.setColor(Color.green);
		g.fillRect(xCord,yCord,20,10);
		g.setColor(new Color(0, 100, 0)); //dark green
		g.fillRect(xCord,yCord,20,1);
		g.fillRect(xCord+19,yCord,1,10);
		g.setColor(new Color(0, 150, 0));//darker green
		g.fillRect(xCord,yCord+1,19,1);
		g.fillRect(xCord+18,yCord+1,1,9);
		g.setColor(Color.black);
		g.fillArc(xCord+14,yCord+2,8,7,90,180);

		g.setColor(Color.yellow);
		g.fillRect(xCord+7,yCord+10,5,10);
	}

	public void drawCoin(Graphics g,int y,int x)
	{
		int xCord = 10 + (x*20);
		int yCord = 10 + (y*20);

		g.setColor(new Color(128,128,255)); //sky blue
		g.fillRect(xCord,yCord,20,20);

		g.setColor(new Color(255, 215, 0)); //golden-yellow
		g.fillOval(xCord,yCord,20,20);

		g.setColor(new Color(200, 175, 0)); //dark golden-yellow
		g.drawArc(xCord+5,yCord+5,10,10,90,-250);
	}

	public void drawCloud(Graphics g,int y,int x)
	{
		int xCord = 10 + (x*20);
		int yCord = 10 + (y*20);

		g.setColor(new Color(128,128,255)); //sky blue
		g.fillRect(xCord,yCord,20,20);

		g.setColor(Color.white);
		g.fillOval(xCord,yCord+2,8,8);
		g.fillOval(xCord+2,yCord+6,8,8);
		g.fillOval(xCord+1,yCord+10,8,8);
		g.fillOval(xCord+2,yCord+14,4,4);

		g.fillOval(xCord+7,yCord+2,8,8);
		g.fillOval(xCord+9,yCord+6,8,8);
		g.fillOval(xCord+8,yCord+10,8,8);
		g.fillOval(xCord+9,yCord+14,4,4);

		g.fillOval(xCord+10,yCord+2,8,8);
		g.fillOval(xCord+12,yCord+6,8,8);
		g.fillOval(xCord+11,yCord+10,8,8);
		g.fillOval(xCord+12,yCord+14,4,4);

		g.setColor(new Color(200,200,200)); //grey
		g.drawArc(xCord,yCord+2,8,8,90,180);
		g.drawArc(xCord+2,yCord+6,8,8,135,90);
		g.drawArc(xCord+1,yCord+10,8,8,90,180);
		g.drawArc(xCord+2,yCord+14,4,4,90,180);

		g.drawArc(xCord+7,yCord+2,8,8,180,135);
		g.drawArc(xCord+9,yCord+6,8,8,135,90);
		g.drawArc(xCord+8,yCord+10,8,8,180,135);

		g.drawArc(xCord+10,yCord+2,8,8,180,135);
		g.drawArc(xCord+12,yCord+6,8,8,135,90);
		g.drawArc(xCord+11,yCord+10,8,8,180,135);
	}

	public void drawTopTubeBeg(Graphics g,int y,int x)
	{
		int xCord = 10 + (x*20);
		int yCord = 10 + (y*20);

		g.setColor(new Color(128,128,255)); //sky blue
		g.fillRect(xCord,yCord,20,20);

		g.setColor(Color.green);
		g.fillRect(xCord+8,yCord,12,20);

		g.setColor(new Color(0,100,0)); //dark green
		g.fillRect(xCord+8,yCord,1,20);
		g.fillRect(xCord+9,yCord+19,11,1);
	}

	public void drawTopTube(Graphics g,int y,int x)
	{
		int xCord = 10 + (x*20);
		int yCord = 10 + (y*20);

		g.setColor(Color.green);
		g.fillRect(xCord,yCord,20,20);

		g.setColor(new Color(0,100,0)); //dark green
		g.fillRect(xCord,yCord+19,20,1);
	}

	public void drawTopTubeEnd(Graphics g,int y,int x)
	{
		int xCord = 10 + (x*20);
		int yCord = 10 + (y*20);

		g.setColor(new Color(128,128,255)); //sky blue
		g.fillRect(xCord,yCord,20,20);

		g.setColor(Color.green);
		g.fillRect(xCord,yCord,12,20);

		g.setColor(new Color(0,100,0)); //dark green
		g.fillRect(xCord,yCord+19,12,1);

		g.setColor(Color.white);
		g.fillRect(xCord+10,yCord,1,19);
	}

	public void drawBaseTubeBeg(Graphics g,int y,int x)
	{
		int xCord = 10 + (x*20);
		int yCord = 10 + (y*20);

		g.setColor(Color.green);
		g.fillRect(xCord,yCord,20,20);

		g.setColor(new Color(0,100,0)); //dark green
		g.fillRect(xCord,yCord,1,20);
	}

	public void drawBaseTubeEnd(Graphics g,int y,int x)
	{
		int xCord = 10 + (x*20);
		int yCord = 10 + (y*20);

		g.setColor(Color.green);
		g.fillRect(xCord,yCord,20,20);

		g.setColor(Color.white);
		g.fillRect(xCord+17,yCord,1,20);
	}

	public void drawMarioRight(Graphics g,int y,int x,boolean check)
	{
		int xCord = 10 + (x*20); //body
		int yCord = 10 + (y*20);

		if(check==true)
		{
			g.setColor(new Color(128,128,255)); //sky blue
			g.fillRect(xCord,yCord,20,20); //bg
			g.fillRect(xCord,yCord-20,20,20); //bg
		}


		g.setColor(Color.blue);
		g.fillRect(xCord+6,yCord,10,17); //body
		g.fillArc(xCord+4,yCord+2,14,17,90,-180); //tummy

		g.setColor(Color.red);
		g.fillArc(xCord+4,yCord,8,18,180,-180); //arm

		g.setColor(new Color(163, 112, 0)); //brown
		g.fillOval(xCord+3,yCord+13,15,6); //foot

		g.setColor(new Color(143, 92, 0)); //darker brown
		g.drawOval(xCord+3,yCord+13,15,6); //foot outline

		g.setColor(Color.white);
		g.fillOval(xCord+1,yCord+8,12,6); //hand

		xCord = 10 + (x*20); //head
		yCord = 10 + ((y-1)*20);

		if(check==true)
		{
			g.setColor(new Color(128,128,255)); //sky blue
			g.fillRect(xCord,yCord,20,20); //bg
			g.fillRect(xCord,yCord-20,20,20); //bg
			check = false;
		}

		g.setColor(Color.pink);
		g.fillOval(xCord+2,yCord+2,16,18); //head
		g.fillOval(xCord+15,yCord+12,5,4); //nose
		g.fillOval(xCord,yCord+12,6,6); //ear

		g.setColor(Color.red);
		g.fillArc(xCord+2,yCord+2,16,18,180,-180); //hat
		g.fillRect(xCord+18,yCord+8,1,3); //hat bill

		g.setColor(Color.black);
		g.fillOval(xCord+11,yCord+11,4,4); //eye

		g.setColor(new Color(153, 102, 0)); //light brown
		g.drawArc(xCord+15,yCord+12,4,4,100,-200); //
		g.fillArc(xCord+10,yCord+14,16,5,100,110); //mustache
		g.drawArc(xCord,yCord+12,6,6,100,-100); //sideburn
		g.drawArc(xCord,yCord+11,7,7,100,-100); //sideburn thicker

		delay();
	}

	public void drawMarioLeft(Graphics g,int y,int x,boolean check)
	{
		int xCord = 10 + (x*20); //body
		int yCord = 10 + (y*20);

		if(check==true)
		{
			g.setColor(new Color(128,128,255)); //sky blue
			g.fillRect(xCord,yCord,20,20);
			g.fillRect(xCord,yCord-20,20,20);
		}

		g.setColor(Color.blue);
		g.fillRect(xCord+4,yCord,10,17); //body
		g.fillArc(xCord,yCord+2,14,17,90,-180); //tummy

		g.setColor(Color.red);
		g.fillArc(xCord+6,yCord,8,18,180,-180); //arm

		g.setColor(new Color(163, 112, 0)); //brown
		g.fillOval(xCord+1,yCord+13,15,6); //foot

		g.setColor(new Color(143, 92, 0)); //darker brown
		g.drawOval(xCord+1,yCord+13,15,6); //foot outline

		g.setColor(Color.white);
		g.fillOval(xCord+3,yCord+8,12,6); //hand

		xCord = 10 + (x*20); //head
		yCord = 10 + ((y-1)*20);

		if(check==true)
		{
			g.setColor(new Color(128,128,255)); //sky blue
			g.fillRect(xCord,yCord,20,20); //bg
			g.fillRect(xCord,yCord-20,20,20); //bg
			check = false;
		}

		g.setColor(Color.pink);
		g.fillOval(xCord+2,yCord+2,16,18); //head
		g.fillOval(xCord,yCord+12,5,4); //nose
		g.fillOval(xCord+14,yCord+12,6,6); //ear

		g.setColor(Color.red);
		g.fillArc(xCord+2,yCord+2,16,18,180,-180); //hat
		g.fillRect(xCord+2,yCord+8,1,3); //hat bill

		g.setColor(Color.black);
		g.fillOval(xCord+5,yCord+11,4,4); //eye

		g.setColor(new Color(153, 102, 0)); //light brown
		g.drawArc(xCord,yCord+12,4,4,80,-200); //
		g.fillArc(xCord,yCord+14,16,5,100,110); //mustache
		g.drawArc(xCord+14,yCord+12,6,6,80,100); //sideburn
		g.drawArc(xCord+14,yCord+11,7,7,80,100); //sideburn thicker

		delay();
	}

	public void moveMarioRight(Graphics g,int r,int c)
	{
		boolean left = false;
		while (r >= 5)
		{
      	for (c = c; c < numCols; c++)
         {
           	switch(background[r].charAt(c))
            {
					case '=' : erase(g,r,c-1); erase(g,r+1,c); r-= 1; drawMarioRight(g,r,c,false); break;
					case '#' : erase(g,r,c-1); drawMarioRight(g,r,c,false); break;
					case '%' : erase(g,r,c-1); erase(g,r,c); r-= 1; drawMarioRight(g,r,c,false); left = true; break;
					case 'B' : erase(g,r,c-1); break;
					case 'H' : erase(g,r,c-1); drawMarioRight(g,r,c,false); break;
					case 'C' : erase(g,r,c-1); drawMarioRight(g,r,c,true); break;
					case 'E' : erase(g,r,c-1);drawMarioRight(g,r,c,false); break;
          		default  : erase(g,r,c-1); drawMarioRight(g,r,c,false);
   			}
   			if(left == true)
   				moveMarioLeft(g,r,c);
         }
		}
	}

	public void moveMarioLeft(Graphics g,int r,int c)
	{
		boolean left = true;
		while (r >= 5)
		{
			for (c = c; c >= 0; c--)
         {
           	switch(background[r].charAt(c))
            {
					case '=' : erase(g,r,c+1); erase(g,r+1,c); r-= 1; drawMarioLeft(g,r,c,false); break;
					case '#' : erase(g,r,c+1); drawMarioLeft(g,r,c,false); break;
					case '%' : erase(g,r,c+1); erase(g,r,c); r-=1; drawMarioLeft(g,r,c,false); left = false; break;
					case 'B' : erase(g,r,c+1); break;
					case 'H' : erase(g,r,c+1); drawMarioLeft(g,r,c,false); break;
					case 'C' : erase(g,r,c+1); drawMarioLeft(g,r,c,true); break;
					case '3' : erase(g,r,c+1); drawMarioLeft(g,r,c,false); break;
          		default  : erase(g,r,c+1); drawMarioLeft(g,r,c,false);
   			}
   			if(left == false)
   				moveMarioRight(g,r,c);

         }
		}
	}

	public void erase(Graphics g,int y,int x)
	{
		int r = y;
		int c = x;

		int xCord = 10 + (c*20);
		int yCord = 10 + (r*20);

		g.setColor(new Color(128,128,255)); //sky blue
		g.fillRect(xCord,yCord,20,20);
		g.fillRect(xCord,yCord-20,20,20);

		switch(background[r].charAt(c))
  		{
			case '=' : drawBeam(g,r,c); break;
			case '#' : drawLadder(g,r,c); break;
			case '%' : drawLadder(g,r,c); break;
			case 'B' : drawBarrel(g,r,c); break;
			case 'H' : drawHammer(g,r,c); break;
			case 'E' : drawBaseTubeBeg(g,r,c); break;
			case '3' : drawBaseTubeEnd(g,r,c); break;
        	default  : ;
   	}
   	switch(background[r-1].charAt(c))
  		{
			case '=' : drawBeam(g,r-1,c); break;
			case '#' : drawLadder(g,r-1,c); break;
			case '%' : drawLadder(g,r-1,c); break;
			case 'B' : drawBarrel(g,r-1,c); break;
			case 'H' : drawHammer(g,r-1,c); break;
			case 'E' : drawBaseTubeBeg(g,r-1,c); break;
			case '3' : drawBaseTubeEnd(g,r-1,c); break;
        	default  : ;
   	}
	}

	public void delay()
	{
		for(double x = 0; x < 900000; x+=0.01)
		{
		}
	}

	public void drawUnknown(Graphics g,int y,int x)
	{
		int xCord = 10 + (x*20);
		int yCord = 10 + (y*20);

		g.setColor(new Color(128,128,255)); //sky blue
		g.fillRect(xCord,yCord,20,20);

		g.setColor(Color.yellow);
      g.drawString("?",xCord+10,yCord+10);
	}
}