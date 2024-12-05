package top.boking.nio;
import java.nio.ByteBuffer;

public class BufferExample {
    public static void main(String[] args) {
        ByteBuffer buffer = ByteBuffer.allocate(10);
        buffer.put((byte) 1);
        buffer.put((byte) 2);
        buffer.flip(); // 切换为读模式
        System.out.println(buffer.get()); // 输出: 1
        System.out.println(buffer.get()); // 输出: 2
        buffer.clear(); // 重置缓冲区
    }
}