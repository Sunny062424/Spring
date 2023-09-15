package com.kopo.memo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.sqlite.SQLiteConfig;

public class DB {
    Connection connection;
	private void open() {
		try {
			Class.forName("org.sqlite.JDBC");
			SQLiteConfig config = new SQLiteConfig();
			this.connection = DriverManager.getConnection("jdbc:sqlite:C:\\kopo\\memo.db", config.toProperties());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void close() {
		if (this.connection != null) {
			try {
				this.connection.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		this.connection = null;
	}
	
	public void createTable() {
		this.open();
		String query = "CREATE TABLE memo (idx INTEGER PRIMARY KEY AUTOINCREMENT, created TEXT, content TEXT)";
		try {
			Statement statement = this.connection.createStatement();
			statement.execute(query);
			statement.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.close();
	}
	
	public void insertData(String content) {
		this.open();
		String query = "INSERT INTO memo (content, created) VALUES (?, ?)";
		try {
			PreparedStatement preparedStatement = this.connection.prepareStatement(query);
			preparedStatement.setString(1, content);
			Date now = new Date();
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String formatedNow = formatter.format(now);
			preparedStatement.setString(2, formatedNow);
			preparedStatement.execute();
			preparedStatement.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.close();
	}
	
	public ArrayList<Memo> selectData() {
		ArrayList<Memo> result = new ArrayList<Memo>();
		this.open();
		try {
			String query = "SELECT * FROM memo";
			PreparedStatement preparedStatement = this.connection.prepareStatement(query);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				int idx = resultSet.getInt("idx");
				String created = resultSet.getString("created");
				String content = resultSet.getString("content");
				result.add(new Memo(idx, created, content));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.close();
		return result;
	}

	public void deleteData(String idx){
		this.open();
		try {
			int intIdx = Integer.parseInt(idx);
			String query = "DELETE FROM memo WHERE idx=?";
			PreparedStatement preparedStatement = this.connection.prepareStatement(query);
			preparedStatement.setInt(1, intIdx);
			preparedStatement.execute();
			preparedStatement.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.close();

	}

    public void updateData(String idx, String content) {
		this.open();
		try {
            int intIdx = Integer.parseInt(idx);
			String query = "UPDATE memo SET content=?, created=? WHERE idx=?";
			PreparedStatement preparedStatement = this.connection.prepareStatement(query);
            preparedStatement.setString(1, content);
            preparedStatement.setInt(3, intIdx);
            Date now = new Date();
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String formatedNow = formatter.format(now);
            preparedStatement.setString(2, formatedNow);
			preparedStatement.execute();
			preparedStatement.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.close();
	}

}
