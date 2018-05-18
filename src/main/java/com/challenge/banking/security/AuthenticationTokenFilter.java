/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.challenge.banking.security;

import com.challenge.banking.model.Tusermain;
import com.challenge.banking.service.ServiceBanking;
import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;

/**
 *
 * @author Tommy Tansen <Stronger21us@yahoo.com>
 */
public class AuthenticationTokenFilter extends UsernamePasswordAuthenticationFilter {

    @Value("${cerberus.token.header}")
    private String tokenHeader;

    @Autowired
    private ServiceBanking bankingService;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private TokenUtils tokenUtils;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String authToken = httpRequest.getHeader(this.tokenHeader);
        String userId = this.tokenUtils.getUsernameFromToken(authToken);

        if (userId != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = userDetailsService.loadUserByUsername(userId);
            if (this.tokenUtils.validateToken(authToken, userDetails)) {
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpRequest));
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        } else if (tokenUtils.validateToken(authToken) == 3) {
            if (userId == null) {
                userId = httpRequest.getHeader("userid");
            }

            if (userId != null) {
                UserDetails userDetails = this.userDetailsService.loadUserByUsername(userId);

                if (this.userDetailsService.loadUserByUsername(userId) != null) {
                    Tusermain o = this.bankingService.findTusermainById(userId);

                    if ((o != null) && (o.getMobileToken().equals(authToken))) {
                        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpRequest));
                        SecurityContextHolder.getContext().setAuthentication(authentication);
                        HttpServletResponse hsr = (HttpServletResponse) response;
                        String newToken = this.tokenUtils.generateToken(userDetails, o, null);
                        hsr.addHeader("newtoken", newToken);
                        o.setMobileToken(newToken);
                        bankingService.save(o, o.getUserId());
                    }
                }
            }
        }
        chain.doFilter(request, response);
    }

}
