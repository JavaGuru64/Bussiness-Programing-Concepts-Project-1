
import java.sql.*;
import java.util.ArrayList;
import java.text.SimpleDateFormat;
/**
 * The class that is used to connect to my library database.
 * 
 * @author Joshua
 * @version 1.0
 */
public class DB {
	private Connection connect = null;
	private String dbURL = "jdbc:mysql://database server IP/server username";
	private String username = database username;
	private String password = database password;

	/**
	 * DB Constructor
	 */
	public DB()
	{
		getConnection();
	}
	  
	/**
	 * Starts a connection with the database. Called by constructor.
	 */
	private void getConnection()
	{
		try
	    {
			  connect = DriverManager.getConnection(dbURL, username, password);
	    }
	    catch (SQLException e)
	    {
	    	System.out.println("Exception thrown calling getConnection.\n" + e.getMessage());
	    }
	}
	
	/**
	 * Adds the data for a new library member into the database.
	 * 
	 * @param m
	 * The Member Object to add to the database
	 * 
	 * @return A String message signifying success or failure.
	 */
	public String add(Member m)
	{
		String result = "";
		PreparedStatement ps = null;
		
		try
		{
			String q = "insert into members (memberId, firstName, lastName, phone, resident, lateFeesDue, booksOut) "
					+ "values (null, ?, ?, ?, ?, 0, 0)";
			ps = connect.prepareStatement(q);
			ps.setString(1, m.getFirstName());
			ps.setString(2, m.getLastName());
			ps.setString(3, m.getPhone());
			ps.setBoolean(4, m.isResident());
			ps.executeUpdate();
			ps.close();

			result = m.getFirstName() + " " + m.getLastName() + " has been added.";
		}
		catch(SQLException e)
		{
			System.out.println("QUERY: " + ps.toString());
			System.out.println("Error: " + e);
			result = "Member add failed.";
		}
		
		return result;
	}

	/**
	 * Adds the data for a new library book into the database.
	 * 
	 * @param book
	 * The Book Object to add to the database
	 * 
	 * @return A String message signifying success or failure.
	 */
	public String add(Book book)
	{
		String result = "";
		PreparedStatement ps = null;
		
		try
		{
			String q = "insert into books (bookId, name, author, edition, available) "
					+ "values (null, ?, ?, ?, true)";
			ps = connect.prepareStatement(q);

			if(book.getName().length() >= 150)
				ps.setString(1, book.getName().substring(0, 150));
			else
				ps.setString(1, book.getName());
			if(book.getName().length() >= 50)
				ps.setString(2, book.getAuthor());
			else
				ps.setString(2, book.getAuthor());
			if(book.getEdition() !=  null && book.getEdition().length() >= 25)
				ps.setString(3, book.getEdition().substring(0, 25));
			else
				ps.setString(3, book.getEdition());
			ps.executeUpdate();
			ps.close();

			result = book.getName() + " " + book.getAuthor() + " has been added.";
		}
		catch(SQLException e)
		{
			System.out.println("QUERY: " + ps.toString());
			System.out.println("Error: " + e);
			result = "Book add failed.";
		}
		
		return result;
	}
	
	/**
	 * Deletes the book with the given id from the database
	 * 
	 * @param id
	 * The Id of the Book to delete
	 * 
	 * @return A String message signifying success or failure.
	 */
	public String delBook(int id)
	{
		String result = "";
		PreparedStatement ps = null;
		
		try
		{
			String q = "delete from books where bookId = ?";
			ps = connect.prepareStatement(q);
			ps.setInt(1, id);
			ps.executeUpdate();
			ps.close();

			result = "Deletion Successful";
		}
		catch(SQLException e)
		{
			System.out.println("QUERY: " + ps.toString());
			System.out.println("Error: " + e);
			result = "Database Error: " + e.getErrorCode();
		}
		
		return result;
	}
	
