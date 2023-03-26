package BusinessLogicLayer;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Energy_Tariffs implements Serializable{

	private String energytariff;
	private String metertype;
	private String meterReading;
	private static final long serialVersionUID = 1L;
	
	//Default Constructor 
	public Energy_Tariffs() {
		energytariff="";
		metertype="";
		meterReading="";
	}
	
	//Parameterized constructor
	public Energy_Tariffs(String energytariff, String metertype) {
		this.energytariff = energytariff;
		this.metertype = metertype;
	}
	
	//Parameterized constructor
	public Energy_Tariffs(String meterReading) {
		this.meterReading = meterReading;
	}

	//Getter for energy tariff
	public String getEnergytariff() {
		return energytariff;
	}
	
	//Getter for energy tariff
	public void setEnergytariff(String energytariff) {
		this.energytariff = energytariff;
	}
	
	//Getter for meter type
	public String getMetertype() {
		return metertype;
	}
	
	//Setter for meter-type
	public void setMetertype(String metertype) {
		this.metertype = metertype;
	}
	
	//Getter for Meter Reading
	public String getMeterReading() {
		return meterReading;
	}
	
	//Setter for Meter Reading
	public void setMeterReading(String meterReading) {
		this.meterReading = meterReading;
	}

	//this will returns all meter-type of a customer
	public String returnmetertype(String username)
	{
		File_Handling obj3=new File_Handling();
		int counter=0;
		String Meter_Type="";
		if (obj3.Return_boolean_MatchingInFile("Customer.txt", username) == true) {
			counter =obj3.Return_Count_MatchingLine("Customer.txt", username);
			Meter_Type = obj3.Return_Balance("Meter_Type.txt", counter);
			return Meter_Type;
		}
		else {
			System.out.println("Username doesnot exist");
		}
		return "User Doesn't exists";
	}

	//returns the energy tariff of a customer
	public String returnenergytariff(String username)
	{
		File_Handling obj3=new File_Handling();
		int counter=0;
		String Energy_Tariff="";
		if (obj3.Return_boolean_MatchingInFile("Customer.txt", username) == true) {
			counter =obj3.Return_Count_MatchingLine("Customer.txt", username);
			Energy_Tariff = obj3.Return_Balance("Energy_Tariff.txt", counter);
			return Energy_Tariff;
		}
		else {
			System.out.println("Username doesnot exist");
		}
		return "User Doesn't exists";
	}
	
//	returns the Gas units consumed by a customer
	public String[] GasUnitsCalulator(String Opening_Read,String Closing_Read) {

		double value1 = Double.parseDouble(Opening_Read);
		double value2 = Double.parseDouble(Closing_Read);
		double result= value2-value1;
		double mcube=2.83*result;
		String[] result1 = new String[2];
		result1[0]=""+new DecimalFormat("#.0#").format(result);
		result1[1]=""+new DecimalFormat("#.#").format(mcube);
		return result1;
	}

	//Calculate the gas charge bill
	public String[] Calculate_Gas_Charges(String mcube) {
		double correctionfactor=1.02264;
		double Calorific_value=39.4;
		double rate=0.03797;
		double result;
		String[] result1 = new String[2];
		double value1 = Double.parseDouble(mcube);
		result=value1*correctionfactor*Calorific_value;
		result=result/3.6;
		result1[0]=""+new DecimalFormat("#.00#").format(result);
		result=result*0.03797;
		result1[1]=""+new DecimalFormat("#.0#").format(result);
		return result1;
	}

	//correct the date syntax, which is coming from GUI
	public String DateSyntx(String dateBeforeString)
	{

		String[] arrSplit_3 = dateBeforeString.split("-");
		String day = "",month = "",year = "";
		for (int i=0; i < arrSplit_3.length; i++){
			if (i==0)
			{
				year=arrSplit_3[i].toString();
			}
			if (i==1)
			{
				month=arrSplit_3[i].toString();
			}
			if (i==2)
			{
				day=arrSplit_3[i].toString()+" "+month+" "+year;
			}
		}
		System.out.println(day);
		return day;
	}
	
	//Calculate the standing gas charges and returns that.
	public String[] Calculate_Standing_Gas_Charges(String Price,String dateBeforeString,String dateAfterString){

		SimpleDateFormat myFormat = new SimpleDateFormat("dd MM yyyy");
		double rate=24.87;
		double balance;
		String[] result1 = new String[5];
		try {
			Date dateBefore = myFormat.parse(dateBeforeString);
			Date dateAfter = myFormat.parse(dateAfterString);
			long difference = dateAfter.getTime() - dateBefore.getTime();
			float daysBetween = (difference / (1000*60*60*24));
			System.out.println("Number of Days between dates: "+daysBetween);
			result1[0]=""+daysBetween;
			rate=rate*daysBetween;
			rate=rate/100;
			result1[1]=""+new DecimalFormat("#.0#").format(rate);

			balance=rate+Double.parseDouble(Price);
			result1[2]=""+new DecimalFormat("#.0#").format(balance);
			rate=balance*0.05;
			result1[3]=""+new DecimalFormat("#.0#").format(rate);
			balance=balance+rate;
			result1[4]=""+new DecimalFormat("#.0#").format(balance);
			return result1;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result1;
	} 

	//Calculate the total gas bill charges and returns that.	
	public String[] Calculate_TotalBill_Gas_Charges_C(String Price){
		double rate=0;
		double balance;
		String[] result1 = new String[3];
		balance=Double.parseDouble(Price);
		result1[0]=""+new DecimalFormat("#.0#").format(balance);
		rate=balance*0.05;
		result1[1]=""+new DecimalFormat("#.0#").format(rate);
		balance=balance+rate;
		result1[2]=""+new DecimalFormat("#.0#").format(balance);

		return result1;
	} 


	//Calculate the gas units and returns that.
	public String Electricity_UnitsCalulator(String Opening_Read,String Closing_Read) {

		double value1 = Double.parseDouble(Opening_Read);
		double value2 = Double.parseDouble(Closing_Read);
		double result= value2-value1;
		String result1;
		result1=""+new DecimalFormat("#.0#").format(result);
		return result1;
	}

	//Calculate the Electricity units and returns that.
	public String Electricity_ChargesCalulator(String KWH) {

		double value1 = Double.parseDouble(KWH);
		double rate=0.19349;
		value1=value1*rate;
		String result1;
		result1=""+new DecimalFormat("#.0#").format(value1);
		return result1;
	}

	//Calculate the Electricity standing charges and returns that.
	public String[] Calculate_Standing_Electricity_Charges(String Price,String dateBeforeString,String dateAfterString){

		SimpleDateFormat myFormat = new SimpleDateFormat("dd MM yyyy");
		double rate=22.63;
		double balance;
		String[] result1 = new String[5];
		try {
			Date dateBefore = myFormat.parse(dateBeforeString);
			Date dateAfter = myFormat.parse(dateAfterString);
			long difference = dateAfter.getTime() - dateBefore.getTime();
			float daysBetween = (difference / (1000*60*60*24));
			System.out.println("Number of Days between dates: "+daysBetween);
			result1[0]=""+daysBetween;
			rate=rate*daysBetween;
			rate=rate/100;
			result1[1]=""+new DecimalFormat("#.0#").format(rate);

			balance=rate+Double.parseDouble(Price);
			result1[2]=""+new DecimalFormat("#.0#").format(balance);
			rate=balance*0.05;
			result1[3]=""+new DecimalFormat("#.0#").format(rate);
			balance=balance+rate;
			result1[4]=""+new DecimalFormat("#.0#").format(balance);

			return result1;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result1;
	} 
	
	//Calculate the Electricity units and returns that.
	public void data_write(String Value) {
		File_Handling obj=new File_Handling();
		obj.WriteToFile("Tariff.txt",Value+"\n");
	}

	//Meter reading.
	public void meterreading(Energy_Tariffs obj){    
		File_Handling obj3=new File_Handling();
		String copySentence =obj3.generating_files1(obj, energytariff);
	}

	//update tariff detail update
	public String TraiffDetailUpdate() {
		File_Handling obj3=new File_Handling();
		String array;
		array = obj3.Return_Balance("Tariff.txt", 1);
		System.out.println("Array : " + array);
		return array;
	}

	//getting meter reading
	@Override
	public String toString() {
		String FinalString=getMeterReading();
		return FinalString;
	}

}