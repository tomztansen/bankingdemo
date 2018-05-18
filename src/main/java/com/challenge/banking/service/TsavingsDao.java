/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.challenge.banking.service;

import com.challenge.banking.model.Tsavings;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 *
 * @author Tommy Tansen
 */
public interface TsavingsDao extends PagingAndSortingRepository<Tsavings, String> {
    public List<Tsavings> findByIdNotIn(List<String> ids);
    public Page findAll(Pageable pageable);
    public Tsavings findByAccNoAndUserId(String accNo,String userId);
    public Tsavings findByAccNo(String accNo);
}
