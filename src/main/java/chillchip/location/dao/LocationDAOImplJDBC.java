package chillchip.location.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import chillchip.location.entity.LocationVO;

public class LocationDAOImplJDBC implements LocationDAO, AutoCloseable {

	// 屬性建立用private，因為只有這個類別會用到
	// 建議資料庫連線字串設定在外部設定檔，這裡只是為了方便範例，所以直接寫在程式碼中(不然每次都要寫相同字串搞人)
	// 建議userid和passwd也設定在外部設定檔，這裡只是為了方便範例，所以直接寫在程式碼中(不然每次都要寫相同字串搞人)
	// 這裡沒有使用finally關閉連線，是因為這裡使用了AutoCloseable，所以在try-with-resources中使用，會自動關閉連線
	// PreparedStatement、ResultSet也是AutoCloseable，所以也會自動關閉
	// LocationDAOImplJDBC自己本身也是AutoCloseable，所以在try-with-resources中使用，會自動關閉

	private Connection connection;
	private String driver = "com.mysql.cj.jdbc.Driver";
	private String url = "jdbc:mysql://localhost:3306/TIA104G2?serverTimezone=Asia/Taipei";
	private String userid = "root";
	private String passwd = "123456";

	private static final String INSERT_STMT = "INSERT INTO location (address,create_time, comments_number, score, location_name) VALUES (?,?,?,?,?);";
	private static final String GET_ALL_STMT = "SELECT location_id,address, create_time, comments_number ,score, location_name FROM location order by location_id";
	private static final String GET_ONE_STMT = "SELECT location_id,address, create_time, comments_number ,score, location_name FROM location WHERE location_id=?";
	private static final String GET_BY_LOCATION_NAME_STMT = "SELECT location_id, address, create_time, comments_number ,score, location_name FROM location WHERE location_name =?";
	private static final String DELETE = "DELETE FROM location where location_id=?";
	private static final String UPDATE = "UPDATE location set address=?, create_time=?, comments_number=?, score=?, location_name=? where location_id = ?";

	public LocationDAOImplJDBC() {

		// 建構子一開始確認是否有載入驅動程式，並且建立連線
		try {
			Class.forName(driver);
			this.connection = DriverManager.getConnection(url, userid, passwd);
		} catch (ClassNotFoundException | SQLException e) {
			throw new RuntimeException("Couldn't load database driver or connect to database. " + e.getMessage());
		}
	}

	private Connection getConnection() {
		return this.connection;
	}

	@Override
	public void insert(LocationVO LocationVO) {
		try (PreparedStatement pstmt = getConnection().prepareStatement(INSERT_STMT)) {
			pstmt.setString(1, LocationVO.getAddress());
			pstmt.setString(2, LocationVO.getCreate_time());
			pstmt.setString(3, LocationVO.getComments_number());
			pstmt.setString(4, LocationVO.getScore());
			pstmt.setString(5, LocationVO.getLocation_name());
			pstmt.executeUpdate();

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		}
	}

	@Override
	public void update(LocationVO LocationVO) {
		try (PreparedStatement pstmt = getConnection().prepareStatement(UPDATE)) {
			pstmt.setString(1, LocationVO.getAddress());
			pstmt.setString(2, LocationVO.getCreate_time());
			pstmt.setString(3, LocationVO.getComments_number());
			pstmt.setString(4, LocationVO.getScore());
			pstmt.setString(5, LocationVO.getLocation_name());
			pstmt.setInt(6, LocationVO.getLocationid());
			pstmt.executeUpdate();

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		}
	}

	@Override
	public void delete(Integer Locationid) {
		try (PreparedStatement pstmt = getConnection().prepareStatement(DELETE)) {
			pstmt.setInt(1, Locationid);
			pstmt.executeUpdate();

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		}
	}

