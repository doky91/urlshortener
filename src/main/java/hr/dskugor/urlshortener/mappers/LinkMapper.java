package hr.dskugor.urlshortener.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import hr.dskugor.urlshortener.models.Link;


public class LinkMapper implements RowMapper<Link> {
	public Link mapRow(ResultSet rs, int rowNum) throws SQLException {
		Link link = new Link();
		link.setUrl(rs.getString("url"));
		link.setShortUrl(rs.getString("short_url"));
		link.setUser(rs.getString("user"));
		link.setLinkVisits(rs.getInt("link_visits"));
	
		return link;
	}

}
