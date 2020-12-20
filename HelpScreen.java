//import libraries
import java.awt.*;
import javax.swing.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import java.awt.event.*;

public class HelpScreen extends JPanel{ //open class HelpScreen extending JPanel
	
	//Properties
	BufferedImage help = null;
	
	//Methods
	public void paintComponent(Graphics g){//override the paintComponent method of JPanel
		super.paintComponent(g);
		g.drawImage(help,0,0,this);//draw image
	}

	//Constructor
	public HelpScreen(){
		super();//construct parent
		try{ //load help screen image
			help = ImageIO.read(new File("help.jpg"));
		}catch(IOException e){
			System.out.println("Unable to load image");
		}
	}
}//close class
