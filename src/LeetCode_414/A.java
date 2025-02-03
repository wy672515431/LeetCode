package LeetCode_414;

public class A {
    public String convertDateToBinary(String date) {
        String[] input = date.split("-");
        String year = Integer.toBinaryString(Integer.parseInt(input[0]));
        String month = Integer.toBinaryString(Integer.parseInt(input[1]));
        String day = Integer.toBinaryString(Integer.parseInt(input[2]));
        return year + "-" + month + "-" + day;
    }
}
