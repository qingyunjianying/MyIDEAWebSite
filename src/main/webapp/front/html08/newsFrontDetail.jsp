<%@ page import="com.MyFirstWebSite.*" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title></title>
    <link rel="stylesheet" href="../css08/index.css"  type="text/css"/>
    <link rel="stylesheet" href="../css08/news.css" type="text/css"/>

    <style type="text/css">
        .right .boxs{
            height:610px;
        }
        .right .artical{
            padding-top: 10px;
            padding-left: 8px;
            line-height: 2em;
            text-indent: 2em;
            margin: 5px;
            font-size: 14px;
            font-family: "新宋体";
        }
        em{font-size: 16px;font-family: "黑体";}
        .word{
            padding-left: 20px;
        }
        .content_main{

            height: 480px;
            overflow-x:hidden ;
            overflow-y: auto;
        }

    </style>
</head>
<body>
<div class="page">
    <header class="head"></header>
    <nav class="nav">
        <ul>
            <li><a href="http://www.helc.edu.cn/">返回学院首页</a></li>
            <li><a href="../../admin/index08.jsp">本系首页</a></li>
            <li><a href="newsFrontList.jsp">消息与新闻</a></li>
            <li><a href="teacher.html">教师团队</a></li>
            <li><a href="edu.html">教育科研</a></li>
            <li><a href="question.html">问题解决</a></li>
        </ul>
    </nav>
    <div class="clear"></div>
    <div class="main">
        <aside class="left">
            <h2>吉祥物</h2>
            <div class="ph1"></div>
            <a href="newsFrontList.jsp" class="mo">more <<</a>
        </aside>
        <div class="right">
            <div class="boxs">
                <h2>院系政策</h2>
                <h3>
                    <p class="img"><img src="../img08/preview.jpg" width="20" height="20" /></p>
                    <p class="word">最新消息</p>
                </h3>
                <p class="pp">当前位置: 本系首页 >> 新闻动态</p>
                <hr  />
                <div style="font-size: 14px; line-height: 36px;">
                    <div id="tab_con">
                        <div id="tab_con_2"  >
                            <div class="content_main" >
                                <%
                                    request.setCharacterEncoding("utf-8");
                                    News news = new News();
                                    String newsId = request.getParameter("newsId");
                                    String sPage = request.getContextPath() + request.getServletPath()+ "?";
                                    String sOK = news.FrontNewsDetail(newsId);
                                    if (sOK.equals("No")) {
                                        out.println("数据服务器出现错误！");
                                    } else {
                                        out.println(sOK);
                                    }
                                %>
                            </div>
                        </div>

                    </div>
                </div>
            </div>
        </div>
        <div class="clear"></div>
        <footer class="footer">版权所有 河北政法职业学院&nbsp;计算机与数字法务系 21级软件技术4班贾慧颖</footer>
    </div>
</body>
</html>
