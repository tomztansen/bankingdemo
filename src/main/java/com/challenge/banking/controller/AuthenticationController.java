/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.challenge.banking.controller;

import com.challenge.banking.model.AuthenticationRequest;
import com.challenge.banking.model.AuthenticationResponse;
import com.challenge.banking.model.MainAuthenticationResponse;
import com.challenge.banking.model.Tusermain;
import com.challenge.banking.service.ServiceBanking;
import com.challenge.banking.tools.FunString;
import com.challenge.banking.security.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Tommy Tansen <Stronger21us@yahoo.com>
 */
@RestController
@RequestMapping({"login-auth"})
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenUtils tokenUtils;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private ServiceBanking serviceBanking;

    // Controller for login 
    @RequestMapping(method = {org.springframework.web.bind.annotation.RequestMethod.POST})
    public ResponseEntity<?> authenticationRequest(@RequestBody AuthenticationRequest authenticationRequest)
            throws AuthenticationException {
        if (authenticationRequest.getUsername() == null || authenticationRequest.getPassword() == null) {
            return ResponseEntity.ok(new AuthenticationResponse(4));
        }
        Tusermain o = serviceBanking.findTusermainById(authenticationRequest.getUsername().toUpperCase());
        if (o == null) {
            return ResponseEntity.ok(new AuthenticationResponse(1));
        }
        String userId = authenticationRequest.getUsername().toUpperCase();
        String password = FunString.encodeMd5(authenticationRequest.getPassword().trim(), userId);
        if (!o.getPassword().equals(password)) {
            return ResponseEntity.ok(new AuthenticationResponse(2));
        }
        Authentication authentication = this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userId, password));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetails userDetails = this.userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
        String token = this.tokenUtils.generateToken(userDetails, o, null);
        o.setMobileToken(token);
        if (authenticationRequest.getFcmId() != null) {
            if (!authenticationRequest.getFcmId().isEmpty() && (!authenticationRequest.getFcmId().equals(o.getFcmId()))) {
                o.setFcmId(authenticationRequest.getFcmId());
            }
        }
        serviceBanking.save(o, o.getUserId());
        return ResponseEntity.ok(new MainAuthenticationResponse(3, token, o.getFcmId(), "1.0"));
    }

}
