import java.awt.*;
import java.awt.Color.*;
import java.awt.image.*;
import javax.swing.*;
import java.io.*;
import javax.imageio.*;

public class CalculatorPanel extends JPanel{ 
	
	//Properties
	BufferedImage dollar = null;
	//endpoints to draw functions
	int intP1;
	int intP2;
	int intP3;
	int intP4;
	//m and b values for functions
	int intM1;
	int intM2;
	int intB1;
	
	//Methods
	public void paintComponent(Graphics g){ 
		super.paintComponent(g);
		
		//draw graph x and y axis
		g.drawLine(425,520,925,520);
		g.drawLine(425,0,425,520);
		g.drawImage(dollar,390,222,null); //draw yaxis label
		//draw graph grid and scale numbers
		for(int i = 0; i <= 500; i+=100){
			g.drawLine(i+425,0,i+425,520);
			g.drawLine(425,520-i,925,520-i);
			g.drawString(""+i,425+i,535);
			if(i !=0 ){ //so the 0 is not drawn twice on the axes
				g.drawString(""+i,400,520-i);
			}
			for(int j = 20; j <= 500; j+=20){
				g.drawLine(j+425,0,j+425,520);
				g.drawLine(425,520-j,925,520-j);
			}
		}
		//update endpoints to draw lines for functions
		intP1 = intB1;
		intP2 = intM1*500+intB1;
		intP3 = 0;
		intP4 = intM2*500;
			
		//draw cost function
		g.setColor(Color.blue);
		g.drawLine(425,520-intP1,925,520-intP2);
		//draw revenue function
		g.setColor(Color.red);
		g.drawLine(425,520-intP3,925,520-intP4);
	}
	
	public void reset(){ //method to reset all properties back to 0
		intP1 = 0;
		intP2 = 0;
		intP3 = 0;
		intP4 = 0;
		intM1 = 0;
		intM2 = 0;
		intB1 = 0;
	}
	
	//Constructor
	public CalculatorPanel(){
		super(); 
		try{ 
			dollar = ImageIO.read(new File("yaxis.png"));
		}catch(IOException e){
			System.out.println("Unable to load image");
		}
	}
}
