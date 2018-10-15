package com.garfield.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.garfield.dao.AbcuserDao;
import com.garfield.util.DBMysqlUtil;
import com.garfield.util.HttpClient;

/**
 * Servlet implementation class BalanceServlet
 */
@WebServlet("/asset.do")
public class AssetServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private final static String BALANCE_QUERY = "bquery";
	private final static String BALANCE_UPDATE = "bupdate";
	private final static String BALANCE_HISTORY = "bhistory";
	private final static String ASSET_LIST = "alist";
	private final static String ASSET_SUMMARY = "asummary";
	
	private final static String SERVER = "http://192.168.1.149:8080";
	
	private static int messageId = 0;
	private static Map<String, Integer> ids;

	public void init() throws ServletException {
		ids = new HashMap<>();
		ids.put("deposit", 0);
	}
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String cmd = request.getParameter("cmd");

		switch (cmd) {
		case BALANCE_QUERY:
			doBalanceQuery(request, response);
			break;
		case BALANCE_UPDATE:
			doBalanceUpdate(request, response);
			break;
		case BALANCE_HISTORY:
			doBalanceHistory(request, response);
			break;
		case ASSET_LIST:
			doAssetList(request, response);
			break;
		case ASSET_SUMMARY:
			doAssetSummary(request, response);
			break;
		default:
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	/****/
	protected void doBalanceQuery(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// http://192.168.1.144:8080/Abcmint/asset.do?cmd=bquery&asset=BTC&userid=1
		messageId++;
		int userid = Integer.parseInt(request.getParameter("userid"));
		String asset = request.getParameter("asset");
		JSONObject json = new JSONObject();
		JSONArray params = new JSONArray();
		params.add(userid);
		params.add(asset);
		
		json.put("id", messageId);
		json.put("method", "balance.query");
		json.put("params", params);
		
		String s = HttpClient.doPost(SERVER, json.toString());
		response.getWriter().write(s);
	}
	protected void doBalanceUpdate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// http://192.168.1.144:8080/Abcmint/asset.do?cmd=bupdate&asset=BTC&userid=1&business=deposit&change=2.56
		messageId++;
		int userid = Integer.parseInt(request.getParameter("userid"));
		String asset = request.getParameter("asset");
		String business = request.getParameter("business");
		String change = request.getParameter("change");
//		String detail = request.getParameter("detail");
		
		int business_id = ids.get(business);
		ids.put(business, business_id+1);
		
		JSONObject json = new JSONObject();
		JSONArray params = new JSONArray();
		params.add(userid);
		params.add(asset);
		params.add(business);
		params.add(business_id);
		params.add(change);
		params.add(new JSONObject());
		
		json.put("id", messageId);
		json.put("method", "balance.update");
		json.put("params", params);
		
		String s = HttpClient.doPost(SERVER, json.toString());
		response.getWriter().write(s);
	}
	protected void doBalanceHistory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// http://192.168.1.144:8080/Abcmint/asset.do?cmd=bhistory&asset=BTC&userid=1&business=deposit&startTime=0&endTime=0&offset=0&limit=10
		messageId++;
		int userid = Integer.parseInt(request.getParameter("userid"));
		String asset = request.getParameter("asset");
		String business = request.getParameter("business");
		int startTime = Integer.parseInt(request.getParameter("startTime"));
		int endTime = Integer.parseInt(request.getParameter("endTime"));
		int offset = Integer.parseInt(request.getParameter("offset"));
		int limit = Integer.parseInt(request.getParameter("limit"));
		
		JSONObject json = new JSONObject();
		JSONArray params = new JSONArray();
		params.add(userid);
		params.add(asset);
		params.add(business);
		params.add(startTime);
		params.add(endTime);
		params.add(offset);
		params.add(limit);
		
		json.put("id", messageId);
		json.put("method", "balance.history");
		json.put("params", params);
		
		String s = HttpClient.doPost(SERVER, json.toString());
		response.getWriter().write(s);
	}
	protected void doAssetList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// http://192.168.1.144:8080/Abcmint/asset.do?cmd=alist
		messageId++;
		JSONObject json = new JSONObject();
		JSONArray params = new JSONArray();
		
		json.put("id", messageId);
		json.put("method", "asset.list");
		json.put("params", params);
		
		String s = HttpClient.doPost(SERVER, json.toString());
		response.getWriter().write(s);
	}
	protected void doAssetSummary(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// http://192.168.1.144:8080/Abcmint/asset.do?cmd=asummary&assets=BTC
		messageId++;
		String[] assets = request.getParameter("assets").split(" ");
		JSONObject json = new JSONObject();
		JSONArray params = new JSONArray();
		for(String asset : assets)
			params.add(asset);
		
		json.put("id", messageId);
		json.put("method", "asset.summary");
		json.put("params", params);
		
		String s = HttpClient.doPost(SERVER, json.toString());
		response.getWriter().write(s);
	}
	
	

}
