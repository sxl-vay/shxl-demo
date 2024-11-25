import java.util.Arrays;
import java.util.List;

public class TestBinary {
    public static void main(String[] args) {


        System.out.println((1)/2);
        List<Integer> list = Arrays.asList( 1,2,3,4,5,6,7,8
                //, 5, 6, 7, 8, 9, 10
                //, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20
                //, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30
                //, 31, 32
        );

        Integer result = erfen(list,9);
        System.out.println("result = " + result);

    }

    private static Integer erfen(List<Integer> list, int target) {
        int left = 0,right = list.size() - 1;
        while (left < right) {
            int mid = (right - left)/2+ left;
            Integer cur = list.get(mid);
            if(cur < target){
                left = mid +1;
            } else if(cur>target){
                right = mid -1;
            } else{
                return cur;
            }
            System.out.println("left = " + left);
            System.out.println("right = " + right);
        }

        return null;
    }


}
