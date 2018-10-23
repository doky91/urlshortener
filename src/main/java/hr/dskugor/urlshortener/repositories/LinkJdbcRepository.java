package hr.dskugor.urlshortener.repositories;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import hr.dskugor.urlshortener.mappers.LinkMapper;
import hr.dskugor.urlshortener.models.Link;

@Repository
public class LinkJdbcRepository {

	@Autowired
	JdbcTemplate jdbcTemplate;

	public List<Link> findAll() {
		return jdbcTemplate.query("select * from demo.links", new LinkMapper());
	}

	public Link findByUrl(String url) {
		try {
			return jdbcTemplate.queryForObject("select * from demo.links where url=?", new Object[] { url },
					new BeanPropertyRowMapper<Link>(Link.class));
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	public Link findByShortUrl(String url) {
		try {
			return jdbcTemplate.queryForObject("select * from demo.links where short_url=?", new Object[] { url },
					new BeanPropertyRowMapper<Link>(Link.class));
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	public List<Link> findByUser(String user) {
		try {
			return jdbcTemplate.query("select * from demo.links where user=?", new Object[] { user },
					new LinkMapper());
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}
	
	public int insert(Link link) {
		return jdbcTemplate.update(
				"insert into demo.links (url, short_url, user, redirect_type,  link_visits) "
						+ " values( ?, ?, ?, ?, ?)",
				new Object[] { link.getUrl(), link.getShortUrl(), link.getUser(), link.getRedirectType(),
						link.getLinkVisits() });
	}

	public int incrementLinkVisits(Link link) {
		return jdbcTemplate.update("update demo.links " + " set link_visits = link_visits+1 " + " where url = ?",
				new Object[] { link.getUrl() });
	}
}
