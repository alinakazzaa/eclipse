package assignment2;

import java.awt.*;
import java.awt.RenderingHints.Key;
import java.awt.event.*;
import javax.swing.*;
import net.miginfocom.swing.MigLayout;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.*;

public class HouseApplication extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	ArrayList<House> houseList = new ArrayList<House>();
	JMenuBar menuBar;
	JMenu modifyMenu, reportMenu, closeMenu;
	JMenuItem createItem, deleteItem, searchItem, summaryItem, closeApp;
	JButton firstItemButton, nextItemButton, prevItemButton, lastItemButton, editItemButton;
	JLabel houseImageLabel, idLabel, streetLabel, cityLabel, bedroomsLabel, bathroomsLabel, priceLabel, changeLabel,
			contactNoLabel;
	JTextField idTextField, streetTextField, cityTextField, bedroomsTextField, bathroomsTextField, priceTextField,
			changeTextField, contactNoTextField;
	String[][] records = { { "113 The Maltings", "Dublin 8", "2", "1", "155500.00", "House1.jpg", "(087) 9011135" },
			{ "78 Newington Lodge", "Dublin 14", "3", "2", "310000.00", "House2.jpg", "(087) 9010580" },
			{ "62 Bohernabreena Road", "Dublin 24", "3", "1", "220000.00", "House3.jpg", "(087) 6023159" },
			{ "18 Castledevitt Park", "Dublin 15", "3", "3", "325000.00", "House4.jpg", "(087) 9010580" },
			{ "40 Dunsawny Road", "Swords", "3", "19", "245000.00", "House5.jpg", "(087) 9011135" } };

	
	private final String password = "3175";
	
	int currentItem;
	ActionListener first, next, prev, last, edit, modify, search, summary, exit;

	public HouseApplication() {
		super("Estate Agent Application");
		for (int i = 0; i < records.length; i++) {
			houseList.add(new House(records[i][0], records[i][1], Integer.parseInt(records[i][2]),
					Integer.parseInt(records[i][3]), Double.parseDouble(records[i][4]), records[i][5], records[i][6]));
		}
		currentItem = 0;
		initComponents();
		displayDetails(currentItem);

		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	public void initComponents() {
		setLayout(new BorderLayout());
		JPanel displayPanel = new JPanel(new MigLayout());

		// Ensures that image is centred in label
		houseImageLabel = new JLabel(new ImageIcon(), SwingConstants.CENTER);
		displayPanel.add(houseImageLabel, "push, grow, span 2, wrap");

		idLabel = new JLabel("House ID: ");
		idTextField = new JTextField(3);
		idTextField.setEditable(false);

		displayPanel.add(idLabel, "growx, pushx");
		displayPanel.add(idTextField, "growx, pushx, wrap");

		streetLabel = new JLabel("Address Line 1: ");
		streetTextField = new JTextField(15);
		streetTextField.setEditable(false);

		displayPanel.add(streetLabel, "growx, pushx");
		displayPanel.add(streetTextField, "growx, pushx, wrap");

		cityLabel = new JLabel("Address Line 2: ");
		cityTextField = new JTextField(15);
		cityTextField.setEditable(false);

		displayPanel.add(cityLabel, "growx, pushx");
		displayPanel.add(cityTextField, "growx, pushx, wrap");

		bedroomsLabel = new JLabel("Number of bedrooms: ");
		bedroomsTextField = new JTextField(3);
		bedroomsTextField.setEditable(false);

		displayPanel.add(bedroomsLabel, "growx, pushx");
		displayPanel.add(bedroomsTextField, "growx, pushx, wrap");

		bathroomsLabel = new JLabel("Number of bathrooms: ");
		bathroomsTextField = new JTextField(3);
		bathroomsTextField.setEditable(false);

		displayPanel.add(bathroomsLabel, "growx, pushx");
		displayPanel.add(bathroomsTextField, "growx, pushx, wrap");

		priceLabel = new JLabel("Price: ");
		priceTextField = new JTextField(10);
		priceTextField.setEditable(false);

		displayPanel.add(priceLabel, "growx, pushx");
		displayPanel.add(priceTextField, "growx, pushx, wrap");

		changeLabel = new JLabel("Price change: ");
		changeTextField = new JTextField(10);
		changeTextField.setEditable(false);

		displayPanel.add(changeLabel, "growx, pushx");
		displayPanel.add(changeTextField, "growx, pushx, wrap");

		contactNoLabel = new JLabel("Contact number: ");
		contactNoTextField = new JTextField(15);
		contactNoTextField.setEditable(false);

		displayPanel.add(contactNoLabel, "growx, pushx");
		displayPanel.add(contactNoTextField, "growx, pushx, wrap");
		add(displayPanel, BorderLayout.CENTER);

		JPanel buttonPanel = new JPanel(new GridLayout(1, 5));

		firstItemButton = new JButton(new ImageIcon("first.png"));
		nextItemButton = new JButton(new ImageIcon("next.png"));
		editItemButton = new JButton("Edit");
		prevItemButton = new JButton(new ImageIcon("prev.png"));
		lastItemButton = new JButton(new ImageIcon("last.png"));

		buttonPanel.add(firstItemButton);
		buttonPanel.add(prevItemButton);
		buttonPanel.add(editItemButton);
		buttonPanel.add(nextItemButton);
		buttonPanel.add(lastItemButton);

		JPanel bottomPanel = new JPanel();
		bottomPanel.add(buttonPanel);

		add(bottomPanel, BorderLayout.SOUTH);

		menuBar = new JMenuBar();
		
		modifyMenu = new JMenu("Modify");
		modifyMenu.setMnemonic(KeyEvent.VK_M);
		createItem = new JMenuItem("Create");
		createItem.setMnemonic(KeyEvent.VK_C);
		deleteItem = new JMenuItem("Delete");
		deleteItem.setMnemonic(KeyEvent.VK_D);
		
		reportMenu = new JMenu("Reports");
		reportMenu.setMnemonic(KeyEvent.VK_R);
		searchItem = new JMenuItem("Search records");
		summaryItem = new JMenuItem("Summary report");
		summaryItem.setMnemonic(KeyEvent.VK_T);
		searchItem.setMnemonic(KeyEvent.VK_S);
		
		closeMenu = new JMenu("Close");
		closeMenu.setMnemonic(KeyEvent.VK_Q);
		closeApp = new JMenuItem("Exit");
		closeApp.setMnemonic(KeyEvent.VK_ESCAPE);
		

		/* Set up your menus and menu items here */

		displayPanel.add(menuBar);
		setJMenuBar(menuBar);

		
		modifyMenu.add(createItem);
		modifyMenu.add(deleteItem);
		reportMenu.add(searchItem);
		reportMenu.add(summaryItem);
		closeMenu.add(closeApp);
		
		menuBar.add(modifyMenu);
		menuBar.add(reportMenu);
		menuBar.add(closeMenu);
		

		modify = new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				
				
				if (e.getActionCommand() == "Delete") {
					
					deleteItem();
					
				}
				else if (e.getActionCommand() == "Create") {
				
					createItem();
				}
			} // end actionPerformed
		};
		
	// end actionListener
	

		// Because each pair of corresponding buttons and menu items have the same
		// functionality, instead
		// of repeating the same code in two locations, we can define an ActionListener
		// object that both
		// components will share by having it added as their action listener.

		first = new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				if (editItemButton.getText().equals("Save")) {
					// Here we make sure that any updated values are saved to the record before
					// we display the next record.
					// This behavior is performed by next, prev and edit, so we move it into a
					// separate method so as to avoid unnecessary repetition of code.
					saveOpenValues();
				}
				currentItem = 0;
				displayDetails(currentItem);
			}
		};

		next = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// No next if at end of list
				if (currentItem != (houseList.size() - 1)) {
					if (editItemButton.getText().equals("Save")) {
						// Here we make sure that any updated values are saved to the record before
						// we display the next record.
						// This behaviour is performed by next, prev and edit, so we move it into a
						// separate method so as to avoid unnecessary repetition of code.
						saveOpenValues();
					}
					currentItem++;
					displayDetails(currentItem);
				}
			}
		};

		prev = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// No previous if at beginning of list
				if (currentItem != 0) {
					if (editItemButton.getText().equals("Save")) {
						saveOpenValues();
					}
					currentItem--;
					displayDetails(currentItem);
				}
			}
		};

		last = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (editItemButton.getText().equals("Save")) {
					// Here we make sure that any updated values are saved to the record before
					// we display the next record.
					// This behaviour is performed by next, prev and edit, so we move it into a
					// separate method so as to avoid unnecessary repetition of code.
					saveOpenValues();
				}
				currentItem = houseList.size() - 1;
				displayDetails(currentItem);
			}
		};

		edit = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getActionCommand() == "Edit") {
					// Allow data to be edited
					editItemButton.setText("Save");
					priceTextField.setEditable(true);
				} else if (e.getActionCommand() == "Save") {
					saveOpenValues();
				}
			}
		};
		
		search = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// Option pane to display current ids in a combo box
				
				JComboBox<String> idList = new JComboBox<String>();
				
				// Search the array list
				for (House house: houseList) {
				
				idList.addItem(Integer.toString(house.getId()));
				
				}
				
				// when user chooses their ID
				
				idList.setEditable(true);
			     
				
				
			  //New Option Pane to display this record in a textArea
			    
			    
			    // I know you said to search again for the item in position specified by user in combo box, but my combo box is set up in a way that it actually has the objects in order and there is no need to search again
			    
			    currentItem = idList.getSelectedIndex();
			    displayDetails(currentItem);
			    
			} // end ActionPerformed
			
		};
		
	
		
		summary = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// Call child window HouseSummaryDialog
				
				HouseSummaryDialog child = new HouseSummaryDialog(houseList);
				child.setVisible(true);
				child.pack();
				
				//System.out.println(houseList.size());
				
			}
			
		};
		
		exit = new ActionListener() {
			
			public void actionPerformed(ActionEvent argo0) {
				
				System.exit(0);
			}
		};

		firstItemButton.addActionListener(first);

		nextItemButton.addActionListener(next);

		prevItemButton.addActionListener(prev);

		lastItemButton.addActionListener(last);

		editItemButton.addActionListener(edit);

		createItem.addActionListener(modify);

		deleteItem.addActionListener(modify);
		
		searchItem.addActionListener(search);
		
		summaryItem.addActionListener(summary);
		
		closeApp.addActionListener(exit);

	}

	private void saveOpenValues() {
		// Save data and revert to other state.
		// Update appearance of button.
		editItemButton.setText("Edit");
		// Try to save items to record.
		try {
			double oldPrice = houseList.get(currentItem).getPrice();
			double newPrice = Double.parseDouble(priceTextField.getText());
			double change = newPrice - oldPrice;
			houseList.get(currentItem).setPrice(newPrice);
			houseList.get(currentItem).setChange(change);
			NumberFormat nf = NumberFormat.getCurrencyInstance();
			priceTextField.setText("€" + nf.format(newPrice));
			changeTextField.setText(nf.format(change));
			if (change > 0.0)
				changeTextField.setForeground(Color.GREEN);
			else if (change < 0.0)
				changeTextField.setForeground(Color.RED);
			else
				changeTextField.setForeground(Color.BLACK);
		} catch (NumberFormatException ex) {
			JOptionPane.showMessageDialog(null, "Not a valid value for price");
			// Reset contents of text field.
			priceTextField.setText(houseList.get(currentItem).getPrice() + "");
		}
		// Disable text fields.
		priceTextField.setEditable(false);
		// Display message.
		JOptionPane.showMessageDialog(this, "Record updated");
	}

	public void displayDetails(int currentItem) {
		houseImageLabel.setIcon(new ImageIcon(houseList.get(currentItem).getImageLocation()));
		idTextField.setText(houseList.get(currentItem).getId() + "");
		streetTextField.setText(houseList.get(currentItem).getStreet());
		cityTextField.setText(houseList.get(currentItem).getCity());
		bedroomsTextField.setText(houseList.get(currentItem).getBedrooms() + "");
		bathroomsTextField.setText(houseList.get(currentItem).getBathrooms() + "");
		NumberFormat nf = NumberFormat.getCurrencyInstance();
		priceTextField.setText(nf.format(houseList.get(currentItem).getPrice()));
		double change = houseList.get(currentItem).getChange();
		changeTextField.setText(nf.format(change));
		if (change > 0.0)
			changeTextField.setForeground(Color.GREEN);
		else if (change < 0.0)
			changeTextField.setForeground(Color.RED);
		else
			changeTextField.setForeground(Color.BLACK);
		contactNoTextField.setText(houseList.get(currentItem).getContactNo());
	}
	
	public boolean getPassword() {
		
		boolean correct = false;
		JPasswordField pwField = new JPasswordField();

//-------------------------------------------------------------------------------------------------\\
		do {
		
		String[] options = new String[] { "OK", "Cancel" };
		
		int option = JOptionPane.showOptionDialog(null, pwField, "Enter password:", JOptionPane.NO_OPTION,
				JOptionPane.PLAIN_MESSAGE, null, options, options[1]);
		
		if (option == JOptionPane.OK_OPTION) {
			
			// the getPassword() method doesn't work
			//if(pwField.getPassword().equals(password)) {
			
			//String pw = pwField.getPassword().toString();
			
			
			if(pwField.getText().equals(password)) {
				
				correct = true;
				
			} // end if
			
			else {
				
				JOptionPane.showMessageDialog(null, "Incorrect password. Please try again");
			}
			
		} // end if
		
		else {
		
			break;
				
		} // end else
		
		} while (!correct);
		
		
		return correct;
	} // end getPassword()
	
	public void deleteItem() {

		
			if(houseList.isEmpty()) {
				
				JOptionPane.showConfirmDialog(null, "No more items! Shutting down.");
				System.exit(0);
				
			}
			else {
		
			JComboBox<String> deleteBox = new JComboBox<String>();
			String [] deleteOptions = new String [] { "OK" , "Cancel"};
			int houseId = 0;
			
			
			// create the combo box and populate it with ID's of houses from houseList
			
			for (House house: houseList) {
				
				deleteBox.addItem(Integer.toString(house.getId()));
				
				} // end for
			
			deleteBox.setEditable(true);
			
			int deleteHouse = JOptionPane.showOptionDialog(null, deleteBox, "House ID:  ", JOptionPane.NO_OPTION,
					JOptionPane.PLAIN_MESSAGE, null, deleteOptions, deleteOptions[1]);
			
			// Search the array list for the house with the selected ID
			
			houseId = Integer.parseInt(deleteBox.getSelectedItem().toString());
			
			int count = 0;
			
			for (House house : houseList) { // search the list of houses
				
				if (house.getId() == houseId) { // if a house with this ID was found
					
					currentItem = count;

				}
				else {
					
					count++;
				}
				
			} // end for
			
			// houseId is equal to the option user has selected from the combo box
			
			
			
			if (deleteHouse == JOptionPane.OK_OPTION) {
				
				int confirmDelete = JOptionPane.showConfirmDialog(null ,"This will delete the record. Are you sure you want to continue?", "Warning! Record will be deleted!", JOptionPane.PLAIN_MESSAGE);
				
				if (confirmDelete == JOptionPane.OK_OPTION) {
					
					if (getPassword()) {
						
						JOptionPane.showMessageDialog(null, "House " + houseId + " has been deleted");
						houseList.remove(currentItem);
						currentItem = 0;
						
						displayDetails(currentItem);
							
					} // end if get password
					
				} // end if ok option
			} // end if ok option
		} // end else
	} // end delete item*/
		
		public void createItem() {
			
			if (getPassword()) {
				
				CreateHouseDialog createHouse;
				try {
					createHouse = new CreateHouseDialog(houseList);
					createHouse.setVisible(true);
					createHouse.pack();
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
					
			} // end if get password
			
			displayDetails(currentItem);
			
} // end create item
	
	

	public static void main(String[] args) {
		
		
		HouseApplication ha = new HouseApplication();
		ha.pack();
		ha.setSize(400, 550);
		ha.setVisible(true);
		
		
		
	}

}
