import java.awt.BorderLayout;

import java.awt.Dimension;
import java.awt.Font;

import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 * The portion of the GUI used to interact with the members in the database
 * 
 * @author Joshua
 * @version 1.0
 */
public class MemberPanel extends JPanel {
	
	private static final long serialVersionUID = 8988877508442223590L;
	private static JTextArea ta;
	private JScrollPane sp;
	private JPanel buttonPane;
	private JPanel buff1, buff2, buff3;
	private JPanel titlePane;
	private JPanel buttons;
	private JButton addMember;
	private JButton delMember;
	private JButton fndMember;
	private JButton rfsMembers;
	private JButton pay;
	private JButton genReport;
	private JButton savReport;
	private JButton pntMembers;
	private JButton exit;
	private JLabel btnL;
	private static JLabel title;
	
	/**
	 * MemberPanel Constructor
	 */
	public MemberPanel()
	{
		buttons = new JPanel();
		titlePane = new JPanel();
		buttonPane = new JPanel();
		buff1 = new JPanel();
		buff2 = new JPanel();
		buff3 = new JPanel();
		setLayout(new BorderLayout());
		
		ta = new JTextArea("Read more books! :)", 30, 50);
		ta.setFont(new Font("Courier", Font.PLAIN, 14));
		ta.setMargin(new Insets(20, 25, 20, 25));
		ta.setEditable(false);
		sp = new JScrollPane(ta);
		sp.setPreferredSize(new Dimension(1000, 500));
		sp.setMaximumSize(new Dimension(1000, 500));
		
		title = new JLabel("Member Page Status: Welcome!");
		title.setFont(new Font(getFont().getName(), Font.PLAIN, 36));
		title.setAlignmentX(CENTER_ALIGNMENT);
		
		titlePane.setLayout(new BoxLayout(titlePane, BoxLayout.Y_AXIS));
		titlePane.add(title);
		titlePane.add(sp);
		
		btnL = new JLabel("Actions");
		btnL.setFont(new Font(getFont().getName(), Font.PLAIN, 24));
		btnL.setAlignmentX(CENTER_ALIGNMENT);
		
		addMember = new JButton("Add Member");
		addMember.addActionListener(new AddListener());
		addMember.setAlignmentX(CENTER_ALIGNMENT);
		
		delMember = new JButton("Delete Member");
		delMember.addActionListener(new DelListener());
		delMember.setAlignmentX(CENTER_ALIGNMENT);
		
		fndMember = new JButton("Find Member");
		fndMember.addActionListener(new FndListener());
		fndMember.setAlignmentX(CENTER_ALIGNMENT);
		
		pay = new JButton("Make Payment");
		pay.addActionListener(new PayListener());
		pay.setAlignmentX(CENTER_ALIGNMENT);
		
		rfsMembers = new JButton("Refresh List");
		rfsMembers.addActionListener(new RfsListener());
		rfsMembers.setAlignmentX(CENTER_ALIGNMENT);
		
		genReport = new JButton("Generate Report");
		genReport.addActionListener(new GenListener());
		genReport.setAlignmentX(CENTER_ALIGNMENT);
		
		savReport = new JButton("Save");
		savReport.addActionListener(new SavListener());
		savReport.setAlignmentX(CENTER_ALIGNMENT);
		
		pntMembers = new JButton("Print");
		pntMembers.addActionListener(new PntListener());
		pntMembers.setAlignmentX(CENTER_ALIGNMENT);
		
		exit = new JButton("Quit");
		exit.addActionListener(new ExtListener());
		exit.setAlignmentX(CENTER_ALIGNMENT);
		
		buttons.setLayout(new GridLayout(9, 1, 180, 10));
		buttons.setMaximumSize(new Dimension(180, 300));
		buttons.add(addMember);
		buttons.add(delMember);
		buttons.add(fndMember);
		buttons.add(pay);
		buttons.add(rfsMembers);
		buttons.add(genReport);
		buttons.add(savReport);
		buttons.add(pntMembers);
		buttons.add(exit);
		
		buttonPane.setLayout(new BoxLayout(buttonPane, BoxLayout.Y_AXIS));
		buttonPane.add(Box.createRigidArea(new Dimension(180, 50)));
		buttonPane.add(btnL);
		buttonPane.add(Box.createRigidArea(new Dimension(180, 25)));
		buttonPane.add(buttons);
	
		buttonPane.setPreferredSize(new Dimension(200, 600));
		buff1.setPreferredSize(new Dimension(100, 100));
		buff2.setPreferredSize(new Dimension(50, 50));
		buff3.setPreferredSize(new Dimension(50, 50));
		
		add(titlePane, BorderLayout.CENTER);
		add(buttonPane, BorderLayout.EAST);
		add(buff1, BorderLayout.WEST);
		add(buff2, BorderLayout.NORTH);
		add(buff3, BorderLayout.SOUTH);
		
		ta.setText(Data.listMembers(0));
	}
	
