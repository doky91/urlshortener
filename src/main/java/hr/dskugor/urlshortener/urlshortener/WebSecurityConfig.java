package hr.dskugor.urlshortener.urlshortener;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
	@Autowired
	private DataSource dataSource;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication().dataSource(this.dataSource)
				.usersByUsernameQuery("select username, password, enabled from demo.users where username=?")
				.authoritiesByUsernameQuery("select username, authority from demo.authorities where username=?")
				.passwordEncoder(NoOpPasswordEncoder.getInstance());
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		
		http.authorizeRequests()
		.antMatchers("/", "/js/*", "/statistics/**/*", "/help", "/help/URLSHORTENER_documentation.pdf").permitAll()
		.antMatchers(HttpMethod.POST, "/account").permitAll()
		.anyRequest().hasAnyRole("USER").and().httpBasic()
		.and().csrf().disable();
		
		http.headers().frameOptions().disable();
	}
}