	/**
	 * Deletes the member with the given id from the database
	 * 
	 * @param id
	 * The Id of the member to delete
	 * 
	 * @return A String message signifying success or failure.
	 */
	public String delMember(int id) {
		String result = "";
		PreparedStatement ps = null;
		
		try
		{
			String q = "delete from members where memberId = ?";
			ps = connect.prepareStatement(q);
			ps.setInt(1, id);
			ps.executeUpdate();
			ps.close();

			result = "Deletion Successful";
		}
		catch(SQLException e)
		{
			System.out.println("QUERY: " + ps.toString());
			System.out.println("Error: " + e);
			result = "Database Error: " + e.getErrorCode();
		}
		
		return result;
	}
	
	/**
	 * A method used to find books and display data for book objects in the database.
	 * 
	 * @param find
	 * The String to find
	 * 
	 * @param option
	 * Sets the search option: if option = 0, Author; option = 1, Name; option = 2, Book Id; 
	 * if option equals anything else the entire list of books is returned.
	 * 
	 * @return Returns an ArrayList of Book Objects that contain the String
	 * specified by find in the area specified by option. 
	 * 
	 */
	public ArrayList<Book> findBooks(String find, int option)
	{
		ArrayList<Book> result = new ArrayList<Book>();
		PreparedStatement ps = null;
		ResultSet rs = null; 
		
		try
		{
			String q = "select bookId, name, author, edition, available from books";
			
			if(option == 2)
			{
				q = q + " where bookId = ? order by bookId, available desc";
			}
			else if(option == 1)
			{
				q = q + " where name like ? order by name, author, available desc";
			}
			else if(option == 0)
			{
				q = q + " where author like ? order by author, name, available desc";
			}
			
			ps = connect.prepareStatement(q);
			if(option == 2)
			{
				int num = Integer.parseInt(find);
				ps.setInt(1, num);
			}
			else{
				ps.setString(1, "%" + find + "%");
			}
			rs = ps.executeQuery();
			

			while(rs.next()){
				if(rs.getString("edition") == null)
					result.add(new Book(rs.getInt("bookId"), rs.getString("name"), rs.getString("author"), rs.getBoolean("available")));
				else
					result.add(new Book(rs.getInt("bookId"), rs.getString("name"), rs.getString("author"), rs.getString("edition"), rs.getBoolean("available")));
			}
		}
		catch(SQLException e)
		{
			System.out.println("QUERY: " + ps.toString());
			System.out.println("Error: " + e);
		}
		
		return result;
	}
	
	/**
	 * A method used to find books and display data for book objects in the database.
	 * 
	 * @param find
	 * The String to find
	 * 
	 * @param option
	 * Sets the Search option: If option = 0, Last Name; option = 1, First Name;
	 * option = 2, Member Id; if option equals anything else the entire list of members is returned.
	 * 
	 * @return Returns an ArrayList of Member Objects that contain the 
	 * String specified by find in the area specified by option. 
	 */
	public ArrayList<Member> findMembers(String find, int option)
	{
		ArrayList<Member> result = new ArrayList<Member>();
		PreparedStatement ps = null;
		ResultSet rs = null; 
		
		try
		{
			String q = "select memberId, firstName, lastName, phone, resident, lateFeesDue, booksOut from members";
			
			if(option == 2)
			{
				q = q + " where memberId = ? order by memberId, resident desc";
			}
			else if(option == 1)
			{
				q = q + " where firstName like ? order by firstName, lastName, resident desc";
			}
			else if(option == 0)
			{
				q = q + " where lastName like ? order by lastName, firstName, resident desc";
			}
			
			ps = connect.prepareStatement(q);
			if(option == 2)
			{
				int num = Integer.parseInt(find);
				ps.setInt(1, num);
			}
			else{
				ps.setString(1, "%" + find + "%");
			}
			rs = ps.executeQuery();
			

			while(rs.next()){
				result.add(new Member(rs.getInt("memberId"), rs.getString("firstName"), rs.getString("lastName"), 
					rs.getString("phone"), rs.getBoolean("resident"), rs.getDouble("lateFeesDue"), rs.getInt("booksOut")));
			}
		}
		catch(SQLException e)
		{
			System.out.println("QUERY: " + ps.toString());
			System.out.println("Error: " + e);
		}
		
		return result;
	}
	
