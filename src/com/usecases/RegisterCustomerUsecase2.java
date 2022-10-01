package com.usecases;

import java.util.Scanner;

import com.bean.Customer;
import com.dao.CustomerDao;
import com.dao.CustomerDaoImpl;

public class RegisterCustomerUsecase2 {

	
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
		
		Customer customer = new Customer();
		   customer.setCname(cname);
		   customer.setPassword(password);
		   customer.setEmail(email);
		   customer.setMobile_no(mobile_no);
		   customer.setBalance(balance);
		   
		
		String result=dao.registerCustomer2(customer);
		
		System.out.println(result);
		
	}



	
	
}
