<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ page import="com.MyFirstWebSite.*" %>
<%
	request.setCharacterEncoding("utf-8"); //注释:设置编码方式为utf-8
%>
<%
	News news = new News();
	String NewsID = request.getParameter("NewsID");    				//获取新闻记录唯一主键
	if (news.DelNews(NewsID))										//将数据提交给后台，获取返回值
		out.print("<script>alert('删除新闻成功!');location.href='adminnews.jsp';</script>");
	else {
		out.print("<script>alert('删除新闻失败!');location.href='adminnews.jsp';</script>");
	}
%>
