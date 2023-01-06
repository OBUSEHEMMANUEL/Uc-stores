package com.example.ucstores.validator;

public class UserDetailValidator {
    public static boolean isValidEmailAddress(String emailAddress){
        return emailAddress.contains("@");
    }
    public static boolean isValidPassword(String password){
        return password.matches("[a-zA-Z0-9(@#$!&*)]{8,20}");
    }
    public static boolean isValidPhoneNumber(String phoneAddress){
        return phoneAddress.length()==11;
    }
}
