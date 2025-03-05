package com.mosaicai.api.helpers;


import com.mosaicai.api.Exceptions.StringFormatException;

import static org.apache.commons.lang3.StringUtils.isBlank;

public class StringValidator {

    public static void validateString(String word) throws StringFormatException {
        if(isBlank(word)){ throw new StringFormatException(word + " is a invalid format!");}
    }
}
