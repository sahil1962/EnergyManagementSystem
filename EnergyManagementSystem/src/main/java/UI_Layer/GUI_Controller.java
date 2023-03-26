package UI_Layer;
import BusinessLogicLayer.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.logging.FileHandler;

import javax.swing.JOptionPane;

import com.itextpdf.text.DocumentException;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class GUI_Controller {
	//Dashboard Buttons
	@FXML private Button ButtonAddNewCustomer;
	@FXML private Button ButtonCustomerProfile;
	@FXML private Button ButtonEnergyUsage;
	@FXML private Button ButtonElectricityBill;
	@FXML private Button ButtonGasBill;
	@FXML private Button ButtonLogout;
	@FXML private Button ButtonShowCustomer;
	@FXML private Button ButtonTraiffInfo;

	//Dashboard Anchors
	@FXML private AnchorPane MainAnchor;
	@FXML private AnchorPane AnchorAddNewCustomer;
	@FXML private AnchorPane AnchorCustomerProfile;
	@FXML private AnchorPane AnchorEnergyUsage;
	@FXML private AnchorPane AnchorElectricityBill;
	@FXML private AnchorPane AnchorShowCustomers;
	@FXML private AnchorPane AnchorTraiffInfo;

	//Admin login setup
	@FXML private TextField AdminUsername;
	@FXML private PasswordField AdminPassword;
	@FXML private Label IncorrectPassword = new Label();
	@FXML private Label IncorrectUsername = new Label();
	//Add new candidate
	@FXML private TextField CustomerName;
	@FXML private TextField CustomerContactNo;
	@FXML private TextField CustomerAddress;
	@FXML private TextField CustomerMeterType;
	@FXML private TextField CustomerTraiffName;
	//Search specific Customer
	@FXML private Label CustomerProfileName = new Label();
	@FXML private Label CustomerProfileContactNo = new Label();
	@FXML private Label CustomerProfileAddress = new Label();
	@FXML private Label CustomerProfileMeterType = new Label();
	@FXML private Label CustomerProfileTariffName = new Label();
	@FXML private TextField SearchCustomerByName;
	@FXML private Label NoUserFound = new Label();
	//Calculating Bills
	@FXML private Label NoCustomerExits;
	@FXML private AnchorPane AnchorGasBillBySubmittedReadingC;
	@FXML private Button InputUnits;
	@FXML private Button EstimateUnits;
	@FXML private Button GotoElectricityBillE;
	@FXML private AnchorPane AnchorElectricityBillBySubmittedReading;
	@FXML private AnchorPane AnchorElectricityBillByEstimate;
	@FXML private TextField CheckCustomerNameForBill;
	@FXML private TextField CheckCustomerNameForGasBill;
	@FXML private Button ButtonCalculateGasType_E;
	@FXML private Button BackFromGasEstimateBill;
	@FXML private AnchorPane AnchorGasBillStatemantType_E;
	@FXML private Button  ButtonCalculateGasType_C;
	@FXML private Button BackFromGasInputBill;
	@FXML private AnchorPane AnchorGasBillStatemantType_C;
	//Gas Type E
	@FXML private AnchorPane AnchorGasBill;
	@FXML private AnchorPane AnchorGasBillTakingName;
	@FXML private AnchorPane AnchorGasBillByEstimate;
	@FXML private Button GasEstimateUnits;
	@FXML private DatePicker GasOpenDateE = new DatePicker();
	@FXML private DatePicker GasCloseDateE = new DatePicker();
	@FXML private TextField GasOpenReadE;
	@FXML private TextField GasCloseReadE;
	@FXML private Label OpenDate;
	@FXML private Label CloseDate;
	@FXML private Label CloseDate2;
	@FXML private Label OpenRead;
	@FXML private Label CloseRead;
	@FXML private Label TotalUnit;
	@FXML private Label UnitConversion1;
	@FXML private Label UnitConversion2;
	@FXML private Label UnitConversion3;
	@FXML private Label GaskWh_E;
	@FXML private Label GasPrice1_E;
	@FXML private Label GasPrice2_E;
	@FXML private Label GasPrice3_E;
	@FXML private Label GasTotalDays_E;
	@FXML private Label GasStandingCharges_E;
	@FXML private Label GasSupplyCharges_E;
	@FXML private Label GasVAT_5_E;
	@FXML private Label GasTotalCharges_E;

	//Gas bill Already paid
	@FXML private AnchorPane AnchorGasBillByEstimateAlreadyPaid;
	@FXML private Label OpeningReadGasAlreadyPaid;
	@FXML private Label OpeningDateGasAlreadyPaid;
	@FXML private DatePicker GasCloseDateAlreadyPaidE= new DatePicker();
	@FXML private TextField GasCloseReadAlreadyPaidE;
	@FXML private Button ButtonCalculateGasAlreadyPaidType_E;
	public boolean GasBillAlreadyPaid = false;
	//Electricity bill
	@FXML private Button ButtonCalculateBillElectricity_E;
	@FXML private Button BackFromElectricityEstimateBill;
	@FXML private Button BackFromElectricityInputBill;
	@FXML private AnchorPane AnchorElectricityBillStatemantType_E;
	@FXML private DatePicker ElectricityOpenDateE = new DatePicker();
	@FXML private DatePicker ElectricityCloseDateE = new DatePicker();
	@FXML private TextField ElectricityDayOpenReadE;
	@FXML private TextField ElectricityDayCloseReadE;
	@FXML private TextField ElectricityNightOpenReadE;
	@FXML private TextField ElectricityNightCloseReadE;

	@FXML private Label ElectricityOpenRead1_E;
	@FXML private Label ElectricityOpenRead2_E;
	@FXML private Label ElectricityCloseRead1_E;
	@FXML private Label ElectricityCloseRead2_E;
	@FXML private Label ElectricityOpenDate1_E;
	@FXML private Label ElectricityOpenDate2_E;
	@FXML private Label ElectricityOpenDate3_E;
	@FXML private Label ElectricityOpenDate4_E;
	@FXML private Label ElectricityCloseDate1_E;
	@FXML private Label ElectricityCloseDate2_E;
	@FXML private Label ElectricityCloseDate3_E;
	@FXML private Label ElectricityCloseDate4_E;
	@FXML private Label ElectricitykHw1_E;
	@FXML private Label ElectricitykHw2_E;
	@FXML private Label ElectricitykHw3_E;
	@FXML private Label ElectricitykHw4_E;
	@FXML private Label ElectricitykHw5_E;

	@FXML private Label ElectricityPrice1_E;
	@FXML private Label ElectricityPrice2_E;
	@FXML private Label ElectricityPrice3_E;
	@FXML private Label ElectricityPrice4_E;

	@FXML private Label ElectricityTotalDaysE;
	@FXML private Label ElectricityStandingCharges_E;
	@FXML private Label ElectricitySupplyCharges_E;
	@FXML private Label ElectricityVAT_5_E;
	@FXML private Label ElectricityTotalCharges_E;

	//Electricity already paid bill
	@FXML private AnchorPane AnchorElectricityBillAlreadyPaidByEstimate;
	@FXML private Button ButtonCalculateAlreadyPaidBillElectricity_E;
	@FXML private DatePicker ElectricityAlreadyPaidCloseDateE;
	@FXML private TextField  ElectricityAlreadyPaidNightCloseReadE;
	@FXML private TextField  ElectricityAlreadyPaidDayCloseReadE;
	@FXML private DatePicker ElectricityAlreadyPaidOpenDateE;
	@FXML private Label  ElectricityOpenDateAlreadyPaid;
	@FXML private Label  ElectricityAlreadyPaidNightOpenReadE;
	@FXML private Label  ElectricityAlreadyPaidDayOpenReadE;
	public boolean ElectricityBillAlreadyPaid = false;

	//Payment
	@FXML private AnchorPane AnchorPayment;
	@FXML private Button ButtonProceedToPay;
	@FXML private Label CustomerNameForPayment;
	@FXML private Label ElectricBillForPayment;
	@FXML private Label GasBillForPayment;
	@FXML private Label TotalElectricAndGasBill;
	@FXML private Button PayBill;
	@FXML private Button BackFromPayBill;
	//Customer Information
	@FXML private TextArea AllCustomerInfo;
	//Tariff info
	@FXML private AnchorPane AnchorUpdateTraiffInfo;
	@FXML private Button UpdateTariffInfo;
	@FXML private Button BackFromUpdateTariffInfo;
	@FXML private TextField TariffNameE;
	@FXML private TextField TariffPaymentMethodE;
	@FXML private TextField TariffEndDateE;
	@FXML private TextField TariffExitFeesE;
	@FXML private TextField TariffAnnualUsageE;
	@FXML private TextField TariffDayRateE;
	@FXML private TextField TariffNightRateE;
	@FXML private TextField TariffStandingChargeE;

	@FXML private TextField TariffNameG;
	@FXML private TextField TariffPaymentMethodG;
	@FXML private TextField  TariffEndDateG;
	@FXML private TextField  TariffExitFeesG;
	@FXML private TextField  TariffAnnualUsageG;
	@FXML private TextField  TariffUpdateRateG;
	@FXML private TextField  TariffStandingChargeG;

	@FXML private Label Tariff_1E;
	@FXML private Label Tariff_2E;
	@FXML private Label Tariff_3E;
	@FXML private Label Tariff_4E;
	@FXML private Label Tariff_5E;
	@FXML private Label Tariff_6E;
	@FXML private Label Tariff_7E;
	@FXML private Label Tariff_8E;

	@FXML private Label Tariff_1G;
	@FXML private Label Tariff_2G;
	@FXML private Label Tariff_3G;
	@FXML private Label Tariff_4G;
	@FXML private Label Tariff_5G;
	@FXML private Label Tariff_6G;
	@FXML private Label Tariff_8G;

	//Energy Usage chart
	@FXML private TextField CustomerNameForGraph;
	@FXML private Button ButtonMakeChart;
	@FXML private Label NoCustomerExitsForEnergyUsage;
	final CategoryAxis xAxis=new CategoryAxis();
	final NumberAxis yAxis=new NumberAxis();

	final CategoryAxis xAxis2=new CategoryAxis();
	final NumberAxis yAxis2=new NumberAxis();
	@FXML private BarChart<String, Number> ElectricityBarChart=new BarChart<String, Number>(xAxis,yAxis);
	@FXML private BarChart<String, Number> GasBarChart=new BarChart<String, Number>(xAxis2,yAxis2);


	@FXML
	// When user click on the cross in the Login page
	public void GoToLoginPage(ActionEvent event) {
		try {
			Stage stage = new Stage();
			Parent root = FXMLLoader.load(getClass().getResource("LoginPage.fxml"));
			Scene scene =  new Scene(root);
			stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			stage.setScene(scene);
			stage.setTitle("Login page");
			stage.show();
			IncorrectPassword.setText("");
			IncorrectUsername.setText("");

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("You are in Login page");
	}

	@FXML
	// When user click on the cross in the Login page
	public void GoToDashboard(ActionEvent event) {
		String Username = AdminUsername.getText().toString();
		String Password = AdminPassword.getText().toString();
		if(Username.equals("admin")) {
			IncorrectPassword.setText("");
			IncorrectUsername.setText("");
			if(Password.equals("admin")) {
				try {
					Stage stage = new Stage();
					Parent root = FXMLLoader.load(getClass().getResource("Dashboard.fxml"));
					Scene scene =  new Scene(root);
					stage = (Stage)((Node)event.getSource()).getScene().getWindow();
					stage.setScene(scene);
					stage.setTitle("Dashboard");
					stage.show();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("You are in Dashboard page");
			}
			else
				IncorrectPassword.setText("Wrong Password");
		}
		else 
			IncorrectUsername.setText("Wrong Username");
	}

	@FXML
	// When user click on the cross in the Login page
	public void AddNewCustomer(ActionEvent event) {
		String CName =  CustomerName.getText().toString();
		String CContactNo = CustomerContactNo.getText().toString();
		String CAddress =  CustomerAddress.getText().toString();
		String CMeterType = CustomerMeterType.getText().toString();
		String CTraiffname = CustomerTraiffName.getText().toString();

		Energy_Management_System obj=new Energy_Management_System();
		Customer obj1=new Customer(CName,CContactNo,CAddress);
		Energy_Tariffs obj2=new Energy_Tariffs(CTraiffname,CMeterType);
		obj.customer_info(obj1, obj2);
		System.out.println("Customer added successfully");
		CustomerName.clear();
		CustomerContactNo.clear();
		CustomerAddress.clear();
		CustomerMeterType.clear();
		CustomerTraiffName.clear();
	}

	//calculate the estimate bill for displaying it on 
	@FXML
	public void CalculateGasBillEType(ActionEvent event) {
		String ODate = GasOpenDateE.getValue().toString();
		String ORead = GasOpenReadE.getText();
		String CDate =  GasCloseDateE.getValue().toString();
		String CRead = GasCloseReadE.getText();
		System.out.println(ODate);
		System.out.println(ORead);
		System.out.println(CDate);
		System.out.println(CRead);
		CloseDate.setText(CDate);
		CloseRead.setText(CRead);
		OpenDate.setText(ODate);
		OpenRead.setText(ORead);
		Energy_Tariffs ET = new Energy_Tariffs();
		String TotalUnitstr = ET.GasUnitsCalulator(ORead, CRead)[0];
		String UnitConversion = ET.GasUnitsCalulator(ORead, CRead)[1];
		TotalUnit.setText(TotalUnitstr);
		UnitConversion1.setText(UnitConversion);
		UnitConversion2.setText(UnitConversion);
		String Price = ET.Calculate_Gas_Charges(UnitConversion)[1];
		GaskWh_E.setText(ET.Calculate_Gas_Charges(UnitConversion)[0]);
		GasPrice1_E.setText(ET.Calculate_Gas_Charges(UnitConversion)[1]);
		GasPrice2_E.setText(Price);
		GasPrice3_E.setText(Price);
		CloseDate2.setText(CDate);
		UnitConversion3.setText(UnitConversion);
		String StrArr[] = ET.Calculate_Standing_Gas_Charges(Price,ET.DateSyntx(ODate),ET.DateSyntx(CDate));
		GasTotalDays_E.setText(StrArr[0]);
		GasStandingCharges_E.setText(StrArr[1]);
		GasSupplyCharges_E.setText(StrArr[2]);
		GasVAT_5_E.setText(StrArr[3]);
		GasTotalCharges_E.setText(StrArr[4]);
	}

	//calculate the estimate bill for displaying it on 
	@FXML
	public void CalculateGasBillAlreadyPaidEType(ActionEvent event) {//.event.
		String ODate = OpeningDateGasAlreadyPaid.getText();
		String ORead = OpeningReadGasAlreadyPaid.getText();
		String CDate =  GasCloseDateAlreadyPaidE.getValue().toString();
		String CRead = GasCloseReadAlreadyPaidE.getText();
		System.out.println(ODate);
		System.out.println(ORead);
		System.out.println(CDate);
		System.out.println(CRead);
		CloseDate.setText(CDate);
		CloseRead.setText(CRead);
		OpenDate.setText(ODate);
		OpenRead.setText(ORead);
		Energy_Tariffs ET = new Energy_Tariffs();
		String TotalUnitstr = ET.GasUnitsCalulator(ORead, CRead)[0];
		String UnitConversion = ET.GasUnitsCalulator(ORead, CRead)[1];
		TotalUnit.setText(TotalUnitstr);
		UnitConversion1.setText(UnitConversion);
		UnitConversion2.setText(UnitConversion);
		String Price = ET.Calculate_Gas_Charges(UnitConversion)[1];
		GaskWh_E.setText(ET.Calculate_Gas_Charges(UnitConversion)[0]);
		GasPrice1_E.setText(ET.Calculate_Gas_Charges(UnitConversion)[1]);
		GasPrice2_E.setText(Price);
		GasPrice3_E.setText(Price);
		CloseDate2.setText(CDate);
		UnitConversion3.setText(UnitConversion);
		String StrArr[] = ET.Calculate_Standing_Gas_Charges(Price,ET.DateSyntx(ODate),ET.DateSyntx(CDate));
		GasTotalDays_E.setText(StrArr[0]);
		GasStandingCharges_E.setText(StrArr[1]);
		GasSupplyCharges_E.setText(StrArr[2]);
		GasVAT_5_E.setText(StrArr[3]);
		GasTotalCharges_E.setText(StrArr[4]);
	}

	@FXML
	//Calculate electricity bill
	public void CheckElectricityBillEstimateOrInputValue(ActionEvent event) {
		String CName = CheckCustomerNameForGasBill.getText();
		File_Handling FH = new File_Handling();
		boolean flag = FH.Return_boolean_MatchingInFile("Customer.txt", CName);
		boolean flag2 = FH.Return_boolean_MatchingInFile("ElectricityOpeningDate.txt", CName);
		if (event.getSource() == GotoElectricityBillE) {
			if (flag == true){
				if (flag2==true) {
					AnchorElectricityBillAlreadyPaidByEstimate.toFront();
					AnchorGasBillStatemantType_E.toBack();
					int counter= FH.Return_Count_MatchingLine("ElectricityOpeningDate.txt", CName);
					String Opening_Date=FH.Return_Balance("ElectricityOpeningDate.txt", counter+1);
					String Opening_Night_Read=FH.Return_Balance("ElectricityOpeningDate.txt", counter+2);
					String Opening_Day_Read=FH.Return_Balance("ElectricityOpeningDate.txt", counter+3);
					ElectricityOpenDateAlreadyPaid.setText(Opening_Date);
					ElectricityAlreadyPaidNightOpenReadE.setText(Opening_Night_Read);
					ElectricityAlreadyPaidDayOpenReadE.setText(Opening_Day_Read);
					ElectricityBillAlreadyPaid = true;
				}
				else {
					AnchorElectricityBillByEstimate.toFront();
					AnchorGasBillStatemantType_E.toBack();
					ElectricityBillAlreadyPaid = false;
				}

			}

		}
	}

	@FXML
	// getting gas bill
	public void CheckGasBillEstimateOrInputValue(ActionEvent event) {
		if (event.getSource() == GasEstimateUnits) {
			String CName = CheckCustomerNameForGasBill.getText();
			File_Handling FH = new File_Handling();
			boolean flag = FH.Return_boolean_MatchingInFile("Customer.txt", CName);
			boolean flag2 = FH.Return_boolean_MatchingInFile("OpeningDate.txt", CName);
			if (flag == true){
				if (flag2==true) {
					AnchorGasBillByEstimateAlreadyPaid.toFront();
					int counter= FH.Return_Count_MatchingLine("OpeningDate.txt", CName);
					String Opening_Date=FH.Return_Balance("OpeningDate.txt", counter+1);
					String Opening_Read=FH.Return_Balance("OpeningDate.txt", counter+2);
					OpeningDateGasAlreadyPaid.setText(Opening_Date);
					OpeningReadGasAlreadyPaid.setText(Opening_Read);

					GasBillAlreadyPaid = true;
				}
				else {
					AnchorGasBillByEstimate.toFront();
					GasBillAlreadyPaid = false;
				}

			}
			else {
				NoCustomerExits.setText("No User Exists with this name\n      First Register Yourself");
			}
		}
		else if (event.getSource() == ButtonCalculateGasType_E) {
			AnchorGasBillStatemantType_E.toFront();
			CalculateGasBillEType(event);
		}

		else if (event.getSource() == ButtonCalculateGasAlreadyPaidType_E) {
			AnchorGasBillStatemantType_E.toFront();
			CalculateGasBillAlreadyPaidEType(event);
		}
	}


	@FXML
	//	getting gas bill
	public void CalculateGBill(ActionEvent event) {
		if (event.getSource() == InputUnits) {
			String CName =  CheckCustomerNameForBill.getText().toString();
			Energy_Tariffs ET = new Energy_Tariffs();
			String MeterType = ET.returnmetertype(CName);
			AnchorElectricityBillBySubmittedReading.toFront();
		}	

		else if (event.getSource() == EstimateUnits) {
			String CName =  CheckCustomerNameForBill.getText().toString();
			Energy_Tariffs ET = new Energy_Tariffs();
			String MeterType = ET.returnmetertype(CName);
			if (MeterType.equals("Gas")) {
				AnchorElectricityBillByEstimate.toFront();
			}
			else if (MeterType.equals("Electricity")) {
				AnchorElectricityBillByEstimate.toFront();
			}		
			else if (MeterType.equals("User Doesn't exists")) {
				//label set here
			}
		}
		else if (event.getSource() == BackFromGasEstimateBill) {
			AnchorGasBillStatemantType_E.toBack();
			AnchorGasBillTakingName.toFront();
		}
		else if (event.getSource() == ButtonCalculateGasType_C) {
			//			CalculateGasBill_C_Type(event);
			AnchorGasBillStatemantType_C.toFront();
		}
		else if (event.getSource() == BackFromGasInputBill) {
			AnchorGasBillStatemantType_C.toBack();
			AnchorElectricityBill.toFront();
		}

	}


	@FXML
	//getting electric bill for displaying it on GUI
	public void CalculateElectricBill_E_Type(ActionEvent event) {
		String ODate = ElectricityOpenDateE.getValue().toString();
		String CDate = ElectricityCloseDateE.getValue().toString();


		String NightOpenRead = ElectricityNightOpenReadE.getText();
		String NightCloseRead = ElectricityNightCloseReadE.getText();
		String DayOpenRead = ElectricityDayOpenReadE.getText();
		String DayCloseRead = ElectricityDayCloseReadE.getText();

		System.out.println(ODate);
		System.out.println(CDate);

		System.out.println(DayOpenRead);
		System.out.println(DayCloseRead);
		System.out.println(NightOpenRead);
		System.out.println(NightCloseRead);		

		ElectricityOpenDate1_E.setText(ODate);
		ElectricityOpenDate2_E.setText(ODate);
		ElectricityOpenDate3_E.setText(ODate);
		ElectricityOpenDate4_E.setText(ODate);
		ElectricityCloseDate1_E.setText(CDate);
		ElectricityCloseDate2_E.setText(CDate);
		ElectricityCloseDate3_E.setText(CDate);
		ElectricityCloseDate4_E.setText(CDate);

		ElectricityOpenRead1_E.setText(NightOpenRead);
		ElectricityOpenRead2_E.setText(DayOpenRead);

		ElectricityCloseRead1_E.setText(NightCloseRead);
		ElectricityCloseRead2_E.setText(DayCloseRead);

		Energy_Tariffs ET = new Energy_Tariffs();
		String NightkWh = ET.Electricity_UnitsCalulator(NightOpenRead, NightCloseRead);
		String DaykWh = ET.Electricity_UnitsCalulator(DayOpenRead, DayCloseRead);
		double value1 = Double.parseDouble(NightkWh);
		double value2 = Double.parseDouble(DaykWh);
		double value3=value1+value2;

		ElectricitykHw1_E.setText(NightkWh);
		ElectricitykHw2_E.setText(DaykWh);
		ElectricitykHw3_E.setText(value3+"");
		ElectricitykHw4_E.setText(NightkWh);
		ElectricitykHw5_E.setText(DaykWh);

		String NightPrice = ET.Electricity_ChargesCalulator(NightkWh);
		String DayPrice = ET.Electricity_ChargesCalulator(DaykWh);
		double Nightvalue1 = Double.parseDouble(NightPrice);
		double Dayvalue2 = Double.parseDouble(DayPrice);
		double Tvalue3=Nightvalue1+Dayvalue2;
		ElectricityPrice1_E.setText(Nightvalue1+"");
		ElectricityPrice2_E.setText(Dayvalue2+"");
		ElectricityPrice3_E.setText(Tvalue3+"");
		ElectricityPrice4_E.setText(Tvalue3+"");

		String DataStr[] = ET.Calculate_Standing_Electricity_Charges(Tvalue3+"",ET.DateSyntx(ODate),ET.DateSyntx(CDate));

		ElectricityTotalDaysE.setText(DataStr[0]);
		ElectricityStandingCharges_E.setText(DataStr[1]);
		ElectricitySupplyCharges_E.setText(DataStr[2]);
		ElectricityVAT_5_E.setText(DataStr[3]);
		ElectricityTotalCharges_E.setText(DataStr[4]);

	}

	@FXML
	//getting electric bill for displaying it on GUI
	public void CalculateElectricBillAlreadyPaid_E_Type(ActionEvent event) {		
		String ODate = ElectricityOpenDateAlreadyPaid.getText();
		String NightOpenRead = ElectricityAlreadyPaidNightOpenReadE.getText();
		String DayOpenRead = ElectricityAlreadyPaidDayOpenReadE.getText();

		String CDate = ElectricityAlreadyPaidCloseDateE.getValue().toString();
		String NightCloseRead  = ElectricityAlreadyPaidNightCloseReadE.getText();
		String DayCloseRead= ElectricityAlreadyPaidDayCloseReadE.getText();

		System.out.println(ODate);
		System.out.println(CDate);

		System.out.println(DayOpenRead);
		System.out.println(DayCloseRead);
		System.out.println(NightOpenRead);
		System.out.println(NightCloseRead);		

		ElectricityOpenDate1_E.setText(ODate);
		ElectricityOpenDate2_E.setText(ODate);
		ElectricityOpenDate3_E.setText(ODate);
		ElectricityOpenDate4_E.setText(ODate);
		ElectricityCloseDate1_E.setText(CDate);
		ElectricityCloseDate2_E.setText(CDate);
		ElectricityCloseDate3_E.setText(CDate);
		ElectricityCloseDate4_E.setText(CDate);

		ElectricityOpenRead1_E.setText(NightOpenRead);
		ElectricityOpenRead2_E.setText(DayOpenRead);

		ElectricityCloseRead1_E.setText(NightCloseRead);
		ElectricityCloseRead2_E.setText(DayCloseRead);

		Energy_Tariffs ET = new Energy_Tariffs();
		String NightkWh = ET.Electricity_UnitsCalulator(NightOpenRead, NightCloseRead);
		String DaykWh = ET.Electricity_UnitsCalulator(DayOpenRead, DayCloseRead);
		double value1 = Double.parseDouble(NightkWh);
		double value2 = Double.parseDouble(DaykWh);
		double value3=value1+value2;

		ElectricitykHw1_E.setText(NightkWh);
		ElectricitykHw2_E.setText(DaykWh);
		ElectricitykHw3_E.setText(value3+"");
		ElectricitykHw4_E.setText(NightkWh);
		ElectricitykHw5_E.setText(DaykWh);

		String NightPrice = ET.Electricity_ChargesCalulator(NightkWh);
		String DayPrice = ET.Electricity_ChargesCalulator(DaykWh);
		double Nightvalue1 = Double.parseDouble(NightPrice);
		double Dayvalue2 = Double.parseDouble(DayPrice);
		double Tvalue3=Nightvalue1+Dayvalue2;
		ElectricityPrice1_E.setText(Nightvalue1+"");
		ElectricityPrice2_E.setText(Dayvalue2+"");
		ElectricityPrice3_E.setText(Tvalue3+"");
		ElectricityPrice4_E.setText(Tvalue3+"");

		String DataStr[] = ET.Calculate_Standing_Electricity_Charges(Tvalue3+"",ET.DateSyntx(ODate),ET.DateSyntx(CDate));

		ElectricityTotalDaysE.setText(DataStr[0]);
		ElectricityStandingCharges_E.setText(DataStr[1]);
		ElectricitySupplyCharges_E.setText(DataStr[2]);
		ElectricityVAT_5_E.setText(DataStr[3]);
		ElectricityTotalCharges_E.setText(DataStr[4]);

	}
	
	@FXML
	// getting buttons pressed information from GUI
	public void DrawChartOfEnergyUsage(ActionEvent event) {
		String CustomerName = CustomerNameForGraph.getText();
		File_Handling FH = new File_Handling();
		boolean flag = FH.Return_boolean_MatchingInFile("Customer.txt", CustomerName);
		if (flag == true){
			NoCustomerExitsForEnergyUsage.setText("");
			int counter  = FH.Return_Count_MatchingLine("PaymentStatus.txt", CustomerName);
			System.out.println("Customer counter: " + counter);

			String Usage = FH.Return_Balance("PaymentStatus.txt", counter+1);

			String[] StrSplit = Usage.split("-");
			String ElectricityUsage = "", GasUsage = "";
			for (int i=0; i < StrSplit.length; i++){
				if (i==0)
					ElectricityUsage = StrSplit[i];
				if (i==1)
					GasUsage = StrSplit[i];
			}	
			System.out.println(ElectricityUsage + "-----"+ GasUsage);

			double Euse = Double.parseDouble(ElectricityUsage);
			int i = (int)Euse;
			double Guse = Double.parseDouble(GasUsage);
			int value = (int)Guse;

			ElectricityBarChart.getData().clear();
			GasBarChart.getData().clear();
			Series<String, Number> series1=new XYChart.Series<>();
			Series<String, Number> series2=new XYChart.Series<>();		

			series1.getData().add(new XYChart.Data<>("Electricity Bar Chart", i));
			ElectricityBarChart.getData().add(series1);

			series2.getData().add(new XYChart.Data<>("Gas Bar Chart", value));
			GasBarChart.getData().add(series2);
		}
		else {
			NoCustomerExitsForEnergyUsage.setText("No User Exists");
		}
	}
	@FXML
	// getting buttons pressed information from GUI
	public void CalculateElectricityBillButtons(ActionEvent event) throws FileNotFoundException, DocumentException {
		if (event.getSource() == ButtonCalculateBillElectricity_E) {
			AnchorElectricityBillStatemantType_E.toFront();
			CalculateElectricBill_E_Type(event);
		}
		else if (event.getSource() == ButtonCalculateAlreadyPaidBillElectricity_E) {
			AnchorElectricityBillStatemantType_E.toFront();
			CalculateElectricBillAlreadyPaid_E_Type(event);
		}

		else if (event.getSource() == BackFromElectricityEstimateBill) {
			AnchorElectricityBillStatemantType_E.toBack();
			AnchorGasBillTakingName.toFront();
		}
		else if (event.getSource() == ButtonProceedToPay) {
			AnchorPayment.toFront();
			AnchorElectricityBillStatemantType_E.toBack();
			ButtonProceedToPayFunc(event);
			WriteMeterReadingFunc(event);
		}
		else if (event.getSource() == PayBill) {
			AnchorGasBillTakingName.toFront();
			AnchorElectricityBillStatemantType_E.toBack();
			AnchorGasBillStatemantType_E.toBack();
			MakingInvioce(event);
		}
		else if (event.getSource() == BackFromPayBill) {
			AnchorGasBillTakingName.toFront();
			AnchorElectricityBillStatemantType_E.toBack();
			AnchorGasBillStatemantType_E.toBack();
		}
	}

	@FXML
	//	getting all customers data for displaying it on GUI
	public void ShowAllCustomersInfo(ActionEvent event) {
		AllCustomerInfo.clear();
		Energy_Management_System EGM = new Energy_Management_System();
		String CustomerInfo[] = EGM.showallcustomerdata();
		File_Handling obj3=new File_Handling();
		int all_lines=obj3.retrunallLines();

		//		System.out.println(all_lines);

		for(int i=1;i<=all_lines;i++) {
			//			System.out.println(CustomerInfo[i]);
			AllCustomerInfo.appendText(CustomerInfo[i] + "\n");
		}
	}

	@FXML
	// getting invoice data from GUI to make pdf
	public void MakingInvioce(ActionEvent event) throws FileNotFoundException, DocumentException {
		String CName =  CheckCustomerNameForGasBill.getText().toString();
		String ElectricityBill = ElectricityTotalCharges_E.getText().toString();
		String GasBill = GasTotalCharges_E.getText().toString();
		CustomerNameForPayment.setText(CName);
		ElectricBillForPayment.setText(ElectricityBill);
		GasBillForPayment.setText(GasBill);
		double result1 = Double.parseDouble(ElectricityBill);
		double result2 = Double.parseDouble(GasBill);
		double TOLTALBILL = result1 + result2;
		String TBill=""+new DecimalFormat("#.00#").format(TOLTALBILL);
		TotalElectricAndGasBill.setText(TBill);

		String ElectricityUsage = ElectricitykHw3_E.getText().toString();
		String GasUsage = GaskWh_E.getText().toString();
		Payment P = new Payment();		
		P.generate_invoices(CName, ElectricityBill, GasBill, TBill);

		File_Handling FH = new File_Handling();
		boolean flag = FH.Return_boolean_MatchingInFile("PaymentStatus.txt", CName);

		if (flag == true) {
			int counter  = FH.Return_Count_MatchingLine("PaymentStatus.txt", CName);
			System.out.println("Customer counter: " + counter);
			String Usage = FH.Return_Balance("PaymentStatus.txt", counter+1);
			FH.modifyFile("PaymentStatus.txt", counter + 1, Usage, ElectricityUsage + "-" + GasUsage+ "-paid");

			if (GasBillAlreadyPaid == true) {
				
				
				String AlreadyPaidDate = GasCloseDateAlreadyPaidE.getValue().toString();
				String AlreadyPaidRead = GasCloseReadAlreadyPaidE.getText().toString();				
				String Sentence = CName +"\n" + AlreadyPaidDate + "\n" +AlreadyPaidRead;
				int counter2 = FH.Return_Count_MatchingLine("OpeningDate.txt", CName);
				String UpdateThisDate = FH.Return_Balance("OpeningDate.txt", counter2 + 1);
				String UpdateThisRead = FH.Return_Balance("OpeningDate.txt", counter2 + 2);

				FH.modifyFile("OpeningDate.txt", counter2 + 1, UpdateThisDate, AlreadyPaidDate);
				FH.modifyFile("OpeningDate.txt", counter2 + 2, UpdateThisRead, AlreadyPaidRead);

				String ODate = 			ElectricityAlreadyPaidCloseDateE.getValue().toString();
				String NightOpenRead = 	ElectricityAlreadyPaidNightCloseReadE.getText();
				String DayOpenRead =	ElectricityAlreadyPaidDayCloseReadE.getText();

				int counter3 = FH.Return_Count_MatchingLine("ElectricityOpeningDate.txt", CName);
				String EUpdateThisDate = FH.Return_Balance("ElectricityOpeningDate.txt", counter3 + 1);
				String ENUpdateThisRead = FH.Return_Balance("ElectricityOpeningDate.txt", counter3 + 2);
				String EDUpdateThisRead = FH.Return_Balance("ElectricityOpeningDate.txt", counter3 + 3);
			
				FH.modifyFile("ElectricityOpeningDate.txt", counter3 + 1, EUpdateThisDate, ODate);
				FH.modifyFile("ElectricityOpeningDate.txt", counter3 + 2, ENUpdateThisRead, NightOpenRead);
				FH.modifyFile("ElectricityOpeningDate.txt", counter3 + 3, EDUpdateThisRead, DayOpenRead);
				
				ElectricityAlreadyPaidCloseDateE.setValue(null);
				ElectricityAlreadyPaidNightCloseReadE.setText("");
				ElectricityAlreadyPaidDayCloseReadE.setText("");
			}
		}
		else {
			String PaidDate = GasCloseDateE.getValue().toString();
			String PaidRead = GasCloseReadE.getText().toString();				
			String Sentence = CName +"\n" + PaidDate + "\n" + PaidRead;
			FH.WriteToFile("OpeningDate.txt", Sentence);
			String PaidElectricityDate = ElectricityCloseDateE.getValue().toString();
			String ElectricityPaidNightRead = ElectricityNightCloseReadE.getText().toString();	
			String ElectricityPaidDayRead = ElectricityDayCloseReadE.getText().toString();	
			String ElectricitySentence = CName +"\n" + PaidElectricityDate + "\n" + ElectricityPaidNightRead+ "\n" + ElectricityPaidDayRead;
			FH.WriteToFile("ElectricityOpeningDate.txt", ElectricitySentence);
			P.data_write(CName + "\n" + ElectricityUsage + "-" + GasUsage);	
		}

	}

	@FXML
	//	getting meter reading
	public void WriteMeterReadingFunc(ActionEvent event) {
		String Data  = CheckCustomerNameForGasBill.getText().toString() + "-";
		Data  += UnitConversion2.getText().toString() + "-";// -- Gas
		Data  += ElectricitykHw3_E.getText().toString();	//  -- Electricity	
		Energy_Tariffs obj = new Energy_Tariffs(Data);
		obj.meterreading(obj);

	}


	@FXML
	// getting data for payment
	public void ButtonProceedToPayFunc(ActionEvent event) {
		String CName =  CheckCustomerNameForGasBill.getText().toString();
		String ElectricityBill = ElectricityTotalCharges_E.getText().toString();
		String GasBill = GasTotalCharges_E.getText().toString();
		CustomerNameForPayment.setText(CName);
		ElectricBillForPayment.setText(ElectricityBill);
		GasBillForPayment.setText(GasBill);

		double result1 = Double.parseDouble(ElectricityBill);
		double result2 = Double.parseDouble(GasBill);
		double TOLTALBILL = result1 + result2;
		String TBill=""+new DecimalFormat("#.00#").format(TOLTALBILL);
		TotalElectricAndGasBill.setText(TBill);

		System.out.println("Name : " + CName);
		System.out.println("Gas : " +GasBill );
		System.out.println("Electricity : " +ElectricityBill );	
		System.out.println("TBill : " +TBill );	
	}


	@FXML
	// getting data from GUI to find for a customer profile by name
	public void SearchCustomer(ActionEvent event) {
		NoUserFound.setText(" ");
		String CName =  SearchCustomerByName.getText().toString();
		Energy_Management_System obj=new Energy_Management_System();

		String profiledata=obj.ReturnProfileInfo(CName);
		if (profiledata.equals("No User Exists")) {
			NoUserFound.setText(profiledata);
		}
		else {
			String[] arrSplit_3 = profiledata.split("-");

			for (int i=0; i < arrSplit_3.length; i++){
				if (i==0)
					CustomerProfileName.setText(arrSplit_3[i].toString());
				if (i==1)
					CustomerProfileContactNo.setText(arrSplit_3[i].toString());
				if (i==2)
					CustomerProfileAddress.setText(arrSplit_3[i].toString());
				if (i==3)
					CustomerProfileTariffName.setText(arrSplit_3[i].toString());
				if (i==4)
					CustomerProfileMeterType.setText(arrSplit_3[i].toString());
			}	
		}
		System.out.println("Profile of customer" + profiledata);
	}

	@FXML
	// getting data from GUI to update tariff data
	public void UpdateTariffInfoFunc(ActionEvent event) {
		System.out.println("Name : =======");
		String TariffData = "";
		TariffData += TariffNameE.getText().toString();
		TariffData += "#";
		TariffData += TariffPaymentMethodE.getText().toString();
		TariffData += "#";
		TariffData += TariffEndDateE.getText().toString();
		TariffData += "#";
		TariffData += TariffExitFeesE.getText().toString();
		TariffData += "#";
		TariffData += TariffAnnualUsageE.getText().toString();
		TariffData += "#";
		TariffData += TariffDayRateE.getText().toString();
		TariffData += "#";
		TariffData += TariffNightRateE.getText().toString();
		TariffData += "#";
		TariffData += TariffStandingChargeE.getText().toString();
		TariffData += "#";
		TariffData += TariffNameG.getText().toString();
		TariffData += "#";
		TariffData += TariffPaymentMethodG.getText().toString();
		TariffData += "#";
		TariffData += TariffEndDateG.getText().toString();
		TariffData += "#";
		TariffData += TariffExitFeesG.getText().toString();
		TariffData += "#";
		TariffData += TariffAnnualUsageG.getText().toString();
		TariffData += "#";
		TariffData += TariffUpdateRateG.getText().toString();
		TariffData += "#";
		TariffData += TariffStandingChargeG.getText().toString();

		File_Handling FH = new File_Handling();
		FH.OverWriteFile(TariffData);
		System.out.println(TariffData);
	}

	// setting tariff labels for GUI
	public void SetTariffLabels(ActionEvent event) {   // This function is called when we have to input the Data of separate person
		File_Handling obj=new File_Handling();
		String Sentence = "";
		Energy_Tariffs ET = new Energy_Tariffs();
		Sentence = ET.TraiffDetailUpdate();
		String[] arrSplit_3 = Sentence.split("#");
		String s="";

		for (int i=0; i < arrSplit_3.length; i++){
			if (i==0) {
				s = arrSplit_3[i].toString();
				System.out.println(arrSplit_3[i].toString());
				Tariff_1E.setText(s);
			}
			if (i==1) {
				s = arrSplit_3[i].toString();
				System.out.println(arrSplit_3[i].toString());
				Tariff_2E.setText(s);
			}
			if (i==2) {
				s = arrSplit_3[i].toString();
				System.out.println(arrSplit_3[i].toString());
				Tariff_3E.setText(s);
			}
			if (i==3) {
				s = arrSplit_3[i].toString();
				System.out.println(arrSplit_3[i].toString());
				Tariff_4E.setText(s);
			}
			if (i==4) {
				s = arrSplit_3[i].toString();
				System.out.println(arrSplit_3[i].toString());
				Tariff_5E.setText(s);
			}
			if (i==5) {
				s = arrSplit_3[i].toString();
				System.out.println(arrSplit_3[i].toString());
				Tariff_6E.setText(s);
			}
			if (i==6) {
				s = arrSplit_3[i].toString();
				System.out.println(arrSplit_3[i].toString());
				Tariff_7E.setText(s);
			}
			if (i==7) {
				s = arrSplit_3[i].toString();
				System.out.println(arrSplit_3[i].toString());
				Tariff_8E.setText(s);
			}
			if (i==8) {
				s = arrSplit_3[i].toString();
				System.out.println(arrSplit_3[i].toString());
				Tariff_1G.setText(s);
			}
			if (i==9) {
				s = arrSplit_3[i].toString();
				System.out.println(arrSplit_3[i].toString());
				Tariff_2G.setText(s);
			}
			if (i==10) {
				s = arrSplit_3[i].toString();
				System.out.println(arrSplit_3[i].toString());
				Tariff_3G.setText(s);			
			}
			if (i==11) {
				s = arrSplit_3[i].toString();
				System.out.println(arrSplit_3[i].toString());
				Tariff_4G.setText(s);			
			}
			if (i==12) {
				s = arrSplit_3[i].toString();
				System.out.println(arrSplit_3[i].toString());
				Tariff_5G.setText(s);			
			}
			if (i==13) {
				s = arrSplit_3[i].toString();
				System.out.println(arrSplit_3[i].toString());
				Tariff_6G.setText(s);			
			}
			if (i==14) {
				s = arrSplit_3[i].toString();
				System.out.println(arrSplit_3[i].toString());
				Tariff_8G.setText(s);			
			}
		}

	}
	@FXML
	//getting Dashborad button information to execute the desire functionilty
	void DashboardTabButtons(ActionEvent event) {
		if (event.getSource() == ButtonAddNewCustomer) {
			AnchorAddNewCustomer.toFront();
		}
		else if (event.getSource() == ButtonCustomerProfile) {
			AnchorCustomerProfile.toFront();
		}
		else if (event.getSource() == ButtonEnergyUsage) {
			AnchorEnergyUsage.toFront();
		}
		else if (event.getSource() == ButtonElectricityBill) {
			AnchorElectricityBill.toFront();
		}
		else if (event.getSource() == ButtonShowCustomer) {
			AnchorShowCustomers.toFront();
			ShowAllCustomersInfo(event);
			System.out.println("Anchor Show Customers");
		}
		else if (event.getSource() == ButtonTraiffInfo) {
			AnchorTraiffInfo.toFront();
			System.out.println("Anchor Traiff Info");
			SetTariffLabels(event);
		}
		else if (event.getSource() == ButtonGasBill) {
			AnchorGasBillTakingName.toFront();
			System.out.println("Anchor Gas Bill Taking Name");
		}
		else if (event.getSource() == UpdateTariffInfo) {
			AnchorUpdateTraiffInfo.toFront();
			System.out.println("Anchor Update Traiff Info");
		}
		else if (event.getSource() == BackFromUpdateTariffInfo) {
			AnchorTraiffInfo.toFront();
			UpdateTariffInfoFunc(event);
			SetTariffLabels(event);
			System.out.println("Anchor Traiff Info");
		}
	}

}
