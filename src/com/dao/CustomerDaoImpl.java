package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.bean.Customer;
import com.bean.Transactions;
import com.exception.AccountantException;
import com.exception.CustomerException;
import com.usecases.GetAllCustomerUsecase;
import com.usecases.RegisterCustomerUsecase1;
import com.utility.DataBUtil;
import com.welcome.Bank;

public class CustomerDaoImpl implements CustomerDao{

	@Override
	public String registerCustomer(String cname, String password,String email, int mobile_no, int balance) {
	
	String message = "Not Inserted...";
	
	
	
	
		try(Connection conn=DataBUtil.provideConnection()){
			
		PreparedStatement ps=conn.prepareStatement
				("insert into Customer (cname,password,email,mobile_no,balance) values(?,?,?,?,?)");
			
		
		ps.setString(1, cname);
		ps.setString(2, password);
		ps.setString(3, email);
		ps.setInt(4, mobile_no);
		ps.setInt(5, balance);
		
		
		int x=ps.executeUpdate();
		if(x>0) {
			message="Customer Registered Sucessfully..!";
		}
		}catch (SQLException e) {
			message=e.getMessage();
		}
		
		
		
		
		return message;
		
	}
	
	@Override
	public String registerCustomer2(Customer customer) {
	String message = "Not Inserted...";
	
	
	
	
	try(Connection conn=DataBUtil.provideConnection()){
		
	PreparedStatement ps=conn.prepareStatement
			("insert into Customer (cname,password,email,mobile_no,balance) values(?,?,?,?,?)");
		
	
	ps.setString(1, customer.getCname());
	ps.setString(2, customer.getPassword());
	ps.setString(3, customer.getEmail());
	ps.setInt(4, customer.getMobile_no());
	ps.setInt(5, customer.getBalance());
	
	
	int x=ps.executeUpdate();
	if(x>0) {
		message="Customer Registered Sucessfully..!";
	}
	}catch (SQLException e) {
		message=e.getMessage();
	}
	
	
	
	
	return message;
	
}

	@Override
	public Customer getCustomerByCAccNo(int caccno)throws CustomerException {
		
		Customer customer=null;
		
		
		try (Connection conn=DataBUtil.provideConnection()){
			
		PreparedStatement ps=	conn.prepareStatement("select * from customer where caccno=? ");
			
			ps.setInt(1, caccno);
			ResultSet rs=ps.executeQuery();
			
			if(rs.next()) {
				
				int a=rs.getInt("caccno");
				String n=rs.getString("cname");
				String p=rs.getString("password");
				String em=rs.getString("email");
				int m=rs.getInt("mobile_no");
				int b=rs.getInt("balance");
				
				customer =new Customer(a,n,p,em,m,b);
			}else 
				throw new CustomerException("Customer does not exist with Account No: " + caccno);
			
			
		}catch(SQLException e) {
			
			e.printStackTrace();
			
			throw new CustomerException(e.getMessage());
			
		}
		
	
		
		
		return customer;
	}

	@Override
	public Customer loginCustomer(String cname, String password) throws CustomerException {
		
		Customer customer =null;
		
		try(Connection conn=DataBUtil.provideConnection()){
			
		PreparedStatement ps =conn.prepareStatement("select * from customer where cname=? AND password=?");
		
		ps.setString(1, cname);
		ps.setString(2, password);
		
		ResultSet rs=ps.executeQuery();
		
		if(rs.next()) {
			
			int a=rs.getInt("caccno");
			String n=rs.getString("cname");
			String p=rs.getString("password");
			String em=rs.getString("email");
			int m=rs.getInt("mobile_no");
			int b=rs.getInt("balance");
			
			customer =new Customer(a,n,p,em,m,b);
		}else 
			throw new CustomerException("Invalid Username or password..! " );
		
		
		}catch(SQLException e) {
			
			throw new CustomerException(e.getMessage());
			
			
		}
		
		
		
		return customer;
		
	}

