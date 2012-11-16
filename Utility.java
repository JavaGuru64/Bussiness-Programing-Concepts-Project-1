/**
 * A class of static Utility methods generally dealing with validation.
 * 
 * @author Joshua
 * @version 1.0
 */
public class Utility {

	/**
	 * Takes a String and adds a margin of the specified width onto the sides of the String.
	 * 
	 * @param marginWidth
	 * The width in spaces of the margin
	 * 
	 * @param s
	 * The String the margin is to be added to
	 * 
	 * @return The newly formated String
	 * @deprecated This method ended up being unnecessary for what I wanted.
	 */
	public static String addMargins(int marginWidth, String s)
	{
		String result = "";
		String margin = "" ;
		
		String[] splits = s.split("\n", s.length());
		
		for(int i = 0; i < marginWidth; i++)
				margin += " ";
		
		for(String split : splits)
			result +=  margin + split + margin + "\n";

		return result;
	}

	/**
	 * Checks to make sure that the String passed in actually has a meaningful value.
	 * 
	 * @param s
	 * The String to be validated
	 * 
	 * @return A boolean signifying whether the value passed in is valid.
	 */
	public static boolean validateString(String s) {
		if(s != null){
			int count = 0;
			for(int i=0; i < s.length(); i++)
				if(s.charAt(i) != ' ')
					count ++;
			
			return (count > 0);
		}
				
		return false;
	}

	/**
	 * Checks to see if the book id given is allowed to be deleted.
	 * 
	 * @param id
	 * The Id of the book to be validated
	 * 
	 * @return A boolean signifying whether the value passed in is valid.
	 */
	public static boolean validateBookDelete(int id) {
		DB mydb = new DB();
		mydb.updateDatabase();
		boolean exists = false;
		
		for(Book b: mydb.listBooks(1))
		{
			if(b.getBookId() == id && b.isAvailable())
				exists = true;
		}
		
		return exists;
	}

	/**
	 * checks to make sure the int passed in is non-negative.
	 * 
	 * @param num
	 * The int to be validated
	 * 
	 * @return A boolean signifying whether the value passed in is valid.
	 */
	public static boolean validateInt(int num) {
		return num >= 0;
	}

	/**
	 * Checks to see if the member id given is allowed to be deleted.
	 * 
	 * @param id
	 * The Id of the member to be validated
	 * 
	 * @return A boolean signifying whether the value passed in is valid.
	 */
	public static boolean validateMemberDelete(int id) {
		DB mydb = new DB();
		mydb.updateDatabase();
		boolean exists = false;
		
		for(Member m: mydb.listMembers(1))
		{
			if(m.getMemberId() == id && m.getBooksOut() == 0 && m.getLateFeesDue() < .001)
				exists = true;
		}
		
		return exists;
	}
	
	/**
	 * Checks to see if the member id given exists in the database.
	 * 
	 * @param id
	 * the Id of the member to be validated
	 * 
	 * @return A boolean signifying whether the value passed in is valid.
	 */
	public static boolean validateMemberId(int id) {
		DB mydb = new DB();
		mydb.updateDatabase();
		boolean exists = false;
		
		for(Member m: mydb.listMembers(1))
		{
			if(m.getMemberId() == id)
				exists = true;
		}
		
		return exists;
	}

	/**
	 * Checks to see if the member id and book id given are valid for a borrow.
	 * 
	 * @param mId
	 * The Id of the member to be validated
	 * 
	 * @param bId
	 * The Id of the book to be validated
	 * 
	 * @return A boolean signifying whether the value passed in is valid.
	 */
	public static boolean borrowAllowed(int mId, int bId) {
		DB mydb = new DB();
		mydb.updateDatabase();
		boolean b = false;
		if(validateMemberId(mId) && validateBookId(bId))
			b = mydb.isBorrowAllowed(mId, bId);
		return b;
	}

	/**
	 * Checks to see if the book id given exists in the database.
	 * 
	 * @param id
	 * The Id of the book to be validated
	 * 
	 * @return A boolean signifying whether the value passed in is valid.
	 */
	public static boolean validateBookId(int id) {
		DB mydb = new DB();
		mydb.updateDatabase();
		boolean exists = false;
		
		for(Book b: mydb.listBooks(1))
		{
			if(b.getBookId() == id)
				exists = true;
		}
		
		return exists;
	}

	/**
	 * Checks to see if the book id given is valid for a return.
	 * 
	 * @param id
	 * The Id of the book to be validated
	 * 
	 * @return A boolean signifying whether the value passed in is valid.
	 */
	public static boolean validateBookReturn(int id) {
		DB mydb = new DB();
		mydb.updateDatabase();
		boolean exists = false;
		
		for(Book b: mydb.listBooks(1))
		{
			if(b.getBookId() == id && !b.isAvailable())
				exists = true;
		}
		
		return exists;
	}
}
