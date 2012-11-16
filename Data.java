import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Date;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;


/**
 * A grouping of static methods used to manipulate and format data retrieved from the database.
 * 
 * @author Joshua
 * @version 1.0
 */
public class Data {
	
	/**
	 * Gets a list of Member Objects from the database and puts their data into a nicely formated String.
	 * 
	 * @return A String formated nicely to be printed to the screen
	 * @deprecated Replaced by listMembers(int order).
	 */
	public static String getCurrentFeeReport()
	{
		DB mydb = new DB();
		mydb.updateDatabase();
		ArrayList<Member> data = mydb.listMembers(0);
		
		double totalOutstandingFees = 0;
		int memberCount = data.size();
		int residents = 0;
		int nonResidents = 0;
		Date date = new Date();
		
		NumberFormat nFmt = NumberFormat.getCurrencyInstance(); 
		StringBuilder report = new StringBuilder("The Library Of Yore\nMemberListing\n" + date.toString() + "\n\n");
		for(int i = 0; i < 100; i++)
			report.append("-");
		
		report.append("\n");
		
		report.append(String.format("%-10s | %-15s | %-15s | %-15s | %-10s | %-5s | %-15s\n",
				"ID", "Last Name", "First Name", "Phone Number", "Fees Due", "Out", "Residency"));
		
		for(int i = 0; i < 100; i++)
			report.append("-");
		
		report.append("\n");
		
		for(Member m : data)
		{
			String first = m.getFirstName();
			String last = m.getLastName();
			String res;
			String due = nFmt.format(m.getLateFeesDue());
			
			if(first.length() > 15)
				first = first.substring(0, 12) + "...";
			
			if(last.length() > 15)
				last = last.substring(0, 12) + "...";
			
			if(m.isResident())
			{
				res = "resident";
				residents ++;
			}
			else
			{
				res = "non-resident";
				nonResidents ++;
			}
				
			totalOutstandingFees += m.getLateFeesDue();
			
			report.append(String.format("%-10s | %-15s | %-15s | %-15s | %10s | %-5s | %-15s\n", 
					m.getMemberId(), last, first, m.getPhone(), due, m.getBooksOut(), res));
		}
		for(int i = 0; i < 100; i++)
			report.append("-");
			
		report.append("\n" + String.format("Residents: %-7s Non-Residents: %-7s Total Members: %-7s Total Fees Due: %-14s", 
				"" + residents, "" + nonResidents, "" + memberCount, nFmt.format(totalOutstandingFees)));
		
		return report.toString();
	}
	
