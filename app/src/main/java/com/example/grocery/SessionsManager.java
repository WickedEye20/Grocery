package com.example.grocery;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashMap;

public class SessionsManager {

    SharedPreferences userSession;
    SharedPreferences.Editor editor;
    Context context;

    private static final String IS_LOGIN = "IsLoggedIn";

    public static final String KEY_FULLNAME = "fullName";
    public static final String KEY_USERNAME = "username";
    public static final String KEY_EMAIL = "email";
    public static final String KEY_PHONE = "phone";
    public static final String KEY_PASSWORD = "password";

    public SessionsManager(Context _context){
        context = _context;
        userSession = _context.getSharedPreferences("userLoginSession", Context.MODE_PRIVATE);
        editor = userSession.edit();
    }

    public void createLoginSession(String username, String password){

        editor.putBoolean(IS_LOGIN, true);

//        editor.putString(KEY_FULLNAME, fullName);
        editor.putString(KEY_USERNAME, username);
//        editor.putString(KEY_EMAIL, email);
//        editor.putString(KEY_PHONE, phone);
        editor.putString(KEY_PASSWORD, password);

        editor.commit(); //to store data in session
    }

    public HashMap<String, String> getUserDetailFromSession(){
        HashMap<String, String> userData = new HashMap<String, String>();

//        userData.put(KEY_FULLNAME, userSession.getString(KEY_FULLNAME, null));
        userData.put(KEY_USERNAME, userSession.getString(KEY_USERNAME, null));
//        userData.put(KEY_EMAIL, userSession.getString(KEY_EMAIL, null));
//        userData.put(KEY_PHONE, userSession.getString(KEY_PHONE, null));
        userData.put(KEY_PASSWORD, userSession.getString(KEY_PASSWORD, null));

        return userData;
    }

    public boolean checkLogin(){
        if (userSession.getBoolean(IS_LOGIN, false)){
            return true;
        }
        else {
            return false;
        }
    }

    public void logoutUserFromSession(){
        editor.clear();
        editor.commit();
    }
}
