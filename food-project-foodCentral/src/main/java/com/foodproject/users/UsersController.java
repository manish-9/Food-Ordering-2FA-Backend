package com.foodproject.users;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "", allowedHeaders = "")
public class UsersController {

	@ApiModelProperty(notes="autowired service for this api")
	@Autowired
	UserService service;

	@ApiOperation(value = "creating a new user", notes = "Hit this URL for creating an new User", response = List.class)
	@RequestMapping(method = RequestMethod.POST, value = "/signup")
	public String signUp(@RequestBody Users user) {
		return service.signUp(user);
	}

	@ApiOperation(value = "Logging into the User account", notes = "Hit this URL for logging into the User account")
	@RequestMapping("/signin/{email}/{password}")
	public String signIn(@PathVariable String email, @PathVariable String password) {
		return service.signIn(email, password);
	}

	@ApiOperation(value = "Find the User by their id", notes = "Hit this URL for getting the User by their ID")
	@RequestMapping("/{id}")
	Optional<Users> getDetailsByUserId(@PathVariable Integer id) {
		return service.getDetailsByUserId(id);
	}

	@ApiOperation(value = "for saving the User", notes = "Hit this URL for saving the User")
	@RequestMapping(method = RequestMethod.POST, value = "/save")
	String saveUser(@RequestBody Users user) {
		return service.save(user);
	}

}
