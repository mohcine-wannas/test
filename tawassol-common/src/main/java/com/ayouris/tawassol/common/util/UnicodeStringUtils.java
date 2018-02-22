package com.ayouris.tawassol.common.util;

import org.apache.commons.lang3.StringEscapeUtils;

public class UnicodeStringUtils {

    /**
     * Convert a string to unicode representation
     * 
     * Example: input: "Hello" output: "\u0048\u0065\u006C\u006C\u006F"
     * 
     * @param str
     *            input string
     * @return string with unicode format
     */
    public static String string2Unicode(String str) {
        return StringEscapeUtils.escapeEcmaScript(str);
    }

    /**
     * Convert an unicoded string to simple string
     * 
     * Example: input: "\u0048\u0065\u006C\u006C\u006F" output: "Hello"
     * 
     * @param str
     *            input unicoded string
     * @return string
     */
    public static String unicode2String(String str) {
        return StringEscapeUtils.unescapeEcmaScript(str);
    }

}
