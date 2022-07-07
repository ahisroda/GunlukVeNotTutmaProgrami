package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBase {

	public static final String YOL = "jdbc:derby:gunluk;create=true";
	public static final String KULLANICI = "";
	public static final String PAROLA = "";

	public static Connection getConnection() {
		Connection connection = null;

		try {
			connection = DriverManager.getConnection(YOL, KULLANICI, PAROLA);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return connection;
	}

}
