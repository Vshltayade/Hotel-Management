package com.vish.app.Controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;

@Controller
public class ErrorController1 implements ErrorController{

	// for error
	@GetMapping("/error")
	public String error(HttpServletRequest request) {
		Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
	    System.out.println("Error occurred, status code: " + status);
	    return "redirect:/";
	}
	
	// error path
//	public String getErrorPath() {
//		return null;
//	}
}
