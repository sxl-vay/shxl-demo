package sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MaoPao {
    private static void erfen(List<Integer> list, int target) {
        for (int i = 0; i < list.size() - 1; i++) {
            boolean change = false;
            for (int j = i + 1; j < list.size(); j++) {
                if (list.get(j)<list.get(i)) {
                    int temp = list.get(i);
                    list.set(i,list.get(j));
                    list.set(j,temp);
                    change = true;
                }
            }
            if (!change) {
                System.out.println("return queck");
                return;
            }
        }


    }
    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(
                9,8,7,6,5,4,1,2,3
        );
        erfen(list, 1);
        System.out.println("list = " + list);

    }
}
