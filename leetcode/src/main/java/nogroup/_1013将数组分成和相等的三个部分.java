package nogroup;

public class _1013将数组分成和相等的三个部分 {
    public boolean canThreePartsEqualSum(int[] arr) {
        int count = 0;
        for(int x:arr){
            count += x;
        }
        if (count%3!=0) return false;
        int x = count/3;

        int left = 0;
        int right = arr.length - 1;
        int leftCount = arr[left];
        int rightCount = arr[right];
        while((leftCount!= x || rightCount != x) && left<right){
            if(leftCount!=x){
                leftCount += arr[++left];
            }
            if(rightCount!=x){
                rightCount += arr[--right];
            }
        }
        if (leftCount == x && rightCount == x && right -1 > left) return true;
        return false;
    }

    public static void main(String[] args) {
        _1013将数组分成和相等的三个部分 aaa = new _1013将数组分成和相等的三个部分();
        System.out.println("aaa.canThreePartsEqualSum(new int[]{18,12,-18,18,-19,-1,10,10}) = " + aaa.canThreePartsEqualSum(new int[]{18, 12, -18, 18, -19, -1, 10, 10}));

    }
}
