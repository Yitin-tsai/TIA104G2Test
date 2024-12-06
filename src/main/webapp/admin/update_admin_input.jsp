<%@page import="chillchip.admin.entity.AdminVO"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="chillchip.admin.*"%>

<% 
   AdminVO adminVO = (AdminVO)request.getAttribute("adminVO");
%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>���u��ƭק� - update_emp_input.jsp</title>

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
		 <h3>���u��ƭק� - update_emp_input.jsp</h3>
		 <h4><a href="select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">�^����</a></h4>
	</td></tr>
</table>

<h3>��ƭק�:</h3>

<%-- ���~��C --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">�Эץ��H�U���~:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<FORM METHOD="post" ACTION="admin.do" name="form1">
<table>
	<tr>
		<td>�޲z���s��:<font color=red><b>*</b></font></td>
		<td><%=adminVO.getAdminid()%></td>
	</tr>
	<tr>
		<td>�޲z���m�W:</td>
		<td><input type="TEXT" name="adminname" value="<%=adminVO.getAdminname()%>" size="45"/></td>
	</tr>
	<tr>
		<td>�޲z���ʺ�:</td>
		<td><input type="TEXT" name="adminnickname"   value="<%=adminVO.getAdminnickname()%>" size="45"/></td>
	</tr>
	<tr>
		<td>�޲z���b��:</td>
		<td><input type="TEXT" name="adminaccount"   value="<%=adminVO.getAdminaccount()%>" size="45"/></td>
	</tr>
	<tr>
		<td>�޲z���K�X:</td>
		<td><input type="TEXT" name="adminpassword"  value="<%=adminVO.getAdminpassword()%>" size="45"/></td>
	</tr>
	<tr>
		<td>�H�c:</td>
		<td><input type="TEXT" name="email" value="<%=adminVO.getEmail()%>" size="45" ></td> 
	</tr>
	<tr>
		<td>�q��:</td>
		<td><input type="TEXT" name="phone" value="<%=adminVO.getPhone()%>" size="45" ></td> 
	</tr>
	<tr>
		<td>���A:</td>
		<td><input type="TEXT" name="status"  value="<%=adminVO.getStatus()%>" size="45"/></td>
	</tr>

	<jsp:useBean id="adminSvc" scope="page" class="chillchip.admin.service.AdminServiceImpl" />
	

</table>
<br>
<input type="hidden" name="action" value="update">
<input type="hidden" name="adminid" value="<%=adminVO.getAdminid()%>">
<input type="submit" value="�e�X�ק�"></FORM>
</body>



        

</html>