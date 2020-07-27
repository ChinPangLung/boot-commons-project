package nio.channel;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * 使用管道完成图片拷贝（非直接缓冲区）
 *
 * @author longzhanpeng longzhanpeng@3vjia.com
 * @since 2020-07-27 13:55
 */
public class ChannelNoDirectDemo {
    public static void main(String[] args) throws IOException {
        FileInputStream fis = null;
        FileOutputStream fos = null;
        FileChannel inChannel = null;
        FileChannel outChannel = null;
        try {
            fis = new FileInputStream("D:\\longzhanpeng\\IdeaProjects\\boot-commons-project\\boot-user-service\\src\\test\\java\\nio\\channel\\0727.png");
            fos = new FileOutputStream("D:\\longzhanpeng\\IdeaProjects\\boot-commons-project\\boot-user-service\\src\\test\\java\\nio\\channel\\copy01.png");

            // 从stream流中获取Channel
            inChannel = fis.getChannel();
            outChannel = fos.getChannel();

            ByteBuffer buf = ByteBuffer.allocate(1024);
            while ((inChannel.read(buf)) != -1) {
                //切换成读模式
                buf.flip();
                outChannel.write(buf);
                buf.clear();
            }
        } finally {
            if (outChannel != null) {
                outChannel.close();
            }
            if (inChannel != null) {
                inChannel.close();
            }
            if (fis != null) {
                fis.close();
            }
            if (fos != null) {
                fos.close();
            }
        }
    }
}