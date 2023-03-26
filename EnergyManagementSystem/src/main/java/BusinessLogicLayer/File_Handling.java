package BusinessLogicLayer;

import java.io.*;
import java.util.StringTokenizer;


public class File_Handling implements Serializable{

	private int check = 0;

	// This Function is called when we have to generate the files.
	public String generating_files(Customer obj,Energy_Tariffs obj1 ) {  
		FileOutputStream f;
		try {
			int check = 0;
			f = new FileOutputStream(new File("Customer_Info.txt"));
			ObjectOutputStream o = new ObjectOutputStream(f);

			o.writeObject(obj);
			o.writeObject(obj1);

			FileInputStream fi = new FileInputStream(new File("Customer_Info.txt"));
			ObjectInputStream oi = new ObjectInputStream(fi);

			// Read objects
			Customer pr1 = (Customer) oi.readObject();
			Energy_Tariffs pr2 = (Energy_Tariffs) oi.readObject();

			System.out.println(pr1.toString());
			System.out.println(pr2.toString());

			String s=pr1.toString()+" "+pr2.toString();

			fi.close();
			oi.close();
			o.close();
			f.close();

			return s;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	// This Function is used to create the File
	//It takes the File name as Arguments.
	public int CreateFile(String Filename) {  
		try {
			File myObj = new File(Filename);
			if (myObj.createNewFile()) {
				System.out.println("File is Created " + myObj.getName());
			} else {
				System.out.println("File Already Existed");
				check = 1;
			}
		} catch (IOException e) {
			System.out.println("Error");
			e.printStackTrace();
		}
		return check;
	}
	// This Function is used to write in the File
	// It takes Filename and Data as Arguments
	public void WriteToFile(String Filename, String Data) {
		try {
			FileWriter myWriter = new FileWriter(Filename, true);
			myWriter.write(Data);
			myWriter.close();
			System.out.println("Written in file");
		} catch (IOException e) {
			System.out.println("Error");
			e.printStackTrace();
		}
	}
	// This Function is used to modify the Data in the File
	// It take the line number, File path , previous data and data modified as Arguments
	public void modifyFile(String filePath, int Counter, String oldString, String newString) {
		File fileToBeModified = new File(filePath);
		String oldContent = "";
		int index = 1;
		BufferedReader reader = null;
		FileWriter writer = null;
		try {
			reader = new BufferedReader(new FileReader(fileToBeModified));
			String line = reader.readLine();
			while (line != null) {
				if ((Counter == index) && (oldString.equals(line))) {
					System.out.print("True");
					oldContent = oldContent + newString + System.lineSeparator();
					line = reader.readLine();
				} else if (Counter != index) {
					System.out.print("False ");
					oldContent = oldContent + line + System.lineSeparator();
					line = reader.readLine();
				}
				index++;
			}
			String newContent = oldContent.replaceAll(oldString, newString);
			writer = new FileWriter(fileToBeModified);
			writer.write(newContent);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				reader.close();
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	// This Function  check whether data is present in file or not.
	// returns True and False
	public boolean Return_boolean_MatchingInFile(String Filename, String value) {
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(Filename));
			String line = reader.readLine();
			while (line != null) {
				if (value.equals(line)) {
					return true;
				}
				line = reader.readLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}
	// This Function  check whether data is present in file or not.
	// returns Line Number.
	public int Return_Count_MatchingLine(String Filename, String value) {
		int counter = 1;
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(Filename));
			String line = reader.readLine();
			while (line != null) {
				if (value.equals(line)) {
					return counter;
				}
				line = reader.readLine();
				counter++;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return 0;
	}
	// This Function  check whether data is present in file or not.
	// returns that matched Data.
	public String Return_Balance(String Filename, int counter) {
		int index = 1;
		String oldContent = "";
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(Filename));
			String line = reader.readLine();
			while (line != null) {
				if (counter == index) {
					oldContent = line;
				}
				line = reader.readLine();
				index++;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return oldContent;
	}
	//This Function returns the lines of all file
	public int retrunallLines(){
		int counter = 0;
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader("Customer.txt"));
			String line = reader.readLine();
			while (line != null) {

				line = reader.readLine();
				counter++;
			}
		} catch (IOException e) {
			e.printStackTrace();

		}
		return counter;
	}

	//	Overwriting an existing file 
	public void OverWriteFile(String source){
		try {
			FileWriter f2 = new FileWriter("Tariff.txt", false);
			f2.write(source);
			f2.close();
		} catch (IOException e) {
			e.printStackTrace();
		}  	
	}
	
	//Writing the meter reading file by object serialization
	public String generating_files1(Energy_Tariffs obj,String name ) {  
		FileOutputStream f;
		try {
			int check = 0;
			f = new FileOutputStream(new File("ElectricMeterReading.txt"));
			ObjectOutputStream o = new ObjectOutputStream(f);
			o.writeObject(obj);
			FileInputStream fi = new FileInputStream(new File("ElectricMeterReading.txt"));
			ObjectInputStream oi = new ObjectInputStream(fi);

			// Read objects
			Energy_Tariffs pr1 = (Energy_Tariffs) oi.readObject();
			System.out.println(pr1.toString());
			String s=name+" "+pr1.toString();

			fi.close();
			oi.close();
			o.close();
			f.close();
			return s;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}