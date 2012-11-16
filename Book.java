/**
 * A class representing a library book.
 * 
 * @author Joshua
 * @version 1.0
 */
public class Book {
	private int bookId;
	private String name;
	private String author;
	private String edition;
	private boolean available;
	
	/**
	 * Book Constructor
	 * 
	 * @param bookId
	 * The book's Id
	 * @param name
	 * The book's title
	 * @param author
	 * The book's author
	 * @param edition
	 * The book's edition
	 * @param available
	 * The book's availability: true = available, false = non-available
	 */
	public Book(int bookId, String name, String author, String edition, boolean available)
	{
		this.bookId = bookId;
		this.name = name;
		this.author = author;
		this.edition = edition;
		this.available = available;
	}
	
	/**
	 * Book Constructor
	 * 
	 * @param bookId
	 * The book's Id
	 * @param name
	 * The book's title
	 * @param author
	 * The book's author
	 * @param available
	 * The book's availability: true = available, false = non-available
	 */
	public Book(int bookId, String name, String author, boolean available)
	{
		this.bookId = bookId;
		this.name = name;
		this.author = author;
		this.available = available;
	}

	/**
	 * Gets the bookId of the Book Object.
	 * 
	 * @return The book's id
	 */
	public int getBookId() {
		return bookId;
	}
	/**
	 * Sets the book id of the Book Object.
	 * 
	 * @param bookId
	 * The id you wish to assign to the book.
	 */
	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	/**
	 * Gets the name (i.e. title) of the Book Object.
	 * 
	 * @return The book's Name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Sets the name (i.e. title) of the Book Object.
	 * 
	 * @param name
	 * The name you wish to assign to the book
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Gets the author of the Book Object.
	 * 
	 * @return The book's author.
	 */
	public String getAuthor() {
		return author;
	}
	
	/**
	 * Sets the author of the Book Object.
	 * 
	 * @param author
	 * The author you wish to assign to the book
	 */
	public void setAuthor(String author) {
		this.author = author;
	}

	/**
	 * Gets the edition of the Book Object.
	 * 
	 * @return The book's edition 
	 */
	public String getEdition() {
		return edition;
	}

	/**
	 * Sets the edition of the Book Object.
	 * 
	 * @param edition
	 * The edition you wish to assign to the book
	 */
	public void setEdition(String edition) {
		this.edition = edition;
	}

	/**
	 * Gets a boolean representing the book objects availability
	 * 
	 * @return The book's availability: true = available, false = non-available
	 */
	public boolean isAvailable() {
		return available;
	}

	/**
	 * Sets a boolean representing the book objects availability
	 * 
	 * @param available
	 * Set the book's availability: true = available, false = non-available
	 */
	public void setAvailable(boolean available) {
		this.available = available;
	}
	
	/**
	 * A generic toString() method
	 */
	public String toString() {
		return "" + bookId + " " + name + " " + author + " " + edition + " " + available;
	}
}
