package com.capedkoala.ck_sms_plugin.utils;

import androidx.annotation.Keep;

@Keep
public class StringUtils {
    public static Boolean isNullOrEmpty(String string){
        return string == null || string.isEmpty();
    }
}
