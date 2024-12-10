<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>chillchip admin:HOME</title>

<style>
  table#table-1 {
	width: 450px;
	background-color: #CCCCFF;
	margin-top: 5px;
	margin-bottom: 10px;
    border: 3px ridge Gray;
    height: 80px;
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

</head>
<body bgcolor='white'>

<table id="table-1">
   <tr><td><h3>chillchip Admin: Home</h3><h4>( MVC )</h4></td></tr>
</table>

<p>This is the admin page for chillchip: admin</p>

<h3>��Ƭd��:</h3>
	
<%-- ���~��C --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">�Эץ��H�U���~:</font>
	<ul>
	    <c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<ul>
  <li><a href='listAllAdmin.jsp' >List</a> all Emps.  <br><br></li>
  
  
  <li>
    <FORM METHOD="post" ACTION="admin.do" >
        <b>��J�޲z���s�� (�п�J�޲z���s�� �p 1):</b>
        <input type="text" name="adminid">
        <input type="hidden" name="action" value="getOne_For_Display">
        <input type="submit" value="�e�X">
    </FORM>
  </li>

  <jsp:useBean id="adminSvc" scope="page" class="chillchip.admin.model.AdminService" />
   
  <li>
     <FORM METHOD="post" ACTION="admin.do" >
       <b>��ܺ޲z�H���s��:</b>
       <select size="1" name="adminid">
         <c:forEach var="adminVO" items="${adminSvc.all}" > 
          <option value="${adminVO.adminid}">${adminVO.adminid}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="�e�X">
    </FORM>
  </li>
  
  <li>
     <FORM METHOD="post" ACTION="admin.do" >
       <b>��ܭ��u�m�W:</b>
       <select size="1" name="adminid">
         <c:forEach var="adminVO" items="${adminSvc.all}" > 
          <option value="${adminVO.adminid}">${adminVO.adminname}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="�e�X">
     </FORM>
  </li>
</ul>


<h3>���u�޲z</h3>

<ul>
  <li><a href='addAdmin.jsp'>Add</a> a new Emp.</li>
</ul>

</body>
</html>