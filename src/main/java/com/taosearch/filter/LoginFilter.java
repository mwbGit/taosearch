package com.taosearch.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.filter.OncePerRequestFilter;

import com.taosearch.model.SysUser;

public class LoginFilter extends OncePerRequestFilter {
	private static final String[] IGNORE_URI = { "/login/" , "/login.jsp","Android","/img/"};
	@Override
	protected void doFilterInternal(HttpServletRequest request,
			HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		boolean flag = false;
		String url = request.getRequestURL().toString();
		System.out.println(">>>: " + url);
		for (String s : IGNORE_URI) {
			if (url.contains(s)) {
				flag = true;
				break;
			}
		}
		System.out.println(flag);
		if (!flag) {
			SysUser user = (SysUser)request.getSession().getAttribute("loginUser");
			if (user == null) {
				request.setCharacterEncoding("UTF-8");
                response.setCharacterEncoding("UTF-8");
				response.sendRedirect(request.getContextPath()+"/home/login/login.jsp");
			}else{
				 filterChain.doFilter(request, response);
			}
		}else{
			filterChain.doFilter(request, response);
		}
	}
}
