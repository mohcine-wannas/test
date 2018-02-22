package com.ayouris.tawassol.common.constant;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class ApplicationConstants {

    // database
    //public static final String EDM_REF_SEQUENCE = "tawassol.alfresco_reference_seq";
    public static final int SEARCH_RESULT_LIMIT = 10;   
    public static final String FUNCTIONAL_REF_SEQUENCE = "tawassol.functional_reference_seq";

    // formatter
    public static final NumberFormat REFERENCE_FORMATTER = new DecimalFormat("000000");
    public static final String DATE_YEAR_FORMAT = "yyyy";
    public static final String DATE_TIMESTAMP_FORMAT = "ddMMyyyyHHmm";

    // HTTP Headers
    public static final String HTTP_DATA_REFERENCE = "Data-reference";
    public static final String HTTP_CONTENT_DISPOSITION = "content-disposition";

    // Security
    public static final String VALIDATOR_TAWASSOL_NAME = "ATAWASSOL";

}
