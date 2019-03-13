<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1 align="center">员工名单</h1>
<table border="1" width="800px" cellspacing="0">
			<tr>
				<th>序号</th>
				<th>姓名</th>
				<th>年龄</th>
				<th>性别</th>
				<th>入职日期</th>
				<th>部门</th>
				<th>操作</th>
			</tr>
			<c:forEach items="${pb.list}" var="emp">
				<tr>
					<td>${emp.eid }</td>
					<td>${emp.ename }</td>
					<td>${emp.eage }</td>
					<td><c:if test="${emp.esex==1 }" var="sex">男</c:if> <c:if
							test="${!sex }">女</c:if></td>
					<td>${emp.ehireDate }</td>
					<td>${emp.dname }</td>
					<td><a href="EmpServlet?method=delete&id=${emp.eid }">删除</a> |
						<a href="EmpServlet?method=findemp&id=${emp.eid }">修改</a> | <a
						href="EmpServlet?method=look&id=${emp.eid }">详情</a></td>
				</tr>
			</c:forEach>
			<tr>
				<th colspan="7"><a href="EmpServlet?method=findDept">录入新员工</a></th>
			</tr>
		</table>
</body>
</html>