/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.challenge.banking.service;

import com.challenge.banking.model.Tstatement;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 *
 * @author Tommy Tansen
 */
public interface TstatementDao extends PagingAndSortingRepository<Tstatement, String> {
    public List<Tstatement> findByIdNotIn(List<String> ids);
    Page findAll(Pageable pageable);
    Page<Tstatement> findStatementByAccNoOrderByDateTransAsc(Pageable pageable,String accNo);
}
