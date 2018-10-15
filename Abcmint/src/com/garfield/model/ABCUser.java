package com.garfield.model;

public class ABCUser {
	public int id;
	public String nickname;
	public String email;
	public String phone;
	public String password;
	public int country;
	public double balance;
	public double freeze;
	public int active;
	public long register_time;
	
	
	public ABCUser() {
		id = -1;
		nickname = null;
		email = null;
		phone = null;
		password = null;
		country = -1;
		balance = 0.0;
		freeze = 0.0;
		active = 0;
		register_time = 0;
	}
}
