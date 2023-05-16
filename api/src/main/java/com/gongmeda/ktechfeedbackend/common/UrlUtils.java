package com.gongmeda.ktechfeedbackend.common;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class UrlUtils {

    public static String encodeKorean(String url) {
        char[] txtChar = url.toCharArray();
        for (char c : txtChar) {
            if (c >= '\uAC00' && c <= '\uD7A3') {
                String targetText = String.valueOf(c);
                url = url.replace(targetText, URLEncoder.encode(targetText, StandardCharsets.UTF_8));
            }
        }
        return url;
    }
}
