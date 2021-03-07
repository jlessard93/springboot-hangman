package com.nexmo.filters;

import org.springframework.stereotype.Component;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class CookieSetter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		//do nothing
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;

		if (req.getSession().isNew()) {
			String sessionId = req.getSession().getId();
			Cookie cookie = new Cookie("JSESSIONID", sessionId);
			cookie.setMaxAge(Integer.MAX_VALUE);
			resp.addCookie(cookie);
		}

		chain.doFilter(req, resp);
	}

	@Override
	public void destroy() {
		//do nothing
	}

}
