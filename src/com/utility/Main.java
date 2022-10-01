package com.utility;

import java.sql.Connection;

public class Main {

	public static void main(String[] args) {
		

		Connection conn=DataBUtil.provideConnection();
		
		System.out.println(conn);
	}

}
