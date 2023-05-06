package com.MyFirstWebSite;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * 文件名：User.java
 * 文件功能描述：后台管理员查看功能
 */
public class User
{

	DBConnection DBConn = new DBConnection();
	Function Fun = new Function();

	/**
	 * 方法名：ListUser
	 * 功能描述：后台管理员信息。（查）
	 * @param toPage:分页跳转页面地址，pageNum:每页显示记录数量
	 * @return 数据库查询，后台管理员信息查询
	 
	 */
	public String ListUser(String toPage, String pageNum) {
		try {
			Connection Conn = DBConn.getConn();
			Statement stmt = Conn.createStatement();
			ResultSet rs = null;
			StringBuffer resultData = new StringBuffer();
			String sSql = "select * from Admin order by AdminID desc";
			rs = stmt.executeQuery(sSql);
			resultData = Fun.ListUser(resultData, rs, toPage, pageNum);
			rs.close();
			stmt.close();
			Conn.close();
			return resultData.toString();
		} catch (Exception e) {
			return "No";
		}
	}

}