package com.accountant;

public class Account {

	//Attributes
		private int accountId;
		private String accountName;
		private String nrcNo;

		//Constructor
		public Account(int accountId, String accountName, String nrcNo){
			this.accountId = accountId;
			this.accountName = accountName;
			this.nrcNo = nrcNo;
		}
		//Behaviors
		//Update accountName
		public void setAccountName(String accountName){
			this.accountName = accountName;
		}
		//Get accountName
		public String getAccountName(){
			return accountName;
		}
		//Update nrc no
		public void setNrcNo(String nrcNo){
			this.nrcNo = nrcNo;
		}
		//Get Nrcno
		public String getNrcNo(){
			return nrcNo;
		}
		//toString
		@Override
		public String toString(){
			return "AccountID: " + accountId +
			       ", AccountName: " + accountName +
			       ", NRC No:" + nrcNo;
		}
}
