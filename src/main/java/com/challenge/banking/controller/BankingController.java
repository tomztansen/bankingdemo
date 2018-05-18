/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.challenge.banking.controller;

import com.challenge.banking.model.AuthenticationResponse;
import com.challenge.banking.model.Register;
import com.challenge.banking.model.Tsavings;
import com.challenge.banking.model.Tstatement;
import com.challenge.banking.model.Tusermain;
import com.challenge.banking.service.ServiceBanking;
import com.challenge.banking.security.TokenUtils;
import com.challenge.banking.tools.FunString;
import java.math.BigDecimal;
import java.util.Date;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Tommy Tansen <Stronger21us@yahoo.com>
 */
@RestController
@RequestMapping({"bank"})
public class BankingController {

    @Autowired
    private ServiceBanking serviceBanking;
    @Autowired
    private TokenUtils tokenUtils;

    // Controller for user registration
    @RequestMapping(value = {"/register/{deviceType}"}, method = {org.springframework.web.bind.annotation.RequestMethod.POST})
    public ResponseEntity<?> register(@PathVariable("deviceType") String deviceType,
            @RequestHeader("X-Auth-Token") String tokenHeader,
            @RequestBody @Valid Register body) throws Exception {
        if (!deviceType.equals("web") || deviceType.equals("mobile")) {
            return ResponseEntity.ok(new AuthenticationResponse(6));
        }
        Tusermain t = serviceBanking.findByMobileToken(tokenHeader);
        Tsavings tm;
        if (t == null) {
            return ResponseEntity.ok(new AuthenticationResponse(5));
        }
        String inputBy = this.tokenUtils.getUsernameFromToken(tokenHeader);
        t = serviceBanking.findTusermainById(body.getUserid());
        if (t != null) {
            return ResponseEntity.ok(new AuthenticationResponse(7));
        } else {
            t = serviceBanking.findByEmail(body.getEmail());
            if (t != null) {
                return ResponseEntity.ok(new AuthenticationResponse(8));
            }
            tm = serviceBanking.findByAccNo(body.getAccno());
            if (tm != null) {
                return ResponseEntity.ok(new AuthenticationResponse(9));
            }
            t = new Tusermain();
        }
        t.setUserId(body.getUserid());
        t.setUserName(body.getUsername());
        t.setActive("Y");
        t.setAuthorities(body.getRole());
        t.setEmail(body.getEmail());
        t.setPassword(FunString.encodeMd5(body.getPassword().trim(), body.getUserid()));
        serviceBanking.save(t, inputBy);
        tm = new Tsavings();
        tm.setAccNo(body.getAccno());
        tm.setUserId(body.getUserid());
        tm.setBalance(body.getBalance());
        serviceBanking.save(tm, inputBy);
        Tstatement ts = new Tstatement();
        ts.setAccNo(body.getAccno());
        ts.setAmount(body.getBalance());
        ts.setDateTrans(new Date());
        ts.setType("REGISTER");
        serviceBanking.save(ts, inputBy);
        return ResponseEntity.ok(new AuthenticationResponse(3));
    }

    // Controller for deposit of money from accounts
    @RequestMapping(value = {"/deposit/{deviceType}"}, method = {org.springframework.web.bind.annotation.RequestMethod.PUT})
    public ResponseEntity<?> deposit(@PathVariable("deviceType") String deviceType,
            @RequestHeader("X-Auth-Token") String tokenHeader,
            @RequestParam(value = "amount", required = true) BigDecimal amount,
            @RequestParam(value = "accno", required = true) String accno) {
        if (!deviceType.equals("web") || deviceType.equals("mobile")) {
            return ResponseEntity.ok(new AuthenticationResponse(6));
        }
        String inputBy = this.tokenUtils.getUsernameFromToken(tokenHeader);
        Tusermain t = serviceBanking.findByMobileToken(tokenHeader);
        if (t == null) {
            return ResponseEntity.ok(new AuthenticationResponse(5));
        }
        if (amount.compareTo(BigDecimal.ZERO) < 1) {
            return ResponseEntity.ok(new AuthenticationResponse(10));
        }
        Tsavings tm = serviceBanking.findByAccNoAndUserId(accno, t.getUserId());
        if (tm != null) {
            tm.setBalance(tm.getBalance().add(amount));
            serviceBanking.save(tm, inputBy);
            Tstatement ts = new Tstatement();
            ts.setAccNo(accno);
            ts.setAmount(amount);
            ts.setDateTrans(new Date());
            ts.setType("DEPOSIT");
            serviceBanking.save(ts, inputBy);
        } else {
            return ResponseEntity.ok(new AuthenticationResponse(11));
        }
        return ResponseEntity.ok(new AuthenticationResponse(3));
    }

