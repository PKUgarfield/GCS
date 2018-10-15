package com.garfield.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.garfield.dao.AbcuserDao;
import com.garfield.model.ABCUser;
 
public class DBMysqlUtil {
	
	private String dbDriver = null;
	private String dbConnectionURL = null;
	private String dbUsername = null;
	private String dbPassword = null;
	
	private Connection conn = null;
	private Properties props=null;
	private Logger logger = Logger.getLogger(DBMysqlUtil.class);
	
	private static DBMysqlUtil instance = null;
	
	public DBMysqlUtil(){
		props = new Properties();
		try {
			String path = this.getClass().getClassLoader().getResource("").getPath();
			InputStream in = new FileInputStream(path + "/db.properties");
			props.load(in);
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		dbDriver = props.getProperty("Driver");
		dbConnectionURL = props.getProperty("ConnectionURL");
		dbUsername = props.getProperty("Username");
		dbPassword = props.getProperty("Password");	
	}
	
	public DBMysqlUtil(String dbDriver, String dbConnectionURL, String dbUsername,String dbPassword){
		this.dbDriver = dbDriver;
		this.dbConnectionURL = dbConnectionURL;
		this.dbUsername = dbUsername;
		this.dbPassword = dbPassword;
	}
	
	public static DBMysqlUtil getInstance() {
		if(instance == null) {
			synchronized (DBMysqlUtil.class) {
				if(instance == null) {
					instance = new DBMysqlUtil();
				}
			}
		}
		return instance;
	}

	public Connection getConnection() {
		if(conn != null)
			return conn;
		
		try {
			Class.forName(dbDriver);
			conn = DriverManager.getConnection(dbConnectionURL, dbUsername,
					dbPassword);
			logger.info("connect to database!");
		} catch (Exception e) {
			logger.error("Error: connect to database!");
		}
		return conn;
	}
    
	public void close() {
		try {
			if (conn != null) {
				conn.close();
			}
			logger.info("close db connection.");
		} catch (Exception e) {
			logger.error("Error: close db connection.", e);
		}
	}
/*	
	public static void main(String[] args) throws SQLException {
		DBMysqlUtil util = new DBMysqlUtil();
		Connection conn = util.getConnection();

		AbcuserDao dao = new AbcuserDao(conn);
		ABCUser user = new ABCUser();
		user.nickname = "Tom";
		user.email = "tom@gmail.com";
		user.phone = "13025638989";
		user.country = 0;
		
		//dao.addUser(user);
		
		ABCUser user1 = dao.getUserByPhone("13025638989");
		System.out.println(user1.nickname);
		user1 = dao.getUserById(1);
		System.out.println(user1.nickname);
		user1 = dao.getUserByNickname("Tom");
		System.out.println(user1.nickname);
		user1 = dao.getUserByEmail("tom@gmail.com");
		System.out.println(user1.nickname);
		
		user.country = 1;
		user.id = user1.id;
		dao.updateUser(user);
		
		if(conn!=null)
			conn.close();
		util.close();
	}
	*/
}
