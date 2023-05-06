package com.MyFirstWebSite;

import java.sql.Connection;			//引入Java包（数据库连接用）
import java.sql.ResultSet;			//引入Java包（数据库连接用）
import java.sql.Statement;  		//引入Java包（数据库连接用）
/**
 * 文件名：News.java                                                                                                                                                                    * 文件功能描述：新闻管理模块的增删改查功能操作
*/
public class News {

	DBConnection DBConn = new DBConnection();                             //引入数据库连接
	Function Fun = new Function();									      //引入功能命令。如验证密码、页面渲染等。

	/**
     * 方法名：ListNews
     * 功能描述：实现新闻列表页。（查）
     * @param toPage:分页跳转页面地址，pageNum:每页显示记录数量
     * @return 数据库查询，新闻列表字符串结果集
	 
    */
	public String ListNews(String toPage, String pageNum) {
		try {
			Connection Conn = DBConn.getConn();						//获取数据库
			Statement stmt = Conn.createStatement();				//创建数据库连接状态
			ResultSet rs = null;									//创建结果集查询
			StringBuffer resultData = new StringBuffer();			//创建结果字符串集合
			String sSql = "select * from News order by NewsID desc";//创建数据库查询语句
			rs = stmt.executeQuery(sSql);							//执行数据库查询
			resultData=Fun.ListNews(resultData,rs,toPage,pageNum);  //获取返回分页结果
			rs.close();												//关闭结果集查询连接
			stmt.close();											//关闭数据库连接
			Conn.close();
		    return resultData.toString();							//给前台返回结果集
		} catch (Exception e) {
			return "No";											//若获取数据库失败，返回“No”
		}
	}
	
	
	
	/**
     * 方法名：AddNews
     * 功能描述：新增新闻数据记录。（增）
     * @param newsData:需要提交的新闻数据
     * @return 返回新增后的状态。返回“yes”，说明新增成功，否则新增失败。
	 
    */
	public String AddNews(String [] newsData)
    {			
		try
    	{
    		Connection Conn = DBConn.getConn();						  //获取数据库
	    	Statement stmt = Conn.createStatement();				  //创建数据库连接状态
	    	ResultSet rs = null;									  //创建结果集查询	
	    	String sSql = "select * from News order by NewsID desc";  //创建数据库查询语句
	    	rs = stmt.executeQuery(sSql); 							  //执行数据库查询
	    	String result=Fun.AddNews(Conn,stmt,rs,newsData);		  //获取查询结果
	    	return result;											  //返回result 结果
	    }catch(Exception e){
            return "添加失败";											  //若获取数据库失败，返回“添加失败”
        }
     }
	
	
	
	/**
     * 方法名：DelNews
     * 功能描述：删除单条新闻数据。（删）
     * @param newsId:新闻唯一主键
     * @return 返回删除后的状态。返回true，说明删除成功，否则删除失败。
	 
    */
	public boolean DelNews(String newsId)
    {	
    	try{
	    	Connection Conn = DBConn.getConn();				//获取数据库
	    	Statement stmt = Conn.createStatement();		//创建数据库连接状态
	    	int NewsID = Fun.StrToInt(newsId);				//将字符串newsId转化为数字类型NewsID
	    	return Fun.DelNews(Conn,stmt,NewsID);			//删除数据并返回删除结果
    	}catch(Exception e){
    		return false;
    	}
	}
	
	
	/**
     * 方法名：EditNews
     * 功能描述：修改单条新闻数据。（改）
     * @param newsId:新闻唯一主键;newsData:修改数据
     * @return 返回修改后的状态。返回"yes"，说明修改成功，否则修改失败。
	 
    */
	public String EditNews(String[] newsData, String newsId) {
		try {
			Connection Conn = DBConn.getConn();				//获取数据库
			Statement stmt = Conn.createStatement();		//创建数据库连接状态
			return Fun.EditNews(Conn,stmt,newsData,newsId); //修改操作，并且返回状态
		} catch (Exception e) {
			return "数据库连接失败!";
		}
	}
	
	
	/**
     * 方法名：ListNewsFront
     * 功能描述：前台新闻列表页面。
     * @param toPage:分页跳转页面地址，pageNum:每页显示记录数量
     * @return 数据库查询，新闻列表字符串结果集
	 
    */
	public String ListNewsFront(String toPage,String pageNum)
    {			
		try
    	{
    		Connection Conn = DBConn.getConn();
	    	Statement stmt = Conn.createStatement();
	    	ResultSet rs = null;
    		StringBuffer resultData = new StringBuffer();
    		String sSql = "select * from News order by NewsID desc";
	    	rs = stmt.executeQuery(sSql);
	    	resultData=Fun.ListNewsFront(resultData,rs,toPage,pageNum);  		//获取返回分页结果
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
     * 方法名：FrontNewsDetail
     * 功能描述：前台新闻详情页面。
     * @param id:文章记录唯一主键
     * @return 数据库查询，新闻详情字符串结果集
	 
    */
	public String FrontNewsDetail(String id) {
		try {
			Connection Conn = DBConn.getConn();
			Statement stmt = Conn.createStatement();
			ResultSet rs = null;
			int NewsID = Fun.StrToInt(id);
			if (NewsID == 0)
				return "No";
			else {
				try {
					String sql = "select * from News where NewsID=" + NewsID;
					rs = stmt.executeQuery(sql);
					StringBuffer sb = new StringBuffer();
					sb=Fun.FrontNewsDetail(sb, rs);
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
