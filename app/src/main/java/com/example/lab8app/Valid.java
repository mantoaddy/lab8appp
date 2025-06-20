package com.example.lab8app;

import android.util.Patterns;

public class Valid {
    public static boolean validemail(String e) {
        return e != null && Patterns.EMAIL_ADDRESS.matcher(e).matches();
    }
    public static boolean validpass(String p) {
        return p != null && p.matches("\\d{1,8}");
    }}
