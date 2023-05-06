<%@ page import="com.MyFirstWebSite.*" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>sum</title>

		<link rel="stylesheet" href="css/amazeui.min.css" type="text/css"/>
		<link rel="stylesheet" href="/front/css08/index.css"  type="text/css"/>
		<link rel="stylesheet" href="/front/css08/news.css" type="text/css"/>
		<script src="js/main.js"></script>
		<script src="js/news.js"></script>
		<style type="text/css">
			.left .ph1{
						height: 152px;
						background: url("/front/img08/new.jpg") no-repeat ;
						margin: 8px;
					}
			.right .boxs{
				height: 610px;
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
			.boxs .bu{

				display: inline-block;
				background-color: #5F9EA0;
			}

		</style>
	</head>
	<body>
				<div class="page">
			<header class="head"></header>
			<nav class="nav">
				<ul>
					<li><a href="http://www.helc.edu.cn/">返回学院首页</a></li>
					<li><a href="index08.jsp">本系首页</a></li>
					<li><a href="adminnews.jsp">消息新闻管理</a></li>


				</ul>
			</nav>
			<div class="clear"></div>
			<div class="main">
				<aside class="left">
					<h2>吉祥物</h2>
					<div class="ph1"></div>
					<a href="news.html" class="mo">more <<</a>
				</aside>
				<div class="right">
					<div class="boxs">
						<h2>院系政策</h2>
						<h3>
							<p class="word"><strong>后台管理</strong>/ <strong>新闻管理</strong></p>
						</h3>
						<p class="pp">当前位置: 本系首页 >> 新闻动态</p>
						<hr  />
						<div class="am-g">
						<div class="am-u-sm-12 am-u-md-6">
							<div class="am-btn-toolbar">
								<div class="am-btn-group am-btn-group-xs">
									<button type="button" class="am-btn am-btn-warning"
											data-am-modal="{target: '#new-popup'}">
										<span class="am-icon-plus"></span> 添加新闻
									</button>
								</div>
							</div>
						</div>
						</div>
							<div class="am-g" style="height: 500px">
								<div class="am-u-sm-12">
									<form class="am-form" style="background-color: #5F9EA0">
										<table class="am-table am-table-striped am-table-hover table-main">
											<thead>
											<tr>
												<th class="table-id">序号</th>
												<th class="table-title">新闻标题</th>
												<th class="table-title">创建人</th>
												<th class="table-author">创建时间</th>
												<th class="table-author">操作</th>
											</tr>
											</thead>
											<tbody style="color: #0a1f3a">
											<%
												request.setCharacterEncoding("utf-8");
												News news = new News();
												String pageNum = request.getParameter("intPage");						   //注释：获取每页显示记录的数量
												String toPage = request.getContextPath() + request.getServletPath()+ "?";  //注释：获取跳转页面的地址
												String sOK = news.ListNews(toPage, pageNum);							   //注释：调用方法ListNews，获取后台返回的页面结果
												if (sOK.equals("No")) {
													out.println("数据服务器出现错误!");  //注释：返回“No”，说明返回失败
												} else {
													out.println(sOK);              //注释：表示成功，渲染出结果值
												}
											%>
											</tbody>
										</table>
									</form>
								</div>
							</div>
				</div>
			</div>
			<div class="clear"></div>
			<footer class="footer">版权所有 河北政法职业学院&nbsp;计算机与数字法务系 21级软件技术4班贾慧颖</footer>
		</div>
		</div>
		</div>
		</div>
				<!-- 注释：新增新闻 -->
				<div class="am-popup" id="new-popup">
					<div class="am-popup-inner">
						<div class="am-popup-hd">
							<h4 class="am-popup-title">
								添加新闻
							</h4>
							<span data-am-modal-close class="am-close">&times;</span>
						</div>

						<div class="am-popup-bd">
							<!-- 注释：提交新闻数据记录 -->
							<form action="newsAdd.jsp" method="post"
								  class="am-form" id="new-msg">
								<fieldset>
									<div class="am-form-group">
										<label for="doc-vld-ta-2-1">
											新闻标题：
										</label>
										<input name="NewsTitle" type="text" maxlength="32"
											   placeholder="请输入新闻标题" data-validation-message="不能为空" required />
									</div>
									<div class="am-form-group">
										<label for="doc-vld-ta-2-1">
											新闻内容：
										</label>
										<textarea name="NewsContent" cols="30" rows="10"
												  placeholder="请输入新闻内容。段落间请用#分隔。" data-validation-message="不能为空" required></textarea>
									</div>

									<input name="Action" type="hidden" value="Add">

									<button class="am-btn am-btn-secondary" type="submit">
										提交
									</button>
									<button onclick='$("#new-popup").modal("close");'
											class="am-btn am-btn-secondary" type="button">
										关闭
									</button>
								</fieldset>
							</form>
							<!-- 注释：提交新闻数据记录结束 -->
						</div>

					</div>
				</div>
				<!-- 注释：新增新闻结束 -->


				<!-- 注释：删除新闻开始 -->
				<div class="am-modal am-modal-confirm" tabindex="-1" id="my-confirm">
					<div class="am-modal-dialog">
						<div class="am-modal-bd">
							确定要删除当前新闻吗？
						</div>
						<div class="am-modal-footer">
							<span class="am-modal-btn" data-am-modal-cancel>取消</span>
							<span class="am-modal-btn" data-am-modal-confirm>确定</span>
						</div>
					</div>
				</div>
				<!-- 注释：删除新闻结束 -->

				<!-- 注释：修改新闻开始 -->
				<div class="am-popup" id="edit-popup">
					<div class="am-popup-inner">
						<div class="am-popup-hd">
							<h4 class="am-popup-title">
								修改新闻
							</h4>
							<span data-am-modal-close class="am-close">&times;</span>
						</div>

						<div class="am-popup-bd">
							<form action="newsEdit.jsp" method="post"
								  class="am-form" id="edit-msg">
								<fieldset>
									<div class="am-form-group">
										<label for="doc-vld-ta-2-1">
											新闻标题：
										</label>
										<input id="upd_NewsTitle" name="upd_NewsTitle" type="text" maxlength="32"
											   placeholder="请输入新闻标题" data-validation-message="不能为空" required />
									</div>
									<div class="am-form-group">
										<label for="doc-vld-ta-2-1">
											新闻内容：
										</label>
										<textarea id="upd_NewsContent" name="upd_NewsContent"  cols="30" rows="10"
												  placeholder="请输入新闻内容" data-validation-message="不能为空" required></textarea>
									</div>

									<input name="Action" type="hidden" value="Edit">
									<input id="newsId" name="newsId" type="hidden" value="">

									<button class="am-btn am-btn-secondary" type="submit">
										提交
									</button>
									<button onclick='$("#edit-popup").modal("close");'
											class="am-btn am-btn-secondary" type="button">
										关闭
									</button>
								</fieldset>
							</form>
						</div>
					</div>
				</div>
				<!-- 注释：修改新闻结束 -->
	</body>
</html>
