/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.challenge.banking.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author Tommy Tansen
 */
@Entity
public class Tusermain implements Serializable {

    @Id
    @NotNull
    @NotEmpty
    @Column(name = "USER_ID", length = 32)
    private String userId;
    @NotNull
    @NotEmpty
    @Column(name = "USER_NAME", length = 100)
    private String userName;
    @NotNull
    @NotEmpty
    @Column(name = "PASSWORD", length = 32)
    private String password;
    @NotNull
    @NotEmpty
    @Column(name = "EMAIL", length = 100, unique = true)
    private String email;
    @NotNull
    @NotEmpty
    @Column(name = "ACTIVE", length = 1)
    private String active;
    @NotNull
    @NotEmpty
    @Column(name = "AUTHORITIES", length = 20)
    private String authorities;
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
    @Column(name = "LAST_PASSWORD_RESET")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastPasswordReset;
    @Column(name = "MOBILE_TOKEN", length = 500)
    private String mobileToken;
    @Column(name = "FCM_ID", length = 500)
    private String fcmId;

    public Tusermain() {
    }

    public Tusermain(String username, String password, Date lastPasswordReset, String authorities) {
        this.setUserName(username);
        this.setPassword(password);
        this.setLastPasswordReset(lastPasswordReset);
        this.setAuthorities(authorities);
    }

    /**
     * @return the userId
     */
    public String getUserId() {
        return userId;
    }

    /**
     * @param userId the userId to set
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * @return the userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * @param userName the userName to set
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the active
     */
    public String getActive() {
        return active;
    }

    /**
     * @param active the active to set
     */
    public void setActive(String active) {
        this.active = active;
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
     * @return the authorities
     */
    public String getAuthorities() {
        return authorities;
    }

    /**
     * @param authorities the authorities to set
     */
    public void setAuthorities(String authorities) {
        this.authorities = authorities;
    }

    /**
     * @return the lastPasswordReset
     */
    public Date getLastPasswordReset() {
        return lastPasswordReset;
    }

    /**
     * @param lastPasswordReset the lastPasswordReset to set
     */
    public void setLastPasswordReset(Date lastPasswordReset) {
        this.lastPasswordReset = lastPasswordReset;
    }

    /**
     * @return the mobileToken
     */
    public String getMobileToken() {
        return mobileToken;
    }

    /**
     * @param mobileToken the mobileToken to set
     */
    public void setMobileToken(String mobileToken) {
        this.mobileToken = mobileToken;
    }

    /**
     * @return the fcmId
     */
    public String getFcmId() {
        return fcmId;
    }

    /**
     * @param fcmId the fcmId to set
     */
    public void setFcmId(String fcmId) {
        this.fcmId = fcmId;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

}
