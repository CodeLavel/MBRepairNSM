package com.example.nsmproject;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import java.util.HashMap;

public class SessionManager {
    SharedPreferences sharedPreferences;
    public SharedPreferences.Editor editor;
    public Context context;
    int PRIVATE_MODE = 0;

    private static final String PREF_NAME = "LOGIN";
    private static final String LOGIN = "IS_LOGIN";
    public static final String EMP_CODE = "EMP_CODE";
    public static final String EMP_FRISTNAME = "EMP_FRISTNAME";
    public static final String EMP_LASTNAME = "EMP_LASTNAME";
    public static final String EMP_EMAIL = "EMP_EMAIL";
    public static final String EMP_TEL = "EMP_TEL";
    public static final String USERLEVEL = "USERLEVEL";

    public SessionManager(Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = sharedPreferences.edit();
    }

    public void createSession(String emp_code, String emp_fristname, String emp_lastname, String emp_email, String emp_tel, String userlevel){
        editor.putBoolean(LOGIN, true);
        editor.putString(EMP_CODE, emp_code);
        editor.putString(EMP_FRISTNAME, emp_fristname);
        editor.putString(EMP_LASTNAME, emp_lastname);
        editor.putString(EMP_EMAIL, emp_email);
        editor.putString(EMP_TEL, emp_tel);
        editor.putString(USERLEVEL, userlevel);
        editor.apply();
    }

    public boolean isLoggin(){
        return sharedPreferences.getBoolean(LOGIN, false);
    }

    public void checkLogin(){
        if(!this.isLoggin()){
            Intent i = new Intent(context, MainActivity.class);
            context.startActivity(i);
            ((HomeActivity) context).finish();
        }
    }

    public HashMap<String, String> getUserDetail() {
        HashMap<String, String> user = new HashMap<>();
        user.put(EMP_CODE, sharedPreferences.getString(EMP_CODE, null));
        user.put(EMP_FRISTNAME, sharedPreferences.getString(EMP_FRISTNAME, null));
        user.put(EMP_LASTNAME, sharedPreferences.getString(EMP_LASTNAME, null));
        user.put(EMP_EMAIL, sharedPreferences.getString(EMP_EMAIL, null));
        user.put(EMP_TEL, sharedPreferences.getString(EMP_TEL, null));
        user.put(USERLEVEL, sharedPreferences.getString(USERLEVEL, null));
        return user;
    }

    public void logout(){
        editor.clear();
        editor.commit();
        Intent i = new Intent(context, MainActivity.class);
        context.startActivity(i);
        ((HomeActivity) context).finish();
    }
}
