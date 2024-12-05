package top.boking.nio;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class ChannelExample {
    public static void main(String[] args){
        RandomAccessFile file = null;
        try {
            file = new RandomAccessFile("/Users/sxl/IdeaProjects/shxl-demo/java-base/nio/README.md", "rw");
            System.out.println(file.getChannel());
            FileChannel channel = file.getChannel();
            ByteBuffer buffer = ByteBuffer.allocate(48);
            int bytesRead = channel.read(buffer); // 读取数据到缓冲区
            while (bytesRead != -1) {
                buffer.flip(); // 切换为读模式
                while (buffer.hasRemaining()) {
                    System.out.print((char) buffer.get());
                }
                buffer.clear(); // 清空缓冲区
                bytesRead = channel.read(buffer);
            }
            channel.close();
            file.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}