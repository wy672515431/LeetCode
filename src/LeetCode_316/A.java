package LeetCode_316;

public class A {
    public static boolean haveConflict(String[] event1, String[] event2) {
        int event1_startTime = Integer.parseInt(event1[0].split(":")[0]) * 60 +
                Integer.parseInt(event1[0].split(":")[1]);
        int event1_endTime = Integer.parseInt(event1[1].split(":")[0]) * 60 +
                Integer.parseInt(event1[1].split(":")[1]);
        int event2_startTime = Integer.parseInt(event2[0].split(":")[0]) * 60 +
                Integer.parseInt(event2[0].split(":")[1]);
        int event2_endTime = Integer.parseInt(event2[1].split(":")[0]) * 60 +
                Integer.parseInt(event2[1].split(":")[1]);
        // intersect
        if (event1_endTime < event2_startTime || event1_startTime > event2_endTime) {
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        String[] event1 = {"00:02","00:44"};
        String[] event2 = {"00:27","23:00"};
        System.out.println(haveConflict(event1, event2));
    }
}
