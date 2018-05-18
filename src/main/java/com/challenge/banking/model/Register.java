/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.challenge.banking.model;

import java.math.BigDecimal;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


/**
 *
 * @author Tommy Tansen <Stronger21us@yahoo.com>
 */
public class Register {

    @NotNull(message = "User Id Not Null")
    @NotEmpty(message = "User Id Not Empty")
    private String userid;
    @NotNull(message = "User Name Not Null")
    @NotEmpty(message = "User Name Not Empty")
    private String username;
    @NotNull(message = "Password Not Null")
    @NotEmpty(message = "Password Not Empty")
    @Size(min = 8, message = "Min Password Length Size is 8 Char")
    private String password;
    @NotNull(message = "Email Not Null")
    @NotEmpty(message = "Email Not Empty")
    private String email;
    @NotNull(message = "Role Not Null")
    @NotEmpty(message = "Role Not Empty")
    private String role;
    @NotNull(message = "Account Number Id Not Null")
    @NotEmpty(message = "Account Number Not Empty")
    private String accno;
    @NotNull(message = "Balance Not Null")
    private BigDecimal balance;

    public Register() {
    }

    /**
     * @return the userid
     */
    public String getUserid() {
        return userid;
    }

    /**
     * @param userid the userid to set
     */
    public void setUserid(String userid) {
        this.userid = userid;
    }

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
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

    /**
     * @return the role
     */
    public String getRole() {
        return role;
    }

    /**
     * @param role the role to set
     */
    public void setRole(String role) {
        this.role = role;
    }

    /**
     * @return the accno
     */
    public String getAccno() {
        return accno;
    }

    /**
     * @param accno the accno to set
     */
    public void setAccno(String accno) {
        this.accno = accno;
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
