/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.challenge.banking.model;

/**
 *
 * @author Tommy Tansen <Stronger21us@yahoo.com>
 */
public class AuthenticationRequest extends ModelBase {

    private static final long serialVersionUID = 6624726180748515507L;
    private String username;
    private String password;
    private String fcmId;

    public AuthenticationRequest() {
    }

    public AuthenticationRequest(String username, String password) {
        setUsername(username);
        setPassword(password);
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFcmId() {
        return this.fcmId;
    }

    public void setFcmId(String fcmId) {
        this.fcmId = fcmId;
    }
}
