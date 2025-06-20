package com.example.lab8app;

import android.content.Context;
import android.content.SharedPreferences;

public class Users {
    private static final String PREFS = "user_prefs";
    public static void saveuser(Context ctx, String name, String email, String pass) {
        SharedPreferences p = ctx.getSharedPreferences(PREFS, Context.MODE_PRIVATE);
        p.edit().putString("name", name).putString("email", email).putString("password", pass).apply();
    }
    public static boolean checkuser(Context ctx, String email, String pass) {
        SharedPreferences p = ctx.getSharedPreferences(PREFS, Context.MODE_PRIVATE);
        return email.equals(p.getString("email", "")) && pass.equals(p.getString("password", ""));
    }
    public static String getuser(Context ctx) {
        return ctx.getSharedPreferences(PREFS, Context.MODE_PRIVATE).getString("name", "Гость");
    }
}
