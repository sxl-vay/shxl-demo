package hash;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 先排序然后根据排序结果进行计算  -- 这里的排序明显时间复杂度高于 O(n)
 */

public class LongestConsecutive {
    public static void main(String[] args) {
        int i = longestConsecutive(new int[]{9, 1, 4, 7, 3, -1, 0, 5, 8, -1, 6});

        System.out.println("i = " + i);
    }
    public static int longestConsecutive(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            return 1;
        }
        Arrays.sort(nums);
        int left = 0, right = 1;
        int max = 1;
        while (right < nums.length) {
            if (nums[right-1] + 1 == nums[right]) {//连续
                max = Math.max(max, right - left + 1);
                right ++;
            } else if (nums[right] == nums[right - 1]) {//相等
                right++;
                left++;
            } else {//跳跃
                left = right++;
            }
        }

        return max;
    }
}
