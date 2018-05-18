/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.challenge.banking.service;

import com.challenge.banking.model.Tusermain;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 *
 * @author Tommy Tansen
 */
public interface TusermainDao extends PagingAndSortingRepository<Tusermain, String> {
//    public List<Tusermain> findByUserIdNotIn(List<String> ids);
    public Tusermain findByUserId(String userId);
    public Page findAll(Pageable pageable);
    public Tusermain findByUserNameAndPassword(String userName,String password);
    public Tusermain findByMobileToken(String mobileToken);
    public Tusermain findByEmail(String email);
}
