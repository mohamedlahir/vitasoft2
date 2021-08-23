package com.mylahir.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.mylahir.MyUserDetailServices.MyUserDetailService;
import com.mylahir.authenticationrequest.AuthenticationRequest;
import com.mylahir.authenticationresponse.AuthenticationResponse;
import com.mylahir.crudservice.CrudServices;
import com.mylahir.jwtutil.JwtUtil;
import com.mylahir.usermodel.UserModel;
import com.mylahir.userrepo.UserRepo;

@RestController
class HelloWorldController {
	
	@Autowired
	UserRepo repo;
	
	@Autowired
	CrudServices services;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtUtil jwtTokenUtil;

	@Autowired
	private MyUserDetailService userDetailsService;

	@RequestMapping({ "/hello" })
	public String firstPage() {
		return "Hello World";
	}

	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest)
			throws Exception {

		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
					authenticationRequest.getUsername(), authenticationRequest.getPassword()));
		} catch (BadCredentialsException e) {
			throw new Exception("Incorrect username or password", e);
		}

		final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());

		final String jwt = jwtTokenUtil.generateToken(userDetails);

		return ResponseEntity.ok(new AuthenticationResponse(jwt));
	}

	

@RequestMapping("/registration") // @ResponseBody 
public ModelAndView reg(HttpServletRequest request)
	{
		ModelAndView mv = new ModelAndView();
		mv.setViewName("registration");

		UserModel model = new UserModel();
		String username = request.getParameter("username");
		String firstname = request.getParameter("first_name");
		String middlename = request.getParameter("middle_name");
		String lastname = request.getParameter("last_name");
		String password = request.getParameter("password");
		String country = request.getParameter("country");
		String state = request.getParameter("state");
		String zipcode = request.getParameter("zipcode");
		String address = request.getParameter("Address");
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		model.setPassword(password);
		model.setCountry(country);
		model.setFirst_name(firstname);
		model.setMiddle_name(middlename);
		model.setLast_name(lastname);
		model.setUsername(username);
		model.setState(state);
		model.setZipcode(zipcode);
		model.setAddress(address);
		model.setPhone(phone);
		model.setEmail(email);
		if (model.getUsername() == null) {
			System.out.println("pls fill the form to save");
		} else {
			services.save(model);
		}

		return mv;
	}

	@RequestMapping(path = "/save")

	@ResponseBody
	public UserModel save(@RequestBody UserModel model) {
		services.save(model);
		return model;
	}

	@RequestMapping("/deletebyid/{id}")
 
 @ResponseBody public String deleteById(@PathVariable("id")int id) {
 
		repo.deleteById(id);
 
 
		return "Given ID is Deleted"; }
}

