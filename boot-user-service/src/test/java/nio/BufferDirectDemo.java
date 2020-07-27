package nio;

import java.nio.ByteBuffer;

/**
 * @author longzhanpeng longzhanpeng@3vjia.com
 * @since 2020-07-27 11:08
 */
public class BufferDirectDemo {
    public static void main(String[] args) {
        ByteBuffer buffer = ByteBuffer.allocateDirect(1024);
        System.out.println(buffer.isDirect());
        System.out.println(buffer.isReadOnly());

    }
}