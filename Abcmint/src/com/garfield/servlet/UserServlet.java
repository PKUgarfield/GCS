package com.garfield.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.garfield.dao.AbcuserDao;
import com.garfield.model.ABCUser;
import com.garfield.util.DBMysqlUtil;
import com.garfield.util.EmailUtil;
import com.garfield.util.SecurityUtil;

/**
 * Servlet implementation class UserServlet
 */
@WebServlet("/user.do")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private final static String REGISTER = "register";
	private final static String LOGIN = "login";
	private final static String ACTIVE = "active";
	private final static String CHGPASSWD = "chgpwd";
	private final static String LOGINOUT = "loginout";

	private String Key = "p4Ro5wpFkKYJ0WnY";
	Map<String, String> map = null;

	private AbcuserDao dao = null;

	public void init() throws ServletException {
		dao = new AbcuserDao(DBMysqlUtil.getInstance().getConnection());
		map = new HashMap<>();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String cmd = request.getParameter("cmd");

		switch (cmd) {
		case REGISTER:
			doRegister(request, response);
			break;
		case LOGIN:
			doLogin(request, response);
			break;
		case ACTIVE:
			doActive(request, response);
			break;
		case CHGPASSWD:
			doChangePasswd(request, response);
			break;
		case LOGINOUT:
			doLoginOut(request, response);
			break;
		default:
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	/****/
	protected void doRegister(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		boolean flag = true;
		do {
			String nickname = request.getParameter("nickname");
			String email = request.getParameter("email");
			String password = request.getParameter("password");

			if (nickname == null || email == null || password == null) {
				request.setAttribute("registerInfo", "All the info can't be empty.");
				flag = false;
				break;
			}

			// check if nickname&email is used
			ABCUser isEmailUsed = dao.getUserByEmail(email);
			if (null != isEmailUsed) {
				request.setAttribute("registerInfo", "Email is used.");
				flag = false;
				break;
			}
			ABCUser isNicknameUsed = dao.getUserByNickname(nickname);
			if (null != isNicknameUsed) {
				request.setAttribute("registerInfo", "Name is used.");
				flag = false;
				break;
			}

			// write to mysql
			ABCUser newUser = new ABCUser();
			newUser.nickname = nickname;
			newUser.email = email;
			newUser.country = -1;
			newUser.password = SecurityUtil.getSHA(password);

			dao.addUser(newUser);
			newUser = dao.getUserByEmail(email);
			// send active email
			String auth = "uid=" + newUser.id + "&time=" + new Date().getTime();
			String url = "";
			try {
				url = "http://localhost:8080/Abcmint/user.do?cmd=active&auth=" + SecurityUtil.AESEncrypt(auth, Key);
			} catch (Exception e) {
			}
			EmailUtil.sendActiveEmail(email, url);

			request.setAttribute("email", email);
		} while (false);

		if (flag)
			request.getRequestDispatcher("active.jsp").forward(request, response);
		else {
			request.setAttribute("action", "register");
			request.getRequestDispatcher("user.jsp").forward(request, response);
		}
	}

	protected void doLogin(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Object loginAttr = request.getSession().getAttribute("loginFlag");

		if(loginAttr != null) System.out.println(loginAttr.toString());
		if(loginAttr == null || loginAttr.toString().equals("false")) {		
			String email = request.getParameter("email");
			String password = request.getParameter("password");
	
			request.setAttribute("action", "login");
			if (email == null || password == null) {
				request.getRequestDispatcher("user.jsp").forward(request, response);
				return;
			}
	
			ABCUser user = dao.getUserByEmail(email);
			boolean flag = false;
			if(user.active==0) {
				request.setAttribute("loginInfo", "The account is not active.");
			}else if (null != user && user.active==1 && user.password.equals(SecurityUtil.getSHA(password))) {
				flag = true;
			}else
				request.setAttribute("loginInfo", "Email is not exist or password is wrong.");
	
			if (flag) {
				request.getSession().setAttribute("user", user);
				request.getSession().setAttribute("loginFlag", "true");
				request.getRequestDispatcher("index_login.jsp").forward(request, response);
			} else {
				request.getRequestDispatcher("user.jsp").forward(request, response);
			}
		}else { // already login
			request.getRequestDispatcher("index_login.jsp").forward(request, response);
		}
	}

	protected void doActive(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String auth = request.getParameter("auth");
		if (auth == null)
			return;
		try {
			auth = SecurityUtil.AESDecrypt(auth, Key);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (auth == null)
			return;

		String params[] = auth.split("&");
		for (String param : params) {
			String kv[] = param.split("=");
			map.put(kv[0], kv[1]);
		}
		String uid = map.get("uid");
		String time = map.get("time");
		boolean flag = true;
		if (new Date().getTime() - Long.parseLong(time) > 20 * 60 * 1000) // timeout
			flag = false;

		ABCUser user = null;
		if (flag) {
			user = dao.getUserById(Integer.parseInt(uid));
			if (user == null)
				flag = false;
			else
				user.active = 1;
			dao.activeUser(user);
		}

		// ������Ӧ��������
		if (flag) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			String title = "激活成功";
			String docType = "<!DOCTYPE html> \n";
			out.println(docType + "<html>\n" + "<head><title>" + title + "</title></head>\n"
					+ "<body bgcolor=\"#f0f0f0\">\n" + "<h1 align=\"center\">" + title + "</h1>\n"
					+ "<p align=\"center\"> info: " + user.nickname + "</p>\n" + "</body></html>");
		} else {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			String title = "激活失败";
			String docType = "<!DOCTYPE html> \n";
			out.println(docType + "<html>\n" + "<head><title>" + title + "</title></head>\n"
					+ "<body bgcolor=\"#f0f0f0\">\n" + "<h1 align=\"center\">" + title + "</h1>\n"
					+ "<p align=\"center\"> info </p>\n" + "</body></html>");
		}
	}

	protected void doChangePasswd(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}
	
	protected void doLoginOut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getSession().setAttribute("loginFlag", "false");
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}
}
