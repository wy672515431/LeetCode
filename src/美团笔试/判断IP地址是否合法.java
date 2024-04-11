package 美团笔试;

import java.util.Scanner;
import java.util.regex.Pattern;

public class 判断IP地址是否合法 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String ip = scanner.nextLine();
        String pattern = "((\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])\\.){3}(\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])";
        if (!Pattern.matches(pattern, ip)) {
            System.out.println("invalid");
            return;
        }
        String[] nums = ip.split("\\.");
        assert (nums.length == 4);
        int a = Integer.parseInt(nums[0]);
        int b = Integer.parseInt(nums[1]);
        int c = Integer.parseInt(nums[2]);
        int d = Integer.parseInt(nums[3]);
        if (a >= 1 && a <= 126) {
            if (a <= 125) {
                System.out.println("A_address");
            } else {
                if (b == 0 && c == 0 && d == 0) {
                    System.out.println("A_address");
                } else {
                    System.out.println("other");
                }
            }
        } else if (a >= 128 && a <= 191) {
            System.out.println("B_address");
        } else if (a >= 192 && a <= 223) {
            System.out.println("C_address");
        } else {
            System.out.println("other");
        }
    }
}
