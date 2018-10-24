package hr.dskugor.urlshortener.repositories;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import hr.dskugor.urlshortener.mappers.AuthorityMapper;
import hr.dskugor.urlshortener.models.Authority;

@Repository
public class AuthorityRepository {
	
		@Autowired
		JdbcTemplate jdbcTemplate;

		public List<Authority> findAll() {
			return jdbcTemplate.query("select * from demo.authorities", new AuthorityMapper());
		}

		public Authority findByUsername(String username) {
			try {
				return jdbcTemplate.queryForObject("select * from demo.authorities where username=?", new Object[] { username },
						new BeanPropertyRowMapper<Authority>(Authority.class));
			} catch (EmptyResultDataAccessException e) {
				return null;
			}
		}

		public int insert(Authority authority) {
			return jdbcTemplate.update("insert into demo.authorities (username, authority) " + " values(?, ?)",
					new Object[] { authority.getUsername(), authority.getAuthority() });
		}

	}

