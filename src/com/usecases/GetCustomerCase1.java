package com.usecases;

import java.util.Scanner;

import com.bean.Customer;
import com.dao.CustomerDao;
import com.dao.CustomerDaoImpl;
import com.exception.CustomerException;

public class GetCustomerCase1 {

	public static void main(String[] args) {
		
		Scanner sc=new Scanner(System.in);
		
		System.out.println("Enter account number: ");
		int caccno=sc.nextInt();
		
		
		CustomerDao dao= new CustomerDaoImpl();
		
         try {
			Customer customer=dao.getCustomerByCAccNo(caccno);
		    System.out.println(customer);
         
         } catch (CustomerException e) {
			
			System.out.println(e.getMessage());
			
			
		}
		
        
	}

}
