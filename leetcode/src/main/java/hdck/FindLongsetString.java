package hdck;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class FindLongsetString {
    public static void main(String[] args) {
        FindLongsetString findLongsetString = new FindLongsetString();
        int i = findLongsetString.lengthOfLongestSubstring("abba");
        System.out.println("i = " + i);
    }
    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int max = 0;
        int start = 0;
        int end = 0;
        Set<Character> set = new HashSet<>();
        while (end < s.length()) {
            char charAt = s.charAt(end);
            if (set.contains(charAt)) {
                while (s.charAt(start)!=charAt) {
                    set.remove(s.charAt(start++));
                }
                set.remove(s.charAt(start++));
            }
            set.add(charAt);
            max = Math.max(max, end - start+1);
            end++;
        }
        return max;
    }
}
