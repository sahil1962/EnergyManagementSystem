package BusinessLogicLayer;
import java.awt.Color;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.security.cert.CRL;
import java.text.DecimalFormat;
import java.util.List;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfDocument;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class Payment implements Serializable{

	private double payments;
	private String bill;
	private String tariffdetails;  
	private static final long serialVersionUID = 1L;
	
	//Default Constructor 
	public Payment() {
		this.payments=0.0;
		this.bill ="";
		this.tariffdetails ="";
	}
	
	//Parameterized constructor
	public Payment(double payments, String bill, String tariffdetails) {
		this.payments = payments;
		this.bill = bill;
		this.tariffdetails = tariffdetails;
	}

	//Getter for payment
	public double getPayments() {
		return payments;
	}
	
	//setter for payment
	public void setPayments(double payments) {
		this.payments = payments;
	}
	
	//getter for bill
	public String getBill() {
		return bill;
	}
	
	//setter for bill
	public void setBill(String bill) {
		this.bill = bill;
	}
	
	//getter for tariff detail 
	public String getTariffdetails() {
		return tariffdetails;
	}
	
	//setter for tariff detail
	public void setTariffdetails(String tariffdetails) {
		this.tariffdetails = tariffdetails;
	}
	
	//creating the invoices, pdf for paid customers.
	public void generate_invoices(String CName, String ElectricityBill, String GasBill, String TBill) throws DocumentException, FileNotFoundException {
		double max=500,min=1;
		Rectangle pageSize = new Rectangle(500, 720);
		pageSize.setBackgroundColor(BaseColor.WHITE);

		Document document = new Document(pageSize);
		PdfWriter.getInstance(document, new FileOutputStream(new File(CName+"_Statement.pdf")));
		document.open();
		Rectangle rect= new Rectangle(480,700,18,15); // you can resize rectangle
		rect.enableBorderSide(1);
		rect.enableBorderSide(2);
		rect.enableBorderSide(4);
		rect.enableBorderSide(8);
		rect.setBorderColor(BaseColor.BLACK);
		rect.setBorderWidth(1);
		document.add(rect);
		double b = (int)(Math.random()*(max-min+1)+min);
		b = 91.22;
		double result = Double.parseDouble(TBill);
		double TOLTALBILL = result - b;
		String TBill2=""+new DecimalFormat("#.00#").format(TOLTALBILL);
		Font f=new Font(FontFamily.TIMES_ROMAN,50.0f,Font.UNDERLINE,BaseColor.RED);    
		Paragraph p = new Paragraph();
		p.setFont(f);
		p.add("\n\nINVOICE");
		p.setAlignment(Element.ALIGN_CENTER);
		document.add(p);
		Paragraph p2 = new Paragraph();
		Font f2 = new Font();
		f2.setStyle(Font.BOLD);
		f2.setColor(BaseColor.BLUE);
		f2.setSize(14);
		p2.setFont(f2);  
		p2.add("\n\n\nYour Electricity and Gas Statement\n\n"); //no alignment          
		Font f3 = new Font();
		f3.setStyle(Font.BOLD);
		f3.setSize(12);
		f3.setColor(BaseColor.GRAY);
		f3.setColor(0, 1, 0);
		p2.setFont(f3);
		p2.add("_____________________________________________________________");
		p2.add("Previous Statement                                       £"+b+"CR");
		p2.add("_____________________________________________________________");
		p2.add("Payment Recieved                                         £0.00");
		p2.add("_____________________________________________________________");
		p2.add("Balance                                                           £"+b+"CR");
		p2.add("_____________________________________________________________");
		p2.setAlignment(Element.ALIGN_MIDDLE);
		document.add(p2);            
		Paragraph p3 = new Paragraph();
		Font f4 = new Font();
		f4.setStyle(Font.BOLD);
		f4.setSize(12);
		f4.setColor(BaseColor.GRAY);
		p3.setFont(f3);
		p3.add("\n_____________________________________________________________");
		p3.add("Total Charges this Bill                                 £" + TBill);
		p3.add("_____________________________________________________________");
		p3.add("Electricity                                                        £ " + ElectricityBill);
		p3.add("_____________________________________________________________");
		p3.add("Gas                                                                  £ "+ GasBill);
		p3.add("_____________________________________________________________");
		p3.add("Account Balance                                            £" + TBill2);
		p3.add("_____________________________________________________________");
		p3.setAlignment(Element.ALIGN_MIDDLE);      
		document.add(p3);



		document.close();

		System.out.println("Done");

	}
	
	// update the paid status in file system.
	public void data_write(String name) {   // This function is called when we have to input the Data of separate person
		File_Handling obj=new File_Handling();
		String s= name + "-paid";
		obj.WriteToFile("PaymentStatus.txt",s+"\n");
	}
}