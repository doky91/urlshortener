package hr.dskugor.urlshortener.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import hr.dskugor.urlshortener.models.AccountRequest;
import hr.dskugor.urlshortener.models.AccountResponse;
import hr.dskugor.urlshortener.models.User;
import hr.dskugor.urlshortener.repositories.UserJdbcRepository;
import hr.dskugor.urlshortener.utils.RandomStringGenerator;

@RestController
public class AccountController {

	@Autowired
	private UserJdbcRepository userRepository;

	@Autowired
	private RandomStringGenerator generator;

	@RequestMapping(value = "/account", method = RequestMethod.POST)
	public AccountResponse account(@RequestBody(required = false) AccountRequest accRequest) {

		AccountResponse accResponse = new AccountResponse();
		User user;

		if (accRequest == null || accRequest.getAccountId().equals("")) {
			accResponse.setDescription("No valid account id provided!");
			return accResponse;
		}
		
		user = userRepository.findByUsername(accRequest.getAccountId());

		if (user != null) {
			accResponse.setSuccess(false);
			accResponse.setDescription("User with ID : " + accRequest.getAccountId() + " already exists!");
			return accResponse;
		}

		user = setUser(accRequest);
		accResponse.setSuccess(true);
		accResponse.setDescription("Your account is open");
		accResponse.setPassword(user.getPassword());

		return accResponse;
	}

	private User setUser(AccountRequest accRequest) {

		User user = new User();

		String password = generator.generateString();
		user.setPassword(password);
		user.setUsername(accRequest.getAccountId());
		user.setEnabled(true);
		userRepository.insert(user);
		return user;
	}

}