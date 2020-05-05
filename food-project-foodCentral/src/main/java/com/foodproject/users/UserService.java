package com.foodproject.users;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class UserService implements UsersInterface {

	@Autowired
	UsersRepository repo;

	// =========================================================

	@Autowired
	RestTemplate restTemplate;

	@Override
	public String signUp(Users user) {
		if (getUserEmail(user.getEmail())) {
			return "email exists";
		} else {
			// here we will call 2FA micro service and we will save the details in db from
			// 2FA microservice
			// we will have to remove repo.save()
			// repo.save(user);
			String url = "http://localhost:5005/fa/auth";
			return restTemplate.postForObject(url, user, String.class);

		}
	}

	@Override
	public boolean getUserEmail(String email) {
		int e = repo.getUserByEmail(email);
		if (e >= 1) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public String signIn(String userName, String userPassword) {
		int k = repo.getUserByCred(userName, userPassword);
		if (k >= 1) {
			return "login successfull";
		} else {
			return "login failed";
		}
	}

	@Override
	public Optional<Users> getDetailsByUserId(Integer id) {
		return repo.findById(id);
	}

	@Override
	public String save(Users user) {

		if (repo.save(user) != null) {
			return "User Sign-up Successfull !";
		} else {
			return "Something went wrong while saving your data! \nPlease try again...";
		}
	}

	// =========================================================

}
