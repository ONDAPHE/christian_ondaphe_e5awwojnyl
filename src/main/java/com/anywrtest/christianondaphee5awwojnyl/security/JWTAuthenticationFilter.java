package com.anywrtest.christianondaphee5awwojnyl.security;

import com.anywrtest.christianondaphee5awwojnyl.entities.SchoolUser;
import com.anywrtest.christianondaphee5awwojnyl.repositories.SchoolUserRepository;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	private AuthenticationManager authenticationManager;
	
	@Autowired
	private SchoolUserRepository userRepository;
	
	public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
	}
	
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) {
		try{
			SchoolUser user = new ObjectMapper().readValue(request.getInputStream(), SchoolUser.class);
			return authenticationManager
					.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
		}catch(Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	@Override
	public void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
		User user = (User) authResult.getPrincipal();
		List<String> roles = new ArrayList<>();
		authResult.getAuthorities().forEach(authority->{
			roles.add(authority.getAuthority());
		});
		Instant expiration = Instant.now().plus(SecurityUtils.TOKEN_VALIDITY, ChronoUnit.MINUTES);

		final String jwt = JWT.create()
							.withIssuer(SecurityUtils.TOKEN_ISSUER)
							.withIssuedAt(new Date())
							.withSubject(user.getUsername())
							.withArrayClaim(SecurityUtils.CLAIM_ROLES, roles.toArray(new String[roles.size()]))
							.withExpiresAt(expiration)
							.sign(Algorithm.HMAC256(SecurityUtils.PRIVATE_SECRET));
		response.addHeader(SecurityUtils.JWT_HEADER, jwt);

	}

}
