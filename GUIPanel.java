
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTabbedPane;

	/**
	 * The main frame containing all of the applications GUI.
	 * 
	 * @author Joshua
	 * @version 1.0
	 */
	public class GUIPanel 
	{
		private JFrame frame;
		private JTabbedPane framePanel;
		
		private BookPanel bk;
		private MemberPanel m;
		private BorrowPanel b;
		
		/**
		 * Constructor sets up the entire GUI.
		 */
		public GUIPanel() 
		{
			framePanel = new JTabbedPane();
			bk = new BookPanel();
			m = new MemberPanel();
			b = new BorrowPanel();
			
			
			framePanel.addTab("Books", bk);
			framePanel.addTab("Members", m);
			framePanel.addTab("Borrows & Returns", b);



			framePanel.setOpaque(true);

			createMenu();
			
			frame = new JFrame("The Library Of Yore");
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			//frame.setJMenuBar(createMenu());
			frame.setContentPane(framePanel);
			frame.setLocation(100, 150);
			frame.setSize(new Dimension(1200, 800));
			frame.setResizable(true);
			frame.setVisible(true);
		} 

		/**
		 * Creates a menu bar (Unused)
		 * 
		 * @return the created menu bar
		 */
		private JMenuBar createMenu()
		{
			JMenuBar menuBar = new JMenuBar();
			JMenu menu = new JMenu("File");
			JMenuItem exitMI;
			
			menuBar.add(menu);
			
			exitMI = new JMenuItem("Quit");
			exitMI.addActionListener(new ExitListener());
			menu.add(exitMI);
			
			return menuBar;
		}
		
		/** 
		 * Exits the application (Unused)
		 * 
		 * @author Joshua
		 * @version 1.0
		 */
		private class ExitListener implements ActionListener
		{
			public void actionPerformed(ActionEvent e)
			{
				System.exit(0);
			}
		}

	}
