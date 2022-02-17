package com.grs.mobile.dao;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.sql.Statement;
import java.util.List;
import java.util.Properties;

import javax.swing.text.html.HTMLDocument.HTMLReader.PreAction;

import com.grs.mobile.dto.MobileDetailsDTO;

public class MobileDAO {

	public int save(MobileDetailsDTO dto) {
		Connection connection = null;
		Statement statement = null;
		int count = 0;
		String query = "INSERT INTO mobile_details VALUES('" + dto.getBrandName() + "'," + dto.getPrice() + ",'"
				+ dto.getModelName() + "'," + dto.getRam() + ",'" + dto.getProcessor() + "')";

		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mobiles", "root", "root");
			statement = connection.createStatement();

			count = statement.executeUpdate(query);
			System.out.println("Saved successfully");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (statement != null)
					statement.close();
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}

		return count;
	}

	public String updatePriceByBrandName(String name, int price) {
		Connection connection = null;
		Statement statement = null;
		int count = 0;
		String query = "UPDATE mobile_details SET price =" + price + " WHERE model_name ='" + name + "'";

		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mobiles", "root", "root");
			connection.setAutoCommit(false);
			statement = connection.createStatement();

			count = statement.executeUpdate(query);
			connection.commit();
			System.out.println("Updated successfully");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (statement != null)
					statement.close();
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}

		return count + " row/s affected";
	}

	public static Connection connection() {
		Connection connection = null;
		try {
			FileInputStream stream = new FileInputStream("D:\\LearnJava\\JDBC\\db.properties");
			Properties properties = new Properties();
			properties.load(stream);
			String url = properties.getProperty("url");
			String user = properties.getProperty("user");
			String pass = properties.getProperty("password");
			connection = DriverManager.getConnection(url, user, pass);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return connection;
	}

	public String deleteByModelName(String name) {
		Connection connection = null;
		Statement statement = null;
		Savepoint point = null;
		int count = 0;
		String query = "DELETE FROM mobile_details WHERE model_name ='" + name + "'";
		try {
			connection = connection();
			connection.setAutoCommit(false);
			statement = connection.createStatement(); 
			count = statement.executeUpdate(query);
			point = connection.setSavepoint();
			connection.commit();
			System.out.println("Deleted successfully");
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				connection.rollback(point);
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally {
			try {
				if (statement != null)
					statement.close();   
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return count + " row/s affected";
	}
	
	
	public void getModelNameAndPriceByBrandName(String name) {
		Statement statement = null;
		Connection connection = null;
		String query = "Select model_name, price FROM mobile_details WHERE brand_name ='" + name + "'";
		try {
			connection = connection();
			connection.setAutoCommit(false);
			statement = connection.createStatement(); 
			ResultSet rs = statement.executeQuery(query);
			if(rs.next()) {
				System.out.println(rs.getString("model_name"));
				System.out.println(rs.getString("price"));
			}else {
				System.out.println("No Data Found");
			}
			connection.commit();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (statement != null)
					statement.close();   
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	public void getModelNameAndPriceByBrandName(String name1,String name2) {
		PreparedStatement statement = null;
		Connection connection = null;
		ResultSet rs = null;
		String query = "Select model_name, price FROM mobile_details WHERE brand_name in (?,?)";//index starts from 1
		try {
			connection = connection();
			connection.setAutoCommit(false);
			statement = connection.prepareStatement(query); 
			statement.setString(1, name1);
			statement.setString(2, name2);
			rs = statement.executeQuery();
			while(rs.next()) {
				System.out.println(rs.getString("model_name")+"\t"+rs.getInt("price"));
			}
			connection.commit();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (statement != null)
					statement.close();   
				if (connection != null)
					connection.close();
				if (rs != null)
					rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	
	public String insert(List<MobileDetailsDTO> mobiles) {
		Connection connection = null;
		PreparedStatement statement = null;
		int count = 0;
		String query = "INSERT INTO mobile_details VALUES(?, ?, ?, ?, ?)";

		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mobiles", "root", "root");
			connection.setAutoCommit(false);
			statement = connection.prepareStatement(query);
			
			for (int i = 0; i < mobiles.size(); i++) {
				statement.setString(1, mobiles.get(i).getBrandName());
				statement.setInt(2, mobiles.get(i).getPrice());
				statement.setString(3, mobiles.get(i).getModelName());
				statement.setInt(4, mobiles.get(i).getRam());
				statement.setString(5, mobiles.get(i).getProcessor());
				
				statement.executeUpdate();
				count++;
			}
			connection.commit();
			System.out.println("All mobiles saved successfully");
		} catch (SQLException e) {
			e.printStackTrace();
			if(connection!=null)
				try {
					connection.rollback();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
		} finally {
			try {
				if (statement != null)
					statement.close();
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}

		return count+" row(s) affected";
	}
}
