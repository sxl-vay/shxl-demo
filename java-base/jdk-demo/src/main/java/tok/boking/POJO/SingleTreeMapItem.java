package tok.boking.POJO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;
import java.util.TreeMap;

/**
 * @Author shxl
 * @Date 2024/9/4 21:37
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SingleTreeMapItem implements Comparable{

    private Integer value;

    /*@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SingleItem that)) return false;
        return Objects.equals(getValue(), that.getValue());
    }*/



    @Override
    public int hashCode() {
        return 1;
    }
    @Override
    public int compareTo(Object o) {
        return 0;
    }

    public static void main(String[] args) {
    }
}
