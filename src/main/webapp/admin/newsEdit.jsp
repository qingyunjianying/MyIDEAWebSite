<%@ page contentType="text/html; charset=utf-8" language="java" %>
<%@ page import="com.MyFirstWebSite.*" %>
<%
	request.setCharacterEncoding("utf-8");	 			//设置编码方式为utf-8
	News News1 = new News();
	String NewsID = request.getParameter("newsId");
	String Action = request.getParameter("Action");
	if (Action!=null && Action.equals("Edit"))
	{
		String [] s = new String[2];
		s[0] = request.getParameter("upd_NewsTitle");
		s[1] = request.getParameter("upd_NewsContent");
		
		String sOK = News1.EditNews(s,NewsID);
		if (sOK.equals("Yes")){
			out.println("<script>alert('修改新闻成功!');location.href='adminnews.jsp';</script>");
			return;
		}
		else {
			out.println("<script>alert('修改新闻失败!');location.href='adminnews.jsp';</script>");
			return;
		}
	}
%>
