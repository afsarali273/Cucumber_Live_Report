package com.automation.cucumber_report.utils;

import org.apache.commons.lang.StringUtils;

public class Utils {


    public static String checkIfNullReturnEmpty(String nullableString){
        if(StringUtils.isBlank(nullableString))
            return "";
        return nullableString;
    }
}
