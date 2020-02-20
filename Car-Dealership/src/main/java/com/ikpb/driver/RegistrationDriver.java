package com.ikpb.driver;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
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
			try {
				userOption = scanner.nextInt();
				}catch (InputMismatchException e) {
					logger.warn("user input was incorrect.");
				    System.err.println("Wrong input! Input only integer numbers please...");
				    scanner.nextLine();
				    logger.info("continue on to retry and enter the correct input");
				    continue;
				    
				}
			if(userOption == 1) {
				logger.debug("getting the first pull of the car list.");
				implCar.getCarsList();
				TimeUnit.MILLISECONDS.sleep(2000);
				userOption = 0;
			}else if(userOption == 2) {
				implCar.getCarsList();
				System.out.println("Which car would you like to Place an Offer on?(By Id):");
					int CarId;
				try{CarId = scanner.nextInt();}
				catch (InputMismatchException e) {
					logger.warn("user input was incorrect. while entering carId");
				    System.err.println("Wrong input! Input only integer numbers please...");
				    scanner.nextLine();
				    logger.info("continue on to retry and enter the correct input");
				    continue;
				    
				}
				scanner.nextLine();
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
				justBusiness.placeOffer(b, implCar.getCarById(CarId), implCar.getCarById(CarId).isCarAvaliable(), offerAmount);
				userOption = 0;
			}else if(userOption == 3) {
				if(users.getUser(b).getCarList().size()!=0) {
				System.out.println("which car do you want to see payments on?(By Id)");
				int carId;
				try{carId = scanner.nextInt();}
				catch (InputMismatchException e) {
					logger.warn("user input was incorrect. while entering carId in option 3");
				    System.err.println("Wrong input! Input only integer numbers please...");
				    scanner.nextLine();
				    logger.info("continue on to retry and enter the correct input");
				    continue;
				}
				scanner.nextLine();
				for(int k=0;k<b.getCarList().size();k++) {
				if(users.getUser(b).getCarList().get(k).getId()==carId) {}
				System.out.println(users.getUser(b).getCarList().get(carId).getRemainingBalance());
				}}else { System.out.println("I'm sorry, you do not own any cars.");
						System.out.println("Let's change that! Place an offer on one of the avaiable cars!");
						userOption =0;}
			}else if(userOption ==4) {
				logger.info("User left customer options.");
				users.saveUSer(users.getUserList());
				logger.info("Users saved");
				implCar.saveCarList(implCar.getCarsList());
				logger.info("Car list saved");
				userOption =0;
				break;
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
					b = loggingIn.LoggingIn(count, UserType.EMPLOYEE);
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
				userOption = 0;
				try{userOption = scanner.nextInt();}
				catch (InputMismatchException e) {
					logger.warn("user input was incorrect. while entering carId in option 1-6 for employee");
				    System.err.println("Wrong input! Input only integer numbers please...");
				    scanner.nextLine();
				    logger.info("continue on to retry and enter the correct input for employee options");
				    continue;
				}
			
				if(userOption == 1) {
					implCar.getCarsList();
					userOption = 0;
					TimeUnit.MILLISECONDS.sleep(2000);
				}else if(userOption == 2) {
					implCar.getCarsList();
					System.out.println("Which car would you like to view the offers on?(By Id):");
					int carId;
					try{carId = scanner.nextInt();}
					catch (InputMismatchException e) {
						logger.warn("user input was incorrect. while entering carId in option c");
					    System.err.println("Wrong input! Input only integer numbers please...");
					    scanner.nextLine();
					    logger.info("continue on to retry and enter the correct input");
					    continue;
					}
					 scanner.nextLine();
					 justBusiness.viewAllOffersOnCar(implCar.getCarById(carId));
					 System.out.println("Do you want to accept an offer? (Y/N):");
					 String usrInput = scanner.nextLine().toLowerCase();
					 if(usrInput.equals("y")) {
						 System.out.println("Which offer do you want to accept?(Input name)");
						 usrInput = scanner.nextLine().toLowerCase();
						 justBusiness.viewAllOffersOnCar(implCar.getCarById(carId));
						 justBusiness.acceptOffer(b, implCar.getCarById(carId));
						 
					 }
					userOption = 0;
				}else if(userOption == 3) {
					implCar.addCar();
					userOption =0;
				}else if(userOption ==4) {
					implCar.getCarsList();
					System.out.println("Which car would you like to view the delete?(By Id):");
					int carId = 0;
					try{carId = scanner.nextInt();}
					catch (InputMismatchException e) {
						logger.warn("user input was incorrect. while entering carId in option 3");
					    System.err.println("Wrong input! Input only integer numbers please...");
					    scanner.nextLine();
					    logger.info("continue on to retry and enter the correct input");
					    continue;
					}
					 scanner.hasNextLine();
					implCar.deleteCar(carId);
					userOption =0;
				}else if(userOption ==5) {
					for(int k=0; k<users.getUserList().size();k++) {
						for(int j=0; j<users.getUserList().get(k).getCarList().size();k++) {
							for(int l = 0;l<users.getUserList().get(k).getCarList().get(j).paymentsMade.size();l++) {
								for(int m =0;m<users.getUserList().get(k).getCarList().get(j).paymentsMade.get(l); l++) {
									System.out.println(users.getUserList().get(k).getFirstName() + " " + users.getUserList().get(k).getCarList().get(j).paymentsMade.get(l));
								}
							}
						}
					}userOption =0;
				}else if(userOption ==6) {
					logger.info("User left Employee options.");
					users.saveUSer(users.getUserList());
					logger.info("Users saved");
					implCar.saveCarList(implCar.getCarsList());
					logger.info("Car list saved");
					userOption =0;
					break;
				}else {
					System.out.println("Please choose one of the 4 options...");
					userOption = 0;
				}
				
				}

		}else if(userOption == 3) {
			
			users.addUser();
			users.saveUSer(users.getUserList());
			userOption = 0;
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
