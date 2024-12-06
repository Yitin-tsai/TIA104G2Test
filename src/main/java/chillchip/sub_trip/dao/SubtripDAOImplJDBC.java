package chillchip.sub_trip.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SubtripDAOImplJDBC {
	
	private Connection connection;
	private String driver = "com.mysql.cj.jdbc.Driver";
	private String url = "jdbc:mysql://localhost:3306/TIA104G2?severTimezone=Asia/Taipei";
	private String userid = "root";
	private String passwd = "123456";
	
	private static final String INSERT_STMT = "INSERT INTO subtrip(trip_id,index,content)VALUES(?,?,?)";
	//按照子行程id排序查詢的SQL
	private static final String GET_ALLSTMT_BYTRIPID = "SELECT sub_trip_id, trip_id, index, content FROM sub_trip by sub_trip_id";
	//按照子行程建立時間查詢的SQL(默認升序排列）
	private static final String GET_ALLSTMT_BYINDEX = "SELECT sub_trip_id, trip_id, index, content FROM sub_trip by index";
	//查詢單一子行程
	private static final String GET_ONE_STMT = "SELECT sub_trip_id, trip_id, index, content FROM sub_trip where sub_trip_id=?";
	private static final String DELETE = "DELETE FROM sub_trip where sub_trip_id=?";
	private static final String UPDATE = "UPDATE sub_trip set trip_id=?, index=?, content=? where sub_trip_id=?";

	
	public SubtripDAOImplJDBC() {
		
		try {
			Class.forName(driver);
			this.connection = DriverManager.getConnection(url, userid, passwd);
			} catch (ClassNotFoundException | SQLException e) {
				throw new RuntimeException("Couldn't load database driver or connect to database." + e.getMessage());
			}
	}
	
	private Connection getConnection() {
		return this.connection;
	}

	
	
	
	

}
