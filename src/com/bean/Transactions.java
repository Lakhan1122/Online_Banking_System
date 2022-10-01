package com.bean;

public class Transactions {
	private int accno;
	private int credit;
	private int debit;
	private int balance;
	
	

	
	public Transactions(int accno, int credit, int debit, int balance) {
		super();
		this.accno = accno;
		this.credit = credit;
		this.debit = debit;
		this.balance = balance;
	}
	@Override
	public String toString() {
		return "Transactions [accno=" + accno + ", credit=" + credit + ", debit=" + debit + ", balance=" + balance
				+ "]";
	}
	public int getAccno() {
		return accno;
	}
	public void setAccno(int accno) {
		this.accno = accno;
	}
	public int getCredit() {
		return credit;
	}
	public void setCredit(int credit) {
		this.credit = credit;
	}
	public int getDebit() {
		return debit;
	}
	public void setDebit(int debit) {
		this.debit = debit;
	}
	public int getBalance() {
		return balance;
	}
	public void setBalance(int balance) {
		this.balance = balance;
	}
	
	
	
	
	
	
	
	
	
	
	
	

}
