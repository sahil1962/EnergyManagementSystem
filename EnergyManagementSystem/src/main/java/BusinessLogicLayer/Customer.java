package BusinessLogicLayer;

import java.io.Serializable;

public class Customer implements Serializable{
	private String name;
	private String phone_no;
	private String currentaddress;
	private static final long serialVersionUID = 1L;

	//Default Constructor 
	public Customer() {
		name="";
		phone_no="";
		currentaddress="";
	}
	public Customer(String Name, String Number) {
		name = Name;
		phone_no = Number;
	}
	//Parameterized constructor
	public Customer(String name, String phone_no, String currentaddress) {
		this.name = name;
		this.phone_no = phone_no;
		this.currentaddress = currentaddress;
	}

	//Getter for name
	public String getName() {
		return name;
	}

	//Setter for Name
	public void setName(String name) {
		this.name = name;
	}

	//Getter for Phone number
	public String getPhone_no() {
		return phone_no;
	}

	//Setter for Phone number
	public void setPhone_no(String phone_no) {
		this.phone_no = phone_no;
	}

	//Getter for Current Address
	public String getCurrentaddress() {
		return currentaddress;
	}

	//Setter for Current Address
	public void setCurrentaddress(String currentaddress) {
		this.currentaddress = currentaddress;
	}
	//it is used for object serialisation, and returns String
	@Override
	public String toString() {
		String FinalString=getName()+" "+getPhone_no()+" "+getCurrentaddress();
		return FinalString;
	}

}