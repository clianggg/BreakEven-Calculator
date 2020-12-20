import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class BreakEven implements ActionListener, ChangeListener{ 
	
	//Properties
	JFrame frame;
	CalculatorPanel panel;
	HelpScreen panel2;
	IntersectionCalculator calculator;
	Timer timer;
	JButton reset;
	JMenu menu;
	JMenuBar mbar;
	JMenuItem help;
	JMenuItem about;
	JMenuItem calculate;
	JSlider vslider;
	JSlider fslider;
	JSlider pslider;
	JLabel vcost;
	JLabel fcost;
	JLabel price;
	JLabel cfunction;
	JLabel rfunction;
	JLabel units;
	JLabel breakeven;
	JLabel quantity;
	JLabel negative;
	JLabel unprofitable;

	public void actionPerformed(ActionEvent evt){
		
		if(evt.getSource() == timer){ 
			panel.repaint();
		}else if(evt.getSource() == help){ 
			frame.setContentPane(panel2);
			frame.pack();
			frame.repaint();
		}else if(evt.getSource() == about){ 
			JOptionPane.showMessageDialog(null,"Created by: Christina Liang","About",JOptionPane.PLAIN_MESSAGE);  
		}else if(evt.getSource() == calculate){ 
			frame.setContentPane(panel);
			frame.pack();
			frame.repaint();
		}else if(evt.getSource() == reset){ 
			calculator.reset();
			panel.reset();
			vslider.setValue(0);
			fslider.setValue(0);
			pslider.setValue(0);
			negative.setVisible(false);
			unprofitable.setVisible(false);
		}
	}
	
	public void stateChanged(ChangeEvent e){ 
		//if JSliders state has changed, change labels accordingly 
		if(e.getSource() == vslider){
			vcost.setText("Variable Cost($):" + vslider.getValue());
			cfunction.setText("Cost Function:"+vslider.getValue() + "x + " + fslider.getValue());
		}else if(e.getSource() == fslider){
			fcost.setText("Fixed Cost($):" + fslider.getValue());
			cfunction.setText("Cost Function:"+vslider.getValue() + "x + " + fslider.getValue());
		}else if(e.getSource() == pslider){
			price.setText("Price($):" + pslider.getValue());
			rfunction.setText("Revenue Function:" + pslider.getValue() + "x");
		}
		//change calculators properties to slider valuues
		calculator.intB2 = 0; //set intB2=0 as price function has no b value
		calculator.intM1 = vslider.getValue();
		calculator.intB1 = fslider.getValue();
		calculator.intM2 = pslider.getValue();
		
		//change panel properties to slider values to draw functions
		panel.intM1 = vslider.getValue();
		panel.intB1 = fslider.getValue();
		panel.intM2 = pslider.getValue();
		
		if(calculator.isParallel() == false){ //calculate point of intersection if lines are not parallel
			//calculate point of intersection using calcX and calcY method from IntersectionCalculator
			double dblX = calculator.calcX();
			double dblY = calculator.calcY();
			//update break even labels
			breakeven.setText("Break Even Point:(" + dblX + "," + dblY + ")");
			units.setText("# of Units to Break Even: "+dblX);
			
			if(dblX < 0 || dblY < 0){ //if point of intersection is in the negatives, display message to user
				negative.setVisible(true);
				unprofitable.setVisible(true);
			}else{
				negative.setVisible(false);
				unprofitable.setVisible(false);
			}
		}else{ //if functions are parallel, display functions are parallel no break even point
			breakeven.setText("Break Even Point: Functions are parallel.");
			units.setText("# of Units to Break Even: Unable to break even");
			negative.setVisible(false);
			unprofitable.setVisible(false);
		}
	}
	
	//Constructor
	public BreakEven(){
		
		calculator = new IntersectionCalculator(); 
		frame = new JFrame("Break Even Calculator"); 
		panel = new CalculatorPanel(); 
		panel.setPreferredSize(new Dimension(960,540));
		panel.setLayout(null);
		
		panel2 = new HelpScreen(); 
		panel2.setPreferredSize(new Dimension(960,540));
		
		quantity = new JLabel("QUANTITY"); //create new JLabel for quantity (x-axis label)
		quantity.setLocation(655,520);
		quantity.setSize(new Dimension(80,20));
		panel.add(quantity);
	
		vcost = new JLabel("Variable Cost($)"); //create new JLabel for variable cost
		vcost.setLocation(40,30);
		vcost.setSize(new Dimension(300,20));
		panel.add(vcost);
		
		vslider = new JSlider(); //create new JSlider for variable cost
		vslider.setValue(0);
		vslider.setLocation(30,50);
		vslider.setSize(new Dimension(300,40));
		vslider.setMajorTickSpacing(10);
		vslider.setMinorTickSpacing(5);
		vslider.setPaintTicks(true);
		vslider.setPaintLabels(true);
		vslider.addChangeListener(this);
		panel.add(vslider);
		
		fcost = new JLabel("Fixed Cost($)"); //create new JLabel for fixed cost
		fcost.setLocation(40,120);
		fcost.setSize(new Dimension(300,20));
		panel.add(fcost);
		
		fslider = new JSlider();//create new JSlider for fixed cost
		fslider.setValue(0);
		fslider.setLocation(30,140);
		fslider.setSize(new Dimension(300,40));
		fslider.setMajorTickSpacing(10);
		fslider.setMinorTickSpacing(5);
		fslider.setPaintTicks(true);
		fslider.setPaintLabels(true);
		fslider.addChangeListener(this);
		panel.add(fslider);
		
		price = new JLabel("Price($)"); //create new JLabel for price
		price.setLocation(40,210);
		price.setSize(new Dimension(100,20));
		panel.add(price);
		
		pslider = new JSlider(); //create new JSlider for price
		pslider.setValue(0);
		pslider.setLocation(30,230);
		pslider.setSize(new Dimension(300,40));
		pslider.setMajorTickSpacing(10);
		pslider.setMinorTickSpacing(5);
		pslider.setPaintTicks(true);
		pslider.setPaintLabels(true);
		pslider.addChangeListener(this);
		panel.add(pslider);
		
		cfunction = new JLabel("Cost Function:"); //create new JLabel for cost function
		cfunction.setLocation(40,300);
		cfunction.setSize(new Dimension(300,20));
		cfunction.setForeground(Color.blue);
		panel.add(cfunction);
		
		rfunction = new JLabel("Revenue Function:");//create new JLabel for revenue function
		rfunction.setLocation(40,330);
		rfunction.setSize(new Dimension(300,20));
		rfunction.setForeground(Color.red);
		panel.add(rfunction);
		
		units = new JLabel("# of Units to Break Even:"); //create new JLabel for number of units
		units.setLocation(40,360);
		units.setSize(new Dimension(300,20));
		panel.add(units);
		
		breakeven = new JLabel("Break Even Point:"); //create new JLabel for break even point
		breakeven.setLocation(40,390);
		breakeven.setSize(new Dimension(300,20));
		panel.add(breakeven);
		
		reset = new JButton("RESET"); //create new JButton for reset
		reset.setLocation(40,430);
		reset.setSize(new Dimension(100,40));
		reset.addActionListener(this);
		panel.add(reset);
		
		negative = new JLabel("Point of intersection is in the negatives."); //create new JLabel to show poi is in the negatives
		negative.setLocation(40,480);
		negative.setSize(new Dimension(300,20));
		negative.setVisible(false);
		panel.add(negative);
		
		unprofitable = new JLabel("Business is unprofitable.");//create new JLabel to show business is unprofitable with current values
		unprofitable.setLocation(40,500);
		unprofitable.setSize(new Dimension(300,20));
		unprofitable.setVisible(false);
		panel.add(unprofitable);
		
		mbar = new JMenuBar();
		menu = new JMenu("Menu");
		help = new JMenuItem("Help");
		about = new JMenuItem("About");
		calculate = new JMenuItem("Calculator");

		help.addActionListener(this);
		about.addActionListener(this);
		calculate.addActionListener(this);
		
		menu.add(help);
		menu.add(about);
		menu.add(calculate);
		mbar.add(menu);
		frame.setJMenuBar(mbar);
		
		frame.setContentPane(panel);
		frame.pack();
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
		timer = new Timer(1000/48,this); 
		timer.start();
	}
	
	//Main Method
	public static void main(String[] args){
		new BreakEven();
	}
}
