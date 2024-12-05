package nogroup;

/**
 * 给你一个仅由整数组成的有序数组，其中每个元素都会出现两次，唯有一个数只会出现一次。
 *
 * 请你找出并返回只出现一次的那个数。
 *
 * 你设计的解决方案必须满足 O(log n) 时间复杂度和 O(1) 空间复杂度。
 */
public class _540有序数组中的单一元素 {
    public int singleNonDuplicate(int[] nums) {
        int low = 0, high = nums.length - 1;
        while (low < high) {
            int mid = low + (high - low) / 2;
            boolean isEvenNumber = mid % 2 == 0;

            if (isEvenNumber && nums[mid] == nums[mid -1]) {
                low = mid + 1;
            } else if (!isEvenNumber && nums[mid] == nums[mid + 1]) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }

        return nums[low];
    }

}
