package LeetCode;

import java.util.TreeSet;

public class MyCalendar {
    //我们维持一个容器来存储排序的日程，如果新加入的没有与任何冲突，则加入。
    TreeSet<Calendar> set;
    public MyCalendar() {
        set = new TreeSet<>((o1, o2) -> o1.start - o2.start);
    }
    
    public boolean book(int start, int end) {
        Calendar calendar = new Calendar(start, end);
        //找到它的前一个和后一个
        Calendar prevCalendar = set.floor(calendar);
        Calendar nextCalendar = set.ceiling(calendar);
        if (prevCalendar == null && nextCalendar == null) {
            set.add(calendar);
            return true;
        } else if (prevCalendar == null) {
            if (calendar.end <= nextCalendar.start) {
                set.add(calendar);
                return true;
            }
            return false;
        } else if (nextCalendar == null) {
            if (calendar.start >= prevCalendar.end) {
                set.add(calendar);
                return true;
            }
            return false;
        } else {
            if (calendar.start >= prevCalendar.end && calendar.end <= nextCalendar.start) {
                set.add(calendar);
                return true;
            }
            return false;
        }
    }
}

class Calendar {
    int start;
    int end;
    Calendar(int start, int end) {
        this.start = start;
        this.end = end;
    }
}