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
		請輸入id名稱（正整數）：
		 <INPUT TYPE="TEXT" NAME="location_id" VALUE="景點ID">
		<P>
			請輸入景點地址： <INPUT TYPE="TEXT" NAME="address" VALUE="地址">
		<P>
			請輸入創建時間（yyyy-mm-dd)： <INPUT TYPE="TEXT" NAME="create_time" VALUE="建立時間">
		<P>
			請輸入評論數（正整數）： <INPUT TYPE="TEXT" NAME="comments_number" VALUE="評論數">
		<P>
			請輸入從1.0～5.0之間的分數（包含小數部分）： <INPUT TYPE="TEXT" NAME="score" VALUE="評分">
		<P>
			請輸入景點名稱： <INPUT TYPE="TEXT" NAME="location_name" VALUE="景點名稱">
		<P>
			<INPUT TYPE="SUBMIT">
	</FORM>
	 <br>
	 <br>


	<FORM METHOD="post" ACTION="LocationInsert">
		請輸入欲新增的地點： 
		<br> 
		<br> 
		請輸入id名稱（正整數）： <INPUT TYPE="TEXT" NAME="location_id" VALUE="10">
		<P>
			請輸入景點地址： <INPUT TYPE="TEXT" NAME="address" VALUE="台北市南港區">
		<P>
			請輸入創建時間（yyyy-mm-dd)： <INPUT TYPE="TEXT" NAME="create_time" VALUE="2024-12-8">
		<P>
			請輸入評論數（正整數）： <INPUT TYPE="TEXT" NAME="comments_number" VALUE="40">
		<P>
			請輸入從1.0～5.0之間的分數（包含小數部分）： <INPUT TYPE="TEXT" NAME="score" VALUE="4.3">
		<P>
			請輸入景點名稱： <INPUT TYPE="TEXT" NAME="location_name" VALUE="故宮">
		<P>
			<INPUT TYPE="SUBMIT">
	</FORM>
	 <br>
	 <br>


	<FORM METHOD="post" ACTION="LocationDelete">
		請輸入要刪除的地點ID： 
		<br> 
		<br> 
		請輸入id名稱（正整數）： <INPUT TYPE="TEXT" NAME="location_id" VALUE="景點ID">
		<P>
			<INPUT TYPE="SUBMIT">
	</FORM>
	 <br>
	 <br>


	<FORM METHOD="post" ACTION="LocationUpdate">
		請輸入要修改的地點ID：
		 <br> 
		 <br> 
		 請輸入id名稱（正整數）： <INPUT TYPE="TEXT" NAME="location_id" VALUE="景點ID">
		<P>
			請輸入要修改的內容：
			<br>
			<br> 
			請輸入景點地址： <INPUT TYPE="TEXT" NAME="address" VALUE="地址">
		<P>
			請輸入創建時間（yyyy-mm-dd)： <INPUT TYPE="TEXT" NAME="create_time" VALUE="建立時間">
		<P>

			請輸入評論數（正整數）： <INPUT TYPE="TEXT" NAME="comments_number" VALUE="評論數">
		<P>
			請輸入從1.0～5.0之間的分數（包含小數部分）： <INPUT TYPE="TEXT" NAME="score" VALUE="評分">
		<P>
			請輸入景點名稱： <INPUT TYPE="TEXT" NAME="location_name" VALUE="景點名稱">
		<P>
			<INPUT TYPE="SUBMIT">
	</FORM>
	 <br>
	 <br>



</body>
</html>