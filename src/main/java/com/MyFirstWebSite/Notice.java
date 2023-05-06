package com.MyFirstWebSite;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class Notice
{

    DBConnection DBConn = new DBConnection();
    Function Fun = new Function();
    
 
    /**
     * 方法名：ListNotice
     * 功能描述：实现公告列表页。（查）
     * @param toPage:分页跳转页面地址，pageNum:每页显示记录数量
     * @return 数据库查询，公告列表字符串结果集
    */
    public String ListNotice(String toPage,String pageNum)
    {			
    	try {
			Connection Conn = DBConn.getConn();						     //获取数据库
			Statement stmt = Conn.createStatement();				     //创建数据库连接状态
			ResultSet rs = null;									     //创建结果集查询
			StringBuffer resultData = new StringBuffer();			//创建结果字符串集合
			String sSql = "select * from Notice order by NoticeId desc"; //创建数据库查询语句
			rs = stmt.executeQuery(sSql);			//执行数据库查询
			resultData=Fun.ListNotice(resultData,rs,toPage,pageNum);			//获取返回分页结果
			rs.close();												     //关闭结果集查询连接
			stmt.close();											     //关闭数据库连接
			Conn.close();											     //关闭数据库
			return resultData.toString();							     //给前台返回结果集
		} catch (Exception e) {
			return "No";											     //若获取数据库失败，返回“No”
		}
    	
    }
    
    

     public String AddNotice(String [] noticeData)
     {			
 		try
     	{
     		Connection Conn = DBConn.getConn();
 	    	Statement stmt = Conn.createStatement();
     		
 	    	ResultSet rs = null;
 	    	String sSql = "select * from Notice order by NoticeId desc";   // 取出记录的唯一主键值
 	    	rs = stmt.executeQuery(sSql);
 	    	String result=Fun.AddNotice(Conn,stmt,rs,noticeData);		   //获取查询结果
	    	return result;											       //返回result 结果
 	    	
 	    }catch(Exception e)
         {
             e.printStackTrace();
             return "服务器异常!";
         }
      }
     
     

	public boolean DelNotice(String id) {
		try {
			Connection Conn = DBConn.getConn();
			Statement stmt = Conn.createStatement();
			int ID = Fun.StrToInt(id);
			return Fun.DelNotice(Conn, stmt, ID);					 //删除数据并返回删除结果
		} catch (Exception e) {
			return false;
		}
	}
	
	
	/**
     * 方法名：EditNotice
     * 功能描述：修改单条新闻数据。（删）
     * @param id:新闻唯一主键;noticeData:修改数据
     * @return 返回修改后的状态。返回"yes"，说明修改成功，否则修改失败。
	 
    */
	public String EditNotice(String[] noticeData, String id) {
		try {
			Connection Conn = DBConn.getConn();
			Statement stmt = Conn.createStatement();
			return Fun.EditNotice(Conn, stmt, noticeData, id);      //修改操作，并且返回状态
		} catch (Exception e) {
			return "数据库连接失败!";
		}
	}
	
	/**
     * 方法名：ListNoticeFront
     * 功能描述：前台公告列表页面。
     * @param toPage:分页跳转页面地址，pageNum:每页显示记录数量
     * @return 数据库查询，公告列表字符串结果集
	 
    */
	public String ListNoticeFront(String toPage,String pageNum)
    {

		try
    	{
    		Connection Conn = DBConn.getConn();
			System.out.println("这是listnoticefront!!");
	    	Statement stmt = Conn.createStatement();
	    	ResultSet rs = null;
    		StringBuffer resultData = new StringBuffer();
    		String sSql = "select * from Notice order by NoticeId desc";
	    	rs = stmt.executeQuery(sSql);
			System.out.println("获得ＲＳ，即将进入function!!");
	    	resultData=Fun.ListNoticeFront(resultData,rs,toPage,pageNum);  		//获取返回分页结果
			System.out.println("返回listnoticefront，结束！！！");
			rs.close();
    		stmt.close();
    		Conn.close();
    		return resultData.toString();	
    	}catch(Exception e)
        {
            return "No";
        }
    }
	
	/**
     * 方法名：FrontNoticeDetail
     * 功能描述：前台公告详情页面。
     * @param id:文章记录唯一主键
     * @return 数据库查询，新闻公告字符串结果集
    */
	public String FrontNoticeDetail(String id) {
		try {
			Connection Conn = DBConn.getConn();
			Statement stmt = Conn.createStatement();
			ResultSet rs = null;
			int ID = Fun.StrToInt(id);
			if (ID == 0)
				return "No";
			else {
				try {
					String sql = "select * from Notice where NoticeId=" + ID;
					rs = stmt.executeQuery(sql);
					StringBuffer sb = new StringBuffer();
					sb=Fun.FrontNoticeDetail(sb, rs);
					rs.close();
					stmt.close();
					Conn.close();
					return sb.toString();
				} catch (Exception e) {
					Conn.rollback();
					Conn.close();
					return "No";
				}
			}
		} catch (Exception e) {
			return "No";
		}

	}
	
}
     