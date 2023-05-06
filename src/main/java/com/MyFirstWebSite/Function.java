package com.MyFirstWebSite;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Function {

	DBConnection DBConn = new DBConnection();
	/**
	 * 方法名：CheckLogin
	 * 功能描述：登录验证

	 */
	public boolean CheckLogin(Connection conn, String s1, String s2)
			throws SQLException {
		Statement stmt = conn.createStatement();
		ResultSet rs = null;
		boolean OK = true;
		String AdminPwd = "";
		String User = CheckReplace(s1);//
		String Pwd = CheckReplace(s2);
		String Sql = "select * from `Admin` where AdminName='" + User + "'";
		rs = stmt.executeQuery(Sql);
		if (!rs.next()) {
			OK = false;
		} else {
			AdminPwd = rs.getString("AdminPwd");
			OK = Pwd.equals(AdminPwd);
		}
		return OK;

	}

	/**
	 * 方法名：CheckReplace
	 * 功能描述：字符串过滤

	 */
	public String CheckReplace(String s) {
		try {
			if ((s == null) || (s.equals("")))
				return "";
			StringBuffer stringbuffer = new StringBuffer();
			for (int i = 0; i < s.length(); i++) {
				char c = s.charAt(i);
				switch (c) {
					case '"':
						stringbuffer.append("&quot;");
						break;
					case '\'':
						stringbuffer.append("&#039;");
						break;
					case '|':
						break;
					case '&':
						stringbuffer.append("&amp;");
						break;
					case '<':
						stringbuffer.append("&lt;");
						break;
					case '>':
						stringbuffer.append("&gt;");
						break;
					default:
						stringbuffer.append(c);
				}
			}

			return stringbuffer.toString().trim();
		} catch (Exception e) {
		}
		return "";
	}


	public String CheckDate(String[] s1, String[] s2) {
		boolean OK = true;
		StringBuffer sb = new StringBuffer();
		try {
			for (int i = 0; i < s1.length; i++) {
				if ((s1[i] == null) || (s1[i].equals(""))
						|| (s1[i].equals(" "))) {
					sb.append("<li> [ " + s2[i] + " ] 不能为空!");
					OK = false;
				}
			}
			if (OK)
				return "Yes";
			return sb.toString().trim();
		} catch (Exception e) {
		}
		return "操作失败！";
	}

	public String getStrCN(String s) {
		if (s == null)
			s = "";
		try {
			byte[] abyte0 = s.getBytes("utf-8");
			s = new String(abyte0);
		} catch (Exception e) {
			s = "";
		}
		return s;
	}

	public int StrToInt(String s) {
		try {
			return Integer.parseInt(CheckReplace(s));
		} catch (Exception e) {
		}
		return 0;
	}

	public boolean StringToBoolean(String s) {
		return (s != null) && (s.equals("Yes"));
	}

	public String Page(String sPage, ResultSet rs, int intPage, int intPageSize) {
		String s = null;
//１.　得到记录总条数　　２.　每页显示的记录个数　　３.　共有多少页　总记录个数/每页个数　+１　　４.　当前页
		int i = 0;
		try {
			rs.last();//rs.next();
			int intRowCount = rs.getRow();//记录总个数
			int intPageCount;
			if (intRowCount % intPageSize == 0)
				intPageCount = intRowCount / intPageSize;
			else
				intPageCount = (int) Math.floor(intRowCount / intPageSize) + 1;
			if (intPageCount == 0)
				intPageCount = 1;

			if (intPage < 1)
				intPage = 1;
			if (intPage > intPageCount)
				intPage = intPageCount;

			if (intRowCount > intPageSize) {
				s = "<table class=\"am-table am-table-striped\" width=\"90%\"  border=\"0\" align=\"center\" cellpadding=\"2\" cellspacing=\"0\"><tr>";
				s = s + "<td width=\"80%\" height=\"30\" class=\"chinese\"><span class=\"chinese\">";
				s = s + "当前第" + intPage + "页/共" + intPageCount+ "页,&nbsp;&nbsp;&nbsp;&nbsp;共" + intRowCount
						+ "条记录,&nbsp;&nbsp;&nbsp;&nbsp;" + intPageSize + "条/页";
//				当前第1页/共2页,    共4条记录,    3条/页         1   2
				int showye = intPageCount;


				if (showye > 10)
					showye = 10;
				for (i = 1; i <= showye; i++)
					;
				s = s + "</span></td>";
				s = s + "<td width=\"20%\">";
				s = s + "<table width=\"100%\" border=\"0\">";
				s = s + "<tr><td><div align=\"right\"><span class=\"chinese\">";
				s = s
						+ "<select id=\"ipage\" name=\"ipage\" class=\"chinese\" onChange=\"jumpMenu('self',this,0)\">";
				s = s + "<option value=\"\" selected>请选择</option>";

				for (i = 1; i <= intPageCount; i++) {
					String sSelect = i == intPage ? "SELECTED" : "";
					s = s + "<option value=\"" + sPage + "intPage=" + i + "\""
							+ sSelect + ">第" + i + "页</option>";
				}

				s = s + "</select></span></div>";
				s = s + "</td></tr></table>";
				return s + "</td></tr></table>";
			}

			return "";
		} catch (Exception e) {
		}
		return "分页出错!";
	}

	public String PageFront(String sPage, ResultSet rs, int intPage,
							int intPageSize) {
		String s = null;

		int i = 0;
		try {
			rs.last();

			int intRowCount = rs.getRow();
			int intPageCount;
			if (intRowCount % intPageSize == 0)
				intPageCount = intRowCount / intPageSize;
			else
				intPageCount = (int) Math.floor(intRowCount / intPageSize) + 1;
			if (intPageCount == 0)
				intPageCount = 1;

			if (intPage < 1)
				intPage = 1;
			if (intPage > intPageCount)
				intPage = intPageCount;

			if (intRowCount > intPageSize) {
				s = "<table  width=\"90%\"  border=\"0\" align=\"left\" cellpadding=\"2\" cellspacing=\"0\"><tr>";
				s = s
						+ "<td style=\"text-align:left\" width=\"80%\" height=\"30\" class=\"chinese\"><span class=\"chinese\">";
				s = s + "当前第" + intPage + "页/共" + intPageCount
						+ "页,&nbsp;&nbsp;&nbsp;&nbsp;共" + intRowCount
						+ "条记录,&nbsp;&nbsp;&nbsp;&nbsp;" + intPageSize
						+ "条/页&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";

				int showye = intPageCount;
				if (showye > 10)
					showye = 10;
				for (i = 1; i <= showye; i++) {
					if (i == intPage)
						s = s + " " + i + " ";
					else {
						s = s + "&nbsp; <a style=\"color:#3F862E\" href=\""
								+ sPage + "intPage=" + i + "\">" + i + "</a> ";
					}
				}
				s = s + "</span></td>";

				return s + "</tr></table>";
			}

			return "";
		} catch (Exception e) {
		}
		return "分页出错!";
	}

	public boolean AddLog(String[] s) {
		try {
			Connection Conn = this.DBConn.getConn();
			Statement stmt = Conn.createStatement(1004, 1007);

			for (int i = 0; i < s.length; i++) {
				s[i] = getStrCN(CheckReplace(s[i]));
			}
			String sql = "insert into Log (User,LogType,LogTime,IP,Result) values (";
			sql = sql + "'" + s[0] + "',";
			sql = sql + "'" + s[1] + "',";
			sql = sql + "'" + s[2] + "',";
			sql = sql + "'" + s[3] + "',";
			sql = sql + "'" + s[4] + "')";
			stmt.executeUpdate(sql);
			stmt.close();
			Conn.close();
			return true;
		} catch (SQLException e) {
		}
		return false;
	}

	public String OutError(String s) {
		try {
			StringBuffer sb = new StringBuffer();
			sb.append("<br><br><table width=\"60%\"  border=\"0\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\">\r\n");
			sb.append("<tr><td align=\"center\" valign=\"top\">\r\n");
			sb.append("<table width=\"90%\"  border=\"1\" align=\"center\" cellpadding=\"6\" cellspacing=\"1\">\r\n");
			sb.append("<tr class=\"chinese\" height=\"25\"><td height=\"27\" background=\"images/bg.gif\" class=\"info\">\r\n");
			sb.append("<div align=\"center\" class=\"title\">错误页面</div></td></tr>\r\n");
			sb.append("<tr class=\"chinese\" height=\"25\"><td><table cellspacing=\"4\" cellpadding=\"1\">\r\n");
			sb.append("<tr><td width=\"511\" height=\"80\" align=\"middle\" valign=\"top\">\r\n");
			sb.append("<p align=\"left\"><span class=\"info1\">操作出错：</span></p><div align=\"left\" class=\"info1\">");
			sb.append(s + "</div></td></tr></table></td></tr>\r\n");
			sb.append("<tr><td background=\"images/bg.gif\" height=\"20\" valign=\"middle\"><div align=\"center\" class=\"chinese\">\r\n");
			sb.append("<a href=\"#\" onClick=\"javascript:history.go(-1)\">返回</a></div></td></tr></table></td></tr></table><br><br>\r\n");
			return sb.toString();
		} catch (Exception e) {
		}
		return "操作出错!";
	}

	public String OutWarn(String s) {
		try {
			StringBuffer sb = new StringBuffer();
			sb.append("<br><br><form name=\"form1\" method=\"post\" action=\"\">\r\n");
			sb.append("<table border=\"1\" align=\"center\" cellpadding=\"1\" cellspacing=\"2\">\r\n");
			sb.append("<tr><td width=\"400\" height=\"80\" align=\"middle\" valign=\"top\">\r\n");
			sb.append("<div align=\"left\" class=\"info1\">系统警告：<br><br>\r\n");
			sb.append("&nbsp;&nbsp;&nbsp;&nbsp;");
			sb.append(s);
			sb.append("</div></td></tr>\r\n");
			sb.append("<tr><td height=\"20\" align=\"middle\" valign=\"top\"><div align=\"center\">\r\n");
			sb.append("<input name=\"Submit\" type=\"button\" class=\"button\" value=\"取消\" onClick=\"javascript:history.go(-1);\">&nbsp;&nbsp;\r\n");
			sb.append("<input name=\"OK\" type=\"hidden\" id=\"OK\" value=\"Yes\">\r\n");
			sb.append("<input name=\"Submit2\" type=\"submit\" class=\"button\" value=\"确定\">\r\n");
			sb.append("</div></td>\r\n");
			sb.append("</tr></table></form>\r\n");
			return sb.toString();
		} catch (Exception e) {
		}
		return "操作出错!";
	}

	/**************************** 新闻管理方法集合（开始） ***************************************/

	public StringBuffer ListNews(StringBuffer sb, ResultSet rs, String strPage,
								 String sPage) throws SQLException {

		int i;
		int intPage = 1;
		int intPageSize = 3;

		if (!rs.next()) {
			sb.append("<tr height=\"25\" bgcolor=\"#d6dff7\"  class=\"info1\"><td colspan=\"4\">\r\n");
			sb.append("<div align=\"center\"><b>没有记录！</b></div></td></tr>\r\n");
		} else {

			intPage = StrToInt(sPage);
			sPage = CheckReplace(strPage);
			if (intPage == 0)
				intPage = 1;

			rs.absolute((intPage - 1) * intPageSize + 1);
			i = 0;
			while (i < intPageSize && !rs.isAfterLast()) {
				int NewsID = rs.getInt("NewsID");
				String NewsTitle = rs.getString("NewsTitle");
				String NewsContent = rs.getString("NewsContent");
				String NewsTime = rs.getString("NewsTime");
				String AdminName = rs.getString("AdminName");

				sb.append("<tr>");
				sb.append("<td class=\"table-id\">" + NewsID + "</td>");

				sb.append("<td>" + NewsTitle + "</td>");
				sb.append("<td class=\"table-title\">" + AdminName + "</td>");
				sb.append("<td class=\"table-title\">" + NewsTime + "</td>");
				sb.append("<td><div class=\"am-btn-toolbar\">");
				sb.append("<div class=\"am-btn-group am-btn-group-xs\">");
				sb.append("<input type=\"hidden\" value=\"" + NewsID + "\">");
				sb.append("<input type=\"hidden\" value=\"" + NewsContent
						+ "\">");
				sb.append("<input type=\"hidden\" value=\"" + NewsTitle + "\">");
				sb.append("<a style=\"background:#2167A9\" onclick=\"edit(this);\"");
				sb.append("class=\"am-btn am-btn-primary am-btn-xs \"");
				sb.append("href=\"javascript:void(0);\"> <span></span>修改<a> ");
				sb.append("<a rel=\""
						+ NewsID
						+ "\" onclick=\"del(this);\" class=\"am-btn am-btn-warning am-btn-xs \" href=\"javascript:void(0);\"> "
						+ "<span></span>删除<a>");
				sb.append("</div></div></td></tr>");
				rs.next();
				i++;
			}
			sb.append(Page(sPage, rs, intPage, intPageSize));
		}
		return sb;
	}


	public String AddNews(Connection Conn, Statement stmt, ResultSet rs,
						  String[] s) throws SQLException {

		int z = 0;
		int newNum = 0;

		if (!rs.next()) {
			newNum = 1;
		} else {
			while (z < 1 && !rs.isAfterLast()) {
				int NewsID = rs.getInt("NewsID");
				newNum = NewsID + 1;
				break;
			}
		}

		for (int i = 0; i < s.length; i++) {
			if (i != 1)
				s[i] = getStrCN(CheckReplace(s[i]));
			else
				s[i] = getStrCN(s[i]);
		}

		SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		String newsTime = format1.format(new Date());

		StringBuffer sql = new StringBuffer();
		sql.append("insert into News (NewsID,NewsTitle,NewsContent,NewsTime,AdminName) values ("
				+ " '"
				+ newNum
				+ "',"
				+ " '"
				+ s[0]
				+ "',"
				+ " '"
				+ s[1]
				+ "'," + " '" + newsTime + "'," + " 'mr')");
		try {

			Conn.setAutoCommit(false);
			stmt.execute(sql.toString());
			Conn.commit();
			Conn.setAutoCommit(true);
			stmt.close();
			Conn.close();

			return "Yes";
		} catch (Exception e) {
			Conn.rollback();
			e.printStackTrace();
			Conn.close();
			return "添加成功!";
		}
	}


	public boolean DelNews(Connection Conn, Statement stmt, int NewsID)
			throws SQLException {
		if (NewsID == 0)
			return false;
		else {
			try {
				String sql = "delete from News where NewsID=" + NewsID;

				Conn.setAutoCommit(false);
				stmt.executeUpdate(sql);

				Conn.commit();
				Conn.setAutoCommit(true);

				stmt.close();
				Conn.close();
				return true;
			} catch (Exception e) {
				Conn.rollback();
				// e.printStackTrace();
				Conn.close();
				return false;
			}
		}
	}


	public String EditNews(Connection Conn, Statement stmt, String[] s,
						   String newsId) throws SQLException {
		for (int i = 0; i < s.length; i++) {
			s[i] = getStrCN(CheckReplace(s[i]));
		}

		int NewsID = StrToInt(newsId);

		StringBuffer sql = new StringBuffer();
		sql.append("update News set NewsTitle='" + s[0] + "'"
				+ " ,NewsContent='" + s[1] + "'" + " where NewsID='" + NewsID
				+ "'");

		stmt.executeUpdate(sql.toString());
		stmt.close();
		Conn.close();

		return "Yes";
	}


	public StringBuffer ListNewsFront(StringBuffer sb, ResultSet rs,
									  String toPage, String pageNum) throws SQLException {
		int i;
		int intPage = 1;
		int intPageSize = 5;
		if (!rs.next()) {
			sb.append("<tr height=\"25\" bgcolor=\"#d6dff7\"  class=\"info1\"><td colspan=\"5\">\r\n");
			sb.append("<div align=\"center\"><b>没有记录!</b></div></td></tr>\r\n");
		} else {

			intPage = StrToInt(pageNum);
			toPage = CheckReplace(toPage);
			if (intPage == 0)
				intPage = 1;

			rs.absolute((intPage - 1) * intPageSize + 1);
			System.out.println("intpage="+rs.absolute((intPage - 1) * intPageSize + 1));
			i = 0;
			while (i < intPageSize && !rs.isAfterLast()) {
				int NewsID = rs.getInt("NewsID");
				String NewsTitle = rs.getString("NewsTitle");
				String NewsTime = rs.getString("NewsTime");
				String AdminName = rs.getString("AdminName");

				sb.append("<tr>");
				sb.append("<td>" + NewsTitle + "</td>");
				sb.append("<td style=\"text-align:center\">" + AdminName + "</td>");
				sb.append("<td style=\"text-align:center\">" + NewsTime + "</td>");
				sb.append("<td style=\"text-align:center\"><a style=\"color:#3F862E\" target=\"_blank\" href=\"newsFrontDetail.jsp?newsId="
						+ NewsID + "\">详情</a></td></tr>");

				rs.next();
				i++;
			}
			sb.append(PageFront(toPage, rs, intPage, intPageSize));
		}
		return sb;
	}


	public StringBuffer FrontNewsDetail(StringBuffer sb, ResultSet rs)
			throws SQLException {
		int i = 0;
		while (i < 1 && !rs.isAfterLast()) {
			rs.next();
			String NewsTitle = rs.getString("NewsTitle");
			String NewsContent = rs.getString("NewsContent");

			String[] content = NewsContent.split("#");

			sb.append("<br><h2 style=\"font-size:28px;margin-left:30px\">"
					+ NewsTitle + "</h2>");

			for (int j = 0; j < content.length; j++) {
				sb.append("<p>" + content[j] + "</p>");
			}
			rs.next();
			i++;
		}
		return sb;
	}

	/**************************** 新闻管理方法集合（结束） ***************************************/

	/**************************** 公告管理方法集合（开始） ***************************************/


	public StringBuffer ListNotice(StringBuffer sb, ResultSet rs,
								   String toPage, String pageNum) throws SQLException {
		int i;
		int intPage = 1;
		int intPageSize = 5;
		System.out.print("4444444");
		if (!rs.next()) {
			sb.append("<tr height=\"25\" bgcolor=\"#d6dff7\"  class=\"info1\"><td colspan=\"4\">\r\n");
			sb.append("<div align=\"center\"><b>没有记录!</b></div></td></tr>\r\n");
		} else {

			intPage = StrToInt(pageNum);
			toPage = CheckReplace(toPage);
			if (intPage == 0)
				intPage = 1;

			rs.absolute((intPage - 1) * intPageSize + 1);
			i = 0;
			while (i < intPageSize && !rs.isAfterLast()) {
				int NoticeId = rs.getInt("NoticeId");
				String NoticeTitle = rs.getString("NoticeTitle");
				String NoticeContent = rs.getString("NoticeContent");
				String NewsTime = rs.getString("NoticeTime");
				String AdminName = rs.getString("AdminName");

				sb.append("<tr>");
				sb.append("<td class=\"table-id\">" + NoticeId + "</td>");
				sb.append("<td>" + NoticeTitle + "</td>");
				sb.append("<td class=\"table-title\">" + AdminName + "</td>");
				sb.append("<td class=\"table-title\">" + NewsTime + "</td>");
				sb.append("<td><div class=\"am-btn-toolbar\">");
				sb.append("<div class=\"am-btn-group am-btn-group-xs\">");
				sb.append("<input type=\"hidden\" value=\"" + NoticeId + "\">");
				sb.append("<input type=\"hidden\" value=\"" + NoticeContent
						+ "\">");
				sb.append("<input type=\"hidden\" value=\"" + NoticeTitle
						+ "\">");
				sb.append("<a style=\"background:#2167A9\" onclick=\"edit(this);\"");
				sb.append("class=\"am-btn am-btn-primary am-btn-xs \"");
				sb.append("href=\"javascript:void(0);\"> <span></span>修改<a> ");
				sb.append("<a rel=\""
						+ NoticeId
						+ "\" onclick=\"del(this);\" class=\"am-btn am-btn-warning am-btn-xs \" href=\"javascript:void(0);\"> "
						+ "<span></span>删除<a>");
				sb.append("</div></div></td></tr>");

				rs.next();
				i++;
			}
			sb.append(Page(toPage, rs, intPage, intPageSize));
		}
		return sb;
	}


	public String AddNotice(Connection Conn, Statement stmt, ResultSet rs,
							String[] s) throws SQLException {
		int z = 0;
		int newNum = 0;
		if (!rs.next()) {
			newNum = 1;
		} else {
			while (z < 1 && !rs.isAfterLast()) {
				int NewsID = rs.getInt("NoticeId");
				newNum = NewsID + 1;
				break;
			}
		}

		for (int i = 0; i < s.length; i++) {
			if (i != 1)
				s[i] = getStrCN(CheckReplace(s[i]));
			else
				s[i] = getStrCN(s[i]);
		}

		SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		String newsTime = format1.format(new Date());

		StringBuffer sql = new StringBuffer();
		sql.append("insert into Notice (NoticeId,NoticeTitle,NoticeContent,NoticeTime,AdminName) values ("
				+ " '"
				+ newNum
				+ "',"
				+ " '"
				+ s[0]
				+ "',"
				+ " '"
				+ s[1]
				+ "'," + " '" + newsTime + "'," + " 'mr')");

		System.out.println(sql);

		try {

			Conn.setAutoCommit(false);
			stmt.execute(sql.toString());
			Conn.commit();
			Conn.setAutoCommit(true);
			stmt.close();
			Conn.close();

			return "Yes";
		} catch (Exception e) {
			Conn.rollback();
			e.printStackTrace();
			Conn.close();
			return "新增成功";
		}
	}


	public boolean DelNotice(Connection Conn, Statement stmt, int NewsID)
			throws SQLException {
		if (NewsID == 0)
			return false;
		else {
			try {
				String sql = "delete from Notice where NoticeId=" + NewsID;

				Conn.setAutoCommit(false);
				stmt.executeUpdate(sql);

				Conn.commit();
				Conn.setAutoCommit(true);

				stmt.close();
				Conn.close();
				return true;
			} catch (Exception e) {
				Conn.rollback();
				Conn.close();
				return false;
			}
		}
	}


	public String EditNotice(Connection Conn, Statement stmt, String[] s,
							 String id) throws SQLException {
		for (int i = 0; i < s.length; i++) {
			s[i] = getStrCN(CheckReplace(s[i]));
		}

		int NewsID = StrToInt(id);

		StringBuffer sql = new StringBuffer();
		sql.append("update Notice set NoticeTitle='" + s[0] + "'"
				+ " ,NoticeContent='" + s[1] + "'" + " where NoticeId='"
				+ NewsID + "'");

		stmt.executeUpdate(sql.toString());
		stmt.close();
		Conn.close();

		return "Yes";
	}


	public StringBuffer ListNoticeFront(StringBuffer sb1, ResultSet rs,
										String toPage, String pageNum) throws SQLException {
		int i;
		int intPage = 1;
		int intPageSize = 3;
		System.out.println("进入function.listnoticefront!!");
		StringBuffer sb=new StringBuffer("utf-8");
		if (!rs.next()) {
			sb.append("<tr height=\"25\" bgcolor=\"#d6dff7\"  class=\"info1\"><td colspan=\"5\">\r\n");
			sb.append("<div align=\"center\"><b>没有记录!</b></div></td></tr>\r\n");
		} else {

			intPage = StrToInt(pageNum);
			System.out.println("intPage="+intPage);
			pageNum = CheckReplace(toPage);
			System.out.println("PageNum="+pageNum);
			if (intPage == 0)
				intPage = 1;

			rs.absolute((intPage - 1) * intPageSize + 1);
			i = 0;
			while (i < intPageSize && !rs.isAfterLast()) {
				int NoticeId = rs.getInt("NoticeId");
				String NoticeTitle = rs.getString("NoticeTitle");
				String NoticeTime = rs.getString("NoticeTime");
				String AdminName = rs.getString("AdminName");

				sb.append("<tr>");
				// sb.append("<td >"+NoticeId+"</td>");
				sb.append("<td>" + NoticeTitle + "</td>");
				sb.append("<td style=\"text-align:center\">" + AdminName + "</td>");
				sb.append("<td style=\"text-align:center\">" + NoticeTime + "</td>");
				sb.append("<td style=\"text-align:center\"><a style=\"color:#3F862E\" target=\"_blank\" href=\"noticeFrontDetail.jsp?newsId="
						+ NoticeId + "\">详情</a></td></tr>");
				System.out.println("获得记录！！");
				rs.next();
				i++;
			}
			sb.append(PageFront(toPage, rs, intPage, intPageSize));
		}
		return sb;
	}

	public StringBuffer FrontNoticeDetail(StringBuffer sb, ResultSet rs)
			throws SQLException {
		int i = 0;
		while (i < 1 && !rs.isAfterLast()) {
			rs.next();
			String NoticeTitle = rs.getString("NoticeTitle");
			String NoticeContent = rs.getString("NoticeContent");

			String[] content = NoticeContent.split("#");

			sb.append("<br><h2 style=\"font-size:28px;margin-left:30%\">"
					+ NoticeTitle + "</h2>");

			for (int j = 0; j < content.length; j++) {
				sb.append("<p>" + content[j] + "</p>");
			}
			rs.next();
			i++;
		}
		return sb;
	}

	/**************************** 公告管理方法集合（结束） ***************************************/

	/**************************** 用户信息管理**********************************************/



	public StringBuffer ListUser(StringBuffer sb, ResultSet rs, String toPage,
								 String pageNum) throws SQLException {
		int i;
		int intPage = 1;
		int intPageSize = 5;
		if (!rs.next()) {
			sb.append("<tr height=\"25\" bgcolor=\"#d6dff7\"  class=\"info1\"><td colspan=\"4\">\r\n");
			sb.append("<div align=\"center\"><b>没有用户!</b></div></td></tr>\r\n");
		} else {

			intPage = StrToInt(toPage);
			pageNum = CheckReplace(pageNum);
			if (intPage == 0)
				intPage = 1;

			rs.absolute((intPage - 1) * intPageSize + 1);
			i = 0;
			while (i < intPageSize && !rs.isAfterLast()) {
				int AdminID = rs.getInt("AdminID");
				String AdminName = rs.getString("AdminName");
				String LastLoginTime = rs.getString("LastLoginTime");
				sb.append("<tr>");
				sb.append("<td class=\"table-id\">" + AdminID + "</td>");

				sb.append("<td>" + AdminName + "</td>");
				sb.append("<td class=\"table-title\">" + LastLoginTime
						+ "</td>");
				sb.append("</tr>");

				rs.next();
				i++;
			}
			sb.append(Page(pageNum, rs, intPage, intPageSize));
		}
		return sb;
	}

}
