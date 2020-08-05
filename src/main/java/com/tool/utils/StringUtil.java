package com.tool.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil{

    public static final String EMPTY = "";

    public StringUtil() {
    }

    public static String utf8UrlEncode(String str) {
        try {
            return urlEncode(str, "UTF-8");
        } catch (UnsupportedEncodingException var2) {
            return str;
        }
    }

    public static String urlEncode(String str, String charset) throws UnsupportedEncodingException {
        return !isEmpty(str) ? URLEncoder.encode(str, charset).replaceAll("\\+", "%20") : str;
    }

    public static boolean isEmpty(String str) {
        return str == null || str.length() == 0;
    }

    public static boolean isNotEmpty(String str) {
        return str != null && str.length() != 0;
    }

    public static boolean isBlank(String str) {
        return str == null || str.trim().length() == 0;
    }

    public static boolean isNotBlank(String str) {
        return str != null && str.trim().length() != 0;
    }

    public static boolean isNotNullString(String str) {
        return str != null && str.trim().length() != 0 && !"NULL".equalsIgnoreCase(str.trim());
    }

    public static boolean isNullString(String str) {
        return str != null && str.trim().length() != 0 && "NULL".equalsIgnoreCase(str.trim());
    }

    public static String nullToEmpty(String str) {
        return str == null ? "" : str;
    }

    public static String nullToString(String str) {
        return str == null ? "NULL" : str;
    }

    public static String capitalizeFirstLetter(String str) {
        if (isEmpty(str)) {
            return str;
        } else {
            char c = str.charAt(0);
            return Character.isLetter(c) && !Character.isUpperCase(c) ? (new StringBuilder(str.length())).append(Character.toUpperCase(c)).append(str.substring(1)).toString() : str;
        }
    }

    public static String halfToFull(String half) {
        if (isEmpty(half)) {
            return half;
        } else {
            char[] c = half.toCharArray();

            for(int i = 0; i < c.length; ++i) {
                if (c[i] == ' ') {
                    c[i] = 12288;
                } else if (c[i] < 127) {
                    c[i] += 'ﻠ';
                }
            }

            return new String(c);
        }
    }

    public static String fullToHalf(String full) {
        if (isEmpty(full)) {
            return full;
        } else {
            char[] c = full.toCharArray();

            for(int i = 0; i < c.length; ++i) {
                if (c[i] == 12288) {
                    c[i] = ' ';
                } else if (c[i] > '\uff00' && c[i] < '｟') {
                    c[i] -= 'ﻠ';
                }
            }

            String result = new String(c);
            return result;
        }
    }

    public static String sqliteEscape(String keyWord) {
        keyWord = keyWord.replace("/", "//");
        keyWord = keyWord.replace("'", "''");
        keyWord = keyWord.replace("[", "/[");
        keyWord = keyWord.replace("]", "/]");
        keyWord = keyWord.replace("%", "/%");
        keyWord = keyWord.replace("&", "/&");
        keyWord = keyWord.replace("_", "/_");
        keyWord = keyWord.replace("(", "/(");
        keyWord = keyWord.replace(")", "/)");
        return keyWord;
    }

    public static boolean isChinese(char c) {
        Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
        return ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A || ub == Character.UnicodeBlock.GENERAL_PUNCTUATION || ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION || ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS;
    }

    public static boolean isEnglish(char c) {
        return c >= 'a' && c <= 'z' || c >= 'A' && c <= 'Z';
    }

    public static String getHrefInnerHtml(String href) {
        if (isEmpty(href)) {
            return "";
        } else {
            String hrefReg = ".*<[\\s]*a[\\s]*.*>(.+?)<[\\s]*/a[\\s]*>.*";
            Pattern hrefPattern = Pattern.compile(hrefReg, 2);
            Matcher hrefMatcher = hrefPattern.matcher(href);
            return hrefMatcher.matches() ? hrefMatcher.group(1) : href;
        }
    }

    public static String htmlEscapeCharsToString(String source) {
        return isEmpty(source) ? source : source.replaceAll("&lt;", "<").replaceAll("&gt;", ">").replaceAll("&amp;", "&").replaceAll("&quot;", "\"");
    }

    public static String byteToHex(byte[] buf) {
        StringBuffer sb = new StringBuffer();

        for(int i = 0; i < buf.length; ++i) {
            String hex = Integer.toHexString(buf[i] & 255);
            if (hex.length() == 1) {
                sb.append("0");
            }

            sb.append(hex);
        }

        return sb.toString().toLowerCase();
    }

    public static byte[] hexToByte(String hexStr) {
        if (hexStr == null) {
            return null;
        } else {
            hexStr = hexStr.trim();
            if (hexStr.length() % 2 == 1) {
                return null;
            } else {
                int len = hexStr.length() / 2;
                byte[] result = new byte[len];

                for(int i = 0; i < len; ++i) {
                    String subStr = hexStr.substring(i * 2, i * 2 + 2);
                    result[i] = (byte)Integer.parseInt(subStr, 16);
                }

                return result;
            }
        }
    }

    public static String join(List<String> list, String separator) {
        return org.apache.commons.lang3.StringUtils.join(list.toArray(), separator);
    }

    public static String joinArray(String[] arr, String separator) {
        return org.apache.commons.lang3.StringUtils.join(arr, separator);
    }

    public static String getSubByLength(String str, int len) {
        if (str != null && len >= 0) {
            return len >= str.length() ? str : str.substring(0, len);
        } else {
            return str;
        }
    }

}
