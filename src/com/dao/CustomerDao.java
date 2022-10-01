package com.dao;

import java.util.List;

import com.bean.Customer;
import com.exception.AccountantException;
import com.exception.CustomerException;

public interface CustomerDao {

	public String registerCustomer(String cname,String password,String email,int mobile_no,int balance);
	
	
	public String registerCustomer2(Customer customer);
	
	public Customer getCustomerByCAccNo(int caccno)throws CustomerException;
	
	public Customer loginCustomer(String cname, String password)throws CustomerException;


	public void loginAccountant(String ACCUsername, String ACCPassword) throws AccountantException, CustomerException;


	public void loginCustomer1(String cname, String cPassword) throws Exception;
	
	public List<Customer> getAllCustomerDatails()throws CustomerException;
	
}
