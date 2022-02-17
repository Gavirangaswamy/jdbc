package com.grs.digitalwallet.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import com.grs.digitalwallet.dto.WalletDTO;

public class WalletDAO {

	public void insert(List<WalletDTO> wallets) {
		Connection connection = null;
		PreparedStatement statement = null;
		String query = "INSERT INTO wallet_details VALUES(?, ?, ?, ?)";

		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mobiles", "root", "root");
			connection.setAutoCommit(false);
			statement = connection.prepareStatement(query);
			
			for (int i = 0; i < wallets.size(); i++) {
				statement.setInt(1, wallets.get(i).getWalletID());
				statement.setString(2, wallets.get(i).getProvider());
				statement.setDouble(3, wallets.get(i).getAvailableBalance());
				statement.setInt(4, wallets.get(i).getTansactions());
				
				statement.addBatch();
			}
			
			statement.executeBatch();
			connection.commit();
			System.out.println("All wallets saved successfully");
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

	}
}
