package com.ikpb.driver;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.util.logging.LogManager;
import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.Logger;

import com.ikpb.daoimpl.CarImpl;
import com.ikpb.daoimpl.CustomerImpl;
import com.ikpb.daoimpl.UserImpl;
import com.ikpb.login.Login;
import com.ikpb.pojo.Car;
import com.ikpb.pojo.User;
import com.ikpb.pojo.User.UserType;
import com.ikpb.util.BusinessLogic;
public class RegistrationDriver implements Serializable{
  private static final Logger logger = Logger.getLogger(RegistrationDriver.class);
	public static void main(String[] args) throws InterruptedException {

		CarImpl implCar = new CarImpl();
		UserImpl users = new UserImpl();
		BusinessLogic justBusiness = new BusinessLogic();

		Scanner scanner = new Scanner(System.in);
		Login loggingIn = new Login();
		logger.info("Object lists created");
		int userOption = 0;
		/////getting into the system.
		while(userOption == 0) {	
		System.out.println("Welcome to King's AutoMall!");
		System.out.println("What whould you like to do?");
		System.out.println("Choose from the options below: (1-4)");
		System.out.println("1:Login as Customer");
		System.out.println("2:Login as Employee");
		System.out.println("3:Register with US");
		System.out.println("4:Quit");
		try {
		userOption = scanner.nextInt();
		}catch (InputMismatchException e) {
			logger.warn("user input was incorrect.");
		    System.err.println("Wrong input! Input only integer numbers please...");
		    scanner.nextLine();
		}
		logger.info("taking user input");
		if(userOption == 1) {
			//log in as customer
			/////////////////////
			UserType a= UserType.NEW_USER;
			User b = new User();
			while(a.equals(UserType.NEW_USER)) {
				b = loggingIn.LoggingIn(UserType.CUSTOMER);
				a = b.getUserType();
				
				userOption = 0;
			}	
			
			System.out.println("Customer: " + b.getFirstName() + " is now logged in!");
			System.out.println("Choose from the options below: (1-4)");
			while(userOption ==0) {
				logger.info("Enter while loop of the customer user interface.");
			System.out.println("1:View Cars on Lot");
			System.out.println("2:Make an Offer on a Car");
			System.out.println("3:View My Garage");
			System.out.println("4: Exit");
			try {
				userOption = scanner.nextInt();
				}catch (InputMismatchException e) {
					logger.warn("user input was incorrect.");
				    System.err.println("Wrong input! Input only integer numbers please...");
				    scanner.nextLine();
				    logger.info("continue on to retry and enter the correct input");
				    continue;
				    
				}
			scanner.nextLine();
			
			///////////display list of cars on the lot
			//////////////////////////////////////////
			if(userOption == 1) {
				logger.debug("getting the first pull of the car list.");
				implCar.printCarsList();
	
				TimeUnit.MILLISECONDS.sleep(1500);
				userOption = 0;
				
				
			///////////make an offer on a car
			/////////////////////////////////
			}else if(userOption == 2) {
				implCar.printCarsList();
				System.out.println("Which car would you like to Place an Offer on?(By Vin):");
					String CarId;
				CarId = scanner.nextLine();
				System.out.println("How much is your offer going to be?:");
				double offerAmount;
				try{offerAmount = scanner.nextDouble();}
				catch (InputMismatchException e) {
					logger.warn("user input was incorrect.");
				    System.err.println("Wrong input! Input only dollars and cents please...");
				    scanner.nextLine();
				    logger.info("continue on to retry and enter the correct input");
				    continue;
				}
				scanner.nextLine();
				if(implCar.getCarById(CarId).isCarAvaliable()!=false) {
				justBusiness.placeOffer(b, implCar.getCarById(CarId), implCar.getCarById(CarId).isCarAvaliable(), offerAmount);	
				System.out.println("Congrats! You just placed an offer of "+offerAmount +" on "+implCar.getCarById(CarId).getMake()+ "!");
				}else {
					System.out.println("Im sorry this car isn't avaliable, it will be taken off the lot shortly.");
				}
				
				userOption = 0;
			/////////////View cars in customers garage
			//////////////////////////////////////////
			}else if(userOption == 3) {
				logger.info("User trying to view cars in garage");
				b.setCar(implCar.getMyCarsList(b));
				if(b.getCarList().size()>0) {
					System.out.println(b.getCarList());
					logger.info("User has at least one car to choose from");
				System.out.println("Do you want to view your remaining balance?(Y/N)");
				String userIn = scanner.nextLine().toLowerCase();
				String carVin;
				if(userIn.equals("y")) {
					System.out.println("Which car do you want to view the remaining balance on?(Car Vin)");
					carVin = scanner.nextLine();
					System.out.println(justBusiness.getCarAmountLeft(b.getEmail(),carVin));
				}else {userOption = 0;continue; }
				System.out.println("Do you want to view your monthly payment?(Y/N)");
				userIn = scanner.nextLine().toLowerCase();
				if(userIn.equals("y")) {
				System.out.println(justBusiness.myMonthlyPayment(b.getEmail(),carVin));
				}else {userOption = 0;continue; }
				System.out.println("Do you want to make a payment?(Y/N)");
				userIn = scanner.nextLine().toLowerCase();
				if(userIn.equals("y")) {
					justBusiness.makeMonthlyPayment(b.getEmail(),carVin);
				System.out.println("payment of "+ justBusiness.myMonthlyPayment(b.getEmail(),carVin)+" was successfully added. Thank you for using Google Pay!");
				userOption =0;
				TimeUnit.MILLISECONDS.sleep(1500);
				logger.info("user finished making a payment");
				continue;
				
				}else {userOption = 0;continue; }
				}else { System.out.println("I'm sorry, you do not own any cars.");
						System.out.println("Let's change that! Place an offer on one of the avaiable cars!");
						logger.info("User doesn't own anycars.");
						userOption =0;}
				///////////Customer option 4 to exit... save user progress and data.
				////////////////////////////////////////////////////////////////////
			}else if(userOption ==4) {
				logger.info("User left customer options.");
				userOption =0;
				TimeUnit.MILLISECONDS.sleep(1000);
				break;
			}else {
				System.out.println("Please choose one of the 4 options...");
				userOption = 0;
			}
			
			}
			}else if(userOption == 2) {
				/////log in as Employee
				/////////////////////////////////////////////////
				int count=0;
				UserType a= UserType.NEW_USER;
				User b = new User();
				while(a.equals(UserType.NEW_USER)) {
					b = loggingIn.LoggingIn(UserType.EMPLOYEE);
					a = b.getUserType();
					++count;
					userOption = 0;
				}
				/////Employee options
				//////////////////////////////////////////////////
				while(userOption ==0) {
				System.out.println("Employee: " + b.getFirstName() + " is now logged in!");
				System.out.println("Choose from the options below: (1-6)");
				System.out.println("1:View Cars on Lot");
				System.out.println("2:View Offers");
				System.out.println("3:Add Car to lot");
				System.out.println("4:Remove Car from lot");
				System.out.println("5:View all payments");
				System.out.println("6: Exit");
				userOption = 0;
				try{userOption = scanner.nextInt();}
				catch (InputMismatchException e) {
					logger.warn("user input was incorrect. while entering carId in option 1-6 for employee");
				    System.err.println("Wrong input! Input only integer numbers please...");
				    scanner.nextLine();
				    logger.info("continue on to retry and enter the correct input for employee options");
				    continue;
				}scanner.nextLine();
				/////Employee show list of cars on lot
				//////////////////////////////////////////////////
				if(userOption == 1) {
					implCar.getAvaliableCarsListInitial();
					implCar.printCarsList();
					userOption = 0;
					TimeUnit.MILLISECONDS.sleep(1500);
				}
				/////Employee accept offer
				//////////////////////////////////////////////////
				else if(userOption == 2) {
					implCar.printCarsList();
					System.out.println("Which car would you like to view the offers on?(By Vin):");
					String carId;
					carId = scanner.nextLine();
					 justBusiness.viewAllOffersOnCar(carId);
					 System.out.println("Do you want to accept an offer? (Y/N):");
					 String usrInput = scanner.nextLine().toLowerCase();
					 if(usrInput.equals("y")) {
						 System.out.println("Which offer do you want to accept?(Input User Id)");
						 String userInput = scanner.nextLine();
//						 try{userInput= scanner.nextLine();}
//						 catch(NullPointerException e) {System.out.println(e);}
						 //cycle through list and match the car user input with the user with the same as the offer and pass that user in
						 
						 Double offerValue =justBusiness.getOneOffersFromDB(carId, userInput);
						 User c = new User();
						 c= users.getUserDB(userInput);
						 c = justBusiness.acceptOffer( c, implCar.getCarById(carId), offerValue);
						 logger.info("accepting offer and setting it to user");
						 logger.debug("trying to debug and get the accepted offer to persist");
						 users.updateUserProgram(c);
						 //need to add logic for persisting the users new car payment/ and persist user to owner of the car.
						 logger.info("updating user information with the car object.");
						 logger.info("saving user c's state.");
						 //users.saveUSer((List<User>) users);
					 }
					userOption = 0;
					
					////Employee Option 3 Add a car to the lot
					////////////////////////////////////////////
				}else if(userOption == 3) {
					implCar.addCar();
					userOption =0;
					/////Employee Option 4 Remove a car from lot
					////////////////////////////////////////////
				}else if(userOption ==4) {
					implCar.printCarsList();
					System.out.println("Which car would you like to remove from inventory?(By Vin):");
					String carVin = "";
					carVin = scanner.nextLine();
					implCar.deleteCar(carVin);
					userOption =0;
					/////Employee Option 5 view all payments
					/////////////////////////////////////////
				}else if(userOption ==5) {
					users.printCustomerList();
					System.out.println("Which Customer do you want to view?(Input User Id)");
					 String userInput= scanner.nextLine();
					 justBusiness.printCustomerCarList(userInput);
					 System.out.println("Which car do you want to view payments on?(Input Cars Vin)");
					  String userInputVin="";
					 try{userInputVin = scanner.nextLine();}
					 catch(NullPointerException e) {System.out.println(e);}
					 justBusiness.printCustomerPaymentList(userInput, userInputVin);
					userOption =0;
					////Employee Option 6 Exit Program
					////////////////////////////////////////////
				}else if(userOption ==6) {
					
					///// add logic to save all cars and their new parts/ update offers and user car list.
					logger.info("User left Employee options.");
					///add a user save method here
					logger.info("Users saved");
					logger.info("Car list saved");
					userOption =0;
					break;
				}else {
					System.out.println("Please choose one of the 4 options...");
					userOption = 0;
				}
				
				}
				////User Option 3 Register a user
				////////////////////////////////////////////
		}else if(userOption == 3) {
			
			users.addUser();
			//add a usersave method here
			userOption = 0;
				////User Option 4 Exit Program, Save all data
				////////////////////////////////////////////
		}else if(userOption == 4) {
			//exit system
			System.out.println("Thank you for visiting us today!");
			
		}else {
		System.out.println("Would you like to stay?(1:No, 2:Yes");
		userOption = 0;
		try{userOption = scanner.nextInt();}
		catch (InputMismatchException e) {
			logger.warn("user input was incorrect. line 242");
		    System.err.println("Wrong input! Input only integer numbers please...");
		    scanner.nextLine();
		    logger.info("continue on to retry and enter the correct input for employee options");
		    continue;
		}
		if(userOption == 1) {
			System.out.println("Goodbye");	
		}
		else if(userOption == 2) {
			System.out.println("Awesome let's try this again!");
			userOption = 0;
		}
		}
		}scanner.close();
		
		 
	 }
}
