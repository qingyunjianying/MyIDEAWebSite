<%@ page contentType="text/html; charset=utf-8" language="java" %>
<%@ page import="com.MyFirstWebSite.*" %>
<%
	request.setCharacterEncoding("utf-8");
	News news = new News();
	String Action = request.getParameter("Action");
	
	if (Action!=null && Action.equals("Add"))
	{
		String [] s = new String[2];                 //创建字符串数组
		s[0] = request.getParameter("NewsTitle");	 //获取新闻标题	
		s[1] = request.getParameter("NewsContent");  //获取新闻内容
		String result = news.AddNews(s);			 //将新闻记录数据提交给后台
		if (result.equals("Yes"))				     //根据返回的结果判断页面走向
		{
			out.print("<script>alert('添加新闻成功!');location.href='adminnews.jsp';</script>");
			return;
		}
		else 
		{
			out.print("<script>alert('添加新闻失败!');location.href='adminnews.jsp';</script>");
			return;
		}
	}
%>