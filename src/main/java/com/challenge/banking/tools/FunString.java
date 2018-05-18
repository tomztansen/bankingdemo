/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.challenge.banking.tools;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;

/**
 *
 * @author Tommy Tansen <Stronger21us@yahoo.com>
 */
public class FunString {

    public static String encodeMd5(String plainText, String salt) {
        try {
            String passwordAndSalt = plainText + "{" + salt + "}";
            MessageDigest md5Digester = MessageDigest.getInstance("MD5");
            byte[] digest = md5Digester.digest(passwordAndSalt.getBytes("UTF-8"));
            return new String(Hex.encodeHex(digest));
        } catch (UnsupportedEncodingException ex) {
            throw new IllegalStateException("UTF-8 not supported!");
        } catch (NoSuchAlgorithmException ex) {
            throw new IllegalStateException("MD5 not supported!");
        }
    }

    public static Object executeMethod(Object obj, String methodName, Object... objParam) throws Exception {
        Object ret = null;
        try {

            Class[] args = null;
            if (objParam != null) {
                args = new Class[objParam.length];
                for (int i = 0; i < objParam.length; i++) {
                    args[i] = objParam[i].getClass();
                }
            }
            Method m = obj.getClass().getMethod(methodName, args);
            ret = m.invoke(obj, objParam);
        } catch (NoSuchMethodException e) {
            System.err.println("Method does not exist");
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            System.err.println("No permission to invoke method");
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            System.err.println("Method threw an: " + e.getTargetException());
            e.printStackTrace();
        }
        return ret;
    }

}
