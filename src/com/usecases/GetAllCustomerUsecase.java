package com.usecases;

import java.util.List;

import com.bean.Customer;
import com.dao.CustomerDao;
import com.dao.CustomerDaoImpl;
import com.exception.CustomerException;

public class GetAllCustomerUsecase {

	
	public static void main(String[] args) {
		
		CustomerDao dao=new CustomerDaoImpl();
		
		try {
			List<Customer> customers= dao.getAllCustomerDatails();
		
			customers.forEach(c -> System.out.println(c));
		
		
			
		
		
		
		} catch (CustomerException e) {
		System.out.println(e.getMessage());
		}
		
	}
}