	@Override
	public void loginAccountant(String ACCUsername, String ACCPassword)throws AccountantException, CustomerException {
	
		String message = "Invalid username of password";

		try (Connection conn = DataBUtil.provideConnection()) {

			PreparedStatement ps = conn
					.prepareStatement("select * from accountant where username = ? AND password = ?");

			ps.setString(1, ACCUsername);
			ps.setString(2, ACCPassword);

			ResultSet rs = ps.executeQuery();

			while(rs.next()) {

				String u = rs.getString("username");
                  
				 while(true) {
					 System.out.println("=================================================");
				System.out.println("Accountant logged in Successfully\n ");
				System.out.println(" Welcome, " + u+"\n");
				System.out.println("1.Add new account for customer");
				System.out.println("2.Edit already available account");
				System.out.println("3.Remove account by using account number");
				System.out.println("4.View account details by account number");
//				System.out.println("5.View transaction history by account number");
				System.out.println("5.Deposit by using account number");
				System.out.println("6.Withdraw by using account number");
				System.out.println("7.View all accounts details");
				System.out.println("8.View all Transactions History");
				System.out.println("9.Logout");
				Scanner sc = new Scanner(System.in);
				int choice = sc.nextInt();
				
				
				switch (choice) {
				case 1:
					RegisterCustomerUsecase1.main1();
					break;
				case 2:
					System.out.println("Enter Account number to update details:");
					int caccno = sc.nextInt();
					try {
						updateCustomersDetails(caccno);
					} catch (CustomerException ce) {

						System.out.println(ce.getMessage());
					}
					break;
				case 3:
					System.out.println("Enter Account number to remove:");
					int caccno1 = sc.nextInt();
					removeCustomerAccount(caccno1);
					break;
					
				case 4:
					
					System.out.println("Enter Account number to view customer details");
					int caccno11 = sc.nextInt();
					try {
						Customer customer = getCustomerByCAccNo(caccno11);
						System.out.println(customer);
					} catch (CustomerException ce) {
						System.out.println(ce.getMessage());
					}
					break;
//				case 5:
//					System.out.println("Enter account number to check transaction history:");
//					String accountno  =sc.next();
//					checkTransactionHistory(accountno);
//					break;
					
				case 5:
					System.out.println("Enter account number:");
					int caccno12=sc.nextInt();
					System.out.println("Enter amount to deposit:");
					int amount = sc.nextInt();
					depositeMoney(amount, caccno12);
					break;
				case 6:
					System.out.println("Enter account number:");
					int caccno13=sc.nextInt();
					System.out.println("Enter amount to Withdraw:");
					int amount2 = sc.nextInt();
					withdrawMoney(amount2, caccno13);
					break;
				case 7:
					try {
						getAllCustomerDatails();
					} catch (CustomerException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					break;
				case 8:
					showAllTransactions();
					break;
					
				case 9:
					System.out.println("Logout Successfuly");
					System.out.println("============================================");
					Bank.main(null);
					break;
//					
				
				default:
					System.out.println("Invalid entry please choose valid options");
				}
				}
                   
                   
                   }
			 
//			else {
//
//				throw new AccountantException("Invalid username or password");
//
//			}
			

		} catch (SQLException se) {
			throw new AccountantException(se.getMessage());
		}
	}
	

	

	private void withdrawMoney(int amount2, int caccno13) throws CustomerException {
		try (Connection conn = DataBUtil.provideConnection()) {

			Customer customer = getCustomerByCAccNo(caccno13);

			int balance = customer.getBalance();

			if (balance > amount2 || balance == amount2) {
				balance = balance - amount2;
				customer.setBalance(balance);

				PreparedStatement ps = conn.prepareStatement("update customer set balance = ? where caccno=?");
				ps.setInt(1, balance);
				ps.setInt(2, caccno13);

				int x = ps.executeUpdate();
				if (x > 0) {
					System.out.println(amount2 + "Rs Withdrawal successfull");
				} else {
					System.out.println(amount2 + "Rs Withdrawal fail");
				}

				customer = getCustomerByCAccNo(caccno13);
				balance = customer.getBalance();

				PreparedStatement ps1 = conn
						.prepareStatement("insert into transaction(caccno,debit,balance) values(?,?,?)");

				ps1.setInt(1, caccno13);
				ps1.setInt(2, amount2);
				ps1.setInt(3, balance);

				ps1.executeUpdate();

			} else {
				System.out.println("No sufficient balance to withdraw");
				System.out.println("Your balance is :" + balance);
			}

		} catch (SQLException e) {
			System.out.println(e.getMessage());

		}
		
	}

	private void depositeMoney(int amount, int caccno12)throws CustomerException {
		try (Connection conn = DataBUtil.provideConnection()) {

			PreparedStatement ps = conn.prepareStatement("update customer set balance=balance+? where caccno=?");

			ps.setInt(1, amount);
			ps.setInt(2, caccno12);

			int x = ps.executeUpdate();
			if (x > 0) {
				System.out.println(amount + " Deposited succesfully");
			} else {
				System.out.println(amount + " Not deposited");
			}

			Customer customer = getCustomerByCAccNo(caccno12);
			int balance = customer.getBalance();

			PreparedStatement ps1 = conn
					.prepareStatement("insert into transaction(caccno,credit,balance) values(?,?,?)");

			ps1.setInt(1, caccno12);
			ps1.setInt(2, amount);
			ps1.setInt(3, balance);

			ps1.executeUpdate();//

		} catch (SQLException e) {
			System.out.println(e.getMessage());

		}

		
	}

	private void showAllTransactions() {
		
		List<Transactions> transactions = new ArrayList<>();

		try (Connection conn = DataBUtil.provideConnection()) {
			PreparedStatement ps = conn.prepareStatement("select * from transaction");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				int a = rs.getInt("caccno");
				int d = rs.getInt("debit");
				int c = rs.getInt("credit");
				int ab = rs.getInt("balance");

				Transactions t = new Transactions(a, d, c, ab);
				transactions.add(t);

			}

			if (transactions.size() == 0) {
				System.out.println("No transactions available");
			} else {
				transactions.forEach(t -> System.out.println(t));
			}

		} catch (SQLException se) {
			System.out.println(se.getMessage());
		}
		
		
		
	}

