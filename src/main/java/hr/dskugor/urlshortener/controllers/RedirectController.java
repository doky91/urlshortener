package hr.dskugor.urlshortener.controllers;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import hr.dskugor.urlshortener.models.Link;
import hr.dskugor.urlshortener.repositories.LinkJdbcRepository;

@RestController
@PropertySource("classpath:application.properties")
public class RedirectController {
	@Autowired
	private LinkJdbcRepository linkRepository;
	@Autowired
	private Environment env;

	@RequestMapping(value = "/short/{shortLink}", method = RequestMethod.GET)
	public String redirectUrl(@PathVariable final String shortLink, HttpServletResponse httpServletResponse) {
		Link link = new Link();

		link = linkRepository.findByShortUrl(env.getProperty("app.url") + "/short/" + shortLink);

		if (link == null) {

			return "Greška!";
		}

		linkRepository.incrementLinkVisits(link);
		httpServletResponse.setHeader("Location", link.getUrl());
		httpServletResponse.setStatus(link.getRedirectType());
		return "";

	}

}
