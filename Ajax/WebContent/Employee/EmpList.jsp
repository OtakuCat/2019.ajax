<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
	<%@ page import="com.dao.*,com.pojo.*,com.utils.*,java.util.*,java.text.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>员工名单</title>
<style type="text/css">
.cx {
	position: absolute;
	left: 36%;
}

.main {
	position: absolute;
	left: 18%;
	top: 60px;
}
</style>
<script type="text/javascript" src="js/jquery-1.4.2.js"></script>
<script type="text/javascript">
$(function(){
	$(".namecls").click(function(){
		var td=$(this);
		var eid=td.prop("title");
		if(td.children().length==0){
		var name=$(this).text();
		var input =$("<input type='text'/>");
		input.keyup(function(e){
			var event=e||window.event;
			if(event.keyCode==13){//回车
				var ename=input.val();
				//1.将文本框中的值发送到后台，更新表
				$.post("EmpAjaxServlet",{"method":"updateName","eid":eid,"ename":ename},function(data){
					//2.文本框中的值写到td中
					td.text(input.val());							
				});
			}
			if(event.keyCode==27){//esc
				td.text(name);
			}
		});
		input.val(name).height(td.height()).width(td.width());
		input.css("border-width","0px");
		td.text("");
		td.append(input);
		}
	});
	$(".dele").click(function(){
		var eid=$(this).prop("title");
		var tr=$(this).parents("tr");
		$.post("EmpAjaxServlet",{"method":"delete","eid":eid},function(data){
			if("deleteok"==data){
				tr.remove();
			}else{
			alert("删除失败！");	
			} 
		});
		});
});

</script>
<script type="text/javascript">
	function tj() {
		var did = document.getElementById("did").value;
		var ename = document.getElementById("ename").value;
		var num = document.getElementById("page").value;
		window.location.href = "EmpServlet?did=" + did + "&ename=" + ename
				+ "&page=" + num;
	}
	function gopage(num) {
		var did = document.getElementById("did").value;
		var ename = document.getElementById("ename").value;
		window.location.href = "EmpServlet?did=" + did + "&ename=" + ename
				+ "&page=" + num;
	}
</script>
</head>
<body>
	<div class="cx">
		查询条件：
		<form action="EmpServlet" method="post">
			<select name="did" id="did">
				<option value="-1">--请选择部门--</option>
				<c:forEach items="${dlist }" var="dp">
					<option value="${dp.did }"
						<c:if test="${dp.did==emp.did }">selected="selected"</c:if>>${dp.dname }</option>
				</c:forEach>
			</select> 关键字：<input type="text" name="inquiry" value="${emp.ename }" />
			<input type="submit" value="查询">
		</form>
	</div>
	<div class="main">
		<table border="1" width="800px" cellspacing="0">
			<tr>
				<th >序号</th>
				<th >姓名</th>
				<th >年龄</th>
				<th >性别</th>
				<th >入职日期</th>
				<th >部门</th>
				<th >操作</th>
			</tr>

			<c:forEach items="${pb.list}" var="emp">
				<tr>
					<td width="10%">${emp.eid }</td>
					<td width="10%" class="namecls">${emp.ename }</td>
					<td width="10%">${emp.eage }</td>
					<td width="10%"><c:if test="${emp.esex==1 }" var="sex">男</c:if> <c:if
							test="${!sex }">女</c:if></td>
					<td width="20%">${emp.ehireDate }</td>
					<td width="10%">${emp.dname }</td>
					<td width="30%"><a href="EmpServlet?method=delete&id=${emp.eid }">删除</a> |
					<a href="javascript:void(0)" class="dele" title="${emp.eid }">AJAX删除</a> |
						<a href="EmpServlet?method=findemp&id=${emp.eid }">修改</a> |
						 <a href="EmpServlet?method=look&id=${emp.eid }">详情</a>
					</td>
				</tr>
			</c:forEach>
			<tr>
				<th colspan="7"><a href="EmpServlet?method=findDept">录入新员工</a></th>
			</tr>
			<tr>
				<td colspan="7">当前${pb.pageNow }页/共${pb.pages },共${pb.rows }条记录

					<form action="EmpServlet" method="post">
						<a href="javascript:gopage(1)">首页</a> <a
							href="javascript:gopage(${pb.pageNow-1 })">上一页</a> <a
							href="javascript:gopage(${pb.pageNow+1 })">下一页</a> <a
							href="javascript:gopage(${pb.pages })">尾页</a> 
						<input type="hidden" id="did" name="did" />
						 <input type="hidden" id="ename" name="ename" />
						  <input type="number" id="page" name="page" style="width: 45px" />
						   <input type="button" value="go" onclick="tj()" />
					</form>
				</td>
			</tr>
		</table>
	</div>

</body>
</html>