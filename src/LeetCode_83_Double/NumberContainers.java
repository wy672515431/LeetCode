package LeetCode_83_Double;

import java.util.HashMap;
import java.util.TreeSet;

public class NumberContainers {
    HashMap<Integer, TreeSet<Integer>> map = new HashMap<>();
    HashMap<Integer, Integer> map1 = new HashMap<>();
    public NumberContainers() {

    }
    
    /*
     *如果在map1中不存在说明这个index没有被用过，直接加上即可. 
     */
    public void change(int index, int number) {
        if (map.get(number) == null) {
            TreeSet<Integer> set = new TreeSet<>();
            set.add(index);
            map.put(number, set);
        } else {
            map.get(number).add(index);
        }

        if (map1.get(index) == null || map1.get(index) == number) {
            map1.put(index, number);
        } else {
            map.get(map1.get(index)).remove(index);
            map1.put(index, number);
        }
        
    }
    
    public int find(int number) {
        if (map.get(number) == null || map.get(number).size() == 0) {
            return -1;
        } else {
            return map.get(number).first();
        }
    }
}
