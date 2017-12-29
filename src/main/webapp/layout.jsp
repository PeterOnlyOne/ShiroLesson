<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>layout.html</title>
	
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="this is my page">
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" type="text/css" href="themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="themes/icon.css">
	<script type="text/javascript" src="jquery.min.js"></script>
	<script type="text/javascript" src="jquery.easyui.min.js"></script>
	<style type="text/css">
		body{
			font-size: 15px;
			margin: 1px;
		}
		a{
			text-decoration: none;
		}
	</style>
	
	<script type="text/javascript">
		function urlClick(myTitle,myUrl){
			var ifExists=$("#tabs").tabs("exists",myTitle);
			if(!ifExists){
				$("#tabs").tabs("add",{
					title: myTitle,
					selected: true,
					closable: true,
					content: '<iframe width="100%" height="100%" frameborder=0 scrolling="no" src="'+myUrl+'"></iframe>'
				});
			}
			$("#tabs").tabs("select",myTitle);
		}
	</script>
  </head>
  
  <body>
    <div class="easyui-layout" style="width:100%;height:100%;">
		<div data-options="region:'north'" style="height:12%">
			<div>
				<img src="logo.jpg" height="90%" />
				<a href="#" style="padding-left: 90%">退出</a>
			</div>
		</div>
		<div data-options="region:'west'" title="导航菜单" style="width:15%;">
			<div class="easyui-accordion" style="width:100%;height:99%;">
				<div title="权限管理" style="padding: 10px">
					<c:forEach var="menu" items="${requestScope.menuList }">
						<p><a href="javascript:urlClick('${menu.menuName }','${pageContext.request.contextPath }${menu.menuUrl }')"><img src="themes/icons/man.png" />${menu.menuName }</a></p>
					</c:forEach>
				</div>
				<div title="系统设置" style="padding: 10px">
					<p><a href="#"><img src="themes/icons/help.png" />&nbsp;帮助</a></p>
				</div>
			</div>
		</div>
		<div data-options="region:'center',">
			<div id="tabs" class="easyui-tabs" style="width:100%;height:100%">
				<div title="欢迎使用" style="padding:10px"></div>
			</div>
		</div>
	</div>
  </body>
</html>
