package info.echoexitwebcorner.resort.Utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Nick Bapu on 10-10-2017.
 */

public class Prefs {
    public static SharedPreferences preferences;

    public static void savePreferance(Context context, String key, String value) {
        preferences = context.getSharedPreferences("MP", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(key, value);
        editor.apply();
        editor.commit();
    }

    public static void savebooleanPreferance(Context context, String key, boolean value) {
        preferences = context.getSharedPreferences("MP", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean(key, value);
        editor.apply();
        editor.commit();
    }

    public static void saveIntPreferance(Context context, String key, int value) {
        preferences = context.getSharedPreferences("MP", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt(key, value);
        editor.apply();
        editor.commit();
    }

    public static String getPrefString(Context context, String key, String defvalue) {
        preferences = context.getSharedPreferences("MP", Context.MODE_PRIVATE);
        String value = preferences.getString(key, defvalue);
        return value;
    }

    public static int getPrefInt(Context context, String key, int defvalue) {
        preferences = context.getSharedPreferences("MP", Context.MODE_PRIVATE);
        int value = preferences.getInt(key, defvalue);
        return value;
    }

    public static boolean getPrefBoolean(Context context, String key, boolean defvalue) {
        preferences = context.getSharedPreferences("MP", Context.MODE_PRIVATE);
        boolean value = preferences.getBoolean(key, defvalue);
        return value;
    }

    public static void Logout(Context context) {
        preferences = context.getSharedPreferences("MP", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.clear();
        editor.apply();
        editor.commit();
    }

    public static void SaveToken(Context context, String key, String value) {
        preferences = context.getSharedPreferences("Token", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(key, value);
        editor.apply();
        editor.commit();
    }

    public static String getDeviceToken(Context context, String key, String defvalue) {
        preferences = context.getSharedPreferences("Token", Context.MODE_PRIVATE);
        String value = preferences.getString(key, defvalue);
        return value;
    }

}