	private void checkTransactionHistory(int caccno) {
		
		List<Transactions> transactions = new ArrayList<>();

		try (Connection conn = DataBUtil.provideConnection()) {
			PreparedStatement ps = conn.prepareStatement("select * from transaction where caccno=?");
			ps.setInt(1, caccno);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				int a = rs.getInt("caccno");
				int d = rs.getInt("debit");
				int c = rs.getInt("credit");
				int bal = rs.getInt("balance");

				Transactions t = new Transactions(a, d, c, bal);

				transactions.add(t);
			}
			
			if(transactions.size()==0) {
				System.out.println("No any transaction done");
			}else {
				transactions.forEach(t->System.out.println(t));
			}
			

		} catch (Exception e) {
			// TODO: handle exception
		}
	
	}

	private void removeCustomerAccount(int caccno) {
		
		try (Connection conn = DataBUtil.provideConnection()) {

			PreparedStatement ps = conn.prepareStatement("delete from customer where caccno = ? ");
			ps.setInt(1, caccno);

			Scanner sc = new Scanner(System.in);

			int x = ps.executeUpdate();
			if (x > 0) {
				System.out.println("Account Deleted successfully");
			} else {
				System.out.println("Account not exists");
			}

		} catch (SQLException se) {
			System.out.println(se.getMessage());
		}
		
	}

	private void updateCustomersDetails(int caccno)throws CustomerException {
		Scanner sc = new Scanner(System.in);
		try (Connection conn = DataBUtil.provideConnection()) {

			PreparedStatement ps = conn.prepareStatement("select * from customer where caccno = ?");
			ps.setInt(1, caccno);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				String a = rs.getString("caccno");
				String u = rs.getString("cname");
				String p = rs.getString("password");
				String em=rs.getString("email");
				int m = rs.getInt("mobile_no");
				int b=rs.getInt("balance");

				System.out.println("Current Details :-");
				System.out.println("accno: " + a + "\nusername: " + u + "\npassword: " + p +"\nemail:"+ em + "\nmobile: " + m+"\nBalance:"+b);
				System.out.println("==========================");
				System.out.println("Enter new Username:");
				String cname = sc.next();
				System.out.println("Enter new password:");
				String password = sc.next();
				System.out.println("Enter new Email:");
				String email = sc.next();
				
				System.out.println("Enter new mobile:");
				int mobile_no = sc.nextInt();
				PreparedStatement ps1 = conn
						.prepareStatement("update customer set cname = ?,mobile_no=?,password=?,email=? where caccno=?");
				ps1.setString(1, cname);
				ps1.setInt(2, mobile_no);
				ps1.setString(3, password);
				ps1.setString(4, email);
				ps1.setInt(5, caccno);
				

				int x = ps1.executeUpdate();

				if (x > 0) {
					System.out.println("Account Details update successfully");
				} else {
					System.out.println("Account Details not updated");
				}

			} else {
				throw new CustomerException("Customer does not exists with acc no " + caccno);
			}

		} catch (SQLException e) {
			e.printStackTrace();

			throw new CustomerException(e.getMessage());
		}
		
	}

	@Override
	public void loginCustomer1(String cname, String password)throws Exception {
	
		String message = "Invalid username of password";

		try (Connection conn = DataBUtil.provideConnection()) {

			PreparedStatement ps = conn.prepareStatement("select * from customer where cname = ? AND password = ?");

			ps.setString(1, cname);
			ps.setString(2, password);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {

				String u = rs.getString("cname");
				int caccno = rs.getInt("caccno");
                   while(true) {
                 System.out.println("============================================");
				System.out.println("Welcome, " + u + "\n Select below Options:");
				System.out.println("1.Transfer money to another account");
				System.out.println("2.Check Transaction History");
				System.out.println("3.Logout");

				Scanner sc = new Scanner(System.in);
				int choice = sc.nextInt();

				switch (choice) {
				
				case 1:
					System.out.println("Enter Receiver's account Number:");
					int raccno = sc.nextInt();
					System.out.println("Enter Amount to transfer:");
					int amount = sc.nextInt();
					transferMoney(amount, caccno, raccno);
					break;
				case 2:
					checkTransactionHistory(caccno);
					break;
				case 3:
					System.out.println("Logout Successfully");
					System.out.println("============================================");
					Bank.main(null);
					break;

				default:
					System.out.println("Invalid entry please choose option 1 or 2");
				}

			} 
			}else {

				throw new CustomerException("Invalid username or password");

			}

		} catch (SQLException ce) {
			throw new CustomerException(ce.getMessage());
		}

		
	}

	private void transferMoney(int amount, int caccno, int raccno) throws CustomerException {
		try (Connection conn = DataBUtil.provideConnection()) {

			Customer rcustomer = getCustomerByCAccNo(raccno);
			Customer acustomer = getCustomerByCAccNo(caccno);

			int abalance = acustomer.getBalance();
			int rbalance = rcustomer.getBalance();

			if (abalance > amount || abalance == amount) {
				abalance = abalance - amount;
				rbalance = rbalance + amount;
				rcustomer.setBalance(rbalance);

				PreparedStatement aps = conn.prepareStatement("update customer set balance = ? where caccno=?");
				PreparedStatement rps = conn.prepareStatement("update customer set balance = ? where caccno=?");
				aps.setInt(1, abalance);
				aps.setInt(2, caccno);
				rps.setInt(1, rbalance);
				rps.setInt(2, raccno);

				int x = aps.executeUpdate();
				int y = rps.executeUpdate();
				if (x > 0 && y > 0) {
					System.out.println(amount + "Rs debited from your account");
					System.out.println("and credited to " + " " + raccno + " " + " account");
					System.out.println("Available Balance in your Account is " + abalance);
				} else {
					System.out.println(amount + "Rs Withdrawal fail");
				}

			} else {
				System.out.println("Insufficient balance");
				System.out.println("Your balance is :" + abalance);
			}

			Customer customer = getCustomerByCAccNo(caccno);
			abalance = customer.getBalance();

			PreparedStatement aps1 = conn
					.prepareStatement("insert into transaction(caccno,debit,balance) values(?,?,?)");

			aps1.setInt(1, caccno);
			aps1.setInt(2, amount);
			aps1.setInt(3, abalance);

			aps1.executeUpdate();

			customer = getCustomerByCAccNo(raccno);
			rbalance = customer.getBalance();
			PreparedStatement rps1 = conn
					.prepareStatement("insert into transaction(caccno,credit,balance) values(?,?,?)");

			rps1.setInt(1, raccno);
			rps1.setInt(2, amount);
			rps1.setInt(3, rbalance);

			rps1.executeUpdate();

		} catch (SQLException e) {
			System.out.println(e.getMessage());

		}
		
	}

	@Override
	public List<Customer> getAllCustomerDatails() throws CustomerException {
		
		List<Customer> customers = new ArrayList<>();
		
		try(Connection conn=DataBUtil.provideConnection()){
			
		PreparedStatement ps=	conn.prepareStatement("select * from customer");
			
		ResultSet rs=ps.executeQuery();
		
       while(rs.next()) {
			
			int a=rs.getInt("caccno");
			String n=rs.getString("cname");
			String p=rs.getString("password");
			String em=rs.getString("email");
			int m=rs.getInt("mobile_no");
			int b=rs.getInt("balance");
			
			Customer customer =new Customer(a,n,p,em,m,b);
		
			customers.add(customer);
       }	
			
		}catch (SQLException e) {
			
			throw new CustomerException(e.getMessage());
		}
		
		if(customers.size()==0) {
			throw new CustomerException("No Accounts Found..");
		}
		
		
		return customers;
	}
	
	
	
	
	

}
