import java.util.Date;

/**
 * A class representing a single borrow from a library
 * 
 * @author Joshua
 * @version 1.0 
 */
public class Borrow 
{
	private int borrowId;
	private Member member;
	private Book book;
	private Date dateBorrowed;
	private Date dateReturned;
	
	/**
	 * Borrow Constructor
	 * 
	 * @param borrowId
	 * The Borrow Object's Id
	 * @param member
	 * The Member Object Related to this Borrow Object
	 * @param book
	 * The Book Object Related to this Borrow Object
	 */
	public Borrow(int borrowId, Member member, Book book)
	{
		this.borrowId = borrowId;
		this.member = member;
		this.book = book;
		dateBorrowed = new Date();
	}
	
	/**
	 * Borrow Constructor
	 * 
	 * @param borrowId
	 * The Borrow Object's Id
	 * @param member
	 * The Member Object Related to this Borrow Object
	 * @param book
	 * The Book Object Related to this Borrow Object
	 * @param dateBorrowed
	 * The Date this Borrow Object was created.
	 */
	public Borrow(int borrowId, Member member, Book book, Date dateBorrowed)
	{
		this.borrowId = borrowId;
		this.member = member;
		this.book = book;
		this.dateBorrowed = dateBorrowed;
	}
	
	/**
	 * Borrow Constructor
	 * 
	 * @param borrowId
	 * The Borrow Object's Id
	 * @param member
	 * The Member Object Related to this Borrow Object
	 * @param book
	 * The Book Object Related to this Borrow Object
	 * @param dateBorrowed
	 * The Date this Borrow Object was created
	 * @param dateReturned
	 * The Date this Borrow Object was closed
	 */
	public Borrow(int borrowId, Member member, Book book, Date dateBorrowed, Date dateReturned)
	{
		this.borrowId = borrowId;
		this.member = member;
		this.book = book;
		this.dateBorrowed = dateBorrowed;
		this.dateReturned = dateReturned;
	}


	/**
	 * Gets the borrow id of the Borrow Object.
	 * 
	 * @return The Borrow Object's Id
	 */
	public int getBorrowId() {
		return borrowId;
	}

	/**
	 * Sets the borrow id of the Borrow Object.
	 * 
	 * @param borrowId
	 * The id you wish to assign to this Borrow Object 
	 */
	public void setBorrowId(int borrowId) {
		this.borrowId = borrowId;
	}

	/**
	 * Gets the Member Object related to this Borrow Object.
	 * 
	 * @return member
	 */
	public Member getMember() {
		return member;
	}

	/**
	 * Sets the Member Object related to this Borrow Object.
	 * 
	 * @param member
	 * The Member Object you wish to assign to this Borrow Object 
	 */
	public void setMember(Member member) {
		this.member = member;
	}

	/**
	 * Gets the Book Object related to this Borrow Object.
	 * 
	 * @return book
	 */
	public Book getBook() {
		return book;
	}

	/**
	 * Sets the Book Object related to this Borrow Object.
	 * 
	 * @param book
	 * The Book Object you wish to assign to this Borrow Object 
	 */
	public void setBook(Book book) {
		this.book = book;
	}

	/**
	 * Gets the date representing the date a book was borrowed in this Borrow Object.
	 * 
	 * @return The Date Borrowed
	 */
	public Date getDateBorrowed() {
		return dateBorrowed;
	}

	/**
	 * Sets the date representing the date a book was borrowed in this Borrow Object.
	 * 
	 * @param dateBorrowed
	 * The Date Borrowed you wish to assign to this Borrow Object 
	 */
	public void setDateBorrowed(Date dateBorrowed) {
		this.dateBorrowed = dateBorrowed;
	}

	/**
	 * Gets the date representing the date a book was returned in this Borrow Object.
	 * 
	 * @return The Date Returned
	 * 
	 */
	public Date getDateReturned() {
		return dateReturned;
	}

	/**
	 * Sets the date representing the date a book was Returned in this Borrow Object.
	 * 
	 * @param dateReturned
	 * The Date Returned you wish to assign to this Borrow Object 
	 */
	public void setDateReturned(Date dateReturned) {
		this.dateReturned = dateReturned;
	}

	/**
	 * A generic toString() method
	 */
	public String toString() {
		return "" + borrowId + " " + member.getMemberId() + " " + book.getBookId() + " " + dateBorrowed + " " + dateReturned;
	}
}
