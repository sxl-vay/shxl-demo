package hash;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * https://leetcode.cn/problems/group-anagrams/description/?envType=study-plan-v2&envId=top-100-liked
 *
 * 两个思路：
 * 1. 将字符串排序后加入hash 判断是否存在
 * 2. 将字符串中每个字符出现的次数进行统计合计到hash中
 */
public class GroupAnagrams {
    public static void main(String[] args) {

    }
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        for (String str : strs) {
            int[] counts = new int[26];
            char[] charArray = str.toCharArray();
            for (char c : charArray) {
                counts[c - 'a']++;
            }
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < 26; i++) {
                if (counts[i] > 0) {
                    sb.append((char) (i + 'a'));
                    sb.append(counts[i]);
                }
            }
            String key = sb.toString();
            if (!map.containsKey(key)) {
                map.put(key, new ArrayList<>());
            }
            map.get(key).add(str);
        }

        return new ArrayList<>(map.values());
    }
}