	/**
	 * Checks the database to see if the member specified by the member is allowed to borrow the book 
	 * specified by the book id. The books availability is also checked in this method
	 * 
	 * @param memberId
	 * The Id of the Member to check.
	 * 
	 * @param bookId
	 * The Id of the Book to check.
	 * 
	 * @return A boolean equaling true if allowed and false otherwise
	 */
	public boolean isBorrowAllowed(int memberId, int bookId)
	{
		PreparedStatement ps = null;
		ResultSet rs = null;
		boolean isAllowed = false;		

		try
		{
			String q = "select booksOut, lateFeesDue from members where memberId = ?";
			ps = connect.prepareStatement(q);
			ps.setInt(1, memberId);
			rs = ps.executeQuery();
			rs.next();
			
			isAllowed = (rs.getInt("booksOut") <= 5 && (rs.getDouble("lateFeesDue") < .001 && rs.getDouble("lateFeesDue") > -.001));

			if(isAllowed)
			{
				q = "select available from books where bookId = ?";
				ps = connect.prepareStatement(q);
				ps.setInt(1, bookId);
				rs = ps.executeQuery();
				rs.next();
				
				isAllowed = rs.getBoolean("available");
			}
			
			ps.close();
			rs.close();
		}
		catch(SQLException e)
		{
			System.out.println("QUERY: " + ps.toString());
			System.out.println("Error: " + e);
		}

		return isAllowed;
	}
	
	/**
	 * This method executes all the data managing that creating a borrow requires.
	 * 
	 * @param b
	 * The Borrow Object to add to the database
	 * 
	 * @return A String message signifying success or failure.
	 */
	public String borrowBook(Borrow b)
	{
		String result = "";
		PreparedStatement ps = null;
		
		try
		{
			String q = "insert into borrows (borrowId, memberId, bookId, dateBorrowed, dateReturned) "
					+ "values (null, ?, ?, ?, null)";
			ps = connect.prepareStatement(q);
			ps.setInt(1, b.getMember().getMemberId());
			ps.setInt(2, b.getBook().getBookId());
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			ps.setString(3, sdf.format(b.getDateBorrowed()));
			ps.executeUpdate();
			
			q = "update books set available = false where bookId = ?";
			
			ps = connect.prepareStatement(q);
			ps.setInt(1, b.getBook().getBookId());
			ps.executeUpdate();
			
			q = "update members set booksOut = booksOut + 1 where memberId = ?";
			
			ps = connect.prepareStatement(q);
			ps.setInt(1, b.getMember().getMemberId());
			ps.executeUpdate();
			
			ps.close();

			result = "Borrow complete.";
		}
		catch(SQLException e)
		{
			System.out.println("QUERY: " + ps.toString());
			System.out.println("Error: " + e);
			result = "Borrow failed.";
		}
		
		return result;
	}
	
	/**
	 * This method executes all the data managing that returning a book requires.
	 * 
	 * @param bookId
	 * The Id of the book to return.
	 * 
	 * @return A String message signifying success or failure.
	 */
	public String returnBook(int bookId)
	{
		String result = "";
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try
		{
			String q = "select borrowId, bookId, memberId from borrows where bookId = ? and ifnull(dateReturned, 0) = 0";
			ps = connect.prepareStatement(q);
			ps.setInt(1, bookId);
			rs = ps.executeQuery();
			rs.next();
			
			q = "update borrows set dateReturned = ? where borrowId = ?";
			ps = connect.prepareStatement(q);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			ps.setString(1, sdf.format(new java.util.Date()));
			ps.setInt(2, rs.getInt("borrowId"));
			ps.executeUpdate();
			
			q = "update books set available = true where bookId = ?";
			ps = connect.prepareStatement(q);
			ps.setInt(1, bookId);
			ps.executeUpdate();
			
			q = "update members set booksOut = booksOut - 1 where memberId = ?";
			ps = connect.prepareStatement(q);
			ps.setInt(1, rs.getInt("memberId"));
			ps.executeUpdate();
			
      ps.close();
      rs.close();

			result = "Return Succesful.";
		}
		catch(SQLException e)
		{
			System.out.println("QUERY: " + ps.toString());
			System.out.println("Error: " + e);
			result = "Return failed.";
		}
		
		return result;
	}
	
