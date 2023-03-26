package BusinessLogicLayer;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Energy_Management_System implements Serializable{

	
	
	
	private List<Customer> customer;
	private List<Energy_Tariffs> energy_Tariff;
	private List<String> filestore=new ArrayList<String>();
	String Sentence="";
	private static final long serialVersionUID = 1L;
	
	//List of all customers detail
	public List<Customer> getCustomerDetails() {
		return customer;
	}
	
	//Setter for customer Details
	public void setCustomerDetails(List<Customer> customer) {
		this.customer = customer;
	}
	
	//List for Energy Tariff details
	public List<Energy_Tariffs> getEnergy_TariffsDetails() {
		return energy_Tariff;
	}
	
	//setter for Tariff Details
	public void setEnergy_TariffsDetails(List<Energy_Tariffs> energy_Tariff) {
		this.energy_Tariff = energy_Tariff;
	}

	//Stores data through object serialisation
	public void customer_info(Customer obj,Energy_Tariffs obj1) {    
		File_Handling obj3=new File_Handling();
		String copySentence = "";
		if (obj3.Return_boolean_MatchingInFile("Customer.txt",obj.getName()) == false) {
			copySentence = obj3.generating_files(obj,obj1 );
			System.out.println("Customer not exsist ");
			Sentence=obj.getName()+"-"+obj.getPhone_no()+"-"+obj.getCurrentaddress()+"-"+obj1.getEnergytariff()+"-"+obj1.getMetertype();
			generating_files();
			if (generating_files() == 1) {
				data_write();
			}
		}
		else
		{
			System.out.println("Customer exsist already");
		}
	}
	
	// This Function is called when we have to generate the files by calling file-handling class.
	public int generating_files() {  
		File_Handling obj=new File_Handling();
		int check;
		check = obj.CreateFile("Customer.txt");
		obj.CreateFile("Phone_no.txt");
		obj.CreateFile("Current_Address.txt");
		obj.CreateFile("Energy_Tariff.txt");
		obj.CreateFile("Gas_Meter_Type.txt");
		obj.CreateFile("Electricity_Meter_Type.txt");
		obj.CreateFile("PaymentStatus.txt");
		obj.CreateFile("Energy_Meter_Reading.txt");
		obj.CreateFile("Tariff.txt");
		obj.CreateFile("MeterType.txt");
		obj.CreateFile("OpeningDate.txt");
		obj.CreateFile("ElectricityOpeningDate.txt");
		return check;

	}
	
	//It tokinize the sentence, written by the object serialization, and send them to different files.
	public void data_write() {   // This function is called when we have to input the Data of separate person
		File_Handling obj=new File_Handling();
		System.out.println(Sentence);
		String[] arrSplit_3 = Sentence.split("-");
		String s="";

		for (int i=0; i < arrSplit_3.length; i++){
			if (i==0) {
				s=arrSplit_3[i].toString();
				obj.WriteToFile("Customer.txt",s+"\n");
			}
			if (i==1) {
				s=arrSplit_3[i].toString();
				obj.WriteToFile("Phone_no.txt",s+"\n");
			}
			if (i==2) {
				s=arrSplit_3[i].toString();
				obj.WriteToFile("Current_Address.txt",s+"\n");
			}
			if (i==3) {
				s=arrSplit_3[i].toString();
				obj.WriteToFile("Energy_Tariff.txt",s+"\n");
			}
			
			if (i==4) {
				s=arrSplit_3[i].toString();
				obj.WriteToFile("MeterType.txt",s+"\n");
				obj.WriteToFile("Gas_Meter_Type.txt","gas"+"\n");
				obj.WriteToFile("Electricity_Meter_Type.txt","electricity"+"\n");
			}
		}

	}
	
	//Get data of all customers and returns a string
	public String [] showallcustomerdata() {
		String name;
		String Phone_no;
		String Current_Address;
		String Energy_Tariff;
		String Gas_Meter_Type;
		String Electricity_Meter_Type ,MeterType;
		File_Handling obj3=new File_Handling();
		int all_lines=obj3.retrunallLines();
		System.out.println(all_lines);
		String []array=new String[200];
		for(int i=0;i<=all_lines;i++)
		{
			name= obj3.Return_Balance("Customer.txt", i);
			Phone_no= obj3.Return_Balance("Phone_no.txt", i);
			Current_Address= obj3.Return_Balance("Current_Address.txt", i);
			Energy_Tariff= obj3.Return_Balance("Energy_Tariff.txt", i);
			Gas_Meter_Type= obj3.Return_Balance("Gas_Meter_Type.txt", i);
			Electricity_Meter_Type= obj3.Return_Balance("Electricity_Meter_Type.txt", i);
			MeterType= obj3.Return_Balance("MeterType.txt", i);
			array[i]=name+" - "+Phone_no+" - "+Current_Address+" - "+MeterType+" - "+Energy_Tariff+" - "+Gas_Meter_Type+" - "+Electricity_Meter_Type + "\n";
		}
//		for(int i=0;i<=all_lines;i++) {
//
//			System.out.println(array[i]);
//		}
		return array;
	}

	// Returns a profile string of a specific customer
	public  String ReturnProfileInfo(String username) {
		File_Handling obj3=new File_Handling();
		String Phone_no;
		String Current_Address;
		String Energy_Tariff;
		String Meter_Type;
		int counter=0;
		if (obj3.Return_boolean_MatchingInFile("Customer.txt", username) == true) {
			System.out.println("Customer  Exist");
			counter =obj3.Return_Count_MatchingLine("Customer.txt", username);

			Phone_no = obj3.Return_Balance("Phone_no.txt", counter);
			Current_Address = obj3.Return_Balance("Current_Address.txt", counter);
			Energy_Tariff =obj3.Return_Balance("Energy_Tariff.txt", counter);
			Meter_Type =obj3.Return_Balance("MeterType.txt", counter);
			String Sentence=  username+"-"+Phone_no+"-"+Current_Address+"-"+Energy_Tariff+"-"+Meter_Type;
			return Sentence;

		}
		else {
			System.out.println("Customer doesnot Exist");
		}

		return Sentence;
	}
}

