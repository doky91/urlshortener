package hr.dskugor.urlshortener.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import hr.dskugor.urlshortener.models.Authority;

public class AuthorityMapper implements RowMapper<Authority> {
	public Authority mapRow(ResultSet rs, int rowNum) throws SQLException {
		Authority authority = new Authority();
		authority.setUsername(rs.getString("username"));
		authority.setAuthority(rs.getString("authority"));
		return authority;
	}
}
