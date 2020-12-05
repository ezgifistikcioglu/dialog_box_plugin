package com.huawei.hms.flutter.dialog_box;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import io.flutter.plugin.common.MethodCall;

import java.util.List;

public class ValueGetter {
    public static int getInt(String key, MethodCall call) {
        final Object value = call.argument(key);
        if (value instanceof Number) {
            return ((Number) value).intValue();
        } else {
            throw new IllegalArgumentException();
        }
    }

    public static float getFloat(String key, MethodCall call) {
        final Object value = call.argument(key);
        if (value instanceof Number) {
            return ((Number) value).floatValue();
        } else {
            throw new IllegalArgumentException();
        }
    }

    public static String getString(String key, MethodCall call) {
        final Object value = call.argument(key);
        if (value instanceof String) {
            return (String) value;
        } else {
            throw new IllegalArgumentException();
        }
    }

    public static Boolean getBoolean(String key, MethodCall call) {
        final Object value = call.argument(key);
        if (value instanceof Boolean) {
            return (Boolean) value;
        } else {
            throw new IllegalArgumentException();
        }
    }

    public static String[] itemListToArray(List<String> additionalScanTypes) {
        String[] scanTypesIntArray = new String[additionalScanTypes.size()];
        for (int i = 0; i < additionalScanTypes.size(); i++) {
            scanTypesIntArray[i] = additionalScanTypes.get(i);
        }
        return scanTypesIntArray;
    }
}
