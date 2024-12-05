<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="chillchip.admin.entity.*"%>

<% //見com.emp.controller.EmpServlet.java第238行存入req的empVO物件 (此為輸入格式有錯誤時的empVO物件)
 AdminVO adminVO = (AdminVO) request.getAttribute("adminVO");
%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>員工資料新增 - addEmp.jsp</title>

<style>
  table#table-1 {
	background-color: #CCCCFF;
    border: 2px solid black;
    text-align: center;
  }
  table#table-1 h4 {
    color: red;
    display: block;
    margin-bottom: 1px;
  }
  h4 {
    color: blue;
    display: inline;
  }
</style>

<style>
  table {
	width: 450px;
	background-color: white;
	margin-top: 1px;
	margin-bottom: 1px;
  }
  table, th, td {
    border: 0px solid #CCCCFF;
  }
  th, td {
    padding: 1px;
  }
</style>

</head>
<body bgcolor='white'>

<table id="table-1">
	<tr><td>
		 <h3>員工資料新增 - addAdmin.jsp</h3></td><td>
		 <h4><a href="select_page.jsp"><img src="images/tomcat.png" width="100" height="100" border="0">回首頁</a></h4>
	</td></tr>
</table>

<h3>資料新增:</h3>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<FORM METHOD="post" ACTION="admin.do" name="form1">
<table>
	
	
	
	
	<tr>
		<td>管理員姓名:</td>
		<td><input type="TEXT" name="adminname" value="<%= (adminVO==null)? "蔡宜庭" : adminVO.getAdminname()%>" size="45"/></td>
	</tr>
	<tr>
		<td>管理員暱稱:</td>
		<td><input type="TEXT" name="adminnickname"   value="<%=(adminVO==null)? "peko" : adminVO.getAdminnickname()%>" size="45"/></td>
	</tr>
	<tr>
		<td>管理員帳號:</td>
		<td><input type="TEXT" name="adminaccount"   value="<%=(adminVO==null)? "yes598" : adminVO.getAdminaccount()%>" size="45"/></td>
	</tr>
	<tr>
		<td>管理員密碼:</td>
		<td><input type="TEXT" name="adminpassword"   value="<%=(adminVO==null)? "no666" : adminVO.getAdminpassword()%> " size="45"/></td>
	</tr>
	<tr>
		<td>信箱:</td>
		<td><input type="TEXT" name="email" value="<%=(adminVO==null)? "david@gmail.com" : adminVO.getEmail()%> " size="45" ></td> 
	</tr>
	<tr>
		<td>電話:</td>
		<td><input type="TEXT" name="phone" value="<%=(adminVO==null)? "0968485312" : adminVO.getPhone()%>" size="45" ></td> 
	</tr>
	<tr>
		<td>狀態:</td>
		<td><input type="TEXT" name="status"  value="<%=(adminVO==null)? "0" : adminVO.getStatus()%>" size="45"/></td>
	</tr>

</table>
<br>
<input type="hidden" name="action" value="insert">
<input type="submit" value="送出新增"></FORM>

</body>




</html>