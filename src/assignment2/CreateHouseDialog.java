package assignment2;
import java.awt.BorderLayout;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.InputMismatchException;

import javax.swing.*;
import javax.swing.text.*;

import net.miginfocom.swing.MigLayout;

public class CreateHouseDialog extends JFrame {

	/* Code goes here */
	JPanel top, buttons;
	private JTextField photo, address1, address2,bedrooms, bathrooms, price;
	private JFormattedTextField phoneNum;
	JButton btnAdd, btnCancel;
	JLabel lblPhoto, lblAddress1, lblAddress2, lblphoneNum, lblBedrooms, lblBathrooms, lblPrice;
	boolean incorrect = false;
	ArrayList<House> theList;
	
	@SuppressWarnings("deprecation")
	public CreateHouseDialog(ArrayList<House> parentList) throws ParseException {
		
		theList = parentList;
		
		// create components
		
		//top = new JPanel(new MigLayout());
		buttons = new JPanel();
		
		photo = new JTextField(10);
		address1 = new JTextField(10);
		address2 = new JTextField(10);
		phoneNum = new JFormattedTextField(new MaskFormatter("(###) #######"));
		bedrooms = new JTextField();
		bathrooms = new JTextField(10);
		price = new JTextField(10);
		btnAdd = new JButton("Add");
		btnCancel = new JButton("Cancel");
		lblPhoto = new JLabel("Photograph File Name: ");
		lblAddress1 = new JLabel("Address Line 1: ");
		lblAddress2 = new JLabel("Address Line 2: ");
		lblphoneNum = new JLabel("Contact Number");
		lblBedrooms = new JLabel("Number of Bedrooms: ");
		lblBathrooms = new JLabel("Number of Bathrooms: ");
		lblPrice = new JLabel("Price: ");
		
		top.add(lblPhoto, "growx, pushx");
		top.add(photo, "growx, pushx, wrap");
		top.add(lblAddress1, "growx, pushx");
		top.add(address1, "growx, pushx, wrap");
		top.add(lblAddress2, "growx, pushx");
		top.add(address2, "growx, pushx, wrap");
		top.add(lblBedrooms, "growx, pushx");
		top.add(bedrooms, "growx, pushx, wrap");
		top.add(lblBathrooms, "growx, pushx");
		top.add(bathrooms, "growx, pushx, wrap");
		top.add(lblPrice, "growx, pushx");
		top.add(price, "growx, pushx, wrap");
		top.add(lblphoneNum, "growx, pushx");
		top.add(phoneNum, "growx, pushx, wrap");
		
		buttons.add(btnAdd);
		buttons.add(btnCancel);
		
		this.add(top, BorderLayout.NORTH);
		this.add(buttons, BorderLayout.SOUTH);
		
		btnAdd.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				
					
					if (photo.getText().equals("") || address1.getText().equals("") || address2.getText().equals("") || phoneNum.getText().equals("") ||
							bedrooms.getText().equals("") || bathrooms.getText().equals("") || price.getText().equals("")) {
					
					JOptionPane.showMessageDialog(null, "You must fill all fields!", "Missing Information!", JOptionPane.ERROR_MESSAGE);
					
					
					} // end if
					
					else {
						
					try {
						
						if (Integer.parseInt(bedrooms.getText()) > 0 || Integer.parseInt(bathrooms.getText()) > 0 || Double.parseDouble(price.getText()) > 0) {
							
							
						} // end if
						
					}
					catch (InputMismatchException e) {
						
							JOptionPane.showMessageDialog(null, "No. of bedrooms/bathroom and price must be a number! ", "ERROR", JOptionPane.ERROR_MESSAGE);
							bedrooms.setText("");
							bathrooms.setText("");
							price.setText("");
					}
					
						
						House house = new House(address1.getText().toString(), address2.getText().toString(), Integer.parseInt(bedrooms.getText().toString()), Integer.parseInt(bathrooms.getText().toString()), Double.parseDouble(price.getText().toString()), photo.getText().toString(), phoneNum.getText().toString());
						theList.add(house);
						JOptionPane.showMessageDialog(null, "Your item has been added to the database.");
						dispose();
					
					} // end else
			
			} // ends method

		});
		
		btnCancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// close window
				
				dispose();
				
			}
			
		});
		
				
}
		
	public boolean verifyField (JTextField comp) { 
		
		boolean passed = false;
		
		try {
					
		int n = Integer.parseInt(comp.getText().toString());
		passed = (n > 0);
		
		} catch (NumberFormatException e) {}
		
			return passed;
			
	} // end verifyField
	
	
		//It displays a set of labels and data fields using MigLayout for the top panel
		//into which the user enters data for the new record. 
		//The buttons on the bottom panel are added using FlowLayout.
		//Clicking the Add button should cause a new record to be created provided that the data
		//is valid: i.) All fields should be non-null. ii.) The value in the bedrooms and bathrooms 
		//fields should represent integers and the value in the price field should represent a double.
	
}