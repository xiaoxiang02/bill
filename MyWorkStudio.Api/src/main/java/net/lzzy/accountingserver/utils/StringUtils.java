package net.lzzy.accountingserver.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.regex.Pattern;

/**
 * @author hp
 */
public class StringUtils {
    private static final String REGEX_MOBILE = "((\\+86|0086)?\\s*)((134[0-8]\\d{7})|" +
             "(((13([0-3]|[5-9]))|(14[5-9])|15([0-3]|[5-9])|(16(2|[5-7]))|17([0-3]|[5-8])|" +
             "18[0-9]|19(1|[8-9]))\\d{8})|(14(0|1|4)0\\d{7})|(1740([0-5]|[6-9]|[10-12])\\d{7}))";
    private static final String REGEX_MAIL = "^([a-z0-9A-Z]+[-l_l\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+" +
                                             "(-[a-z0-9A-Z]+)?\\.[a-zA-Z]{2,}$)";
    public static boolean validPhone(String phone){
        if (StringUtils.isEmpty(phone)) {
            return false;
        }
        return Pattern.matches(REGEX_MOBILE, phone);
    }
    public static boolean validMail(String mail) {
        if (StringUtils.isEmpty(mail)) {
            return false;
        }
        return Pattern.matches(REGEX_MAIL, mail);
    }


    public static String md5Encode(String origin, String salt){
        try{
            MessageDigest digest = MessageDigest.getInstance("MD5");
            if (!isEmpty(salt)){
                origin += salt;
            }
            digest.update(origin.getBytes());
            byte[] bytes = digest.digest();
            int i;
            StringBuilder buffer = new StringBuilder();
            for (byte b : bytes) {
                i = b;
                if (i < 0) {
                    i += 256;
                }
                if (i < 16) {
                    buffer.append("0");
                }
                buffer.append(Integer.toHexString(i));
            }
            return buffer.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static boolean isEmpty(String str) {
        return str == null || str.isEmpty();
    }

}
