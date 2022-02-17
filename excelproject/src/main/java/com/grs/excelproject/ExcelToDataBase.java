package com.grs.excelproject;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelToDataBase {
	static Connection connection = null;
	static Statement statement = null;
	static String query = "CREATE TABLE movies_details(`movie_id` int,`year` int ,`movie_name` varchar(50) NOT NULL UNIQUE,`director_name` varchar(50) NOT NULL,"
			+ "`language` varchar(30) NOT NULL,`platform` varchar(30) NOT NULL DEFAULT 'theatre',`budget` double(20,2),`collection` double(20,2),"
			+ "`rating` float CHECK (rating>0.0 AND rating<10.0), PRIMARY KEY (`movie_id`))";

	public static void createTable() {
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/movies", "root", "root");
			statement = connection.createStatement();

			statement.executeUpdate(query);
			System.out.println("Table created successfully");
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

	public static String insertMovieDetails() {
		Workbook workbook = null;
		int count = 0;
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/movies", "root", "root");
			statement = connection.createStatement();

			workbook = new XSSFWorkbook("./file/movies.xlsx");

			Sheet sheet = workbook.getSheet("movies_details");

			int rows = sheet.getLastRowNum();
			for (int r = 1; r <=rows; r++) {
				Row row = sheet.getRow(r);
				int movieID = (int) row.getCell(0).getNumericCellValue();
				int year = (int) row.getCell(1).getNumericCellValue();
				String movieName = row.getCell(2).getStringCellValue();
				String directorName = row.getCell(3).getStringCellValue();
				String lang = row.getCell(4).getStringCellValue();
				String platform = row.getCell(5).getStringCellValue();
				double budget = row.getCell(6).getNumericCellValue();
				double collection = row.getCell(7).getNumericCellValue();
				float rating = (float) row.getCell(8).getNumericCellValue();
				query = "INSERT INTO `movies_details` VALUES (" + movieID +","+year+",'" + movieName + "','"
						+ directorName + "','" + lang + "','" + platform + "'," + budget + "," + collection + ","
						+ rating + ")";
				statement.executeUpdate(query);
				count++;
			}
			System.out.println("Data Inserted successfully");
		} catch (SQLException | IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (statement != null)
					statement.close();
				if (connection != null)
					connection.close();
				if (workbook != null)
					workbook.close();
			} catch (SQLException | IOException e) {
				e.printStackTrace();
			}
		}
		return count+" row(s) affected";
	}

	public static void main(String[] args) {

		createTable();
		System.out.println(insertMovieDetails());
	}

}
