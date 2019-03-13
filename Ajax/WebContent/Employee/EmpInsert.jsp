<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	pageContext.setAttribute("basePath", basePath);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>员工注册</title>
<base href="<%=basePath%>">
</head>
<body>
	<form action="EmpServlet" method="post">
		<input type="hidden" name="method" value="insert" />
		<table width="400px" align="center">
			<tr>
				<th>姓名</th>
				<td><input type="text" name="ename" /></td>
			</tr>
			<tr>
				<th>年龄</th>
				<td><input type="number" name="eage" /></td>
			</tr>
			<tr>
				<th>性别</th>
				<td><input type="radio" name="esex" value="1" checked="checked" />男
					<input type="radio" name="esex" value="2" />女</td>
			</tr>
			<tr>
				<th>部门</th>
				<td><select name="did">
						<c:forEach items="${dlist }" var="dp">
							<option value="${dp.did }">${dp.dname }</option>
						</c:forEach>
				</select></td>
			</tr>
			<tr>
				<th>入职日期</th>
				<td><input type="date" name="ehiredate" /></td>
			</tr>
		</table>
		<input type="submit" value="添加员工" /> <input type="reset" value="重置" />

	</form>
</body>
</html>