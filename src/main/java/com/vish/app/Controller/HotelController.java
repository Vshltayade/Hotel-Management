package com.vish.app.Controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.vish.app.Services.ReservationService;
import com.vish.app.Services.UserService;
import com.vish.app.temp.CurrentReservation;
import com.vish.app.temp.CurrentUser;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;



@Controller
public class HotelController {
	
	// inject services
	@Autowired
	private UserService userService;
	
	@Autowired
	private ReservationService reservationService;
	
	
	// constructor
	public HotelController(UserService us, ReservationService rs) {
		this.userService = us;
		this.reservationService = rs;
	}
	
	
	// bind data
	@InitBinder
	public void initBinder(WebDataBinder webDataBinder) {
		StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
		webDataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
	}

	// APIs
	// home page
	@GetMapping("/")
	public String homePage() {
		return "index";
	}
	
	
	// login page
	@GetMapping("/login")
	public String loginPage(Model model) {
		
		// if logged in - redirect to home
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if(!(authentication instanceof AnonymousAuthenticationToken)) {
			return "redirect:/";
		}
		
		// for new user, add model attr
		model.addAttribute("newUser", new CurrentUser());
		System.out.println(model);
		return "login";
	}
	
	
	// process login
	@PostMapping("/registration-process")
	public String processRegistration(@Valid @ModelAttribute("newUser") CurrentUser currentUser, BindingResult bindingResult, Model model) {

		// user already exist
		if(userService.findUserByEmail(currentUser.getEmail()) != null) {
			model.addAttribute("newUser", new CurrentUser());
			model.addAttribute("registrationError", "Email Already Exists.");
			
			return "login";
		}
		
		// create user
		userService.saveUser(currentUser);
		model.addAttribute("registrationSuccess", "Successfully Registered.");
		
		return "redirect:/login";
	}
	
	
	// new reservation page
	@GetMapping("/new-reservation")
	public String newReservation(Model model) {
		
		model.addAttribute("newRes", new CurrentReservation());
		
		return "reservation";
	}
	
	
	// save reservation
	@PostMapping("/process-reservation")
	public String processReservation(@Valid @ModelAttribute("newRes") CurrentReservation currentReservation, BindingResult bindingResult, Model model) {
		
		reservationService.saveOrUpdateReservation(currentReservation);
		
		return "redirect:/your-reservations";
	}
	
	
	// list reservations
	@GetMapping("/your-reservations")
	public String yourReservations(Model model) {
		
		model.addAttribute("resList", reservationService.getReservationsForLoggedUser());
		
		return "your-reservation";
	}
	
	
	// update reservation
	@PostMapping("/reservation-update")
	public String updateReservation(@RequestParam("resId") int resId, Model model) {
		
		model.addAttribute("newRes", reservationService.reservationToCurrentReservationById(resId));
		
		return "reservation";
	}
	
	
	// delete reservation
	@PostMapping("reservation-delete")
	public String deleteReservation(@RequestParam("resId") int resId) {
		
		reservationService.deleteReservation(resId);
		
		return "redirect:/your-reservations";
	}
	
	
	// log out
	@GetMapping("/logout")
	public String logout(HttpServletRequest request, HttpServletResponse response) {
		
		// log user out
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		if(authentication != null) {
			new SecurityContextLogoutHandler().logout(request, response, authentication);
		}
		
		return "redirect:/login?logout";
	}
	
	
	
}
