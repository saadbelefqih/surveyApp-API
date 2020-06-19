package ma.surveyapp.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

public class JWTAuthorizationFilter extends OncePerRequestFilter{

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		//<!-- Enable cross origin HTTP requests
		response.addHeader("Access-Control-Allow-Origin", "*");
		response.addHeader("Access-Control-Allow-Headers","Origin,Accept, X-Requested-With,Content-Type, Access-Control-Request-Method,Access-Control-Request-Headers,Authorization");
		response.addHeader("Access-Control-Expose-Headers", "Access-Control-Allow-Origin,Access-Control-Allow-Credentials,Authorization");
		response.addHeader("Access-Control-Allow-Methods", "GET,POST,PUT,DELETE");
		if(request.getMethod().equals("OPTIONS"))
		{
			response.setStatus(HttpServletResponse.SC_OK);
		}
		if(request.getRequestURI().equals("/login")){
			filterChain.doFilter(request, response);
			return;
		}
		//!>
		//get header of token
		String jwtToken=request.getHeader(SecurityParams.JWT_HEADER_NAME);
		//in case header of token not containing jwt header name or prefix
		if(jwtToken==null || (!jwtToken.startsWith(SecurityParams.JWT_HEADER_PREFIX)) || (request.getRequestURI().equals("/login")) || (request.getRequestURI().equals("/register")))
		{
			//passer au filtre suivant
			filterChain.doFilter(request, response);
			return;
		}
		else{
			try{
				
				JWTVerifier verifier=JWT.require(Algorithm.HMAC256(SecurityParams.JWT_SECRET)).build();
				String jwt=jwtToken.replace(SecurityParams.JWT_HEADER_PREFIX, "");
				DecodedJWT decodedJWT=verifier.verify(jwt);
				String username=decodedJWT.getSubject();
				if(!username.equals(null))
					{
					List<String>roles=decodedJWT.getClaims().get("roles").asList(String.class);
					Collection<GrantedAuthority> authorities = new ArrayList<>();
					roles.forEach(role->{
						authorities.add(new SimpleGrantedAuthority(role));
					});
					//preparer ath
					UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(username,null,authorities);
					//demander à spring d'authentifier cet utilisateur
					SecurityContextHolder.getContext().setAuthentication(auth);
					//passer au filtre suivant
					filterChain.doFilter(request, response);
					}
			}
			catch (Exception e) {
				response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
				System.out.println(e.getMessage());
				SecurityContextHolder.clearContext();
			}
		}
		
	}

}
