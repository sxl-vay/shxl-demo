package top.boking.limit;

public class ShxlLimiter {
    private int[] array =new int[8];
    private int count;
    private int interval;
    public ShxlLimiter(int count,int interval) {
        this.count = count;
        this.interval = interval;
    }

}
