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
	 * The portion of the GUI used to interact with the books in the database
	 * 
	 * @author Joshua
	 * @version 1.0
	 */
	public class BorrowPanel extends JPanel {

		private static final long serialVersionUID = 3199623057121628197L;
		private JTextArea ta;
		private JScrollPane sp;
		private JPanel buttonPane;
		private JPanel buff1, buff2, buff3;
		private JPanel titlePane;
		private JPanel buttons;
		private JButton addBorrow;
		private JButton rtnBorrow;
		private JButton lstopBorrows;
		private JButton lstalBorrows;
		private JButton savReport;
		private JButton pntMembers;
		private JButton exit;
		private JLabel btnL;
		private JLabel title;
		
		/**
		 * BorrowPanel Constructor
		 */
		public BorrowPanel()
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
			
			title = new JLabel("Borrow Page Status: Welcome!");
			title.setFont(new Font(getFont().getName(), Font.PLAIN, 36));
			title.setAlignmentX(CENTER_ALIGNMENT);
			
			titlePane.setLayout(new BoxLayout(titlePane, BoxLayout.Y_AXIS));
			titlePane.add(title);
			titlePane.add(sp);
			
			btnL = new JLabel("Actions");
			btnL.setFont(new Font(getFont().getName(), Font.PLAIN, 24));
			btnL.setAlignmentX(CENTER_ALIGNMENT);
			
			addBorrow = new JButton("Borrow Book");
			addBorrow.addActionListener(new AddListener());
			addBorrow.setAlignmentX(CENTER_ALIGNMENT);
			
			rtnBorrow = new JButton("Return Book");
			rtnBorrow.addActionListener(new RtnListener());
			rtnBorrow.setAlignmentX(CENTER_ALIGNMENT);
			
			lstopBorrows = new JButton("List Open Borrows");
			lstopBorrows.addActionListener(new LstopListener());
			lstopBorrows.setAlignmentX(CENTER_ALIGNMENT);
			
			lstalBorrows = new JButton("List All Borrows");
			lstalBorrows.addActionListener(new LstalListener());
			lstalBorrows.setAlignmentX(CENTER_ALIGNMENT);
			
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
			buttons.add(addBorrow);
			buttons.add(rtnBorrow);
			buttons.add(lstopBorrows);
			buttons.add(lstalBorrows);
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
			
			ta.setText(Data.listBorrows(false));
		}
		
		/**
		 * A private class that is triggered when the Borrow Book Button on the BorrowPanel is pressed.
		 * 
		 * @author Joshua
		 * @version 1.0
		 */
		private class AddListener implements ActionListener
		{
			public void actionPerformed(ActionEvent e) 
			{
				String mIds = null;
				String bIds = null;
				int mId = -1;
				int bId = -1;
				boolean invalid = false;
				String er = "";
				int trys = 0;
				int  confirm = JOptionPane.NO_OPTION;
				
				do
				{
					if(trys > 0)
						er = "Try Again\n";
					mIds = null;
					mIds = JOptionPane.showInputDialog(er + "Enter Member Id", "256");
					trys++;
					try{
						mId = Integer.parseInt(mIds);
						invalid = !Utility.validateMemberId(mId);
					}catch(Exception ex)
					{
						invalid = true;
					}
				}while(invalid && mIds != null);

				if(!invalid)
				{
					er = "";
					trys = 0;
					
					do
					{
						if(trys > 0)
							er = "Try Again\n";
						bIds = null;
						bIds = JOptionPane.showInputDialog(er + "Enter Book Id", "1000");
						trys++;
						try{
							bId = Integer.parseInt(bIds);
							invalid = !Utility.validateBookId(bId);
						}catch(Exception ex)
						{
								invalid = true;
						}
					}while(invalid && bIds != null);
				
					if(!invalid && Utility.borrowAllowed(mId, bId))
					{			
						confirm = JOptionPane.NO_OPTION;
						confirm = JOptionPane.showConfirmDialog(null, "Member Id " + mId + " Borrowed  Book Id " + bId+ "?");
						if(confirm == JOptionPane.YES_OPTION)
						{
							ta.setText(Data.add(mId, bId));
							BookPanel.refresh();
							MemberPanel.refresh();
						}
					}
					else if(!invalid && confirm == JOptionPane.YES_OPTION)
					{
						title.setText("Borrow Page Status: Borrow Blocked");
					}
				}
			}
		}
		
		/**
		 * A private class that is triggered when the Return Book Button on the BorrowPanel is pressed.
		 * 
		 * @author Joshua
		 * @version 1.0
		 */
		private class RtnListener implements ActionListener
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
					s = JOptionPane.showInputDialog( er + "Enter the Id of the book you wish returned", "00");
					trys++;
					
					if(s != null)
						try
						{
							id = Integer.parseInt(s);
							invalid = false;
						}catch(Exception ex)
						{ 
							id = 0;
							invalid = true;
						}

				}while(invalid && !Utility.validateBookReturn(id) && s != null);
				
				if(!invalid){
					if(Utility.validateBookReturn(id))
					{
						ta.setText(Data.returnBook(id));
						title.setText("Borrow Page Status: Return Successful");
						BookPanel.refresh();
						MemberPanel.refresh();
					}
					else
					{				
						title.setText("Borrow Page Status: Return Failed | Check Id");
					}
				}
			}	
		}
		
		/**
		 * A private class that is triggered when the List Open Button on the BorrowPanel is pressed.
		 * 
		 * @author Joshua
		 * @version 1.0
		 */
		private class LstopListener implements ActionListener
		{
			public void actionPerformed(ActionEvent e) {
					ta.setText(Data.listBorrows(false));
					title.setText("Borrow Page Status: Open Borrows");
			}
		}
		
		/**
		 * A private class that is triggered when the List All Button on the BorrowPanel is pressed.
		 * 
		 * @author Joshua
		 * @version 1.0
		 */
		private class LstalListener implements ActionListener
		{
			public void actionPerformed(ActionEvent e) {
				ta.setText(Data.listBorrows(true));
				title.setText("Borrow Page Status: All Borrows");
			}
		}
		
		
		/**
		 * A private class that is triggered when the Save Button on the BorrowPanel is pressed.
		 * 
		 * @author Joshua
		 * @version 1.0
		 */
		private class SavListener implements ActionListener
		{
			public void actionPerformed(ActionEvent e) {
				if(Data.toFile("borrow", ta.getText()))
					title.setText("Borrow Page Status: File Saved");
				else
					title.setText("Borrow Page Status: File Save Failed");
			}
		}
		
		/**
		 * A private class that is triggered when the Print Button on the BorrowPanel is pressed.
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
						title.setText("Borrow Page Status: Print Successful");
				}
				catch(PrinterException pe)
				{
					title.setText("Borrow Page Status: Print Failed");
				}

			}
		}
		
		/**
		 * A private class that is triggered when the Quit Button on the BorrowPanel is pressed.
		 * 
		 * @author Joshua
		 * @version 1.0
		 */
		private class ExtListener implements ActionListener
		{
			public void actionPerformed(ActionEvent e) 
			{
				System.exit(0);
			}
		}
}

