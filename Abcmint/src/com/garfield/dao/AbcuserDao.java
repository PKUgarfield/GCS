package com.garfield.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.garfield.model.ABCUser;

public class AbcuserDao {
	private Connection conn = null;
	private String table = "abc_user";
	private Logger logger = Logger.getLogger(AbcuserDao.class);
	
	public AbcuserDao(Connection conn) {
		this.conn = conn;
	}
	
	public void addUser(ABCUser newUser) {
		String sql = "insert into " + table + " (nickname, email, phone, passwd, country, balance, freeze) values (?, ?, ?, ?, ?, ?, ?)";
		PreparedStatement stmt = null;
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, newUser.nickname);
			stmt.setString(2, newUser.email);
			stmt.setString(3, newUser.phone);
			stmt.setString(4, newUser.password);
			stmt.setInt(5, newUser.country);
			stmt.setDouble(6, newUser.balance);
			stmt.setDouble(7, newUser.freeze);
			
			stmt.execute();
		} catch (SQLException e) {
			logger.info("adduser " + newUser.nickname + " get error: " + e.getMessage());
		} finally {
			if(stmt!=null)
				try {
					stmt.close();
				} catch (SQLException e) {
				}
		}
	}
	
	public void updateUser(ABCUser newUser) {
		String sql = "update " + table + " set nickname=?, email=?, phone=?, country=?, balance=?, freeze=? where id=?";
		PreparedStatement stmt = null;
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, newUser.nickname);
			stmt.setString(2, newUser.email);
			stmt.setString(3, newUser.phone);
			stmt.setInt(4, newUser.country);
			stmt.setDouble(5, newUser.balance);
			stmt.setDouble(6, newUser.freeze);
			stmt.setInt(7, newUser.id);
			stmt.execute();
		} catch (SQLException e) {
			logger.info("updateuser " + newUser.id + " get error: " + e.getMessage());
		} finally {
			if(stmt!=null)
				try {
					stmt.close();
				} catch (SQLException e) {
				}
		}
	}
	
	public void activeUser(ABCUser newUser) {
		String sql = "update " + table + " set active=? where id=?";
		PreparedStatement stmt = null;
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, newUser.active);
			stmt.setInt(2, newUser.id);
			stmt.execute();
		} catch (SQLException e) {
			logger.info("activeuser " + newUser.id + " get error: " + e.getMessage());
		} finally {
			if(stmt!=null)
				try {
					stmt.close();
				} catch (SQLException e) {
				}
		}
	}
	
	public void changePWD(ABCUser newUser) {
		String sql = "update " + table + " set active=? where id=?";
		PreparedStatement stmt = null;
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, newUser.password);
			stmt.setInt(2, newUser.id);
			stmt.execute();
		} catch (SQLException e) {
			logger.info("activeuser " + newUser.id + " get error: " + e.getMessage());
		} finally {
			if(stmt!=null)
				try {
					stmt.close();
				} catch (SQLException e) {
				}
		}
	}
	
	public ABCUser getUserById(int uid) {
		String sql = "select * from " + table + " where id = ?";
		PreparedStatement stmt = null;
		ABCUser user = null;
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, uid);
			ResultSet result = stmt.executeQuery();
			while(result.next()){
				if(user == null)
					user = new ABCUser();
				else
					logger.warn("getUserById " + uid + " get more than one record!");
				user.id = result.getInt("id");
				user.nickname = result.getString("nickname");
				user.email = result.getString("email");
				user.phone = result.getString("phone");
				user.password = result.getString("passwd");
				user.country = result.getInt("country");
				user.active = result.getInt("active");
				user.register_time = result.getTimestamp("register_time").getTime();
			}
		} catch (SQLException e) {
			logger.info("getUserById " + uid + " get error: " + e.getMessage());
		} finally {
			if(stmt!=null)
				try {
					stmt.close();
				} catch (SQLException e) {
				}
		}
		return user;
	}
	
	public ABCUser getUserByEmail(String email) {
		String sql = "select * from " + table + " where email = ?";
		PreparedStatement stmt = null;
		ABCUser user = null;
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, email);
			ResultSet result = stmt.executeQuery();
			while(result.next()){
				if(user == null)
					user = new ABCUser();
				else
					logger.warn("getUserByEmail " + email + " get more than one record!");
				user.id = result.getInt("id");
				user.nickname = result.getString("nickname");
				user.email = result.getString("email");
				user.phone = result.getString("phone");
				user.password = result.getString("passwd");
				user.country = result.getInt("country");
				user.active = result.getInt("active");
				user.register_time = result.getTimestamp("register_time").getTime();
			}
		} catch (SQLException e) {
			logger.info("getUserByEmail " + email + " get error: " + e.getMessage());
		} finally {
			if(stmt!=null)
				try {
					stmt.close();
				} catch (SQLException e) {
				}
		}
		return user;
	}
	
	public ABCUser getUserByPhone(String phone) {
		String sql = "select * from " + table + " where phone = ?";
		PreparedStatement stmt = null;
		ABCUser user = null;
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, phone);
			ResultSet result = stmt.executeQuery();
			while(result.next()){
				if(user == null)
					user = new ABCUser();
				else
					logger.warn("getUserByPhone " + phone + " get more than one record!");
				user.id = result.getInt("id");
				user.nickname = result.getString("nickname");
				user.email = result.getString("email");
				user.phone = result.getString("phone");
				user.password = result.getString("passwd");
				user.country = result.getInt("country");
				user.active = result.getInt("active");
				user.register_time = result.getTimestamp("register_time").getTime();
			}
		} catch (SQLException e) {
			logger.info("getUserByPhone " + phone + " get error: " + e.getMessage());
		} finally {
			if(stmt!=null)
				try {
					stmt.close();
				} catch (SQLException e) {
				}
		}
		return user;
	}
	
	public ABCUser getUserByNickname(String nickname) {
		String sql = "select * from " + table + " where nickname = ?";
		PreparedStatement stmt = null;
		ABCUser user = null;
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, nickname);
			ResultSet result = stmt.executeQuery();
			while(result.next()){
				if(user == null)
					user = new ABCUser();
				else
					logger.warn("getUserByNickname " + nickname + " get more than one record!");
				user.id = result.getInt("id");
				user.nickname = result.getString("nickname");
				user.email = result.getString("email");
				user.phone = result.getString("phone");
				user.password = result.getString("passwd");
				user.country = result.getInt("country");
				user.active = result.getInt("active");
				user.register_time = result.getTimestamp("register_time").getTime();
			}
		} catch (SQLException e) {
			logger.info("getUserByNickname " + nickname + " get error: " + e.getMessage());
		} finally {
			if(stmt!=null)
				try {
					stmt.close();
				} catch (SQLException e) {
				}
		}
		return user;
	}
}
