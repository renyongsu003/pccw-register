package com.pccw.register.domain.utils;

import com.pccw.register.domain.exceptions.BussinessException;

import java.math.BigInteger;
import java.security.MessageDigest;

public class PasswordSec {

    public static String sec(String s){
        try {
            MessageDigest md = MessageDigest.getInstance("SHA");
            md.update(s.getBytes());
            return new BigInteger(md.digest()).toString(32);
        }catch (Exception e){
            throw new BussinessException("密码加密失败！");
        }
    }
}
