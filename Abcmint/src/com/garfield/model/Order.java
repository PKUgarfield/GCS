package com.garfield.model;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class Order {
	public static final String taker_fee_rate = "0.002";
	public static final String maker_fee_rate = "0.001";
	
	public int user_id;
	public String market;
	public int side;  //1: sell, 2: buy
	public String amount;
	public String pride;
	public String source;
	public int type;  //1: limit 2: market
	
	private int messageId;
	
	public Order(int messageId, int uid, String market, int side, String amount, String pride) {
		this.user_id = uid;
		this.market = market;
		this.side = side;
		this.amount = amount;
		this.pride = pride;
		this.type = 1;
		this.source = "";
		
		this.messageId = messageId;
	}
	
	public Order(int messageId, int uid, String market, int side, String amount) {
		this.user_id = uid;
		this.market = market;
		this.side = side;
		this.amount = amount;
		this.pride = "";
		this.type = 2;
		this.source = "";
		
		this.messageId = messageId;
	}
	
	public String toString() {
		JSONObject json = new JSONObject();
		JSONArray params = new JSONArray();
		if(this.type == 1) {
			params.add(user_id);
			params.add(market);
			params.add(side);
			params.add(amount);
			params.add(pride);
			params.add(taker_fee_rate);
			params.add(maker_fee_rate);
			params.add(source);
			
			json.put("id", messageId);
			json.put("method", "order.put_limit");
			json.put("params", params);
		}else if(this.type == 2) {
			params.add(user_id);
			params.add(market);
			params.add(side);
			params.add(amount);
			params.add(taker_fee_rate);
			params.add(source);
			
			json.put("id", messageId);
			json.put("method", "order.put_market");
			json.put("params", params);
		}
		return json.toString();
	}
}
