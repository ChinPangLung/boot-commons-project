package nio.channel;

import java.io.IOException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * 使用管道完成图片复制（只用直接内存，即内存映射文件）
 *
 * @author longzhanpeng longzhanpeng@3vjia.com
 * @since 2020-07-27 14:05
 */
public class ChannelDirectDemo {
    public static void main(String[] args) throws IOException {
        // 注意：Channel.open的StandardOpenOption要和Channel.map中MapMode模式相互对应
        FileChannel inChannel = FileChannel.open(Paths.get("D:\\longzhanpeng\\IdeaProjects\\boot-commons-project\\boot-user-service\\src\\test\\java\\nio\\channel\\0727.png"),
                StandardOpenOption.READ);
        FileChannel outChannel = FileChannel.open(Paths.get("D:\\longzhanpeng\\IdeaProjects\\boot-commons-project\\boot-user-service\\src\\test\\java\\nio\\channel\\copy02.png"),
                StandardOpenOption.CREATE, StandardOpenOption.WRITE, StandardOpenOption.READ);

        // 内存映射文件，和ByteBuffer.allocateDirect()原理类似
        MappedByteBuffer inMap = inChannel.map(FileChannel.MapMode.READ_ONLY, 0, inChannel.size());
        MappedByteBuffer outMap = outChannel.map(FileChannel.MapMode.READ_WRITE, 0, inChannel.size());
        // 现在不需要通道，因为都直接在内存映射文件中
        byte[] buf = new byte[inMap.limit()];
        inMap.get(buf);
        outMap.put(buf);
        
        inChannel.close();
        outChannel.close();

    }
}