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
public class MainAuthenticationResponse extends AuthenticationResponse {

    private static final long serialVersionUID = 1L;
    private String token;
    private String fcmId;
    private String androidVersion;

    public MainAuthenticationResponse() {
    }

    public MainAuthenticationResponse(int type, String token, String fcmId, String androidVersion) {
        super(type);
        setToken(token);
        setFcmId(fcmId);
        setAndroidVersion(androidVersion);
    }

    public String getToken() {
        return this.token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getFcmId() {
        return this.fcmId;
    }

    public void setFcmId(String fcmId) {
        this.fcmId = fcmId;
    }

    public String getAndroidVersion() {
        return this.androidVersion;
    }

    public void setAndroidVersion(String androidVersion) {
        this.androidVersion = androidVersion;
    }
}
