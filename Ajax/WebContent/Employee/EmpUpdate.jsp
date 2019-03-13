<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>修改员工数据</title>
</head>
<body>
<form action="EmpServlet" method="post">
	<input type="hidden" name="method" value="update"/>
	<input type="hidden"  value="${emp.eid }"/>
	<table>
		<tr>
		<th>姓名</th>
		<td><input type="text" name="ename" value="${emp.ename }"/></td>
		</tr>
		<tr>
		<th>年龄</th>
		<td><input type="text" name="eage" value="${emp.eage }"/></td>
		</tr>
		<tr>
		<th>性别</th>
		<td>
		<input type="radio" name="esex" <c:if test="${emp.esex==1 }">checked="checked"</c:if> value="1"/>男
		<input type="radio" name="esex"<c:if test="${emp.esex==2 }">checked="checked"</c:if> value="2"/>女
		</td>
		</tr>
		<tr>
		<th>入职日期</th>
		<td><input type="date" name="ehiredate" value="${emp.ehireDate }"/></td>
		</tr>
		<tr>
		<th>部门</th>
		<td><select name="did">
						<c:forEach items="${dlist }" var="dp">
							<option value="${emp.did }">${dp.dname }</option>
						</c:forEach>
				</select></td>
		</tr>	
	</table>
</form>
</body>
</html>