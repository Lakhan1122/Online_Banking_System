package com.usecases;

import java.util.Scanner;

import com.dao.CustomerDao;
import com.dao.CustomerDaoImpl;

public class RegisterCustomerUsecase1 {
	


	
	public static void main1() {
		
		Scanner sc=new Scanner(System.in);
		
		System.out.println("Enter CustomerName:");
		String cname = sc.next();

		System.out.println("Enter password:");
		String password = sc.next();

		System.out.println("Enter email:");
		String email = sc.next();

		System.out.println("Enter mobile_no:");
		int mobile_no = sc.nextInt();

		System.out.println("Enter balance minimum amount Rs.1000:");
		int balance = sc.nextInt();

		
		
		CustomerDao dao = new CustomerDaoImpl();
		
		String result=dao.registerCustomer(cname,password,email,mobile_no,balance);
		
		System.out.println(result);
		
	}

	


	
	
}
