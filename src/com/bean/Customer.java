package com.bean;

public class Customer {
	
	private int caccno;
	private String cname;
	private String password;
	private String email;
	private int mobile_no;
	private int balance;
	
	
	
	public Customer() {
		
	}
	
	
	public Customer(int caccno, String cname, String password, String email, int mobile_no, int balance) {
		super();
		this.caccno = caccno;
		this.cname = cname;
		this.password = password;
		this.email = email;
		this.mobile_no = mobile_no;
		this.balance = balance;
	}
	public int getCaccno() {
		return caccno;
	}
	public void setCaccno(int caccno) {
		this.caccno = caccno;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getMobile_no() {
		return mobile_no;
	}
	public void setMobile_no(int mobile_no) {
		this.mobile_no = mobile_no;
	}
	public int getBalance() {
		return balance;
	}
	public void setBalance(int balance) {
		this.balance = balance;
	}


	@Override
	public String toString() {
		return "Customer [caccno=" + caccno + ", cname=" + cname + ", password=" + password + ", email=" + email
				+ ", mobile_no=" + mobile_no + ", balance=" + balance + "]";
	}
	
	
	
	

}
