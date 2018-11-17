package com.sandeepsingh.feedsapplication.base.prefs;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

import java.util.List;

/**
 * Created by Sandeep on 11/17/18.
 */
public class Prefs {
    private static final String PREF_NAME = "githubauth";

    /**
     * <p>Provides shared object</p>
     *
     * @param context
     * @return
     */
    private static SharedPreferences getPrefs(Context context) {
        return context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }

    /**
     * <p>Saves a string value in AES encrypted format for a given key</p>
     *
     * @param context
     * @param key
     * @param value
     * @throws Exception
     */
    public static void setEncryptString(Context context, String key, String value) throws Exception {
        getPrefs(context).edit().putString(key, value).apply();
    }

    /**
     * <p>Saves a string value for a given key</p>
     *
     * @param context
     * @param key
     * @param value
     */
    public static void setString(Context context, String key, String value) {
        getPrefs(context).edit().putString(key, value).apply();
    }

    /**
     * <p>Saves a list value for a given key</p>
     *
     * @param context
     * @param key
     * @param list
     */
    public static <T> void setList(Context context, String key, List<T> list) {
        Gson gson = new Gson();
        String json = gson.toJson(list);

        getPrefs(context).edit().putString(key, json).apply();
    }


    /**
     * <p>Saves integer value for a given key</p>
     *
     * @param context
     * @param key
     * @param value
     */
    public static void setInt(Context context, String key, int value) {
        getPrefs(context).edit().putInt(key, value).apply();
    }

    /**
     * <p>Saves float value for a given key</p>
     *
     * @param context
     * @param key
     * @param value
     */
    public static void setFloat(Context context, String key, float value) {
        getPrefs(context).edit().putFloat(key, value).apply();
    }

    /**
     * <p>Saves long value for a given key</p>
     *
     * @param context
     * @param key
     * @param value
     */
    public static void setLong(Context context, String key, long value) {
        getPrefs(context).edit().putLong(key, value).apply();
    }

    /**
     * <p>Saves boolean value for a given key</p>
     *
     * @param context
     * @param key
     * @param value
     */
    public static void setBoolean(Context context, String key, boolean value) {
        getPrefs(context).edit().putBoolean(key, value).apply();
    }

    /**
     * Provides string from the Shared Preferences
     *
     * @param context
     * @param key
     * @param defaultValue
     * @return
     * @throws Exception on failing to encrypt or decrypt title
     */
    public synchronized static String getEncryptString(Context context, String key, String defaultValue) throws Exception {
        return getPrefs(context).getString(key, defaultValue);
    }

    /**
     * Provides string from the Shared Preferences
     *
     * @param context
     * @param key
     * @param defaultValue
     * @return
     */
    public static String getString(Context context, String key, String defaultValue) {
        return getPrefs(context).getString(key, defaultValue);
    }

    /**
     * Provides int from Shared Preferences
     *
     * @param context
     * @param key
     * @param defaultValue
     * @return
     */
    public static int getInt(Context context, String key, int defaultValue) {
        return getPrefs(context).getInt(key, defaultValue);
    }

    /**
     * Provides boolean from Shared Preferences
     *
     * @param context
     * @param key
     * @param defaultValue
     * @return
     */
    public static boolean getBoolean(Context context, String key, boolean defaultValue) {
        return getPrefs(context).getBoolean(key, defaultValue);
    }

    /**
     * Provides float value from Shared Preferences
     *
     * @param context
     * @param key
     * @param defaultValue
     * @return
     */
    public static float getFloat(Context context, String key, float defaultValue) {
        return getPrefs(context).getFloat(key, defaultValue);
    }

    /**
     * Provides long value from Shared Preferences
     *
     * @param context
     * @param key
     * @param defaultValue
     * @return
     */
    public static long getLong(Context context, String key, long defaultValue) {
        return getPrefs(context).getLong(key, defaultValue);
    }

    /**
     * Clears all the shared preference payLoad
     *
     * @param context
     */
    public static void clearPrefs(Context context) {
        getPrefs(context).edit().clear().commit();
    }

    /**
     * Clears a key-value pair from shared preference payLoad
     *
     * @param context
     * @param key
     */
    public static void clearPrefs(Context context, String key) {
        getPrefs(context).edit().remove(key).apply();
    }
}
