package com.usecases;

import java.util.Scanner;

import com.bean.Customer;
import com.dao.CustomerDao;
import com.dao.CustomerDaoImpl;
import com.exception.CustomerException;

public class LoginCustomer {
	
	
	
	public static void main(String[] args) {
		
		Scanner sc=new Scanner(System.in);
		
		System.out.println("Enter Username: ");
		String cname=sc.next();
		
		System.out.println("Enter Password: ");
		String password=sc.next();
		
		
		CustomerDao dao=new CustomerDaoImpl();
		
		try {
			Customer customer =dao.loginCustomer(cname, password);
			System.out.println(" Welcome "+customer.getCname());
		} catch (CustomerException e) {
			System.out.println(e.getMessage());
		}
		
		
	}

}
