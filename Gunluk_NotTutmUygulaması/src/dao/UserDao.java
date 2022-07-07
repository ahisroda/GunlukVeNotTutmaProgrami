package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.xml.crypto.Data;

import db.DataBase;
import domain.UserInformationAndUtilDomain;

public class UserDao {

	// CREATE TABLE

	public static void createTable() {
		Connection connection = DataBase.getConnection();

		try {
			PreparedStatement query = connection.prepareStatement(
					"CREATE TABLE gunlukTable (id INT GENERATED ALWAYS AS IDENTITY (START WITH 1 INCREMENT BY 1), "
							+ "ide VARCHAR(50), password VARCHAR(50), accountsave VARCHAR(50), "
							+ "name VARCHAR(50), activity INT, signature VARCHAR(50), "
							+ "pagenumber VARCHAR(50), themetittle VARCHAR(50), " + "maindaily VARCHAR(10000))");
			query.executeUpdate();
		} catch (SQLException e) {
			System.out.println("gunlukTable already exist");
		}
	}

	// CREATE ACCOUNT

	public static void createAccount(UserInformationAndUtilDomain addData) {
		Connection connection = DataBase.getConnection();

		try {
			PreparedStatement query = connection.prepareStatement("INSERT INTO gunlukTable "
					+ "(password, accountsave, name, activity, signature, ide) VALUES (?,?,?,?,?,?,?)");
			query.setString(1, addData.getSifre());
			query.setString(2, addData.getHesapKurtarmaNo());
			query.setString(3, addData.getAdi());
			query.setInt(4, addData.getAktiflik());
			query.setString(5, addData.getImza());
			query.setString(6, addData.getKimlik());

			query.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// ADD DAÝLY

	public static void dailyAdd(UserInformationAndUtilDomain dailyAdd) {

		Connection connection = DataBase.getConnection();

		try {
			PreparedStatement query = connection.prepareStatement(
					"INSERT INTO gunlukTable (pagenumber, themetittle, maindaily, signature) VALUES (?,?,?,?)");
			query.setString(1, dailyAdd.getSayfaSayisi());
			query.setString(2, dailyAdd.getKonuBasligi());
			query.setString(3, dailyAdd.getEsasGunluk());
			query.setString(4, dailyAdd.getImza());

			query.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// ACCESS CONTROL

	public static boolean accessControl(String ide, String passw) {
		boolean control = false;

		Connection connection = DataBase.getConnection();

		try {
			PreparedStatement query = connection
					.prepareStatement("SELECT * FROM gunlukTable WHERE ide = ? AND password = ?");
			query.setString(1, ide);
			query.setString(2, passw);

			ResultSet rs = query.executeQuery();

			while (rs.next()) {
				control = true;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return control;
	}

	// ACCOUNT EXÝST CONTROL

	public static boolean accountExistControl(String ide) {
		boolean control = false;

		Connection connection = DataBase.getConnection();
		try {
			PreparedStatement query = connection.prepareStatement("SELECT * FROM gunlukTable WHERE ide = ?");
			query.setString(1, ide);
			ResultSet rs = query.executeQuery();

			while (rs.next()) {
				control = true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return control;
	}

	// SÝGNATURE EXÝST CONTROL

	public static boolean signatureExistControl(String sig) {
		boolean control = false;

		Connection connection = DataBase.getConnection();
		try {
			PreparedStatement query = connection.prepareStatement("SELECT * FROM gunlukTable WHERE signature = ?");
			query.setString(1, sig);
			ResultSet rs = query.executeQuery();

			while (rs.next()) {
				control = true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return control;
	}

	// ACTÝVE ACTÝVÝTY

	public static void activityActive(String ide) {
		Connection connection = DataBase.getConnection();

		try {
			PreparedStatement query = connection.prepareStatement("UPDATE gunlukTable SET activity = 1 WHERE ide = ?");
			query.setString(1, ide);

			query.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// PASÝVE ACTÝVÝTY

	public static void activityPasive() {
		Connection connection = DataBase.getConnection();

		try {
			PreparedStatement query = connection
					.prepareStatement("UPDATE gunlukTable SET activity = 2 WHERE activity = 1");

			query.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// NO REMEMBER ACOOUNT CONTROL

	public static boolean noRememberAccountControl(String ide, String accountsave) {
		boolean control = false;

		Connection connection = DataBase.getConnection();
		try {
			PreparedStatement query = connection
					.prepareStatement("SELECT * FROM gunlukTable WHERE ide = ? AND accountsave = ?");
			query.setString(1, ide);
			query.setString(2, accountsave);
			ResultSet rs = query.executeQuery();

			while (rs.next()) {
				control = true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return control;
	}

	// PASSWORD UPDATE
	public static void passwordUpdate(String passw, String ide) {
		Connection connection = DataBase.getConnection();

		try {
			PreparedStatement query = connection.prepareStatement("UPDATE gunlukTable SET password = ? WHERE ide = ?");
			query.setString(1, passw);
			query.setString(2, ide);

			query.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// DELETE ACCOUNT
	public static void accountDelete(String ide, String signature) {
		Connection connection = DataBase.getConnection();

		try {
			PreparedStatement query = connection
					.prepareStatement("DELETE FROM gunlukTable WHERE ide = ? AND signature = ?");
			query.setString(1, ide);
			query.setString(2, signature);

			query.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// DELETE ACCOUNT ÝNFORMATÝON CONTROL

	public static boolean deleteAccountInfoControl(String ide, String signature, String password) {
		boolean control = false;

		Connection connection = DataBase.getConnection();
		try {
			PreparedStatement query = connection
					.prepareStatement("SELECT * FROM gunlukTable WHERE ide = ? AND signature = ? AND password = ?");
			query.setString(1, ide);
			query.setString(2, signature);
			query.setString(3, password);
			ResultSet rs = query.executeQuery();

			while (rs.next()) {
				control = true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return control;
	}

}
