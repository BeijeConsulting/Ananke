package it.beije.ananke.altro;

import javax.swing.*;  

public class BankView {
	
	public static void main(String args[]) {  
	    JFrame f= new JFrame("Label Example");  
	    JLabel l1,l2;  
	    l1=new JLabel("First Label.");  
	    l1.setBounds(50,50, 100,30);  
	    
	    JButton b=new JButton("Click Here");
	    b.setBounds(50,100,95,30);  
	    f.add(b); 
	    f.setSize(400,400);  
	    f.setLayout(null);  
	    f.setVisible(true); 
	    
	}  
}  
