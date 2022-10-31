package LeetCode_303;

import java.io.FilePermission;
import java.security.PublicKey;
import java.util.Comparator;
import java.util.HashMap;
import java.util.TreeSet;

public class C {
    HashMap<String, String> foodCuisineMap = new HashMap<>();
    HashMap<String, TreeSet<FoodCuisine>> map = new HashMap();
    HashMap<String, Integer> foodRatingMap = new HashMap<>();
    public C(String[] foods, String[] cuisines, int[] ratings) {
        for (int i = 0; i < foods.length; i++) {
            foodCuisineMap.put(foods[i], cuisines[i]);
            foodRatingMap.put(foods[i], ratings[i]);
        }
        for (int i = 0; i < cuisines.length; i++) {
            if (map.get(cuisines[i]) == null) {
                TreeSet<FoodCuisine> set = new TreeSet<>((o1, o2) -> {
                    if (o1.rating == o2.rating) {
                        return o1.food.compareTo(o2.food);
                    } else {
                        return o2.rating - o1.rating;
                    }
                });
                map.put(cuisines[i], set);
            }
            TreeSet<FoodCuisine> set = map.get(cuisines[i]);
            set.add(new FoodCuisine(foods[i], ratings[i]));
        }
    }

    public void changeRating(String food, int newRating) {
        String cuision = foodCuisineMap.get(food);
        TreeSet<FoodCuisine> set = map.get(cuision);
        set.remove(new FoodCuisine(food, foodRatingMap.get(food)));
        set.add(new FoodCuisine(food, newRating));
        foodRatingMap.put(food, newRating);
    }
    
    public String highestRated(String cuisine) {
        return map.get(cuisine).first().food;
    }



    class FoodCuisine {
        String food;
        int rating;

        FoodCuisine(String food, int rating) {
            this.food = food;
            this.rating = rating;
        }
        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            FoodCuisine foodCuisine = (FoodCuisine) obj;
            return this.food.equals(foodCuisine.food) && this.rating == foodCuisine.rating;
        }

        @Override
        public int hashCode() {
            int hash = 17;
            hash = hash * 31 + food.hashCode();
            hash = hash * 31 + rating;
            return hash;
        }
    }
}
