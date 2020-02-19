package com.ikpb.driver;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.util.logging.LogManager;
import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.Logger;

import com.ikpb.daoimpl.CarImpl;
import com.ikpb.daoimpl.CustomerImpl;
import com.ikpb.daoimpl.UserImpl;
import com.ikpb.login.Login;

import com.ikpb.pojo.User;
import com.ikpb.pojo.User.UserType;
public class RegistrationDriver {
	private static final Logger logger = Logger.getLogger(RegistrationDriver.class);
	public static void main(String[] args) throws InterruptedException {
		CustomerImpl cust = new CustomerImpl();
		CarImpl implCar = new CarImpl();
		UserImpl users = new UserImpl();

		Scanner scanner = new Scanner(System.in);
		Login loggingIn = new Login();
		logger.info("Object lists created");
		int userOption = 0;
		
		while(userOption == 0) {
		
		System.out.println("Welcome to King's AutoMall!");
		System.out.println("What whould you like to do?");
		System.out.println("Choose from the options below: (1-4)");
		System.out.println("1:Login as Customer");
		System.out.println("2:Login as Employee");
		System.out.println("3:Register with US");
		System.out.println("4:Quit");
		userOption = scanner.nextInt();
		logger.info("taking user input");
		if(userOption == 1) {
			//log in as customer
			int count=0;
			UserType a= UserType.NEW_USER;
			User b = new User();
			while(a.equals(UserType.NEW_USER)) {
				b = loggingIn.LoggingIn(count, UserType.CUSTOMER);
				a = b.getUserType();
				++count;
				userOption = 0;
			}	
			System.out.println("Customer: " + b.getFirstName() + " is now logged in!");
			System.out.println("Choose from the options below: (1-4)");
			while(userOption ==0) {
				logger.info("Enter while loop of the customer user interface.");
			System.out.println("1:View Cars on Lot");
			System.out.println("2:Make an Offer on a Car");
			System.out.println("3:View Remaining Payments");
			System.out.println("4: Exit");
			userOption = scanner.nextInt();
			if(userOption == 1) {
				logger.debug("getting the first pull of the car list.");
				implCar.getCarsList();
				TimeUnit.MILLISECONDS.sleep(7000);
				userOption = 0;
			}else if(userOption == 2) {
				System.out.println("Which car would you like to Place an Offer on?(By Id):");
				int CarId = scanner.nextInt();
				scanner.nextLine();
				System.out.println("How much is your offer going to be?:");
				double offerAmount = scanner.nextDouble();
				scanner.nextLine();
				implCar.getCarById(CarId).addOffer(b, offerAmount);
				userOption = 0;
			}else if(userOption == 3) {
			}else if(userOption ==4) {
				
			}else {
				System.out.println("Please choose one of the 4 options...");
				userOption = 0;
			}
			
			}
			}else if(userOption == 2) {
				//log in as customer
				int count=0;
				UserType a= UserType.NEW_USER;
				User b = new User();
				while(a.equals(UserType.NEW_USER)) {
					b = loggingIn.LoggingIn(count, UserType.CUSTOMER);
					a = b.getUserType();
					++count;
					userOption = 0;
				}
				while(userOption ==0) {
				System.out.println("Employee: " + b.getFirstName() + "is now logged in!");
				System.out.println("Choose from the options below: (1-6)");
				System.out.println("1:View Cars on Lot");
				System.out.println("2:View Offers");
				System.out.println("3:Add Car to lot");
				System.out.println("4:Remove Car from lot");
				System.out.println("5:View all payments");
				System.out.println("6: Exit");
				userOption = scanner.nextInt();
			
				if(userOption == 1) {
					implCar.getCarsList();
					
					userOption = 0;
					TimeUnit.MILLISECONDS.sleep(7000);
				}else if(userOption == 2) {
					System.out.println("Which car would you like to view the offers on?:");
					 int carId =scanner.nextInt();
					 scanner.hasNextLine();
					 if(carId <= implCar.getCarsList().size())
					 {
						implCar.getCarsList().get(carId).getOffers();
					 }else {
						 System.out.println("Please enter a valid number");
					 }
					userOption = 0;
				}else if(userOption == 3) {
					implCar.addCar();
					userOption =0;
				}else if(userOption ==4) {
					System.out.println("Which car would you like to view the delete?:");
					 int carId =scanner.nextInt();
					 scanner.hasNextLine();
					implCar.deleteCar(carId);
					userOption =0;
				}else if(userOption ==5) {
					
				}else if(userOption ==6) {
					
				}else {
					System.out.println("Please choose one of the 4 options...");
					userOption = 0;
				}
				
				}

		}else if(userOption == 3) {
			
			users.addUser();
			users.getUserList();
			userOption = 0;
		}else if(userOption == 4) {
			//exit system
			System.out.println("Thank you for visiting us today!");
		}else {
		System.out.println("Would you like to stay?(1:No, 2:Yes");
			userOption = scanner.nextInt();
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
