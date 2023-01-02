package com.msvc.order.util;

public class Utils {

    public static boolean validateNotNullParams(Object... parameters) {
        for(Object param: parameters) {
            if(param == null) {
                return false;
            }
        }
        return true;
    }
}
