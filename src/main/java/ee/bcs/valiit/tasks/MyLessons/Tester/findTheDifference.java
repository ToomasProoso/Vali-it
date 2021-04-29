package ee.bcs.valiit.tasks.MyLessons.Tester;

import java.util.HashMap;

public class findTheDifference {
    public static void main(String[] args) {
        System.out.println(findTheDifference("abcd","abcde"));
    }
    public static char findTheDifference(String s, String t) {
        HashMap<Character, Integer> map = new HashMap<>();
        for (char c : s.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }

        for(char c: s.toCharArray()){
            if (map.containsKey(c) && map.get(c) == 0 || !map.containsKey(c)) {
                return c;
            } else {
                map.put(c, map.get(c));
            }
            System.out.println(c);
        }
        return '!';
    }
}
