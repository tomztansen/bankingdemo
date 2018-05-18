/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.challenge.banking.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author Tommy Tansen
 */
@Entity
public class Tsavings implements Serializable {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    private String id;
    @NotNull
    @NotEmpty
    @Column(name = "USER_ID", length = 32)
    private String userId;
    @NotNull
    @NotEmpty
    @Column(name = "ACC_NO", length = 20, unique = true)
    private String accNo;
    @NotNull
    @Column(name = "BALANCE")
    private BigDecimal balance;
    @Column(name = "INPUT_BY", length = 32)
    private String inputBy;
    @Column(name = "INPUT_DT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date inputDt;
    @Column(name = "UPDATE_BY", length = 32)
    private String updateBy;
    @Column(name = "UPDATE_DT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateDt;

    /**
     * @return the accNo
     */
    public String getAccNo() {
        return accNo;
    }

    /**
     * @return the inputBy
     */
    public String getInputBy() {
        return inputBy;
    }

    /**
     * @param inputBy the inputBy to set
     */
    public void setInputBy(String inputBy) {
        this.inputBy = inputBy;
    }

    /**
     * @return the inputDt
     */
    public Date getInputDt() {
        return inputDt;
    }

    /**
     * @param inputDt the inputDt to set
     */
    public void setInputDt(Date inputDt) {
        this.inputDt = inputDt;
    }

    /**
     * @return the updateBy
     */
    public String getUpdateBy() {
        return updateBy;
    }

    /**
     * @param updateBy the updateBy to set
     */
    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    /**
     * @return the updateDt
     */
    public Date getUpdateDt() {
        return updateDt;
    }

    /**
     * @param updateDt the updateDt to set
     */
    public void setUpdateDt(Date updateDt) {
        this.updateDt = updateDt;
    }

    /**
     * @return the userId
     */
    public String getUserId() {
        return userId;
    }

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @param userId the userId to set
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * @param accNo the accNo to set
     */
    public void setAccNo(String accNo) {
        this.accNo = accNo;
    }

    /**
     * @return the balance
     */
    public BigDecimal getBalance() {
        return balance;
    }

    /**
     * @param balance the balance to set
     */
    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

}
