package com.ciandt.dojoandroid.marvelsapp.utils.commons;

import android.content.res.Resources;
import android.util.Log;

import com.ciandt.dojoandroid.marvelsapp.R;
import com.ciandt.dojoandroid.marvelsapp.utils.adapters.implementations.DateTypeAdapter;
import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Vitor on 07/07/2016.
 */
public class Common {
    public static final String md5(final String message) {
        StringBuffer md5 = new StringBuffer();
        try {
            MessageDigest digest = java.security.MessageDigest.getInstance("MD5");
            digest.update(message.getBytes());
            byte messageDigest[] = digest.digest();
            for (int value : messageDigest) {
                String hexString = Integer.toHexString(0xFF & value);
                while (hexString.length() < 2)
                    hexString = String.format("0%s", hexString);
                md5.append(hexString);
            }
        } catch (NoSuchAlgorithmException e) {
            Log.e("getHash", e.getCause().toString());
        }
        return md5.toString();
    }

    public static Gson getGson() {

        ExclusionStrategy exclusionStrategy = new ExclusionStrategy() {
            @Override
            public boolean shouldSkipField(FieldAttributes f) {
                return f.getAnnotation(SerializedName.class) == null;
            }

            @Override
            public boolean shouldSkipClass(Class<?> clazz) {
                return false;
            }
        };

        return new GsonBuilder()
                .registerTypeAdapter(Date.class,new DateTypeAdapter())
                .serializeNulls()
                .addSerializationExclusionStrategy(exclusionStrategy)
                .addDeserializationExclusionStrategy(exclusionStrategy)
                //.excludeFieldsWithoutExposeAnnotation()
                .create();
    }

    public static Map<String, String> getParams(Resources resources, Integer offset, Map<String, String> additionalParams) {
        final String PUBLIC_KEY = resources.getString(R.string.publickey);
        final String SECRET_KEY = resources.getString(R.string.secretkey);
        final String TIMESTAMP = String.valueOf(System.currentTimeMillis());
        final String HASH = Common.md5(TIMESTAMP + SECRET_KEY + PUBLIC_KEY);
        Integer numResults = resources.getInteger(R.integer.limit_results);

        Map<String, String> params = new HashMap();
        params.put("limit", numResults.toString());
        params.put("offset", offset.toString());
        params.put("apikey", PUBLIC_KEY);
        params.put("hash", HASH);
        params.put("ts", TIMESTAMP);

        if (additionalParams != null)
            params.putAll(additionalParams);

        return params;
    }
}
