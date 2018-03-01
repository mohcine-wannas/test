//package com.ayouris.tawassol.common.util;
//
//import java.text.MessageFormat;
//import java.util.Locale;
//
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Profile;
//import org.springframework.context.support.ReloadableResourceBundleMessageSource;
//
//import com.ayouris.tawassol.common.enums.Language;
//
//
///**
// * a Helper to deal with i18n massages.
// *
// */
////
//@Configuration
//@Profile({"dev", "prod"})
//@ConfigurationProperties(prefix = "tawassol.messages")
//public class ResourceBundleHelper {
//
//    private static ReloadableResourceBundleMessageSource appMessageSource;
//
////    @Autowired
////    @Qualifier("appMessageSource")
////    public void setAppMessageSource(ReloadableResourceBundleMessageSource appMessageSource) {
////        ResourceBundleHelper.appMessageSource = appMessageSource;
////    }
//    
//    @Bean
//    public ReloadableResourceBundleMessageSource getBundleMessageSource(){
//    	appMessageSource = new ReloadableResourceBundleMessageSource();
//    	return appMessageSource;
//    }
//    
//    /**
//     * Get the message for key and LangTag
//     * 
//     * @param key
//     * @param langTag : must conform to IETF BCP 47 standard
//     * @return
//     */
//    public String getMessageForLangTag(String key, String langTag, Object... params) {
//        return appMessageSource.getMessage(key, params, getLocaleByLang(langTag));
//    }
//
//    public String getMessage(String lang, String key, Object[] params) {
//        return getMessageFromProperties(lang, key, params);
//    }
//
//    private String getMessageFromProperties(String lang, String key, Object[] params) {
//        Locale locale = getLocaleByLang(lang);
//        return MessageFormat.format(appMessageSource.getMessage(key, null, locale), params);
//    }
//
//    private Locale getLocaleByLang(String lang) {
//        return Language.FR.getLang().equalsIgnoreCase(lang) ? Locale.FRANCE
//                : Language.EN.getLang().equalsIgnoreCase(lang) ? Locale.ENGLISH : Locale.US;
//
//    }
//
//}
