package com.welcome;

import java.util.Scanner;

import com.dao.CustomerDao;
import com.dao.CustomerDaoImpl;
import com.exception.AccountantException;
import com.exception.CustomerException;
import com.usecases.RegisterCustomerUsecase2;

public class Bank {
	
	public static void main(String[] args) {

		
		Scanner sc = new Scanner(System.in);
		
		while(true) {
		System.out.println("*****Welcome to Online Banking System*********\n");
		System.out.println("Choose the following Option:");
		System.out.println("1.Login as a Accountant");
		System.out.println("2.Login as a Customer");
		System.out.println("3.New Customer? Register..!");
      
		try {
			int choice = sc.nextInt();
			switch (choice) {
			case 1:
				System.out.println("=======================================");
				System.out.println("Login as a Accountant");
				System.out.println("Enter username:");
				String ACCUsername = sc.next();
				System.out.println("Enter password:");
				String AccPassword = sc.next();

				CustomerDao dao = new CustomerDaoImpl();
				try {
					dao.loginAccountant(ACCUsername, AccPassword);

				} catch (AccountantException ae) {
					System.out.println(ae.getMessage());
				}

				break;
			case 2:
				System.out.println("=======================================");
				System.out.println("Login as a customer");
				System.out.println("Enter username:");
				String cname = sc.next();
				System.out.println("Enter password:");
				String Password = sc.next();

				CustomerDao dao1 = new CustomerDaoImpl();
				try {

					dao1.loginCustomer1(cname, Password);

				} catch (CustomerException ce) {
					System.out.println(ce.getMessage());
				}
				break;
			case 3:
				RegisterCustomerUsecase2.main1();
				
				break;
			default:
				System.out.println("Invalid Entry.Please Choice Given Options");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		}
		
	}
	

}
