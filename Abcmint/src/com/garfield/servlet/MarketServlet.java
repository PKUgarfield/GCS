package com.garfield.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.garfield.util.HttpClient;

@WebServlet("/market.do")
public class MarketServlet  extends HttpServlet {
	private final static String MARKET_KLINE = "kline";
	private final static String MARKET_LAST = "last";
	private final static String MARKET_DEALS = "deals";
	private final static String MARKET_USER_DEALS = "userDeals";	
	private final static String MARKET_STATUS = "status";
	private final static String MARKET_STATUS_TODAY = "statusToday";
	private final static String MARKET_LIST = "list";
	private final static String MARKET_SUMMARY = "summary";
	
	private final static String SERVER = "http://192.168.1.149:8080";
	
	private static int messageId = 0;
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String cmd = request.getParameter("cmd");

		switch (cmd) {
		case MARKET_KLINE:
			doMarketKline(request, response);
			break;
		case MARKET_LAST:
			doMarketLast(request, response);
			break;
		case MARKET_DEALS:
			doMarketDeals(request, response);
			break;
		case MARKET_USER_DEALS:
			doMarketUserDeals(request, response);
			break;
		case MARKET_STATUS:
			doMarketStatus(request, response);
			break;
		case MARKET_STATUS_TODAY:
			doMarketStatusToday(request, response);
			break;
		case MARKET_LIST:
			doMarketList(request, response);
			break;
		case MARKET_SUMMARY:
			doMarketSummary(request, response);
			break;
		default:
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	/*************************************************************************************************************/
	protected void doMarketKline(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// http://192.168.1.144:8080/Abcmint/market.do?cmd=kline&market=BTCBCH&start=1538465656&end=1539156898&interval=10
		messageId++;
		String market = request.getParameter("market");
		int start = Integer.parseInt(request.getParameter("start"));
		int end = Integer.parseInt(request.getParameter("end"));
		int interval = Integer.parseInt(request.getParameter("interval"));

		JSONObject json = new JSONObject();
		JSONArray params = new JSONArray();
		params.add(market);
		params.add(start);
		params.add(end);
		params.add(interval);
		
		json.put("id", messageId);
		json.put("method", "market.kline");
		json.put("params", params);
		
		String s = HttpClient.doPost(SERVER, json.toString());
		response.getWriter().write(s);
	}
	protected void doMarketLast(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// http://192.168.1.144:8080/Abcmint/market.do?cmd=last&market=BTCBCH
		messageId++;
		String market = request.getParameter("market");
		
		JSONObject json = new JSONObject();
		JSONArray params = new JSONArray();
		params.add(market);

		json.put("id", messageId);
		json.put("method", "market.last");
		json.put("params", params);
		
		String s = HttpClient.doPost(SERVER, json.toString());
		response.getWriter().write(s);
	}
	protected void doMarketDeals(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //http://192.168.1.144:8080/Abcmint/market.do?cmd=deals&market=BTCBCH&limit=10&lastid=10
		messageId++;		
		String market = request.getParameter("market");
		int limit = Integer.parseInt(request.getParameter("limit"));
		int lastid = Integer.parseInt(request.getParameter("lastid"));
		
		JSONObject json = new JSONObject();
		JSONArray params = new JSONArray();
		params.add(market);
		params.add(limit);
		params.add(lastid);
		
		json.put("id", messageId);
		json.put("method", "market.deals");
		json.put("params", params);
		
		String s = HttpClient.doPost(SERVER, json.toString());
		response.getWriter().write(s);
	}
	protected void doMarketUserDeals(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// http://192.168.1.144:8080/Abcmint/market.do?cmd=userDeals&market=BTCBCH&userid=2&offset=0&limit=10
		messageId++;
		int userid = Integer.parseInt(request.getParameter("userid"));
		String market = request.getParameter("market");
		int offset = Integer.parseInt(request.getParameter("offset"));
		int limit = Integer.parseInt(request.getParameter("limit"));
		
		JSONObject json = new JSONObject();
		JSONArray params = new JSONArray();
		params.add(userid);
		params.add(market);
		params.add(offset);
		params.add(limit);
		
		json.put("id", messageId);
		json.put("method", "market.user_deals");
		json.put("params", params);
		
		String s = HttpClient.doPost(SERVER, json.toString());
		response.getWriter().write(s);
	}
	protected void doMarketStatus(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// http://192.168.1.144:8080/Abcmint/market.do?cmd=status&market=BTCBCH&period=86400
		messageId++;
		String market = request.getParameter("market");
		Integer period = Integer.parseInt(request.getParameter("period")); //unit(s) 86400 for last 24 hours
		
		JSONObject json = new JSONObject();
		JSONArray params = new JSONArray();
		params.add(market);
		params.add(period);
		
		json.put("id", messageId);
		json.put("method", "market.status");
		json.put("params", params);
		
		String s = HttpClient.doPost(SERVER, json.toString());
		response.getWriter().write(s);
	}
	protected void doMarketStatusToday(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// http://192.168.1.144:8080/Abcmint/market.do?cmd=statusToday&market=BTCBCH
		messageId++;
		String market = request.getParameter("market");
		
		JSONObject json = new JSONObject();
		JSONArray params = new JSONArray();
		params.add(market);
		
		json.put("id", messageId);
		json.put("method", "market.status_today");
		json.put("params", params);
		
		String s = HttpClient.doPost(SERVER, json.toString());
		response.getWriter().write(s);
	}
	protected void doMarketList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// http://192.168.1.144:8080/Abcmint/market.do?cmd=list
		messageId++;
		JSONObject json = new JSONObject();
		JSONArray params = new JSONArray();
		
		json.put("id", messageId);
		json.put("method", "market.list");
		json.put("params", params);
		
		String s = HttpClient.doPost(SERVER, json.toString());
		response.getWriter().write(s);
	}
	protected void doMarketSummary(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// http://192.168.1.144:8080/Abcmint/market.do?cmd=summary&markets=BTCBCH
		messageId++;
		String markets[] = request.getParameter("markets").split("#");
		
		JSONObject json = new JSONObject();
		JSONArray params = new JSONArray();
		for(String market : markets)			
			params.add(market);
	
		json.put("id", messageId);
		json.put("method", "market.summary");
		json.put("params", params);
		
		String s = HttpClient.doPost(SERVER, json.toString());
		response.getWriter().write(s);
	}
}
