package com.ayouris.tawassol.common.util;

import org.apache.commons.lang3.StringUtils;

public final class BeanUtils {

    // This class can't be instantiated
    private BeanUtils() {
    }

    public static String getCompleteAddress(String address, String additionalAddress, String zipCode, String city,
            String countrylabel, String countryCode) {

        StringBuilder st = new StringBuilder();
        st.append(fragment(address));
        st.append(fragment(additionalAddress));
        st.append(fragment(zipCode));
        st.append(fragment(city));
        st.append(fragment(countrylabel + " (" + countryCode + ")"));
        return StringUtils.stripEnd(st.toString(), " - ");
    }

    private static String fragment(String fragment) {

        String result = "";
        if (StringUtils.isNotEmpty(fragment))
            result = fragment + " - ";

        return result;
    }
}