	/**
	 * A private class that is triggered when the Add Button on the MemberPanel is pressed.
	 * 
	 * @author Joshua
	 * @version 1.0
	 */
	private class AddListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e) 
		{
			String firstName;
			String lastName;
			String phone;
			String er = "";
			int trys = 0;
			int  confirm = JOptionPane.NO_OPTION;
			boolean resident;
			
			do
			{
				if(trys > 0)
					er = "Try Again\n";
				
				firstName = JOptionPane.showInputDialog(er + "Enter Member First Name", "George");
				trys++;
			}while(!Utility.validateString(firstName) && firstName != null);

			if(firstName != null)
			{
				er = "";
				trys = 0;
				
				do
				{
					if(trys > 0)
						er = "Try Again\n";
						
					lastName = JOptionPane.showInputDialog(er + "Enter Member Last Name", "McCloony");
					trys++;
				}while(!Utility.validateString(lastName) && lastName != null);
			
				if(lastName != null)
				{
					er = "";
					trys = 0;
			
					do{
						if(trys > 0)
							er = "Try Again\n";
							
						phone = JOptionPane.showInputDialog(er + "Enter Phone Number", "(111)-222-3333");
						trys++;
					}while(!Utility.validateString(phone) && phone != null);
					
					if(phone != null)
					{
						resident = (JOptionPane.showConfirmDialog(null, "Is the new Member a resident") == JOptionPane.YES_OPTION);
					
				
						confirm = JOptionPane.NO_OPTION;
						confirm = JOptionPane.showConfirmDialog(null, "Add Member: " + firstName + " " + lastName);
						if(confirm == JOptionPane.YES_OPTION)
						{
							ta.setText(Data.add(new Member(0, firstName, lastName, phone, resident, 0.00, 0)));
						}
					}
				}
			}
		}
	}
	
	/**
	 * A private class that is triggered when the Delete Button on the MemberPanel is pressed.
	 * 
	 * @author Joshua
	 * @version 1.0
	 */
	private class DelListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e) {
			String s = "";
			String er ="";
			int id = 0;
			int trys = 0;
			boolean invalid = false;

			do{
				if(trys > 0)	
					er = "Try Again\n";
				s = null;
				s = JOptionPane.showInputDialog( er + "Enter the Id of the member you wish deleted", "00");
			 
				trys++;
				
				if(s != null)
					try
					{
						id = Integer.parseInt(s);
						invalid = !Utility.validateMemberId(id);
					}
					catch(Exception ex)
					{
						id = 0; 
						invalid = true;
					}

			}while(invalid && s != null);
			
			if(!invalid)
			{
				if(Utility.validateMemberDelete(id))
				{
					ta.setText(Data.delMember(id));
					title.setText("Member Page Status: Deletion Complete");
				}
				else if(id != 0)
				{				
					title.setText("Member Page Status: Deletion Failed");
				}
			}
		}	
	}
	
	/**
	 * A private class that is triggered when the Find Button on the MemberPanel is pressed.
	 * 
	 * @author Joshua
	 * @version 1.0
	 */
	private class FndListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e) {
			String s = null;
			int option;
			int num = -1;
			
			option = JOptionPane.showOptionDialog(null, "What criteria do you wish to search by", "Search Criteria?", 
					JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, new String[] {"Last Name", "First Name", "Id"}, null);
			
			if(option == 2){
				s = JOptionPane.showInputDialog("Enter member Id to search", "00");
				try{
					num = Integer.parseInt(s);
				}catch(Exception ex){
					num = -1;
				}
				if(num >= 0)
					s = "" + num;
				else
					s = "";
			}
			else if(option == 1)
				s = JOptionPane.showInputDialog("Enter first name to search", "George");
			else if(option == 0)
				s = JOptionPane.showInputDialog("Enter last name to search", "McCloony");

			
			if(Utility.validateString(s))
			{
				ta.setText(Data.findMembers(s, option));
				title.setText("Member Page Status: Search Results for \"" + s + "\"");
			}
			else if(num != -1)
				title.setText("Member Page Status: Search for \"" + s + "\" Failed");
		}
	}
	
	/**
	 * A private class that is triggered when the Refresh Button on the MemberPanel is pressed.
	 * 
	 * @author Joshua
	 * @version 1.0
	 */
	private class RfsListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e) {
			int option = -1;
			
			// I need to find code to make a better layout
			option = JOptionPane.showOptionDialog(null, "Sort by?", "Sort by?", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE,
					null, new Object[] {"Id", "Last Name", "First Name"}, null);
				
				
			if(option >= 0)
			{
				title.setText("Member Page Status: List Refreshed");
				ta.setText(Data.listMembers(option));
			}
		}
	}
	
	/**
	 * A private class that is triggered when the Generate Report Button on the MemberPanel is pressed.
	 * 
	 * @author Joshua
	 * @version 1.0
	 */
	private class GenListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e) {
			ta.setText(Data.listMembers(0));
			title.setText("Member Page Status: Report Generated");
		}
	}
	
	/**
	 * A private class that is triggered when the Make Payment Button on the MemberPanel is pressed.
	 * 
	 * @author Joshua
	 * @version 1.0
	 */
	private class PayListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e) {
			int memberId = 0;
			double pay = 0;
			boolean invalid = false;
			String id;
			String change;
			String payment = null;
			int trys = 0;
			String er = "";
			
			do{
				if(trys > 0)	
					er = "Try Again\n";
				id = null;	
				id = JOptionPane.showInputDialog(er + "Enter Member Id.", "5");
				try
				{
						memberId = Integer.parseInt(id);
						invalid =  !Utility.validateMemberId(memberId);
				}
				catch(Exception ex)
				{
						invalid = true;
				}
			}while(invalid && id != null);
			
			if(!invalid)
			{
				trys = 0;
				er = "";
				do{
					if(trys > 0)	
						er = "Try Again\n";
					payment = null;
					payment = JOptionPane.showInputDialog( er + "Enter dollar amount.", "2.00");
					try
					{
						pay = Double.parseDouble(payment);
						invalid = false;
					}
					catch(Exception ex)
					{
						invalid = true;
					}
				}while(invalid && payment != null);
				
				if(payment != null)
				{
					change = Data.makePayment(memberId, pay);
					title.setText("Member Page Status: Payment Made | " + change + " Change");
					ta.setText(Data.listMembers(0));
				}
			}
		}
	}
	
	/**
	 * A private class that is triggered when the Save Button on the MemberPanel is pressed.
	 * 
	 * @author Joshua
	 * @version 1.0
	 */
	private class SavListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e) {
			if(Data.toFile("member", ta.getText()))
				title.setText("Member Page Status: File Saved");
			else
				title.setText("Member Page Status: File Save Failed");
		}
	}
	
	/**
	 * A private class that is triggered when the Print Button on the MemberPanel is pressed.
	 * 
	 * @author Joshua
	 * @version 1.0
	 */
	private class PntListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e) {
			ta.setFont(new Font("Courier", Font.PLAIN, 14));
			try
			{
				if(ta.print())
					title.setText("Member Page Status: Print Successful");
			}
			catch(PrinterException pe)
			{
				title.setText("Member Page Status: Print Failed");
			}

		}
	}
	
	/**
	 * A private class that is triggered when the Quit Button on the MemberPanel is pressed.
	 * 
	 * @author Joshua
	 * @version 1.0
	 */
	private class ExtListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e) {
			System.exit(0);
		}
	}

	/**
	 * A static method used to refresh the MemberPanel from the other panels in the GUI.
	 */
	public static void refresh() {
		ta.setText(Data.listMembers(0));
		title.setText("Member Page Status: Welcome!");
	}
}

