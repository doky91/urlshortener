package hr.dskugor.urlshortener.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import hr.dskugor.urlshortener.models.Link;
import hr.dskugor.urlshortener.repositories.LinkJdbcRepository;

@RestController
public class StatisticsController {
	@Autowired
	private LinkJdbcRepository linkRepository;

	@RequestMapping(value = "/statistic/{accountId}", method = RequestMethod.GET)
	public Map<String, Integer> redirectUrl(@PathVariable final String accountId) {
		Map<String, Integer> statistics = new HashMap<String, Integer>();

		List<Link> linkovi = linkRepository.findByUser(accountId);

		if (linkovi.isEmpty()) {
			return statistics;
		}

		for (Link link : linkovi) {
			statistics.put(link.getShortUrl(), link.getLinkVisits());
		}

		return statistics;
	}

}