	/**
	 * Gets a list of Book Objects in the specified order.
	 * 
	 * @param order
	 * Sets the order: order = 1, Book Id; order = 2, Name; order = 3, Author; 
	 * if order equals anything else the order is by Available descending.
	 * 
	 * @return An ArrayList of Book Objects in an order specified by order. 
	 */
	public ArrayList<Book> listBooks(int order)
	{
		ArrayList<Book> books = new ArrayList<Book>();
		PreparedStatement ps = null;
		ResultSet rs = null; 
		
		try
		{
			String q = "select bookId, name, author, edition, available from books";
			
			if(order == 1)
				q = q + " order by bookId, available desc";
			else if(order == 2)
				q = q + " order by name, author, available desc";
			else if(order == 3)
				q = q + " order by author, name, available desc";
			else
				q = q + " order by available desc, bookId";
			ps = connect.prepareStatement(q);
			rs = ps.executeQuery();
			

			while(rs.next()){
				if(rs.getString("edition") == null)
					books.add(new Book(rs.getInt("bookId"), rs.getString("name"), rs.getString("author"), rs.getBoolean("available")));
				else
					books.add(new Book(rs.getInt("bookId"), rs.getString("name"), rs.getString("author"), rs.getString("edition"), rs.getBoolean("available")));
			}
		}
		catch(SQLException e)
		{
			System.out.println("QUERY: " + ps.toString());
			System.out.println("Error: " + e);
		}
		
		return books;
	}
	
	/**
	 * Gets a list of Member Objects in the specified order.
	 * 
	 * @param order
	 * Sets the order: if option = 0, Member Id; option = 1, Last Name; 
	 * option = 3, First Name; option = 4, Late Fees Due;  option = 5, Books Out;
	 * if option equals anything else the order is by MySQL default.
	 * 
	 * @return An ArrayList of Member Objects in an order specified by order. 
	 */
	public ArrayList<Member> listMembers(int order)
	{
		ArrayList<Member> members = new ArrayList<Member>();
		PreparedStatement ps = null;
		ResultSet rs = null; 
		
		try
		{
			String q = "select memberId, firstName, lastName, phone, resident, lateFeesDue, booksOut from members";
			
			if(order == 0)
				q = q + " order by memberId";
			else if(order == 1)
				q = q + " order by lastName, firstName, lateFeesDue, booksOut";
			else if(order == 2)
				q = q + " order by firstName, lastName, lateFeesDue, booksOut";
			else if(order == 4)
				q = q + " order by lateFeesDue, lastName,  firstName, booksOut";
			else if(order == 5)
				q = q + " order by booksOut, lateFeesDue, lastName,  firstName";
				
			ps = connect.prepareStatement(q);
			rs = ps.executeQuery();
			

			while(rs.next()){
					members.add(new Member(rs.getInt("memberId"), rs.getString("firstName"), rs.getString("lastName"),
							rs.getString("phone"), rs.getBoolean("resident"), rs.getDouble("lateFeesDue"), rs.getInt("booksOut")));
			}
		}
		catch(SQLException e)
		{
			System.out.println("QUERY: " + ps.toString());
			System.out.println("Error: " + e);
		}
		
		return members;
	}
	
