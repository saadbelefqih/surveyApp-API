package ma.surveyapp.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import ma.surveyapp.exception.ApiNotFoundException;
import ma.surveyapp.model.UserForm;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter{
	private AuthenticationManager authenticationManager;
	
	
	public JWTAuthenticationFilter( AuthenticationManager authenticationManager) {
		this.authenticationManager=authenticationManager;
	}
	
	/* Trigger when we issue POST request to /login
    We also need to pass in {"username":"", "password":""} in the request body
     */
	
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		try {
			// get object USERAPP from FORMAT JSON
			UserForm userForm =new ObjectMapper().readValue(request.getInputStream(),UserForm.class);
			return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userForm.getUsername(), userForm.getPassword()));
		} catch (IOException e) {
			e.printStackTrace();
			throw new ApiNotFoundException(e.getMessage());
		}
	}
	
	
	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		User user=null;
		user=(User) authResult.getPrincipal();//retourner l objet user Authentifié
		List<String> roles=new ArrayList<>();
		authResult.getAuthorities().forEach(authorities->{
			roles.add(authorities.getAuthority());
		});
		// Create JWT Token
		String jwt=JWT.create()
				.withIssuer(request.getRequestURI())
				.withSubject(user.getUsername())//subject => nom utilisateur
				.withArrayClaim("roles", roles.toArray(new String[roles.size()]))
				.withExpiresAt(new Date(System.currentTimeMillis()+SecurityParams.JWT_EXPIRATION_IN_MS))
				.sign(Algorithm.HMAC256(SecurityParams.JWT_SECRET));
		response.addHeader(SecurityParams.JWT_HEADER_NAME,SecurityParams.JWT_HEADER_PREFIX+ jwt);
	}

}
