<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>CRUD Of Location Table</title>
</head>
<body>

<FORM METHOD="get" ACTION="LocationList">
    請輸入查詢條件：
    <br>
    <br>
    <!-- <INPUT TYPE="TEXT" NAME="address" VALUE="地址"><P> -->
    <INPUT TYPE="TEXT" NAME="location_name" VALUE="名稱"><P>
    <!-- 請輸入從1.0～5.0之間的分數（包含小數部分）：
    <INPUT TYPE="TEXT" NAME="score" VALUE="評分"><P>
    請輸入一個正整數的評論人數：
    <INPUT TYPE="TEXT" NAME="comments_number" VALUE="評論數"><P> -->
    <INPUT TYPE="SUBMIT">
 </FORM>

</body>
</html>