	/**
	 * Writes out the String s to a text file with a name beginning 
	 * with the String str followed by _report_(Current Date in Milis)
	 * 
	 * @param str
	 * The beginning of the file name
	 * 
	 * @param s
	 * The String to be sent to a file
	 * 
	 * @return A boolean signifying whether the file write was successful or not.
	 */
	public static boolean toFile(String str, String s)
	{
		boolean b = false;
		try{
			File f = new File(str + "_report_" + new Date().getTime() + ".txt");
			BufferedWriter out = new BufferedWriter(new FileWriter(f));
			
			out.write(s);
			
			b = true;
			out.close();
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		return b;
	}

	/**
	 * Gets a list of Book Objects from the database and puts their data into a nicely formated String.
	 * Designed for easier report generation than listBooks(int order, int limiter).
	 * 
	 * @return A String formated nicely to be printed to the screen
	 */
	public static String getBookReport() {
		DB mydb = new DB();
		mydb.updateDatabase();
		ArrayList<Book> data = mydb.listBooks(1);
		
		int bookCount = data.size();
		int available = 0;
		int notAvailable = 0;
		Date date = new Date();
		
		StringBuilder report = new StringBuilder("The Library Of Yore\nBook Inventory Report\n" + date.toString() + "\n\n");
		for(int i = 0; i < 95; i++)
			report.append("-");
		
		report.append("\n");
		
		report.append(String.format("%-15s | %-20s | %-20s | %-10s | %15s\n",
				"ID", "Title", "Author", "Editon", "Availability"));
		
		for(int i = 0; i < 95; i++)
			report.append("-");
		
		report.append("\n");
		
		for(Book bk : data)
		{
			String name = bk.getName();
			String author = bk.getAuthor();
			String edition = "N/A";
			String availability = "";
			
			if(bk.getEdition() != null)
			{
				edition = bk.getEdition();
				
				if(edition.length() > 10)
					edition = edition.substring(0, 7) + "...";
			}
			
			if(bk.isAvailable())
			{
				available++;
				availability = "Available";
			}
			else
			{
				notAvailable++;
				availability = "Not-Available";
			}
			
			if(name.length() > 20)
				name = name.substring(0, 17) + "...";
			
			if(author.length() > 20)
				author = author.substring(0, 17) + "...";
			
			if(edition.length() > 10)
				author = author.substring(0, 7) + "...";
			
				
			report.append(String.format("%-15s | %-20s | %-20s | %-10s | %15s\n", 
					bk.getBookId(), name, author, edition, availability));
		}
		for(int i = 0; i < 95; i++)
			report.append("-");
			
		report.append("\n" + String.format("Available: %-12s Not-Available: %-9s Total Books: %-15s", 
				"" + available, "" + notAvailable, "" + bookCount));
		
		return report.toString();
	}

	/**
	 * Adds the specified Book Object to the data base and returns the updated book listing.
	 * 
	 * @param book
	 * The Book Object to ad to the database
	 * 
	 * @return The String generated by getBookReport() after the book is added.
	 */
	public static String add(Book book) {
		DB mydb = new DB();
		mydb.add(book);
		mydb.updateDatabase();
		
		return getBookReport();
	}

	/**
	 * Deletes the Book Object with the specified Book Id from the database and returns the updated book listing.
	 * 
	 * @param id
	 * The Id of the book to be deleted
	 * 
	 * @return The String generated by getBookReport() after the book is deleted.
	 */
	public static String delBook(int id) {
		DB mydb = new DB();
		mydb.delBook(id);
		mydb.updateDatabase();
		
		return getBookReport();
	}

	/**
	 * Gets a list of Book Objects that are found in the database based on the search parameters 
	 * and puts their data into a nicely formated String.
	 * 
	 * @param s
	 * The String to search for
	 * 
	 * @param option
	 * Where to search: refer to DB.findBooks() for full details
	 * 
	 * @return A String formated nicely to be printed to the screen
	 */
	public static String findBooks(String s, int option) {
		DB mydb = new DB();
		mydb.updateDatabase();
		ArrayList<Book> data = mydb.findBooks(s, option);
		
		int bookCount = data.size();
		int available = 0;
		int notAvailable = 0;
		Date date = new Date();
		
		StringBuilder report = new StringBuilder("The Library Of Yore\n" + "Search Results \"" +  s + "\"\n" + date.toString() + "\n\n");
		for(int i = 0; i < 95; i++)
			report.append("-");
		
		report.append("\n");
		
		report.append(String.format("%-15s | %-20s | %-20s | %-10s | %15s\n",
				"ID", "Title", "Author", "Editon", "Availability"));
		
		for(int i = 0; i < 95; i++)
			report.append("-");
		
		report.append("\n");
		
		for(Book bk : data)
		{
			String name = bk.getName();
			String author = bk.getAuthor();
			String edition = "N/A";
			String availability = "";
			
			if(bk.getEdition() != null)
			{
				edition = bk.getEdition();
				
				if(edition.length() > 10)
					edition = edition.substring(0, 7) + "...";
			}
			
			if(bk.isAvailable())
			{
				available++;
				availability = "Available";
			}
			else
			{
				notAvailable++;
				availability = "Not-Available";
			}
			
			if(name.length() > 20)
				name = name.substring(0, 17) + "...";
			
			if(author.length() > 20)
				author = author.substring(0, 17) + "...";
			
			
				
			report.append(String.format("%-15s | %-20s | %-20s | %-10s | %15s\n", 
					bk.getBookId(), name, author, edition, availability));
		}
		for(int i = 0; i < 95; i++)
			report.append("-");
			
		report.append("\n" + String.format("Available: %-12s Not-Available: %-9s Total Matches: %-15s", 
				"" + available, "" + notAvailable, "" + bookCount));
		
		return report.toString();
	}
	
	/**
	 * Gets a list of the specified Book Objects from the database and puts
	 * their data into a nicely formated String in the specified order.
	 * 
	 * @param order
	 * specifies order: refer to DB.listBooks() for full details
	 * 
	 * @param limiter
	 * limits the listing: if limiter = 1, show Available; 
	 * if limiter = 2, show non-available; if limit equals anything else show all.
	 * 
	 * @return A String formated nicely to be printed to the screen
	 */
	public static String listBooks(int order, int limiter) {
		DB mydb = new DB();
		mydb.updateDatabase();
		ArrayList<Book> data = mydb.listBooks(order);
		
		int bookCount = data.size();
		int available = 0;
		int notAvailable = 0;
		Date date = new Date();
		String lmt, ord;
		
		if(limiter == 1)
			lmt = "Available";
		else if(limiter == 2)
			lmt = "Not-available";
		else
			lmt = "All";
		
		if(order == 1)
			ord = "Id";
		else if(order == 2)
			ord = "Title";
		else
			ord = "Author";
		
		StringBuilder report = new StringBuilder("The Library Of Yore\n" + lmt + " Books Sorted by " + ord + "\n" + date.toString() + "\n\n");
		for(int i = 0; i < 95; i++)
			report.append("-");
		
		report.append("\n");
		
		report.append(String.format("%-15s | %-20s | %-20s | %-10s | %15s\n",
				"ID", "Title", "Author", "Editon", "Availability"));
		
		for(int i = 0; i < 95; i++)
			report.append("-");
		
		report.append("\n");
		
		for(Book bk : data)
		{
			String name = bk.getName();
			String author = bk.getAuthor();
			String edition = "N/A";
			String availability = "";
			
			if(bk.getEdition() != null)
			{
				edition = bk.getEdition();
				
				if(edition.length() > 10)
					edition = edition.substring(0, 7) + "...";
			}
			
			
			
			if(name.length() > 20)
				name = name.substring(0, 17) + "...";
			
			if(author.length() > 20)
				author = author.substring(0, 17) + "...";
			
			if(edition.length() > 10)
				author = author.substring(0, 7) + "...";
			
			if(bk.isAvailable())
			{
				available++;
				availability = "Available";

			}
			else
			{
				notAvailable++;
				availability = "Not-Available";
			}

				
				
			if(limiter == 3 && !bk.isAvailable())
			{
				report.append(String.format("%-15s | %-20s | %-20s | %-10s | %15s\n",
					bk.getBookId(), name, author, edition, availability));
			}
			else if(limiter == 2 && bk.isAvailable())
			{
				report.append(String.format("%-15s | %-20s | %-20s | %-10s | %15s\n",
					bk.getBookId(), name, author, edition, availability));
			}
			else if(limiter == 1)
			{
				report.append(String.format("%-15s | %-20s | %-20s | %-10s | %15s\n",
					bk.getBookId(), name, author, edition, availability));
			}
			
				
			
		}
		for(int i = 0; i < 95; i++)
			report.append("-");
			
		report.append("\n" + String.format("Available: %-12s Not-Available: %-9s Total Books: %-15s", 
				"" + available, "" + notAvailable, "" + bookCount));
		
		return report.toString();
	}

	/**
	 * Deletes the Member Object with the specified member id from the database and returns the updated member listing.
	 * 
	 * @param id
	 * The Id of the member to be deleted
	 * 
	 * @return A String formated nicely to be printed to the screen.
	 */
	public static String delMember(int id) {
		DB mydb = new DB();
		mydb.delMember(id);
		mydb.updateDatabase();
		
		return listMembers(0);
	}

	/**
	 * Gets a list of Member Objects that are found in the database based on the 
	 * search parameters and puts their data into a nicely formated String.
	 * 
	 * @param s
	 * The String to search for
	 * 
	 * @param option
	 * @return Specifies search option: refer to DB.findMembers() for full details
	 */
	public static String findMembers(String s, int option) {
		DB mydb = new DB();
		mydb.updateDatabase();
		ArrayList<Member> data = mydb.findMembers(s, option);
		
		double totalOutstandingFees = 0;
		int memberCount = data.size();
		int residents = 0;
		int nonResidents = 0;
		Date date = new Date();
		
		NumberFormat nFmt = NumberFormat.getCurrencyInstance(); 
		StringBuilder report = new StringBuilder("The Library Of Yore\nOutstanding Fees Report\n" + date.toString() + "\n\n");
		for(int i = 0; i < 95; i++)
			report.append("-");
		
		report.append("\n");
		
		report.append(String.format("%-10s | %-15s | %-15s | %-15s | %-10s |  %-5s | %-15s\n",
				"ID", "Last Name", "First Name", "Phone Number", "Fees Due", "Out", "Residency"));
		
		for(int i = 0; i < 95; i++)
			report.append("-");
		
		report.append("\n");
		
		for(Member m : data)
		{
			String first = m.getFirstName();
			String last = m.getLastName();
			String res;
			String due = nFmt.format(m.getLateFeesDue());
			
			if(first.length() > 15)
				first = first.substring(0, 12) + "...";
			
			if(last.length() > 15)
				last = last.substring(0, 12) + "...";
			
			if(m.isResident())
			{
				res = "resident";
				residents ++;
			}
			else
			{
				res = "non-resident";
				nonResidents ++;
			}
				
			totalOutstandingFees += m.getLateFeesDue();
			
			report.append(String.format("%-10s | %-15s | %-15s | %-15s | %-10s |  %-5s | %-15s\n",
					m.getMemberId(), last, first, m.getPhone(), due, m.getBooksOut(), res));
		}
		for(int i = 0; i < 95; i++)
			report.append("-");
			
		report.append("\n" + String.format("Residents: %-7s Non-Residents: %-7s Total Members: %-7s Total Fees Due: %-14s", 
				"" + residents, "" + nonResidents, "" + memberCount, nFmt.format(totalOutstandingFees)));
		
		return report.toString();
	}

	/**
	 * Adds the specified Member Object to the data base and returns the updated member listing.
	 * 
	 * @param member
	 * The Member Object to add to the database
	 * 
	 * @return The String generated by listMembers(0) after adding the member.
	 */
	public static String add(Member member) {
		DB mydb = new DB();
		mydb.updateDatabase();
		mydb.add(member);
		
		return listMembers(0);
	}

	/**
	 * Gets a list of  Member Objects from the database and puts their data into a nicely formated String in the specified order.
	 * 
	 * @param order
	 * The order to list by: refer to DB.listMembers() for more details
	 * 
	 * @return A nicely formated String in an order specified by the option parameter
	 */
	public static String listMembers(int order) {
		DB mydb = new DB();
		mydb.updateDatabase();
		ArrayList<Member> data = mydb.listMembers(order);
		
		double totalOutstandingFees = 0;
		int memberCount = data.size();
		int residents = 0;
		int nonResidents = 0;
		Date date = new Date();
		
		NumberFormat nFmt = NumberFormat.getCurrencyInstance(); 
		StringBuilder report = new StringBuilder("The Library Of Yore\nOutstanding Fees Report\n" + date.toString() + "\n\n");
		for(int i = 0; i < 95; i++)
			report.append("-");
		
		report.append("\n");
		
		report.append(String.format("%-10s | %-15s | %-15s | %-15s | %-10s |  %-5s | %-15s\n",
				"ID", "Last Name", "First Name", "Phone Number", "Fees Due", "Out", "Residency"));
		
		for(int i = 0; i < 95; i++)
			report.append("-");
		
		report.append("\n");
		
		for(Member m : data)
		{
			String first = m.getFirstName();
			String last = m.getLastName();
			String res;
			String due = nFmt.format(m.getLateFeesDue());
			
			if(first.length() > 15)
				first = first.substring(0, 12) + "...";
			
			if(last.length() > 15)
				last = last.substring(0, 12) + "...";
			
			if(m.isResident())
			{
				res = "resident";
				residents ++;
			}
			else
			{
				res = "non-resident";
				nonResidents ++;
			}
				
			totalOutstandingFees += m.getLateFeesDue();
			
			report.append(String.format("%-10s | %-15s | %-15s | %-15s | %-10s |  %-5s | %-15s\n", 
					m.getMemberId(), last, first, m.getPhone(), due, m.getBooksOut(), res));
		}
		for(int i = 0; i < 95; i++)
			report.append("-");
			
		report.append("\n" + String.format("Residents: %-7s Non-Residents: %-7s Total Members: %-7s Total Fees Due: %-14s", 
				"" + residents, "" + nonResidents, "" + memberCount, nFmt.format(totalOutstandingFees)));
		
		return report.toString();
	}

	/**
	 * Executes the makePayment(int memberId, double payment) from the DB class and returns the change in a nicely formated String.
	 * 
	 * @param memberId
	 * The Id of the member making the payment
	 * 
	 * @param payment
	 * The amount being payed
	 * 
	 * @return The change after payment in a nicely formated String.
	 */
	public static String makePayment(int memberId, double payment) {
		DB mydb = new DB();
		mydb.updateDatabase();
		double chng;
		String change;
		
		NumberFormat nbf = NumberFormat.getCurrencyInstance();
		chng = mydb.makePayment(memberId, payment);
		change = nbf.format(chng);
		
		return change;
	}

	/**
	 * Gets the member and book object specified from the database and uses 
	 * them to construct a new Borrow Object. This new Borrow Object is then added to the database its self.
	 * 
	 * @param memberId
	 * The Id of the member making the borrow
	 * 
	 * @param bookId
	 * The Id of the book being borrowed 
	 * 
	 * @return The String generated by listBorrows(false) after the Borrow Object is added
	 */
	public static String add(int memberId, int bookId) {
		DB mydb = new DB();
		mydb.updateDatabase();
		
		Member m = mydb.findMembers("" + memberId, 2).get(0);
		Book b = mydb.findBooks("" + bookId, 2).get(0);
		
		Borrow borrow = new Borrow(0, m, b, new Date());
		
		mydb.borrowBook(borrow);
		
		return listBorrows(false);
	}
	
	/**
	 * Executes the returnBook(int bookId) from the DB class passing it 
	 * the specified id and returns an updated member listing
	 *
	 * @param bookId
	 * The Id of the book being returned.
	 * 
	 * @return The String generated by listBorrows(false) after the return is complete
	 */
	public static String returnBook(int bookId)
	{
		DB mydb = new DB();
		mydb.returnBook(bookId);
		return listBorrows(false);
	}

	/**
	 * Gets an ArrayList of Borrow Objects from the database and lists them in a nicely formated string 
	 * limited by the boolean passed in.
	 * 
	 * @param b
	 * A limiter on what is being returned: if b = true, List all borrows; b = false, List only open borrows
	 * @return A String formated nicely to be printed to the screen
	 */
	public static String listBorrows(boolean b) {
		DB mydb = new DB();
		mydb.updateDatabase();
		ArrayList<Borrow> data = mydb.listBorrows(1);
		
		int allBorrows = data.size();
		int open = 0;
		int closed = 0;
		String listType = "";
		Date date = new Date();
		
		if(b)
			listType = "Listing All Borrows";
		else
			listType = "Listing Open Borrows";
		
		StringBuilder report = new StringBuilder("The Library Of Yore\n" + listType + "\n" + date.toString() + "\n\n");
		for(int i = 0; i < 95; i++)
			report.append("-");
		
		report.append("\n");
		
		report.append(String.format("%-15s | %-25s | %-20s | %-10s | %10s\n",
				"ID", "Member Name", "Book Name", "Borrowed", "Returned"));
		
		
		for(int i = 0; i < 95; i++)
			report.append("-");
		
		report.append("\n");
		
		for(Borrow br : data)
		{
			String memberName = br.getMember().getLastName() + ", " + br.getMember().getFirstName();
			String bookName = br.getBook().getName();
			String dateReturned = "Not Returned";
			
			if(memberName.length() > 25)
				memberName = memberName.substring(0, 22) + "...";
			
			if(bookName.length() > 20)
				bookName = bookName.substring(0, 17) + "...";
			
			if(br.getDateReturned() != null)
			{
				closed ++;
				dateReturned = br.getDateReturned().toString();
				if(b)
					report.append(String.format("%-15s | %-25s | %-20s | %-10s | %10s\n",
							br.getBorrowId(), memberName, bookName, br.getDateBorrowed(), dateReturned));
			}
			else
			{
				open++;
				report.append(String.format("%-15s | %-25s | %-20s | %-10s | %10s\n",
						br.getBorrowId(), memberName, bookName, br.getDateBorrowed(), dateReturned));
			}
			
			
		}
		for(int i = 0; i < 95; i++)
			report.append("-");
			
		report.append("\n" + String.format("Closed: %-12s Open: %-9s Total Borrows To Date: %-15s", 
				"" + closed, "" + open, "" + allBorrows));
		
		return report.toString();
	}
		
}


