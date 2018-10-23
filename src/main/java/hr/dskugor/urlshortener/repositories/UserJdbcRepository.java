package hr.dskugor.urlshortener.repositories;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import hr.dskugor.urlshortener.mappers.UserMapper;
import hr.dskugor.urlshortener.models.User;

@Repository
public class UserJdbcRepository {
	@Autowired
	JdbcTemplate jdbcTemplate;

	public List<User> findAll() {
		return jdbcTemplate.query("select * from demo.users", new UserMapper());
	}

	public User findByUsername(String username) {
		try {
			return jdbcTemplate.queryForObject("select * from demo.users where username=?", new Object[] { username },
					new BeanPropertyRowMapper<User>(User.class));
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	public int insert(User user) {
		return jdbcTemplate.update("insert into demo.users (username, password, enabled) " + " values(?, ?, ?)",
				new Object[] { user.getUsername(), user.getPassword(), user.isEnabled() });
	}

}
