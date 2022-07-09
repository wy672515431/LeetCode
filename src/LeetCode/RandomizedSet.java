package LeetCode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class RandomizedSet{
    private List<Integer> mList;
    private HashMap<Integer, Integer> mMap;
    private Random random;
    public RandomizedSet() {
        mList = new ArrayList();
        mMap = new HashMap();
        random = new Random();
    }
    
    public boolean insert(int val) {
        if (!mMap.containsKey(val)) {
            int index = mList.size();
            mMap.put(val, index);
            mList.add(val);
            return true;
        } else {
            return false;
        }

    }
    
    public boolean remove(int val) {
        if (mMap.containsKey(val)) {
            //to be deleted
            int index = mMap.get(val);
            int size = mList.size();
            mList.set(index, mList.get(size - 1));
            mMap.put(mList.get(size - 1), index);
            mList.remove(size - 1);
            mMap.remove(val);
            return true;
        } else {
            return false;
        }
    }
    
    public int getRandom() {
        return mList.get(random.nextInt(mList.size()));
    }
}