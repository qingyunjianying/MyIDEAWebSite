package com.MyFirstWebSite;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;


public class DBConnection					
{

    private String FileName;			
    private int DBType;					
    private Connection conn;			
    private String MySqlDriver;			
    private String MySqlURL; 			
        
   
    public DBConnection()
    {
    	conn = null;
    }

	public  Connection getConn()
	{

		//DBType= new Function().StrToInt(getPara("DBType"));
		DBType=1;

		switch(DBType)
		{
			case 1:return(getConnToMySql());
			default:return null;
		}	
	}
	
	
	public String getPara(String ParaName) 
	{
		FileName="../DBConfig.property";
		Properties prop= new Properties();
		try
		{
			InputStream is=getClass().getResourceAsStream(FileName);
			prop.load(is);
			if(is!=null) is.close();
		}
		catch(Exception e) {
			return "Error!";
		}
		return prop.getProperty(ParaName);
	}
	
	
    public Connection getConnToMySql()
    {

		try{
			System.out.println("进入dbconnection!!");
			MySqlDriver = "com.mysql.cj.jdbc.Driver";
			MySqlURL = "jdbc:mysql://localhost:3306/myfirstwebsite?user=root&password=0143&serverTimezone=Asia/Shanghai&characterEncoding=utf-8";
			Class.forName(MySqlDriver).newInstance();
			conn = DriverManager.getConnection(MySqlURL);
			System.out.println("链接数据库成功！！");
		}catch(Exception e){
		}
	    return conn;
    }       
    
}
