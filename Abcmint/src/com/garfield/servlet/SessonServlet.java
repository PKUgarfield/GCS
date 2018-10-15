package com.garfield.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/session.do")
public class SessonServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String referer = request.getHeader("referer");
		response.getWriter().write(referer + "  " + request.getServerName() + "  " + request.getRequestedSessionId());
		response.getWriter().write(request.getSession().getId() +"\n");
		request.getSession().setMaxInactiveInterval(1800);
		response.getWriter().write(request.getSession().getMaxInactiveInterval() +"\n");
		response.getWriter().write(request.getSession().getAttributeNames() +"\n");
		response.getWriter().write(request.getSession().getCreationTime() +"\n");
		response.getWriter().write(request.getSession().getLastAccessedTime() +"\n");
		response.getWriter().write(request.getSession().isNew() +"\n");
	}
}
