/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.challenge.banking.security;

import com.challenge.banking.model.Tusermain;
import com.challenge.banking.model.UserSecurity;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.mobile.device.Device;
import org.springframework.stereotype.Component;

/**
 *
 * @author Tommy Tansen <Stronger21us@yahoo.com>
 */
@Component
public class TokenUtils {

    private final Logger logger = Logger.getLogger(this.getClass());

    private final String AUDIENCE_UNKNOWN = "unknown";
    private final String AUDIENCE_WEB = "web";
    private final String AUDIENCE_MOBILE = "mobile";
    private final String AUDIENCE_TABLET = "tablet";

    @Value("${cerberus.token.secret}")
    private String secret;

    @Value("${cerberus.token.expiration}")
    private Long expiration;

    public String getUsernameFromToken(String token) {
        String username;
        try {
            Claims claims = getClaimsFromToken(token);
            username = claims.getSubject();
        } catch (Exception e) {
            username = null;
        }
        return username;
    }

    public Date getCreatedDateFromToken(String token) {
        Date created;
        try {
            Claims claims = getClaimsFromToken(token);
            created = new Date(((Long) claims.get("created")));
        } catch (Exception e) {
            created = null;
        }
        return created;
    }

    public Date getExpirationDateFromToken(String token) {
        Date exp;
        try {
            Claims claims = getClaimsFromToken(token);
            exp = claims.getExpiration();
        } catch (Exception e) {
            exp = null;
        }
        return exp;
    }

    public String getAudienceFromToken(String token) {
        String audience;
        try {
            Claims claims = getClaimsFromToken(token);
            audience = (String) claims.get("audience");
        } catch (Exception e) {
            audience = null;
        }
        return audience;
    }

    public String getIusernameFromToken(String token) {
        String username;
        try {
            Claims claims = getClaimsFromToken(token);
            username = (String) claims.get("iusername");
        } catch (Exception e) {
            username = null;
        }
        return username;
    }

    public String getInamadeptFromToken(String token) {
        String dept;
        try {
            Claims claims = getClaimsFromToken(token);
            dept = (String) claims.get("inamadept");
        } catch (Exception e) {
            dept = null;
        }
        return dept;
    }

    private Claims getClaimsFromToken(String token) {
        Claims claims;
        try {
            claims = (Claims) Jwts.parser()
                    .setSigningKey(secret
                            .getBytes("UTF-8"))
                    .parseClaimsJws(token)
                    .getBody();
        } catch (ExpiredJwtException | MalformedJwtException | SignatureException | UnsupportedJwtException | UnsupportedEncodingException | IllegalArgumentException e) {
            claims = null;
        }
        return claims;
    }

    private Date generateCurrentDate() {
        return new Date(System.currentTimeMillis());
    }

    private Date generateExpirationDate() {
        return new Date(System.currentTimeMillis() + expiration);
    }

    private Boolean isTokenExpired(String token) {
        Date exp = getExpirationDateFromToken(token);
        return exp.before(generateCurrentDate());
    }

    private Boolean isCreatedBeforeLastPasswordReset(Date created, Date lastPasswordReset) {
        return (lastPasswordReset != null) && (created.before(lastPasswordReset));
    }

    private String generateAudience(Device device) {
        getClass();
        String audience = "unknown";
        if (device != null) {
            if (device.isNormal()) {
                getClass();
                audience = "web";
            } else if (device.isTablet()) {
                audience = "tablet";
            } else if (device.isMobile()) {
                audience = "mobile";
            }
        }
        return audience;
    }

    private Boolean ignoreTokenExpiration(String token) {
        String audience = getAudienceFromToken(token);
        getClass();
        if (!"tablet".equals(audience)) {
            getClass();
        }
        return "mobile".equals(audience);
    }

    public String generateToken(UserDetails userDetails, Tusermain v, Device device) {
        Map claims = new HashMap();
        claims.put("sub", userDetails.getUsername());
        claims.put("audience", generateAudience(device));
        claims.put("created", generateCurrentDate());
        claims.put("iusername", v.getUserName());
        return generateToken(claims);
    }

    private String generateToken(Map<String, Object> claims) {
        try {
            return Jwts.builder()
                    .setClaims(claims)
                    .setExpiration(generateExpirationDate())
                    .signWith(SignatureAlgorithm.HS512, secret
                            .getBytes("UTF-8"))
                    .compact();
        } catch (UnsupportedEncodingException ex) {
            this.logger.warn(ex.getMessage());
        }
        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(generateExpirationDate())
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    public Boolean canTokenBeRefreshed(String token, Date lastPasswordReset) {
        Date created = getCreatedDateFromToken(token);
        return (!isCreatedBeforeLastPasswordReset(created, lastPasswordReset)) && ((!isTokenExpired(token)) || (ignoreTokenExpiration(token)));
    }

    public String refreshToken(String token) {
        String refreshedToken;
        try {
            Claims claims = getClaimsFromToken(token);
            if (claims == null) {
                claims.put("created", generateCurrentDate());
            }
            refreshedToken = generateToken(claims);
        } catch (Exception e) {
            refreshedToken = null;
        }
        return refreshedToken;
    }

    public int validateToken(String authToken) {
        int i = -1;
        try {
            Jwts.parser().setSigningKey(secret).parseClaimsJws(authToken);
            i = 0;
        } catch (SignatureException e) {
            i = 1;
        } catch (MalformedJwtException e) {
            i = 2;
        } catch (ExpiredJwtException e) {
            i = 3;
        } catch (UnsupportedJwtException e) {
            i = 4;
        } catch (IllegalArgumentException e) {
            i = 5;
        }
        return i;
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        UserSecurity user = (UserSecurity) userDetails;
        String username = getUsernameFromToken(token);
        Date created = getCreatedDateFromToken(token);
        Date exp = getExpirationDateFromToken(token);
        return (username.equals(user.getUsername())) && (!isTokenExpired(token)) && (!isCreatedBeforeLastPasswordReset(created, user.getLastPasswordReset()));
    }
}
