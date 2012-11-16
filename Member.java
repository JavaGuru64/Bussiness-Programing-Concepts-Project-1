/**
 * A class representing a library member.
 * 
 * @author Joshua
 * @version 1.0
 */
public class Member {
	private int memberId;
	private String firstName;
	private String lastName;
	private String phone;
	private boolean resident;
	// actually this could be more than just late fees
	private double lateFeesDue;
	private int booksOut;
	
	/**
	 * Member Constructor
	 * 
	 * @param memberId
	 * The Id of the member
	 * 
	 * @param firstName
	 * The member's first name
	 * @param lastName
	 * The member's last name
	 * @param phone
	 * the members phone number: preferred format = (111)-222-3333 
	 * 
	 * @param resident
	 * A boolean signifying whether the member is a resident of the area
	 * 
	 * @param lateFeesDue
	 * The fees the member currently owes
	 * 
	 * @param booksOut
	 * The books the member is currently borrowing
	 */
	public Member(int memberId, String firstName, String lastName, String phone, boolean resident, double lateFeesDue, int booksOut)
	{
		this.memberId = memberId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.phone = phone;
		this.resident = resident;
		this.lateFeesDue = lateFeesDue;
		this.booksOut = booksOut;
	}

	/**
	 * Gets the member id of the Member Object.
	 * 
	 * @return The member's Id
	 */
	public int getMemberId() {
		return memberId;
	}

	/**
	 * Sets the member id of the Member Object.
	 * 
	 * @param memberId
	 * The Id you wish to assign to this Member Object
	 */
	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}
	/**
	 * Gets the first name of the Member Object.
	 * 
	 * @return The member's first name
	 */
	public String getFirstName() {
		return firstName;
	}
	/**
	 * Sets the first name of the Member Object.
	 * 
	 * @param firstName
	 * The first name you wish to assign to this Member Object
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	/**
	 * Gets the last name of the Member Object.
	 * 
	 * @return The member's last name
	 */
	public String getLastName() {
		return lastName;
	}
	/**
	 * Sets the last name of the Member Object.
	 * 
	 * @param lastName
	 * The last name you wish to assign to this Member Object
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	/**
	 * Gets the phone number of the Member Object.
	 * 
	 * @return The member's phone number
	 */
	public String getPhone() {
		return phone;
	}
	/**
	 * Sets the phone number of the Member Object.
	 * 
	 * @param phone
	 * The phone number you wish to assign to this Member Object
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}
	/**
	 * Gets a boolean signifying whether the Member Object is a resident.
	 * 
	 * @return whether the member is a resident
	 */
	public boolean isResident()
	{
		return resident;
	}
	/**
	 * Sets a boolean signifying whether the Member Object is a resident.
	 * 
	 * @param resident
	 * The resident value you wish to assign to this Member Object
	 */
	public void setResident(boolean resident)
	{
		this.resident = resident;
	}
	/**
	 * Gets the late fees due for the Member Object.
	 * 
	 * @return The fees the member owes
	 */
	public double getLateFeesDue() {
		return lateFeesDue;
	}
	/**
	 * Sets the late fees due for the Member Object.
	 * 
	 * @param lateFeesDue
	 * The fees owed you wish to assign to this Member Object
	 */
	public void setLateFeesDue(double lateFeesDue) {
		this.lateFeesDue = lateFeesDue;
	}
	/**
	 * Get the number of books out for the Member Object.
	 * 
	 * @return The number of books a member has out
	 */
	public int getBooksOut() {
		return booksOut;
	}
	/**
	 * Set the number of books out for the Member Object.
	 * 
	 * @param booksOut
	 * The number of books out you wish to assign to this Member Object
	 */
	public void setBooksOut(int booksOut) {
		this.booksOut = booksOut;
	}
	/**
	 * A generic toString() method
	 */
	public String toString() {
		return "" + memberId + " " + firstName + " " + lastName + " " + phone + " " + lateFeesDue + " " + booksOut;
	}
}
