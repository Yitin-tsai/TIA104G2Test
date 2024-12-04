package chillchip.sub_trip.dao;

public class SubtripDAOImplJDBC {
	
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/TIA104G2?severTimezone=Asia/Taipei";
	String userid = "root";
	String passwd = "123456";
	
	private static final String INSERT_STMT=
			"INSERT INTO subtrip(trip_id,index,contect)VALUES(?,?,?)";
	
	
	
	
	
	

}