    // Controller for withdrawal of money from accounts
    @RequestMapping(value = {"/withdraw/{deviceType}"}, method = {org.springframework.web.bind.annotation.RequestMethod.PUT})
    public ResponseEntity<?> withdraw(@PathVariable("deviceType") String deviceType,
            @RequestHeader("X-Auth-Token") String tokenHeader,
            @RequestParam(value = "amount", required = true) BigDecimal amount,
            @RequestParam(value = "accno", required = true) String accno) {
        if (!deviceType.equals("web") || deviceType.equals("mobile")) {
            return ResponseEntity.ok(new AuthenticationResponse(6));
        }
        String inputBy = this.tokenUtils.getUsernameFromToken(tokenHeader);
        Tusermain t = serviceBanking.findByMobileToken(tokenHeader);
        if (t == null) {
            return ResponseEntity.ok(new AuthenticationResponse(5));
        }
        if (amount.compareTo(BigDecimal.ZERO) < 1) {
            return ResponseEntity.ok(new AuthenticationResponse(10));
        }
        Tsavings tm = serviceBanking.findByAccNoAndUserId(accno, t.getUserId());
        if (tm != null) {
            if (amount.compareTo(tm.getBalance()) == 1) {
                return ResponseEntity.ok(new AuthenticationResponse(12));
            }
            tm.setBalance(tm.getBalance().subtract(amount));
            serviceBanking.save(tm, inputBy);
            Tstatement ts = new Tstatement();
            ts.setAccNo(accno);
            ts.setAmount(amount);
            ts.setDateTrans(new Date());
            ts.setType("WITHDRAW");
            serviceBanking.save(ts, inputBy);
        } else {
            return ResponseEntity.ok(new AuthenticationResponse(11));
        }
        return ResponseEntity.ok(new AuthenticationResponse(3));
    }

    // Controller for transfer of money between accounts
    @RequestMapping(value = {"/transfer/{deviceType}"}, method = {org.springframework.web.bind.annotation.RequestMethod.PUT})
    public ResponseEntity<?> transfer(@PathVariable("deviceType") String deviceType,
            @RequestHeader("X-Auth-Token") String tokenHeader,
            @RequestParam(value = "amount", required = true) BigDecimal amount,
            @RequestParam(value = "fromaccno", required = true) String fromaccno,
            @RequestParam(value = "toaccno", required = true) String toaccno
    ) {
        if (!deviceType.equals("web") || deviceType.equals("mobile")) {
            return ResponseEntity.ok(new AuthenticationResponse(6));
        }
        if (fromaccno.equals(toaccno)) {
            return ResponseEntity.ok(new AuthenticationResponse(13));
        }
        String inputBy = this.tokenUtils.getUsernameFromToken(tokenHeader);
        Tusermain t = serviceBanking.findByMobileToken(tokenHeader);
        if (t == null) {
            return ResponseEntity.ok(new AuthenticationResponse(5));
        }
        if (amount.compareTo(BigDecimal.ZERO) < 1) {
            return ResponseEntity.ok(new AuthenticationResponse(10));
        }
        Tsavings tm = serviceBanking.findByAccNoAndUserId(fromaccno, t.getUserId());
        if (tm != null) {
            if (amount.compareTo(tm.getBalance()) == 1) {
                return ResponseEntity.ok(new AuthenticationResponse(12));
            }
            tm.setBalance(tm.getBalance().subtract(amount));
            serviceBanking.save(tm, inputBy);
            Tstatement ts = new Tstatement();
            ts.setAccNo(fromaccno);
            ts.setLinkAccNo(toaccno);
            ts.setAmount(amount);
            ts.setDateTrans(new Date());
            ts.setType("TRANSFEROUT");
            serviceBanking.save(ts, inputBy);
            Tsavings tm2 = serviceBanking.findByAccNo(toaccno);
            if (tm2 != null) {
                tm2.setBalance(tm2.getBalance().add(amount));
                serviceBanking.save(tm2, inputBy);
                Tstatement ts2 = new Tstatement();
                ts2.setAccNo(toaccno);
                ts2.setLinkAccNo(fromaccno);
                ts2.setAmount(amount);
                ts2.setDateTrans(new Date());
                ts2.setType("TRANSFERIN");
                serviceBanking.save(ts2, inputBy);
            } else {
                return ResponseEntity.ok(new AuthenticationResponse(12));
            }
        } else {
            return ResponseEntity.ok(new AuthenticationResponse(11));
        }
        return ResponseEntity.ok(new AuthenticationResponse(3));
    }

    // Controller for view of transactions
    @RequestMapping(value = {"/view/{deviceType}"}, method = {org.springframework.web.bind.annotation.RequestMethod.GET})
    public ResponseEntity<?> view(Pageable page, @PathVariable("deviceType") String deviceType,
            @RequestHeader("X-Auth-Token") String tokenHeader,
            @RequestParam(value = "accno", required = true) String accno
    ) {
        if (!deviceType.equals("web") || deviceType.equals("mobile")) {
            return ResponseEntity.ok(new AuthenticationResponse(6));
        }
        Tusermain t = serviceBanking.findByMobileToken(tokenHeader);
        if (t == null) {
            return ResponseEntity.ok(new AuthenticationResponse(5));
        }
        Tsavings tm = serviceBanking.findByAccNoAndUserId(accno, t.getUserId());
        if (tm == null) {
            return ResponseEntity.ok(new AuthenticationResponse(11));
        }
        return ResponseEntity.ok(serviceBanking.findStatementByAccNo(page, accno));
    }
}