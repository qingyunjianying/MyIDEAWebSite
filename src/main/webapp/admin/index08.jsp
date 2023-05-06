<%@ page import="com.MyFirstWebSite.*" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
	request.setCharacterEncoding("utf-8"); //注释:设置编码方式为utf-8
	String Action = request.getParameter("Action"); //注释:判断是否点击“登录”
	if (Action != null && Action.equals("Login")) {
		String User = request.getParameter("User"); //注释:得到登录用户名
		String Pwd = request.getParameter("Pwd"); //注释:得到登录密码
		out.println("<script>alert('" + User + "');</script>");
		out.println("<script>alert('" + Pwd + "');</script>");
		Login login = new Login(); //注释：新建登录类Login
		boolean isOK = login.LoginCheck(User, Pwd); //注释：调用方法LoginCheck，判断返回值是真还是假
		if (isOK) {
			//如果isOK=true，说明验证成功，可以进入后台页面news.jsp
			out.println("<SCRIPT LANGUAGE='JavaScript'>alert('登录成功！');location.href='adminnews.jsp';</SCRIPT>");
		} else {
			//如果isOK=false，说明验证失败，无法进入后台页面
			out.println("<SCRIPT LANGUAGE='JavaScript'>alert('登录失败！');location.href='index08.jsp';</SCRIPT>");
		}
	}
%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8" />
		<title></title>
		<link type="text/css" href="../front/css08/index.css" rel="stylesheet" />
	</head>
	<body>

		<div class="page">
			<header class="head"></header>
			<nav class="nav">
				<ul>
					<li><a href="http://www.helc.edu.cn/">返回学院首页</a></li>
					<li><a href="index08.jsp">本系首页</a></li>
					<li><a href="../front/html08/newsFrontList.jsp">消息与新闻</a></li>
					<li><a href="../front/html08/teacher.html">教师团队</a></li>
					<li><a href="../front/html08/edu.html">教育科研</a></li>
					<li><a href="../front/html08/question.html">问题解决</a></li>
				</ul>
			</nav>
			<div class="clear"></div>
			<div class="main">
				<aside class="left">
					<h2>吉祥物</h2>
					<div class="ph"></div>
					<h2>在线登录</h2>
					<form action="index08.jsp" method="post" onSubmit="return LoginCheck()">
						<p>
							今天是：2022-12-03
						</p>
						<p>
							<span>账号</span>
							<input type="text" name="User" value="admin" class="num"/>
						</p>
						<p>
							<span>密码</span>
							<input type="password" name="Pwd"  class="pass"/>
						</p>	
						<p>
							<input type="checkbox"  checked="checked"/>记住账号
						</p>
						<p>
							<input name="Action" type="hidden" value="Login">
							<input type="submit" class="btu1" value="登录" />
							<input type="reset" class="btu2" value="重置" />
						</p>
							
					</form>
					<div class="ph2"></div>
				</aside>
				<div class="right">
					<div class="banner">
						<a href="#" class="le" > < </a>
							<a href="#" class="ri" > > </a>
						
						<ul>
							<li><a href="#" ></a></li>
							<li><a href="#" ></a></li>
							<li><a href="#" ></a></li>
							<li><a href="#" ></a></li>
							<li><a href="#" ></a></li>
						</ul>
					</div>
					<div class="box">
						<h2>院系政策
						<a href="/front/html08/newsFrontList.jsp" class="more">
							更多
							<img src="../front/img08/preview.jpg" width="20px" height="20px" align="right"/>
							</a>
						</h2>
						<img src="../front/img08/01.jpg" width="145" height="145" align="left"/>
						<ul>
							<li><a href="#">河北政法职业学院省级三好学生、优秀学</a></li>
							<li><a href="#">河北政法职业学院学生奖励规定</a></li>
							<li><a href="#">河北政法职业学院大学生创新创业基金管</a></li>
							<li><a href="#">河北政法职业学院大学生创新创业奖学金</a></li>
							<li><a href="#">河北政法职业学院大学生创新创业学分认</a></li>
							
						</ul>
						<div class="clear"></div>
					</div>
					<div class="box">
						<h2>年度总结
						<a href="http://jsj.helc.edu.cn/ndzj.htm" class="more">
							更多
							<img src="../front/img08/preview.jpg" width="20px" height="20px" align="right"/>
							</a>
						</h2>
						<img src="../front/img08/02.jpg" width="145" height="145" align="left"/>
						<ul>
						<li><a href="#">计算机系2021年工作总结</a></li>
						<li><a href="#">计算机系2021年工作总结</a></li>
						<li><a href="#">计算机系2021年工作总结</a></li>
						<li><a href="#">计算机系2021年工作总结</a></li>
						<li><a href="#">计算机系2021年工作总结</a></li>
						
						</ul>
					</div>
					<div class="box">
						<h2>通知公告
							<a href="http://jsj.helc.edu.cn/tzgg.htm" class="more">
							更多
							<img src="../front/img08/preview.jpg" width="20px" height="20px" align="right"/>
							</a>
						</h2>
						
						<img src="../front/img08/03.jpg" width="145" height="145" align="left"/>
						
						<ul>
						<li><a href="#"> 新华区人社局再就业招聘</a></li>
						<li><a href="#"> 河北盛安网络科技有限公司招聘启示</a></li>
						<li><a href="#"> 2022届省级优秀毕业生推荐名单公示</a></li>
						<li><a href="#"> 人民大会堂选招工作人员</a></li>
						<li><a href="#">先天下商场烘焙店招聘</a></li>
						
						</ul>
					</div>
					<div class="box">
						<h2>下载专区
						<a href="http://jsj.helc.edu.cn/xzzq.htm" class="more">
							更多
							<img src="../front/img08/preview.jpg" width="20px" height="20px" align="right"/>
							</a>
						</h2>
						<img src="../front/img08/04.jpg" width="145" height="145" align="left"/>
						<ul>
						<li><a href="#">普通请假条</a></li>
						<li><a href="#">定州开元寺塔疫情防控请假条</a></li>
						<li><a href="#">毕业生办理离校手续清单</a></li>
						<li><a href="#">入驻孵化园申请表</a></li>
						<li><a href="#">听课记录表（新版）</a></li>
						
						</ul>

					</div>
				</div>
			</div>
			<div class="clear"></div>
			<footer class="footer">版权所有 河北政法职业学院&nbsp;计算机与数字法务系 21级软件技术4班贾慧颖</footer>
		</div>
	</body>
</html>
