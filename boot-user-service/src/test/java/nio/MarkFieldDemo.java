package nio;

import java.nio.ByteBuffer;

/**
 * @author longzhanpeng longzhanpeng@3vjia.com
 * @since 2020-07-27 10:34
 */
public class MarkFieldDemo {
    public static void main(String[] args) {
        ByteBuffer buf = ByteBuffer.allocate(1024);
        String str = "abcde";
        buf.put(str.getBytes());

        buf.flip();

        byte[] dst = new byte[buf.limit()];
        buf.get(dst, 0, 2);
        System.out.println(new String(dst, 0, 2));    // ab
        System.out.println(buf.position());           // 2

        // 标记一下此时位置为2，便于之后reset回来
        buf.mark();

        buf.get(dst, 2, 2);
        System.out.println(new String(dst, 0, 2));    // cd
        System.out.println(buf.position());           // 4

        // 恢复到原来mark的地方，即postion为2
        buf.reset();
        System.out.println(buf.position());           // 2

        // 缓冲区中是否还有剩余数据，此时reset到mark2的位置上，还有3个数据
        if (buf.hasRemaining()) {
            System.out.println(buf.remaining());      // 3
        }
    }
}
