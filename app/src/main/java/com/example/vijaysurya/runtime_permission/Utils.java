package com.example.vijaysurya.runtime_permission;

import android.content.pm.PackageManager;

/**
 * Created by ${BY} on 18/06/2016.
 */
public class Utils {

    public static boolean VerifyPermission(int grantResult){
        if(grantResult <1 || grantResult != PackageManager.PERMISSION_GRANTED){
            return false;
        } else {
            return true;
        }

    }
}
