package tok.boking.jvm;

import java.util.ArrayList;

/**
 * 解锁实验VM选项 设置不回收垃圾 设置初试和最大内存，记录dump日志
 * -XX:+UnlockExperimentalVMOptions
 * -XX:+UseEpsilonGC
 * -Xms15m
 * -Xmx50m
 * -XX:+HeapDumpOnOutOfMemoryError
 * -XX:HeapDumpPath=/Users/sxl/data/heapdump.hprof
 */
public class NoGCTest {
    public static void main(String[] args) {
        /*
        jvm参数设置
-Xms4G
-Xmx4G
-XX:+UseG1GC
-XX:+HeapDumpOnOutOfMemoryError
         */
        ArrayList<Object> objects = new ArrayList<>();


        for (int i = 0; i < 100000000; i++) {
            new StringBuilder("shxl::::1111");
            //objects.add("shxl::::"+i);
        }
    }
}