	@Override
	public List<LocationVO> getAll() {
		List<LocationVO> list = new ArrayList<>();
		try (PreparedStatement pstmt = getConnection().prepareStatement(GET_ALL_STMT);
				ResultSet rs = pstmt.executeQuery()) {

			while (rs.next()) {
				LocationVO LocationVO = new LocationVO();
				LocationVO.setLocationid(rs.getInt("Location_id"));
				LocationVO.setAddress(rs.getString("address"));
				LocationVO.setCreate_time(rs.getString("create_time"));
				LocationVO.setComments_number(rs.getString("comments_number"));
				LocationVO.setScore(rs.getString("score"));
				LocationVO.setLocation_name(rs.getString("location_name"));
				list.add(LocationVO);
			}

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		}
		return list;
	}

	@Override
	public List<Map<String, Object>> getAllPro() {
		List<Map<String, Object>> list = new ArrayList<>();
		try (PreparedStatement pstmt = getConnection().prepareStatement(GET_ALL_STMT);
				ResultSet rs = pstmt.executeQuery()) {

			while (rs.next()) {

				Map<String, Object> map = new HashMap<>();

				map.put("Location_id", rs.getInt("Location_id"));
				map.put("address", rs.getString("address"));
				map.put("create_time", rs.getString("create_time"));
				map.put("comments_number", rs.getString("comments_number"));
				map.put("score", rs.getString("score"));
				map.put("location_name", rs.getString("location_name"));
				list.add(map);
			}

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		}
		return list;
	}

	@Override
	public LocationVO getById(Integer Locationid) {
		LocationVO LocationVO = null;
		try (PreparedStatement pstmt = getConnection().prepareStatement(GET_ONE_STMT)) {
			pstmt.setInt(1, Locationid);
			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					LocationVO = new LocationVO();
					LocationVO.setLocationid(rs.getInt("Location_id"));
					LocationVO.setAddress(rs.getString("address"));
					LocationVO.setCreate_time(rs.getString("create_time"));
					LocationVO.setComments_number(rs.getString("comments_number"));
					LocationVO.setScore(rs.getString("score"));
					LocationVO.setLocation_name(rs.getString("location_name"));
				}
			}

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		}
		return LocationVO;
	}

	@Override
	public List<Map<String, Object>> getByLocationName(String location_name) {
		List<Map<String, Object>> list = new ArrayList<>();
		try (PreparedStatement pstmt = getConnection().prepareStatement(GET_BY_LOCATION_NAME_STMT);) {
			// 設定參數
			pstmt.setString(1, location_name);

			try (ResultSet rs = pstmt.executeQuery()) {
				while (rs.next()) {

					Map<String, Object> map = new HashMap<>();

					map.put("Location_id", rs.getInt("Location_id"));
					map.put("address", rs.getString("address"));
					map.put("create_time", rs.getString("create_time"));
					map.put("comments_number", rs.getString("comments_number"));
					map.put("score", rs.getString("score"));
					map.put("location_name", rs.getString("location_name"));
					list.add(map);
				}
			}

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		}
		return list;
	}

	@Override
	public void close() {
		if (this.connection != null) {
			try {
				this.connection.close();
			} catch (SQLException e) {
				throw new RuntimeException("Failed to close the connection. " + e.getMessage());
			}
		}
	}

	public static void main(String[] args) {
		try (LocationDAOImplJDBC dao = new LocationDAOImplJDBC()) {
			// 測試程式碼
			// 插入SQL 測試
			LocationVO LocationVO = new LocationVO();
			LocationVO.setAddress("日本東京都文京區後樂");
			LocationVO.setCreate_time("2024-12-12 20:00");
			LocationVO.setComments_number("3");
			LocationVO.setScore("5.0");
			LocationVO.setLocation_name("東京巨蛋");
			dao.insert(LocationVO);
		} catch (RuntimeException e) {
			System.err.println("An unexpected error occurred: " + e.getMessage());
			e.printStackTrace();
		}
	}
}