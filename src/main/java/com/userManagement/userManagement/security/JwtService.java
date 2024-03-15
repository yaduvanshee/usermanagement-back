package com.userManagement.userManagement.security;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;


/**
 * Service class for handling JSON Web Tokens (JWT) operations.
 * This class provides methods for generating, parsing, and validating JWT tokens.
 *
 * @author saurabh-ssingh
 * Created By Saurabh on 15-March-2023
 * @project usermanagement-back
 */
@Component
public class JwtService {

    /** The secret key used for signing JWT tokens. */
    public static final String SECRET = "357638792F423F4428472B4B6250655368566D597133743677397A2443264629";

    /**
     * Extracts the username from the JWT token.
     *
     * @param token The JWT token from which to extract the username.
     * @return The username extracted from the token.
     */
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    /**
     * Extracts the expiration date from the JWT token.
     *
     * @param token The JWT token from which to extract the expiration date.
     * @return The expiration date extracted from the token.
     */
    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    /**
     * Extracts a claim from the JWT token using the provided resolver function.
     *
     * @param token           The JWT token from which to extract the claim.
     * @param claimsResolver  The function to resolve the desired claim from the token's claims.
     * @param <T>             The type of the claim to extract.
     * @return The claim extracted from the token.
     */
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    /**
     * Extracts all claims from the JWT token.
     *
     * @param token The JWT token from which to extract all claims.
     * @return All claims extracted from the token.
     */
    private Claims extractAllClaims(String token) {
        return Jwts
            .parserBuilder()
            .setSigningKey(getSignKey())
            .build()
            .parseClaimsJws(token)
            .getBody();
    }

    /**
     * Checks if the JWT token is expired.
     *
     * @param token The JWT token to check for expiration.
     * @return True if the token is expired, otherwise false.
     */
    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    /**
     * Validates the JWT token against the provided UserDetails object.
     *
     * @param token       The JWT token to validate.
     * @param userDetails The UserDetails object against which to validate the token.
     * @return True if the token is valid for the provided UserDetails, otherwise false.
     */
    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    /**
     * Generates a JWT token for the given username.
     *
     * @param username The username for which to generate the token.
     * @return The generated JWT token.
     */
    public String GenerateToken(String username){
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, username);
    }

    /**
     * Creates a JWT token with the provided claims and username.
     *
     * @param claims   The claims to include in the token.
     * @param username The username to include in the token.
     * @return The created JWT token.
     */
    private String createToken(Map<String, Object> claims, String username) {
        return Jwts.builder()
            .setClaims(claims)
            .setSubject(username)
            .setIssuedAt(new Date(System.currentTimeMillis()))
            .setExpiration(new Date(System.currentTimeMillis()+1000*60*1)) // Token valid for 1 minute
            .signWith(getSignKey(), SignatureAlgorithm.HS256).compact();
    }

    /**
     * Retrieves the signing key used for JWT token generation.
     *
     * @return The signing key.
     */
    private Key getSignKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}