package com.capedkoala.ck_sms_plugin.utils;

import androidx.annotation.Keep;

@Keep
public class BooleanUtils {
    public static boolean getValue(Boolean booleanObject){
        return booleanObject != null && booleanObject.booleanValue();
    }
}
