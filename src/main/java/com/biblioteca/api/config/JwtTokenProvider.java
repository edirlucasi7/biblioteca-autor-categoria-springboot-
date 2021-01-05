package com.biblioteca.api.config;

import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import com.biblioteca.api.exception.InvalidJwtAuthenticationException;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class JwtTokenProvider {

	    private static String secretKey = "nZq4t7w!z%C*F-JaNdRgUkXp2s5u8x/A?D(G+KbPeShVmYq3t6w9y$B&E)H@McQf";

	    private long validityInMilliseconds = 36000000; // 1h

	    @Autowired
	    private UserDetailsService userDetailsService;

	    @PostConstruct
	    protected void init() {
	        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
	    }

	    public String createToken(String username, List<String> rolesUser) {

	        Claims claims = Jwts.claims().setSubject(username);
	        claims.put("roles", rolesUser);

	        Date now = new Date();
	        Date validity = new Date(now.getTime() + validityInMilliseconds);

	        return Jwts.builder()//
	                .setClaims(claims)//
	                .setIssuedAt(now)//
	                .setExpiration(validity)//
	                .signWith(SignatureAlgorithm.HS512, secretKey)//
	                .compact();
	    }
	    
	    public static Claims getClaims(String token) {
			byte[] signingKey = JwtTokenProvider.secretKey.getBytes();

			token = token.replace("Bearer ", "");

			return Jwts.parser()
					.setSigningKey(signingKey)
					.parseClaimsJws(token).getBody();
		}
	    
	    public static List<GrantedAuthority> getRoles(String token) {
	    	Claims claims = getClaims(token);
	    	
	    	if (claims == null) {
				return null;
			}
			return ((List<?>) claims
					.get("rol")).stream()
					.map(authority -> new SimpleGrantedAuthority((String) authority))
					.collect(Collectors.toList());
	    }

	    public Authentication getAuthentication(String token) {
	        UserDetails userDetails = this.userDetailsService.loadUserByUsername(getUsername(token));
	        return new UsernamePasswordAuthenticationToken(userDetails, userDetails.getPassword(), userDetails.getAuthorities());
	    }

	    public String getUsername(String token) {
	        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
	    }

	    public String resolveToken(HttpServletRequest req) {
	        String bearerToken = req.getHeader("Authorization");
	        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
	            return bearerToken.substring(7, bearerToken.length());
	        }
	        return null;
	    }

	    public boolean validateToken(String token) {
	        try {
	            Jws<Claims> claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);

	            if (claims.getBody().getExpiration().before(new Date())) {
	                return false;
	            }

	        } catch (JwtException | IllegalArgumentException e) {
	            throw new InvalidJwtAuthenticationException("Expired or invalid JWT token");
	        }

	        return true;
	    }

	    public String createRefreshToken(String token) {
	        Jws<Claims> claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);

	        Claims newClaims = Jwts.claims().setSubject(claims.getBody().getSubject());
	        newClaims.put("roles", claims.getBody().get("roles"));

	        Date now = new Date();
	        Date validity = new Date(now.getTime() + validityInMilliseconds);

	        return Jwts.builder()//
	                .setClaims(newClaims)//
	                .setIssuedAt(now)//
	                .setExpiration(validity)//
	                .signWith(SignatureAlgorithm.HS512, secretKey)//
	                .compact();
	    }
	    
}
