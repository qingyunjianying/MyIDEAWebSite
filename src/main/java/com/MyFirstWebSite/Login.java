package com.MyFirstWebSite;

import java.sql.Connection;
import java.sql.SQLException;

public class Login {

	DBConnection DBConn = new DBConnection();
	Function Fun = new Function();

	public Login() {

	}
	public boolean LoginCheck(String s1, String s2) {
		try {
			Connection Conn = DBConn.getConn();
			boolean OK = true;
			OK = Fun.CheckLogin(Conn, s1, s2);
			return OK;

		} catch (SQLException e) {
			return false;
		}
	}
}