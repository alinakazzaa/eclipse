package assignment2;

import java.text.NumberFormat;
import java.util.ArrayList;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;


public class HouseSummaryDialog extends JFrame {
	
	/* Code goes here */
	
	JPanel mainPnl;
	JLabel lblID, lblAddress1, lblAddress2, lblBedrooms, lblBathrooms, lblPrice, lblphoneNum, lblAve;
	ArrayList<House> list;
	double totalSum = 0;
	double averagePrice = 0;
	
	public HouseSummaryDialog(ArrayList<House> parentList) {
		
		list = parentList;
		mainPnl = new JPanel();
		mainPnl.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		
		c.weightx = 1;
		c.insets.set(1, 4, 1, 1);
		c.ipadx = 2;
		c.ipady = 1;
		c.anchor = GridBagConstraints.WEST;
		
		// add the headings/ labels
		c.gridy = 0;
		lblID = new JLabel("ID");
		lblAddress1 = new JLabel("Address Line 1");
		lblAddress2 = new JLabel("Address Line 2");
		lblBedrooms = new JLabel("Number of Bedrooms");
		lblBathrooms = new JLabel("Number of Bathrooms");
		lblPrice = new JLabel("Price");
		lblphoneNum = new JLabel("Contact Number");
		lblAve = new JLabel("Average Price: ");
		
		mainPnl.add(lblID, c);
		mainPnl.add(lblAddress1, c);
		mainPnl.add(lblAddress2, c);
		mainPnl.add(lblBedrooms, c);
		mainPnl.add(lblBathrooms, c);
		mainPnl.add(lblPrice, c);
		mainPnl.add(lblphoneNum, c);
		
		int i = 1;
		
		
		for (House house: list) {
			
			c.gridy = i;
			mainPnl.add(new JLabel(Integer.toString(house.getId())), c);
			mainPnl.add(new JLabel(house.getStreet()), c);
			mainPnl.add(new JLabel(house.getCity()), c);
			mainPnl.add(new JLabel(Integer.toString(house.getBedrooms())), c);
			mainPnl.add(new JLabel(Integer.toString(house.getBathrooms())), c);
			mainPnl.add(new JLabel("€" + Double.toString(house.getPrice())), c);
			mainPnl.add(new JLabel(house.getContactNo()), c);
			i++;
			
			totalSum = averagePrice + house.getPrice();
			averagePrice = Math.round(totalSum/ list.size());
			c.gridy = i;
		}
		
		c.fill = GridBagConstraints.BASELINE;
		mainPnl.add(lblAve, c);
		c.gridx = 5;
		mainPnl.add(new JLabel("€" + Double.toString(averagePrice)), c);
		
		this.add(mainPnl);
		
		
		
	}

}
