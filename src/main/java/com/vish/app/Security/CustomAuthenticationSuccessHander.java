package com.vish.app.Security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.vish.app.Entity.User;
import com.vish.app.Services.UserService;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Component
public class CustomAuthenticationSuccessHander implements AuthenticationSuccessHandler{
	
	@Autowired
	private UserService userService;

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		
		String email = authentication.getName();
		User user = userService.findUserByEmail(email);
		
		// use in session
		HttpSession session = request.getSession();
		session.setAttribute("user", user);

		// send to home page
		response.sendRedirect(request.getContextPath() + "/");
		
	}

}
