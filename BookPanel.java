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
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 * The portion of the GUI used to interact with the books in the database
 * 
 * @author Joshua
 * @version 1.0
 */
public class BookPanel extends JPanel {
	
	private static final long serialVersionUID = -6402970633467557805L;
	private static JTextArea ta;
	private JScrollPane sp;
	private JPanel buttonPane;
	private JPanel buff1, buff2, buff3;
	private JPanel titlePane;
	private JPanel buttons;
	private JButton addBook;
	private JButton delBook;
	private JButton fndBook;
	private JButton rfsBooks;
	private JButton genReport;
	private JButton savReport;
	private JButton pntBooks;
	private JButton exit;
	private JLabel btnL;
	private static JLabel title;
	
	/**
	 * BookPanel Constructor
	 */
	public BookPanel()
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
		
		title = new JLabel("Book Page Status: Welcome!");
		title.setFont(new Font(getFont().getName(), Font.PLAIN, 36));
		title.setAlignmentX(CENTER_ALIGNMENT);
		
		titlePane.setLayout(new BoxLayout(titlePane, BoxLayout.Y_AXIS));
		titlePane.add(title);
		titlePane.add(sp);
		
		btnL = new JLabel("Actions");
		btnL.setFont(new Font(getFont().getName(), Font.PLAIN, 24));
		btnL.setAlignmentX(CENTER_ALIGNMENT);
		
		addBook = new JButton("Add Book");
		addBook.addActionListener(new AddListener());
		addBook.setAlignmentX(CENTER_ALIGNMENT);
		
		delBook = new JButton("Delete Book");
		delBook.addActionListener(new DelListener());
		delBook.setAlignmentX(CENTER_ALIGNMENT);
		
		fndBook = new JButton("Find Book");
		fndBook.addActionListener(new FndListener());
		fndBook.setAlignmentX(CENTER_ALIGNMENT);
		
		rfsBooks = new JButton("Refresh List");
		rfsBooks.addActionListener(new RfsListener());
		rfsBooks.setAlignmentX(CENTER_ALIGNMENT);
		
		genReport = new JButton("Generate Report");
		genReport.addActionListener(new GenListener());
		genReport.setAlignmentX(CENTER_ALIGNMENT);
		
		savReport = new JButton("Save");
		savReport.addActionListener(new SavListener());
		savReport.setAlignmentX(CENTER_ALIGNMENT);
		
		pntBooks = new JButton("Print");
		pntBooks.addActionListener(new PntListener());
		pntBooks.setAlignmentX(CENTER_ALIGNMENT);
		
		exit = new JButton("Quit");
		exit.addActionListener(new ExtListener());
		exit.setAlignmentX(CENTER_ALIGNMENT);
		
		buttons.setLayout(new GridLayout(9, 1, 180, 10));
		buttons.setMaximumSize(new Dimension(180, 300));
		buttons.add(addBook);
		buttons.add(delBook);
		buttons.add(fndBook);
		buttons.add(rfsBooks);
		buttons.add(genReport);
		buttons.add(savReport);
		buttons.add(pntBooks);
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
		
