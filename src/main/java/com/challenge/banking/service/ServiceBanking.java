/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.challenge.banking.service;

import com.challenge.banking.model.Tsavings;
import com.challenge.banking.model.Tstatement;
import com.challenge.banking.model.Tusermain;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 *
 * @author Tommmy Tansen
 */

public interface ServiceBanking extends UserDetailsService {
    
        // konfigurasi tusermain
	void save(Tusermain ac,String userId);
	void delete(Tusermain ac);
	Tusermain findTusermainById(String id);
        Page<Tusermain> findAllTusermains(Pageable pageable);
        Tusermain findByUserNameAndPasswd(String userName,String password);
        Tusermain findByMobileToken(String mobileToken);
        Tusermain findByEmail(String email);

        // konfigurasi Tsavings
        void save(Tsavings ac,String userId);
	void delete(Tsavings ac);
        Optional<Tsavings> findTmemberById(String id);
        Page<Tsavings> findAllTmembers(Pageable pageable);
        Tsavings findByAccNoAndUserId(String accNo,String userId);
        Tsavings findByAccNo(String accNo);
               
        //konfigurasi Tstatement
        void save(Tstatement ac,String userId);
	void delete(Tstatement ac);
        Optional<Tstatement> findTstatementById(String id);
        Page<Tstatement> findAllTstatements(Pageable pageable);
        Page<Tstatement> findStatementByAccNo(Pageable pageable,String accNo);
        
}
