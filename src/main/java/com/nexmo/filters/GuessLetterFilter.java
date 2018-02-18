package com.nexmo.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

import com.nexmo.dao.PlayerDataRepository;
import com.nexmo.dto.HangmanSessionBean;

@Component
@DependsOn("hangmanSessionBean")
public class GuessLetterFilter implements Filter {

	@Autowired
	private PlayerDataRepository playerDataRepo;
	
	@Autowired
	private HangmanSessionBean hangmanSessionBean;	
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		String sessionId = req.getSession().getId();
		hangmanSessionBean.setSessionId(sessionId);
		chain.doFilter(req, resp);
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

}
