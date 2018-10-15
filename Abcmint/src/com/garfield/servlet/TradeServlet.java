package com.garfield.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.garfield.model.Order;
import com.garfield.util.HttpClient;

@WebServlet("/trade.do")
public class TradeServlet extends HttpServlet {
	
	private final static String ORDER_LIMIT = "limit";
	private final static String ORDER_MARKET = "market";
	private final static String ORDER_CANCEL = "cancel";
	private final static String ORDER_DEALS = "deals";
	private final static String ORDER_BOOK = "book";
	private final static String ORDER_DEPTH = "depth";
	private final static String ORDER_PENDING = "pending";
	private final static String ORDER_PENDING_DETAIL = "pendingDetail";
	private final static String ORDER_FINISHED = "finished";
	private final static String ORDER_FINISHED_DETAIL = "finishedDetail";
	
	private static int messageId = 0;
	private final static String SERVER = "http://192.168.1.149:8080";
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String cmd = request.getParameter("cmd");

		switch (cmd) {
		case ORDER_LIMIT:
			doLimit(request, response);
			break;
		case ORDER_MARKET:
			doMarket(request, response);
			break;
		case ORDER_CANCEL:
			doCancel(request, response);
			break;
		case ORDER_DEALS:
			doDeals(request, response);
			break;
		case ORDER_BOOK:
			doBook(request, response);
			break;
		case ORDER_DEPTH:
			doDepth(request, response);
			break;
		case ORDER_PENDING:
			doPending(request, response);
			break;
		case ORDER_PENDING_DETAIL:
			doPendingDetail(request, response);
			break;
		case ORDER_FINISHED:
			doFinished(request, response);
			break;
		case ORDER_FINISHED_DETAIL:
			doFinishedDetail(request, response);
			break;
		default:
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
	
	protected void doLimit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// http://192.168.1.144:8080/Abcmint/trade.do?cmd=limit&market=BTCBCH&userid=1&side=1&amount=0.05&price=6000
		messageId++;
		int uid = Integer.parseInt(request.getParameter("userid"));
		String market = request.getParameter("market").toString();
		int side = Integer.parseInt(request.getParameter("side"));
		String amount = request.getParameter("amount").toString();
		String pride = request.getParameter("price").toString();
		
		Order order = new Order(messageId, uid, market, side, amount, pride);
		
		String s = HttpClient.doPost(SERVER, order.toString());
		response.getWriter().write(s);
	}
	
	protected void doMarket(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// http://192.168.1.144:8080/Abcmint/trade.do?cmd=market&market=BTCBCH&userid=1&side=1&amount=0.05&takerfeerate=0.002&source=
		messageId++;
		int userid = Integer.parseInt(request.getParameter("userid"));
		String market = request.getParameter("market");
		int side = Integer.parseInt(request.getParameter("side"));
		String amount = request.getParameter("amount");
		String takerfeerate = request.getParameter("takerfeerate");
		String source = request.getParameter("source");

		JSONObject json = new JSONObject();
		JSONArray params = new JSONArray();
		params.add(userid);
		params.add(market);
		params.add(side);
		params.add(amount);
		params.add(takerfeerate);
		params.add(source);
		
		json.put("id", messageId);
		json.put("method", "order.put_market");
		json.put("params", params);
		
		String s = HttpClient.doPost(SERVER, json.toString());
		response.getWriter().write(s);
	}
	
	protected void doCancel(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// http://192.168.1.144:8080/Abcmint/trade.do?cmd=cancel&orderid=7&userid=1&market=BTCBCH
		messageId++;
		int userid = Integer.parseInt(request.getParameter("userid"));
		String market = request.getParameter("market");
		int orderid = Integer.parseInt(request.getParameter("orderid"));

		JSONObject json = new JSONObject();
		JSONArray params = new JSONArray();
		params.add(userid);
		params.add(market);
		params.add(orderid);
		
		json.put("id", messageId);
		json.put("method", "order.cancel");
		json.put("params", params);
		
		String s = HttpClient.doPost(SERVER, json.toString());
		response.getWriter().write(s);
	}
	
