package bytedance;

import java.util.regex.Pattern;

public class 验证IP {

    public static void main(String[] args) {
        验证IP a = new 验证IP();
        System.out.println(a.validIPAddress("2001:0db8:85a3:0:0:8A2E:0370:7334:"));
    }
    public String validIPAddress(String queryIP) {
        // 0 ~ 9 10~99 100~199 200 249
        if (checkForIPV4(queryIP)) {
            return "IPv4";
        } else if (checkForIPV6(queryIP)) {
            return "IPv6";
        }
        return "Neither";
    }

    private boolean checkForIPV4(String queryIP) {
        String pattern = "((\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])\\.){3}(\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])";
        return Pattern.matches(pattern, queryIP);
    }

    private boolean checkForIPV6(String queryIP) {
        String pattern = "([0-9a-fA-F]{1,4}:){7}[0-9a-zA-Z]{1,4}";
        return Pattern.matches(pattern, queryIP);
    }
}
