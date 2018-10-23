package hr.dskugor.urlshortener.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import hr.dskugor.urlshortener.models.Link;
import hr.dskugor.urlshortener.models.UrlRequest;
import hr.dskugor.urlshortener.models.UrlResponse;
import hr.dskugor.urlshortener.models.User;
import hr.dskugor.urlshortener.repositories.LinkJdbcRepository;
import hr.dskugor.urlshortener.repositories.UserJdbcRepository;
import hr.dskugor.urlshortener.utils.RandomStringGenerator;

@RestController
@PropertySource("classpath:application.properties")
public class URLController {
	@Autowired
	private Environment env;

	@Autowired
	private LinkJdbcRepository linkRepository;

	@Autowired
	private UserJdbcRepository userRepository;

	@Autowired
	private RandomStringGenerator generator;

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public UrlResponse shorterUrl(@RequestBody UrlRequest urlRequest) {

		UrlResponse urlResponse = new UrlResponse();

		Link link = linkRepository.findByUrl(urlRequest.getUrl());

		if (urlRequest == null || urlRequest.getUrl().equals("")) {
			urlResponse.setSuccess(false);
			urlResponse.setDescription("No valid url provided!");
			return urlResponse;
		}

		if (urlRequest.getRedirectType() == null) {
			urlRequest.setRedirectType(302);
		}

		else if (urlRequest.getRedirectType() != 301 && urlRequest.getRedirectType() != 302) {
			urlResponse.setSuccess(false);
			urlResponse.setDescription("Redirect type should be 301 or 302!");
			return urlResponse;
		}

		if (link == null) {
			link = setLink(urlRequest);
			urlResponse.setDescription("Your URL is shorten!");
		}

		else {
			urlResponse.setDescription("Your URL is already shorten!");
		}

		urlResponse.setShortUrl(link.getShortUrl());
		urlResponse.setSuccess(true);

		return urlResponse;
	}

	private Link setLink(UrlRequest urlRequest) {
		// --- for testing purposes
		User user = new User();
		user.setUsername("Dolores");
		userRepository.insert(user);
		// ---

		Link link = new Link();

		String formattedLink = env.getProperty("app.url") + "/short/" + generator.generateString();

		link.setUrl(urlRequest.getUrl());
		link.setUser(user.getUsername());
		link.setShortUrl(formattedLink);
		link.setLinkVisits(0);
		link.setRedirectType(urlRequest.getRedirectType());

		linkRepository.insert(link);

		return link;
	}

}
