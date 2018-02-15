package com.ayouris.tawassol.utils;

import org.apache.commons.lang3.StringUtils;

public class DateFilterUtils {

    public static final String DAY = "DAY";
    public static final String MONTH = "MONTH";
    public static final String YEAR = "YEAR";

    public static final String SPLITTER = "/";
    
    /**
     * Return date part from a date string having a format DD/MM/YYYY
     * The date can be defined partially. To control part presence, you should call the method {@link DateFilterUtils#isDatePartDefined(String, String)}
     *  
     * The method does not check the presence of the part.
     * To check if part exists, you have to call the method {@link DateFilterUtils#isDatePartDefined(String, String)} 
     * 
     * @param dateString date as string
     * @param datePart DAY, MONTH or YEAR
     * @return int value of date part
     */
    public static Integer getDatePartFromDate(String dateString, String datePart){
        int result = -1;
        switch (datePart) {
        case DAY:
            result = Integer.valueOf(dateString.split(SPLITTER)[0]);
            break;
        case MONTH:
            result = Integer.valueOf(dateString.split(SPLITTER)[1]);
            break;
        case YEAR:
            result = Integer.valueOf(dateString.split(SPLITTER)[2]);
            break;
        default:
            break;
        }
        
        return result;
    }
    
    /**
     * Check if date part is defined in the date string
     * 
     * @param dateString date as string
     * @param datePart DAY, MONTH or YEAR
     * @return true if date part exists
     */
    public static boolean isDatePartDefined(String dateString, String datePart){
        boolean result = false;
        
        switch (datePart) {
        case DAY:
            String dayFromDate = dateString.split(SPLITTER)[0];
            if(StringUtils.isNumeric(dayFromDate)){
                return true;
            }
            break;
        case MONTH:
            if(dateString.split(SPLITTER).length >= 2){
                String monthFromDate = dateString.split(SPLITTER)[1];
                if(StringUtils.isNumeric(monthFromDate)){
                    return true;
                }
            }
            break;
        case YEAR:
            if(dateString.split(SPLITTER).length >= 3){
                String yearFromDate = dateString.split(SPLITTER)[2];
                if(StringUtils.isNumeric(yearFromDate)){
                    return true;
                }
            }
            break;

        default:
            break;
        }
        
        return result;
    }
    
}
