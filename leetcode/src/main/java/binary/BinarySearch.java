package binary;

/**
 * 二分查找注意点
 */
public class BinarySearch {
    public static void main(String[] args) {
        BinarySearch binarySearch = new BinarySearch();
         //           0  1  2  3  4  5  6  7  8  9
        int[] ints = {1, 2, 3, 3, 3, 3, 3, 3, 3, 3, 5, 6, 8, 9};
        int i1 = binarySearch.leftSearch(ints, 3);
        System.out.println("i1 = " + i1);
        int i = binarySearch.rightSearch2(ints, 3);
        System.out.println("i = " + i);
    }


    /**
     * 右开区间二分查找
     * @param nums
     * @param target
     * @return
     */
    public int search(int[] nums, int target) {
        int left = 0, right = nums.length;//搜索区间 [left,right)
        while (left < right) {//结束区间用 [left,right)往里套如果 left=right 那么 【left，left）空区间，满足条件可以返回
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] > target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        if (left == nums.length) return -1;
        return nums[left] == target ? left : -1;
    }

    /**
     * 全闭区间查找
     * @param nums
     * @param target
     * @return
     */
    public int search2(int[] nums, int target) {
        int left = 0, right = nums.length-1;//搜索区间 [left,right]
        while (left <= right) {//结束区间用 [left,right]往里套如果 left>right 那么 [left，left]空区间，满足条件可以返回
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] > target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return -1;
    }

    /**
     * 左区间查找
     */
    public int leftSearch(int[] nums, int target){
        int left = 0, right = nums.length-1;//区间[left,right]
        while (left <= right) {
            int mid = left +(right -left)/2;
            if (nums[mid] == target) {
                right = mid-1;
            } else if (nums[mid] > target) {
                right = mid-1;
            } else if (nums[mid] < target) {
                left = mid+1;
            }
        }

        if (left<0 || right>nums.length-1) {
            return -1;
        }
        return nums[left] == target ? left : -1;
    }

    public int leftSearch2(int[] nums, int target){
        int left = 0, right = nums.length;//区间[left,right)
        while (left < right) {
            int mid = left +(right -left)/2;
            if (nums[mid] == target) {
                right = mid;
            } else if (nums[mid] > target) {
                right = mid;
            } else if (nums[mid] < target) {
                left = mid+1;
            }
        }
        if (left<0 || right>nums.length-1) {
            return -1;
        }
        return nums[left] == target ? left : -1;
    }

    public int rightSearch(int[] nums, int target){
        int left = 0, right = nums.length-1;
        while (left <= right) {
            int mid = left + (right -left)/2;
            if (nums[mid] == target) {
                left = mid+1;
            } else if (nums[mid] > target) {
                right = mid - 1;
            } else if (nums[mid] < target) {
                left = mid+1;
            }
        }
        if (left<0 || right>nums.length-1) {
            return -1;
        }
        return nums[right] == target ? right : -1;
    }


    public int rightSearch2(int[] nums, int target){
        int left = 0, right = nums.length;
        while (left < right) {
            int mid = left + (right -left)/2;
            if (nums[mid] == target) {
                left = mid+1;
            } else if (nums[mid] > target) {
                right = mid;
            } else if (nums[mid] < target) {
                left = mid+1;
            }
        }
        if (left-1<0 || right-1>nums.length-1) {
            return -1;
        }
        return nums[right-1] == target ? right-1 : -1;
    }
}