	protected void doDeals(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// http://192.168.1.144:8080/Abcmint/trade.do?cmd=deals&orderid=7&offset=0&limit=10
		messageId++;
		int orderid = Integer.parseInt(request.getParameter("orderid"));
		int offset = Integer.parseInt(request.getParameter("offset"));
		int limit = Integer.parseInt(request.getParameter("limit"));

		JSONObject json = new JSONObject();
		JSONArray params = new JSONArray();
		params.add(orderid);
		params.add(offset);
		params.add(limit);
		
		json.put("id", messageId);
		json.put("method", "order.deals");
		json.put("params", params);
		
		String s = HttpClient.doPost(SERVER, json.toString());
		response.getWriter().write(s);
	}
	protected void doBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// http://192.168.1.144:8080/Abcmint/trade.do?cmd=book&market=BTCBCH&side=1&offset=0&limit=10
		messageId++;
		String market = request.getParameter("market");
		int side = Integer.parseInt(request.getParameter("side"));
		int offset = Integer.parseInt(request.getParameter("offset"));
		int limit = Integer.parseInt(request.getParameter("limit"));

		JSONObject json = new JSONObject();
		JSONArray params = new JSONArray();
		params.add(market);
		params.add(side);
		params.add(offset);
		params.add(limit);
		
		json.put("id", messageId);
		json.put("method", "order.book");
		json.put("params", params);
		
		String s = HttpClient.doPost(SERVER, json.toString());
		response.getWriter().write(s);
	}
	protected void doDepth(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// http://192.168.1.144:8080/Abcmint/trade.do?cmd=depth&market=BTCBCH&interval=0&limit=10
		messageId++;
		String market = request.getParameter("market");
		int limit = Integer.parseInt(request.getParameter("limit"));
		String interval = request.getParameter("interval");

		JSONObject json = new JSONObject();
		JSONArray params = new JSONArray();
		params.add(market);
		params.add(limit);
		params.add(interval);
		
		json.put("id", messageId);
		json.put("method", "order.depth");
		json.put("params", params);
		
		String s = HttpClient.doPost(SERVER, json.toString());
		response.getWriter().write(s);
	}
	protected void doPending(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		http://192.168.1.144:8080/Abcmint/trade.do?cmd=pending&userid=1&market=BTCBCH&offset=0&limit=10
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
		json.put("method", "order.pending");
		json.put("params", params);
		
		String s = HttpClient.doPost(SERVER, json.toString());
		response.getWriter().write(s);
	}
	protected void doPendingDetail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// http://192.168.1.144:8080/Abcmint/trade.do?cmd=pendingDetail&orderid=7&market=BTCBCH
		messageId++;
		String market = request.getParameter("market");
		int orderid = Integer.parseInt(request.getParameter("orderid"));

		JSONObject json = new JSONObject();
		JSONArray params = new JSONArray();
		params.add(market);
		params.add(orderid);
		
		json.put("id", messageId);
		json.put("method", "order.pending_detail");
		json.put("params", params);
		
		String s = HttpClient.doPost(SERVER, json.toString());
		response.getWriter().write(s);
	}
	protected void doFinished(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//  http://192.168.1.144:8080/Abcmint/trade.do?cmd=finished&userid=1&market=BTCBCH&start=0&end=0&offset=0&limit=10&side=1
		
		messageId++;
		int userid = Integer.parseInt(request.getParameter("userid"));
		String market = request.getParameter("market");
		int  start = Integer.parseInt(request.getParameter("start"));
		int end = Integer.parseInt(request.getParameter("end"));
		int offset = Integer.parseInt(request.getParameter("offset"));
		int limit = Integer.parseInt(request.getParameter("limit"));
		int side = Integer.parseInt(request.getParameter("side"));

		JSONObject json = new JSONObject();
		JSONArray params = new JSONArray();
		params.add(userid);
		params.add(market);
		params.add(start);
		params.add(end);
		params.add(offset);
		params.add(limit);
		params.add(side);
		
		json.put("id", messageId);
		json.put("method", "order.finished");
		json.put("params", params);
		
		String s = HttpClient.doPost(SERVER, json.toString());
		response.getWriter().write(s);
	}
	protected void doFinishedDetail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// http://192.168.1.144:8080/Abcmint/trade.do?cmd=finishedDetail&orderid=7
		messageId++;
		int orderid = Integer.parseInt(request.getParameter("orderid"));

		JSONObject json = new JSONObject();
		JSONArray params = new JSONArray();
		params.add(orderid);
		
		json.put("id", messageId);
		json.put("method", "order.finished_detail");
		json.put("params", params);
		
		String s = HttpClient.doPost(SERVER, json.toString());
		response.getWriter().write(s);
	}
}
