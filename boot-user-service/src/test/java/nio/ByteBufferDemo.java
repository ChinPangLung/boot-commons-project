package nio;


import java.nio.ByteBuffer;
import java.util.Arrays;

/**
 * @author longzhanpeng longzhanpeng@3vjia.com
 * @since 2020-07-27 09:21
 */
public class ByteBufferDemo {
    public static void main(String[] args) {
        //初始化一个1024容量缓冲区
        ByteBuffer buffer = ByteBuffer.allocate(1024);

        System.out.println("-----------allocate----------");
        //容量，表示缓冲区中最大存储数据的容量，一旦声明就不能改变
        System.out.println(buffer.capacity());
        //界限，表示缓冲区中可以操作数据的大小，limit之后的数据不能进行读写
        /**
         * 在写模式下，Buffer的limit表示你最多能往Buffer里写多少数据。 写模式下，limit等于Buffer的capacity。
         * 当切换Buffer到读模式时， limit表示你最多能读到多少数据。
         */
        System.out.println(buffer.limit());
        //表示缓冲区中正在操作数据的位置
        System.out.println(buffer.position());

        String str = "abcde";
        //把数据写到缓冲区
        buffer.put(str.getBytes());
        System.out.println("--------------put-------------");
        System.out.println(buffer.capacity());
        System.out.println(buffer.limit());
        System.out.println(buffer.position());

        //切换读写模式
        buffer.flip();
        System.out.println("----------flip-------------");
        System.out.println(buffer.capacity());
        System.out.println(buffer.limit());
        System.out.println(buffer.position());

        System.out.println("---------get-----------------");
        byte[] dst = new byte[buffer.limit()];
        buffer.get(dst);
        System.out.println("读取到数据: " + new String(dst));
        System.out.println(buffer.capacity());
        System.out.println(buffer.limit());
        System.out.println(buffer.position());

        // 5. 使用rewind()将position恢复到读模式最开始状态, 可重复读
        buffer.rewind();
        System.out.println("----------------rewind()-----------------");
        System.out.println(buffer.capacity());
        System.out.println(buffer.limit());
        System.out.println(buffer.position());

        // 6. 使用clear()方法清空缓冲区, 恢复到allocate状态, 但是缓冲区中数据依然存在
        buffer.clear();
        buffer.rewind();
        System.out.println("----------------clear()-----------------");
        System.out.println(buffer.capacity());         // 1024
        System.out.println(buffer.limit());            // 1024
        System.out.println(buffer.position());         // 0
        System.out.println((char)buffer.get());        // a，仍能获取到数据，数据在缓冲区中仍然存在
        System.out.println(buffer.position());
    }
}