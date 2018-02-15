package ma.salamgaz.tawassol.security.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.codec.Hex;

import ma.salamgaz.tawassol.common.util.UnicodeStringUtils;

public final class TokenUtils {

    public static final String MAGIC_KEY = "obfuscate";

    // Token is valid for one hour
    public static final Long TOKEN_VALIDITY_MILLISECONDE = +1000L * 60 * 60;

    private TokenUtils() {

    }

    public static String createToken(String ipAddress, UserDetails userDetails) {
        /* Expires in one hour */
        long expires = System.currentTimeMillis() + TOKEN_VALIDITY_MILLISECONDE;

        StringBuilder sb = new StringBuilder();
        sb.append(userDetails.getUsername());
        sb.append(":");
        sb.append(expires);
        sb.append(":");
        sb.append(computeSignature(userDetails, ipAddress, expires));

        return UnicodeStringUtils.string2Unicode(sb.toString());
    }

    
    public static String computeSignature(UserDetails userDetails, String ipaddress, long expires) {
        StringBuilder sb = new StringBuilder();
        sb.append(userDetails.getUsername());
        sb.append(":");
        sb.append(expires);
        sb.append(":");
        sb.append(ipaddress);
        sb.append(":");
        sb.append(TokenUtils.MAGIC_KEY);
        sb.append(userDetails.toString());

        MessageDigest digest;
        try {
            digest = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException("No MD5 algorithm available!");
        }

        return new String(Hex.encode(digest.digest(sb.toString().getBytes())));
    }

    public static String getUserNameFromToken(String authToken) {
        if (null == authToken) {
            return null;
        }

        String[] parts = authToken.split(":");
        if (parts.length <= 0) {
            return null;
        }
        return parts[0];
    }

    public static boolean validateToken(String authToken, String ipaddress, UserDetails userDetails) {
        String[] parts = authToken.split(":");
        if (parts.length != 3) {
            return false;
        }
        long expires = Long.parseLong(parts[1]);
        String signature = parts[2];

        if (expires < System.currentTimeMillis()) {
            return false;
        }

        return signature.equals(computeSignature(userDetails, ipaddress, expires));
    }

}