		ta.setText(Data.getBookReport());
	}
	
	/**
	 * A private class that is triggered when the Add Button on the BookPanel is pressed.
	 * 
	 * @author Joshua
	 * @version 1.0
	 */
	private class AddListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e) 
		{
			String name;
			String author;
			String edition = null;
			String er = "";
			int trys = 0;
			int  confirm = JOptionPane.NO_OPTION;
			// try a better validation if time allows
			do
			{
				if(trys > 0)
					er = "Try Again\n";
				
				name = JOptionPane.showInputDialog(er + "Enter Book Name","Moby Dick");
				trys++;
			}while(!Utility.validateString(name) && name != null);

			if(name != null)
			{
				er = "";
				trys = 0;
				
				do
				{
					if(trys > 0)
						er = "Try Again\n";
						
					author = JOptionPane.showInputDialog(er + "Enter Author Name", "J.R.R. Tolkien");
					trys++;
				}while(!Utility.validateString(author) && author != null);
			
				if(author != null)
				{
					er = "";
					trys = 0;
					
					if(JOptionPane.showOptionDialog(null, "Does your book have an edition?", "Edition?", JOptionPane.YES_NO_OPTION, 
							JOptionPane.QUESTION_MESSAGE, null, null, null) == 0)
					{
			
						do{
							if(trys > 0)
								er = "Try Again\n";
							
							edition = JOptionPane.showInputDialog(er + "Enter Book Edition", "5,000th");
							trys++;
							
							if(edition == null)
								confirm = JOptionPane.showConfirmDialog(null, "Does the book have an edition?", "Confirm Cancel", JOptionPane.YES_NO_OPTION);
						}while(!Utility.validateString(edition) && confirm != JOptionPane.YES_OPTION);
					}
					
					confirm = JOptionPane.NO_OPTION;
					confirm = JOptionPane.showConfirmDialog(null, "Add Book: " + name + " " + author + " " + edition);
					
					if(confirm == JOptionPane.YES_OPTION){
						if(Utility.validateString(edition)){
							ta.setText(Data.add(new Book(0, name, author, edition, true)));
						}
						else{
							ta.setText(Data.add(new Book(0, name, author, true)));
							title.setText("Book Page Status: Add Complete");	
						}
					}
				}
			}
		}
	}
	
	/**
	 * A private class that is triggered when the Delete Button on the BookPanel is pressed.
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
			
			// try a better validation if time allows
			do{
				if(trys > 0)	
					er = "Try Again\n";
					
				s = JOptionPane.showInputDialog( er + "Enter the Id of the book you wish deleted", "00");
			 
				trys++;
				
				if(s != null)
					try{
						id = Integer.parseInt(s);
					}catch(Exception ex){ id = 0; }

			}while((!Utility.validateInt(id) && s != null) || id == 0 && Utility.validateString(s));
			
			if(s != null){
				if(Utility.validateBookDelete(id))
				{
					ta.setText(Data.delBook(id));
					title.setText("Book Page Status: Deletion Complete");
				}
				else
				{				
					title.setText("Book Page Status: Deletion Failed | Bad Id");
				}
			}
		}	
	}
	
	/**
	 * A private class that is triggered when the Find Button on the BookPanel is pressed.
	 * 
	 * @author Joshua
	 * @version 1.0
	 */
	private class FndListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e) {
			String s = "";
			int option;
			int num;
			
			option = JOptionPane.showOptionDialog(null, "What criteria do you wish to search by", "Search Criteria?", 
					JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, new String[] {"Author", "Title", "Id"}, null);
			
			if(option == 2){
				s = JOptionPane.showInputDialog("Enter book Id to search", "00");
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
				s = JOptionPane.showInputDialog("Enter book title to search", "Art of War");
			else if(option == 0)
				s = JOptionPane.showInputDialog("Enter author name to search", "George");
			else
				s = "";
			
			if(Utility.validateString(s))
			{
				ta.setText(Data.findBooks(s, option));
				title.setText("Book Page Status: Search Results for \"" + s + "\"");
			}
			else if(option != -1)
				title.setText("Book Page Status: Search for \"" + s + "\" Failed");
		}
	}
	
	/**
	 * A private class that is triggered when the Refresh Button on the BookPanel is pressed.
	 * 
	 * @author Joshua
	 * @version 1.0
	 */
	private class RfsListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e) {
			ButtonGroup bg = new ButtonGroup();
			
			JRadioButton op1 = new JRadioButton("List all");
			op1.setSelected(true);
			JRadioButton op2 = new JRadioButton("List available");
			JRadioButton op3 = new JRadioButton("List non-available");
			
			bg.add(op1);
			bg.add(op2);
			bg.add(op3);
			
			int option = 0;
			
			// I need to find code to make a better layout
			option = JOptionPane.showOptionDialog(null, "Sort by?", "Sort by?", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE,
					null, new Object[] {op1, op2, op3, "Id", "Title", "Author"}, null);
			
			if(option > 0 && op1.isSelected())
				ta.setText(Data.listBooks(option - 2, 1));
			else if(option > 0 && op2.isSelected())
				ta.setText(Data.listBooks(option - 2, 2));
			else if(option > 0 && op3.isSelected())
				ta.setText(Data.listBooks(option - 2, 3));
				
			if(option < 0)
				title.setText("Book Page Status: List Refreshed");

		}
	}
	
	/**
	 * A private class that is triggered when the Generate Report Button on the BookPanel is pressed.
	 * 
	 * @author Joshua
	 * @version 1.0
	 */
	private class GenListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e) {
			ta.setText(Data.getBookReport());
			title.setText("Book Page Status: Report Generated");
		}
	}
	
	/**
	 * A private class that is triggered when the Save Button on the BookPanel is pressed.
	 * 
	 * @author Joshua
	 * @version 1.0
	 */
	private class SavListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e) {
			if(Data.toFile("book", ta.getText()))
				title.setText("Book Page Status: File Saved");
			else
				title.setText("Book Page Status: File Save Failed");
		}
	}
	
	/**
	 * A private class that is triggered when the Print Button on the BookPanel is pressed.
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
					title.setText("Book Page Status: Print Successful");
			}
			catch(PrinterException pe)
			{
				title.setText("Book Page Status: Print Failed");
			}

		}
	}
	
	/**
	 * A private class that is triggered when the Exit Button on the BookPanel is pressed.
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
	 * A static method used to refresh the BookPanel from the other panels in the GUI.
	 */
	public static void refresh() {
		ta.setText(Data.getBookReport());
		title.setText("Book Page Status: Welcome!");
	}
}
