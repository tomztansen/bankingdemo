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
import org.hibernate.annotations.GenericGenerator;

/**
 *
 * @author Tommy Tansens
 */
@Entity
public class Tstatement implements Serializable {
    
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    private String id;
    @Column(name = "ACC_NO", length = 20)
    private String accNo;
    @Column(name = "TYPE", length = 20)
    private String type;
    @Column(name = "AMOUNT")
    private BigDecimal amount;
    @Column(name = "LINK_ACC_NO", length = 20)
    private String linkAccNo;
    @Column(name = "DATE_TRANS")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateTrans;
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
     * @return the accNo
     */
    public String getAccNo() {
        return accNo;
    }

    /**
     * @param accNo the accNo to set
     */
    public void setAccNo(String accNo) {
        this.accNo = accNo;
    }

    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return the amount
     */
    public BigDecimal getAmount() {
        return amount;
    }

    /**
     * @param amount the amount to set
     */
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    /**
     * @return the fromAccNo
     */
    public String getLinkAccNo() {
        return linkAccNo;
    }

    /**
     * @param fromAccNo the fromAccNo to set
     */
    public void setLinkAccNo(String fromAccNo) {
        this.linkAccNo = fromAccNo;
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
     * @return the dateTrans
     */
    public Date getDateTrans() {
        return dateTrans;
    }

    /**
     * @param dateTrans the dateTrans to set
     */
    public void setDateTrans(Date dateTrans) {
        this.dateTrans = dateTrans;
    }
    
}