	/**
	 * Gets a list of Borrow Objects in the specified order.
	 * 
	 * @param order
	 * Sets the order: if order = 1, Borrow Id; order = 2, Date Borrowed;
	 * order = 3, Member Id;  order = 4, Book Id;
	 * if order equals anything else the order is by MySQL default.
	 * 
	 * @return An ArrayList of Borrow Objects in an order specified by order. 
	 */
	public ArrayList<Borrow> listBorrows(int order)
	{
		ArrayList<Borrow> borrows = new ArrayList<Borrow>();
		PreparedStatement ps = null;
		ResultSet rs = null; 
		
		try
		{
			String q = "select b.borrowId, m.memberId, m.firstName, m.lastName, m.phone, " +
					"m.resident, m.lateFeesDue, m.booksOut, bk.bookId, bk.name, bk.author, " +
					"bk.edition, bk.available, b.dateBorrowed, b.dateReturned " +
					"from borrows b inner join members m on b.memberId = m.memberId " +
					"inner join books bk on b.bookId = bk.bookId";
			
			if(order == 1)
				q = q + " order by b.borrowId desc";
			else if(order == 2)
				q = q + " order by b.dateBorrowed";
			else if(order == 3)
				q = q + " order by m.memberId";
			else if(order == 4)
				q = q + " order by bk.bookId";
				
			ps = connect.prepareStatement(q);
			rs = ps.executeQuery();
			

			while(rs.next()){
				Member m = new Member(rs.getInt("m.memberId"), rs.getString("m.firstName"), rs.getString("m.lastName"),
						rs.getString("m.phone"), rs.getBoolean("m.resident"), rs.getDouble("m.lateFeesDue"), rs.getInt("m.booksOut"));
				
				Book bk = new Book(rs.getInt("bk.bookId"), rs.getString("bk.name"), rs.getString("bk.author"), rs.getBoolean("bk.available"));
				if(rs.getString("bk.edition") != null)
					bk.setEdition(rs.getString("bk.edition"));
				
				Borrow b = new Borrow(rs.getInt("b.borrowId"), m, bk, rs.getDate("b.dateBorrowed"));
				if(rs.getDate("b.dateReturned") != null)
					b.setDateReturned(rs.getDate("b.dateReturned"));
				
				borrows.add(b);
			}
		}
		catch(SQLException e)
		{
			System.out.println("QUERY: " + ps.toString());
			System.out.println("Error: " + e);
		}
		
		return borrows;
	}
	
	/**
	 * Updates database for when the specified member makes the specified payment.
	 * 
	 * @param memberId
	 * The id of the member making the payment
	 * 
	 * @param payment
	 * The amount to be paid
	 * 
	 * @return Returns the change, if any, due after the payment is made.
	 */
	public double makePayment(int memberId, double payment) {
		updateDatabase();
		PreparedStatement ps = null;
		ResultSet rs = null;
		double change = 0.00;
		double left = 0.00;
		
		try
		{
			String q = "select lateFeesDue from members where memberId = ?";	
			ps = connect.prepareStatement(q);
			ps.setDouble(1, memberId);
			rs = ps.executeQuery();
			rs.next();
			
			double fees = rs.getDouble("lateFeesDue");
			change = payment - fees;
			if(change <= 0.001)
			{
				left = change * -1;
				change = 0.00000;
			}
			else
			{
				left = 0.00000;
			}
			
			q = "update members set lateFeesDue = ? where memberId = ?";
			ps = connect.prepareStatement(q);
			ps.setDouble(1, left);
			ps.setDouble(2, memberId);
			ps.executeUpdate();
			ps.close();
			rs.close();
		}
		catch(SQLException e)
		{
			System.out.println("QUERY: " + ps.toString());
			System.out.println("Error: " + e);
		}
		
		return change;
	}

	
	
	/**
	 * Updates the database when called (i.e. updates the Late Fees Due). 
	 * Work needs to be done to make the update completely accurate. Method is untested.
	 * 
	 * @return A boolean signifying whether the database was successfully updated or not.
	 */
	public boolean updateDatabase()
	{
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try
		{
			String q = "select dateBorrowed, memberId from borrows where ifnull(dateReturned, 0) = 0";
			ps = connect.prepareStatement(q);
			rs = ps.executeQuery();
			
			while(rs.next())
			{
				int daysOut = (int) ((rs.getDate("dateBorrowed").getTime() - new java.util.Date().getTime()) 
	                 / (/*milliseconds in a day*/ 86400000));
			
				//needs work to be truly accurate
				if(daysOut > 14)
				{
					q = "update members set lateFeesDue = ? where memberId = ?";
					ps = connect.prepareStatement(q);
					ps.setDouble(1, (daysOut - 14) * .10);
					ps.setInt(2, rs.getInt("memberId"));
				}
			}
		}
		catch(SQLException e)
		{
			System.out.println("QUERY: " + ps.toString());
			System.out.println("Error: " + e);
			return false;
		}
		return true;
	}
}
