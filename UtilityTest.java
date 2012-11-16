

import static org.junit.Assert.*;

import org.junit.Test;

public class UtilityTest {

	@SuppressWarnings("deprecation")
	
	@Test
	public void testAddMargins()
	{
		// Alas this is but a token test because this method is deprecated.
		String st = "Good\nMorning", sr = "  Good  \n  Morning  \n";
		assertEquals("Result 1",sr, Utility.addMargins(2, st));
		
		System.out.println("@Test - testAddMargins() - deprecated");
	}
	
	@Test
	public void testValidateString() {
		assertEquals("Result 2", false, Utility.validateString(""));
		assertEquals("Result 3", false, Utility.validateString("      "));
		assertEquals("Result 4", false, Utility.validateString(null));
		assertEquals("Result 5", true, Utility.validateString("Over 9,000!!!!!!!"));
		
		System.out.println("@Test - testValidateString()");
	}
	
	@Test
	public void testValidateBookDelete() {
		assertEquals("Result 6", false, Utility.validateBookDelete(211));
		assertEquals("Result 7", false, Utility.validateBookDelete(13));
		assertEquals("Result 8", true, Utility.validateBookDelete(1));
		
		System.out.println("@Test - testValidateBookDelete()");
	}
	
	@Test
	public void testValidateInt() {
		assertEquals("Result 9", false, Utility.validateInt(-50));
		assertEquals("Result 10", true, Utility.validateInt(1000000));
		
		System.out.println("@Test - testValidateInt()");
	}
	
	@Test
	public void testValidateMemberDelete() {
		assertEquals("Result 11", false, Utility.validateMemberDelete(0));
		assertEquals("Result 12", false, Utility.validateMemberDelete(-20));
		assertEquals("Result 13", false, Utility.validateMemberDelete(1000000));
		assertEquals("Result 14", false, Utility.validateMemberDelete(4));
		assertEquals("Result 15", false, Utility.validateMemberDelete(1));
		assertEquals("Result 16", true, Utility.validateMemberDelete(3));
		
		System.out.println("@Test - testValidateMemberDelete()");
	}
	
	@Test
	public void testValidateMemberId() {
		assertEquals("Result 17", false, Utility.validateMemberId(0));
		assertEquals("Result 18", false, Utility.validateMemberId(-21));
		assertEquals("Result 19", false, Utility.validateMemberId(5000));
		assertEquals("Result 20", true, Utility.validateMemberId(15));
		
		System.out.println("@Test - testValidateMemberID()");
	}
	
	@Test
	public void testBorrowAllowed() {
		assertEquals("Result 21", false, Utility.borrowAllowed(500, 23));
		assertEquals("Result 22", false, Utility.borrowAllowed(4, 6));
		assertEquals("Result 23", false, Utility.borrowAllowed(3, 2));
		assertEquals("Result 24", false, Utility.borrowAllowed(11, 200));
		assertEquals("Result 25", true, Utility.borrowAllowed(11, 1));
		
		System.out.println("@Test - testBorrowAllowed()");
	}
	
	@Test
	public void testValidateBookId() {
		assertEquals("Result 26", false, Utility.validateBookId(0));
		assertEquals("Result 27", false, Utility.validateBookId(-3));
		assertEquals("Result 28", false, Utility.validateBookId(42));
		assertEquals("Result 29", true, Utility.validateBookId(39));
		
		System.out.println("@Test - testValidateBookId()");
	}
	
	@Test
	public void testValidateBookReturn() {
		assertEquals("Result 30", false, Utility.validateBookReturn(0));
		assertEquals("Result 31", false, Utility.validateBookReturn(-3));
		assertEquals("Result 32", false, Utility.validateBookReturn(1000));
		assertEquals("Result 33", false, Utility.validateBookReturn(1));
		assertEquals("Result 34", true, Utility.validateBookReturn(39));

		System.out.println("@Test - testValidateBookReturn()");
	}
}
