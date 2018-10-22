package hr.dskugor.urlshortener.controllers;

import java.security.SecureRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import hr.dskugor.urlshortener.models.AccountRequest;
import hr.dskugor.urlshortener.models.AccountResponse;
import hr.dskugor.urlshortener.models.User;
import hr.dskugor.urlshortener.repositories.UserJdbcRepository;

@RestController
public class AccountResponseController {

	@Autowired
	UserJdbcRepository userRepository;

	@RequestMapping(value = "/account", method = RequestMethod.POST)
	public AccountResponse account(@RequestBody(required = false) AccountRequest accRequest) {

		AccountResponse accResponse = new AccountResponse();

		if (accRequest == null || accRequest.getAccountId().equals("")) {
			accResponse.setDescription("No valid account id provided!");
			return accResponse;
		}

		if (userRepository.findByUsername(accRequest.getAccountId()) != null) {
			accResponse.setSuccess(false);
			accResponse.setDescription("User with ID : " + accRequest.getAccountId() + " already exists!");
		}

		else {
			User user = new User();
			accResponse.setSuccess(true);
			accResponse.setDescription("Your account is open");
			String password = passwordGenerator();
			accResponse.setPassword(password);
			user.setPassword(password);
			user.setUsername(accRequest.getAccountId());
			user.setEnabled(true);
			userRepository.insert(user);
		}
		return accResponse;

	}

	private String passwordGenerator() {

		final String allPossibleValues = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
		final SecureRandom rnd = new SecureRandom();

		StringBuilder sb = new StringBuilder(8);
		for (int i = 0; i < 8; i++)
			sb.append(allPossibleValues.charAt(rnd.nextInt(allPossibleValues.length())));

		return sb.toString();

	}
}