package TestCases;
import BusinessLogicLayer.*;
import static org.junit.Assert.*;
import java.io.IOException;

import org.junit.Test;

public class WhiteBoxTestCases {
	@Test
	public void testcase1() {// true case
		int counter ;
		File_Handling test=new File_Handling();
		counter= test.Return_Count_MatchingLine("Customer.txt", "Ace Excel");
		String accountno = test.Return_Balance("Customer.txt", counter);
		assertEquals("Ace Excel",accountno);
	}
	@Test
	public void testcase2() {// true case
		int counter ;
		File_Handling test=new File_Handling();
		counter= test.Return_Count_MatchingLine("Customer.txt", "Khuram Ali");
		String accountno = test.Return_Balance("Customer.txt", counter);
		assertNotEquals("Khuram Ali",accountno);
	}
	@Test
	public void testcase3() {// true case
		int counter ;
		File_Handling test=new File_Handling();
		counter= test.Return_Count_MatchingLine("Customer.txt", "Sahil Parkash");
		String accountno = test.Return_Balance("Customer.txt", counter);
		assertNotEquals("Sahil Parkash",accountno);
	}

	@Test
	public void testcase4() {// true case
		int counter ;
		File_Handling test=new File_Handling();
		counter= test.Return_Count_MatchingLine("Customer.txt", "Abid Hussain");
		String ph_no = test.Return_Balance("Phone_no.txt", counter);
		assertNotEquals("0231816598",ph_no);
	}
	@Test
	public void testcase5() {// true case
		int counter ;
		File_Handling test=new File_Handling();
		counter= test.Return_Count_MatchingLine("Customer.txt", "Khuram Ali");
		String ph_no = test.Return_Balance("Phone_no.txt", counter);
		assertNotEquals("278543213",ph_no);
	}
	@Test
	public void testcase6() {// true case
		int counter ;
		File_Handling test=new File_Handling();
		counter= test.Return_Count_MatchingLine("Customer.txt", "Ava Max");
		String ph_no = test.Return_Balance("Phone_no.txt", counter);
		assertEquals("02315896541",ph_no);
	}



	@Test
	public void testcase7() {// true case
		Energy_Tariffs obj=new Energy_Tariffs();
		String[] result1 = new String[2];
		result1=obj.GasUnitsCalulator("10091.5","10127.6");
		assertEquals("36.1",result1[0]);
	}

	@Test
	public void testcase9() throws IOException {// true case
		int counter ;
		Energy_Tariffs obj=new Energy_Tariffs();
		String[] result1 = new String[2];
		result1=obj.Calculate_Standing_Gas_Charges("36.1",obj.DateSyntx("2021-09-30"),obj.DateSyntx("2021-11-2"));
		System.out.println(result1[1]);
		assertEquals("8.21",result1[1]);
	}




}
