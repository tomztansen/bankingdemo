/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.challenge.banking.service.impl;

import com.challenge.banking.model.Tsavings;
import com.challenge.banking.model.Tstatement;
import com.challenge.banking.model.Tusermain;
import com.challenge.banking.model.UserSecurity;
import com.challenge.banking.service.ServiceBanking;
import com.challenge.banking.service.TstatementDao;
import com.challenge.banking.service.TusermainDao;
import com.challenge.banking.tools.FunString;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import com.challenge.banking.service.TsavingsDao;

/**
 *
 * @author Tommy Tansen
 */
@Service
@Transactional
public class ServiceBankingImpl implements ServiceBanking {

    private static final Logger log = LoggerFactory.getLogger(ServiceBanking.class);
    @Autowired
    private TsavingsDao tsavingsDao;
    @Autowired
    private TstatementDao tstatementDao;
    @Autowired
    private TusermainDao tusermainDao;

    private void addTrack(Object o, String id) {
        try {
            Date today = new Date();
            Timestamp ts = new Timestamp(today.getTime());
            String inputBy = (String) FunString.executeMethod(o, "getInputBy");
            if (inputBy == null || inputBy.isEmpty()) {
                FunString.executeMethod(o, "setInputDt", today);
                FunString.executeMethod(o, "setInputBy", id);
            } else {
                FunString.executeMethod(o, "setUpdateDt", today);
                FunString.executeMethod(o, "setUpdateBy", id);
            }
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(ServiceBankingImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void save(Tusermain ac, String userId) {
        addTrack(ac, userId);
        tusermainDao.save(ac);
    }

    @Override
    public void delete(Tusermain ac) {
        tusermainDao.delete(ac);
    }

    @Override
    public Tusermain findTusermainById(String id) {
        if (!StringUtils.hasText(id)) {
            return null;
        }
        return tusermainDao.findByUserId(id);
    }

    @Override
    public Page<Tusermain> findAllTusermains(Pageable pageable) {
        if (pageable == null) {
            pageable = new PageRequest(0, 20);
        }
        return tusermainDao.findAll(pageable);
    }

    @Override
    public void save(Tsavings ac, String userId) {
        addTrack(ac, userId);
        tsavingsDao.save(ac);
    }

    @Override
    public void delete(Tsavings ac) {
        tsavingsDao.delete(ac);
    }

    @Override
    public Optional<Tsavings> findTmemberById(String id) {
        if (!StringUtils.hasText(id)) {
            return null;
        }
        return tsavingsDao.findById(id);
    }

    @Override
    public Page<Tsavings> findAllTmembers(Pageable pageable) {
        if (pageable == null) {
            pageable = new PageRequest(0, 20);
        }
        return tsavingsDao.findAll(pageable);
    }

    @Override
    public void save(Tstatement ac, String userId) {
        addTrack(ac, userId);
        tstatementDao.save(ac);
    }

    @Override
    public void delete(Tstatement ac) {
        tstatementDao.delete(ac);
    }

    @Override
    public Optional<Tstatement> findTstatementById(String id) {
        if (!StringUtils.hasText(id)) {
            return null;
        }
        return tstatementDao.findById(id);
    }

    @Override
    public Page<Tstatement> findAllTstatements(Pageable pageable) {
        if (pageable == null) {
            pageable = new PageRequest(0, 20);
        }
        return tstatementDao.findAll(pageable);
    }

    @Override
    public Page<Tstatement> findStatementByAccNo(Pageable pageable,String accNo) {
        if (!StringUtils.hasText(accNo)) {
            return null;
        }
        return tstatementDao.findStatementByAccNoOrderByDateTransAsc(pageable,accNo);
    }

    @Override
    public Tusermain findByUserNameAndPasswd(String userName, String password) {
        return tusermainDao.findByUserNameAndPassword(userName, password);
    }

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        Tusermain user = this.findTusermainById(userId);

        if (user == null) {
            throw new UsernameNotFoundException(String.format("No user found with username '%s'.", new Object[]{userId}));
        }
        return (UserDetails) create(user);
    }

    public static UserSecurity create(Tusermain user) {
        Collection authorities;
        try {
            authorities = AuthorityUtils.commaSeparatedStringToAuthorityList("ADMIN");
        } catch (Exception e) {
            authorities = null;
        }
        return new UserSecurity(user
                .getUserId(), user
                        .getPassword(), user
                        .getEmail(), authorities);
    }

    @Override
    public Tusermain findByMobileToken(String mobileToken) {
        return tusermainDao.findByMobileToken(mobileToken);
    }

    @Override
    public Tsavings findByAccNoAndUserId(String accNo,String userId) {
        return tsavingsDao.findByAccNoAndUserId(accNo, userId);
    }

    @Override
    public Tusermain findByEmail(String email) {
        return tusermainDao.findByEmail(email);
    }

    @Override
    public Tsavings findByAccNo(String accNo) {
        return tsavingsDao.findByAccNo(accNo);
    }

}
