/**
 * This class is used to start the entire program.
 * 
 * @author Joshua
 * @version 1.0
 */
public class LibraryApp {

	/**
	 * LibraryApp Constructor
	 */
	public LibraryApp() {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new GUIPanel();
			}
		});
	}
	
	/**
	 * The main method that begins the program.
	 * @param args
	 */
	public static void main(String[] args) {
		new LibraryApp();
	}

}
