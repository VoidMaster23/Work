package com.example.cape_medics;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

public class Cache {
    Context context;

    Cache(Context contextValue) {
        context = contextValue;
    }


    public String getStringProperty(String key) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("preferences", Activity.MODE_PRIVATE);
        String res = null;
        if (sharedPreferences != null) {
            res = sharedPreferences.getString(key, null);
        }
        return res;
    }

    public int setStringProperty(String key, String value) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("preferences", Activity.MODE_PRIVATE);
        if (sharedPreferences != null) {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString(key, value);
            editor.apply();
        }
        return 0;
    }

    public void removeStringProperty(String key) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("preferences", Activity.MODE_PRIVATE);
        if (sharedPreferences != null) {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.remove(key);
            editor.apply();
        }
    }
}
